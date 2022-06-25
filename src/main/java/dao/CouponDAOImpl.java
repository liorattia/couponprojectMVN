package dao;

import beans.Coupon;
import db.JDBCUtils;
import db.ResultsUtils;
import exception.CouponSystemException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponDAOImpl implements CouponDAO {

    private static final String QUERY_INSERT = "INSERT INTO `coupon`.`coupons`(`companyID`,`categoryID`,`title`,`description`,`startDate`,`endDate`,`amount`,`price`,`image`)\n" +
            "VALUES(?,?,?,?,?,?,?,?,?);\n";
    private static final String QUERY_UPDATE = "UPDATE `coupon`.`coupons`\n" +
            "SET\n" +
            "`companyID` = ?,\n" +
            "`categoryID` = ?,\n" +
            "`title` = ?,\n" +
            "`description` = ?,\n" +
            "`startDate` = ?,\n" +
            "`endDate` = ?,\n" +
            "`amount` = ?,\n" +
            "`price` = ?,\n" +
            "`image` = ?\n" +
            "WHERE `id` = ?;";
    private static final String QUERY_DELETE = "DELETE FROM `coupon`.`coupons` WHERE `id` = ?";
    private static final String QUERY_GET_ALL = "Select * From `coupon`.`coupons`";
    private static final String QUERY_GET_ALL_BY_COMPANY_ID = "Select * From `coupon`.`coupons` Where  (`companyID` = ?)";
    private static final String QUERY_GET_ALL_BY_COMPANY_ID_AND_CATEGORY = "Select * From `coupon`.`coupons` Where  (`companyID` = ? And `categoryID` = ? )";
    private static final String QUERY_GET_ALL_BY_COMPANY_ID_AND_PRICE = "Select * From `coupon`.`coupons` Where  (`companyID` = ? And `price` <= ? )";
    private static final String QUERY_GET_ONE = "Select * From `coupon`.`coupons` Where (`id` = ?)";
    private static final String QUERY_GET_ALL_COUPON_BY_CUSTOMER = "SELECT Distinct `coupons`.`id`,\n" +
            "    `coupons`.`companyID`,\n" +
            "    `coupons`.`categoryID`,\n" +
            "    `coupons`.`title`,\n" +
            "    `coupons`.`description`,\n" +
            "    `coupons`.`startDate`,\n" +
            "    `coupons`.`endDate`,\n" +
            "    `coupons`.`amount`,\n" +
            "    `coupons`.`price`,\n" +
            "    `coupons`.`image`\n" +
            "FROM `coupon`.`coupons`\n" +
            "Inner join\n" +
            "`coupon`.`customers_vs_coupons`\n" +
            "On `coupons`.`id` = `customers_vs_coupons`.`couponID`\n" +
            "Where (`customers_vs_coupons`.`customerID` =  ?)";

    private static final String QUERY_GET_ALL_COUPON_BY_CUSTOMER_AND_CATEGORY = "SELECT Distinct `coupons`.`id`,\n" +
            "    `coupons`.`companyID`,\n" +
            "    `coupons`.`categoryID`,\n" +
            "    `coupons`.`title`,\n" +
            "    `coupons`.`description`,\n" +
            "    `coupons`.`startDate`,\n" +
            "    `coupons`.`endDate`,\n" +
            "    `coupons`.`amount`,\n" +
            "    `coupons`.`price`,\n" +
            "    `coupons`.`image`\n" +
            "FROM `coupon`.`coupons`\n" +
            "Inner join\n" +
            "`coupon`.`customers_vs_coupons`\n" +
            "On `coupons`.`id` = `customers_vs_coupons`.`couponID`\n" +
            "Where `customers_vs_coupons`.`customerID` =  ?\n" +
            "And  `coupons`.`categoryID` = ?";

    private static final String QUERY_GET_ALL_COUPON_BY_CUSTOMER_AND_MAX_PRICE = "SELECT Distinct `coupons`.`id`,\n" +
            "    `coupons`.`companyID`,\n" +
            "    `coupons`.`categoryID`,\n" +
            "    `coupons`.`title`,\n" +
            "    `coupons`.`description`,\n" +
            "    `coupons`.`startDate`,\n" +
            "    `coupons`.`endDate`,\n" +
            "    `coupons`.`amount`,\n" +
            "    `coupons`.`price`,\n" +
            "    `coupons`.`image`\n" +
            "FROM `coupon`.`coupons`\n" +
            "Inner join\n" +
            "`coupon`.`customers_vs_coupons`\n" +
            "On `coupons`.`id` = `customers_vs_coupons`.`couponID`\n" +
            "Where (`customers_vs_coupons`.`customerID` =  ? And `coupons`.`price` <= ?)";

    private static final String QUERY_INSERT_COUPON_PURCHASE = "INSERT INTO `coupon`.`customers_vs_coupons`(`customerID`,`couponID`) VALUES( ? , ?)";
    private static final String QUERY_DELETE_COUPON_PURCHASE = "DELETE From `coupon`.`customers_vs_coupons` WHERE (`customerID` = ? And `couponID` = ?) ";
    private static final String QUERY_DELETE_COUPON_PURCHASE_BY_CUSTOMER_ID = "DELETE From `coupon`.`customers_vs_coupons` WHERE (`customerID` = ?) ";
    private static final String QUERY_DELETE_COUPON_BY_COUPON_ID_PURCHASE = "DELETE From `coupon`.`customers_vs_coupons` WHERE (`couponID` = ?) ";
    private static final String QUERY_DELETE_COUPON_EXPIRED = "DELETE From  `coupon`.`coupons` Where WHERE (`endDate` < ?) ";

    private static final String QUERY_DELETE_COUPON_PURCHASE_EXPIRED = "DELETE From  `coupon`.`customers_vs_coupons` Where WHERE (`endDate` < ?) ";


    private static final String QUERY_IS_EXISTS_CUSTOMER_COUPON = "Select Exists(Select * From `coupon`.`customers_vs_coupons` Where (`customerID` = ? And `couponID` = ?)) As res";
    private static final String QUERY_IS_EXISTS_COUPON_NAME = "Select Exists(Select * From `coupon`.`coupons` Where (`companyID` = ? And `title` = ?)) As res";

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyID());
        params.put(2, coupon.getCategoryID());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStarDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());

        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyID());
        params.put(2, coupon.getCategoryID());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStarDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());
        params.put(10, coupon.getId());
        JDBCUtils.executeQuery(QUERY_UPDATE, params);

    }

    @Override
    public void deleteCoupon(int couponID) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public void deleteCouponExpired(LocalDateTime endDate) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, endDate);
        System.out.println(params);
//        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_PURCHASE_EXPIRED, params);
//        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_EXPIRED, params);
    }

    @Override
    public List<Coupon> getAllCoupons() throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }


    @Override
    public List<Coupon> getAllCouponsByCustomer(int customerID) throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_CUSTOMER, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }

    @Override
    public List<Coupon> getAllCouponsByCustomerAndCategory(int customerID, int categoryID) throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, categoryID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_CUSTOMER_AND_CATEGORY, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }

    @Override
    public List<Coupon> getAllCouponsByCustomerByPrice(int customerID, double price) throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, price);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_COUPON_BY_CUSTOMER_AND_MAX_PRICE, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }
    @Override
    public List<Coupon> getAllCouponsByCompanyID(int companyID) throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_BY_COMPANY_ID, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }

    @Override
    public List<Coupon> getAllCouponsByCompanyIDAndCategory(int companyID, int categoryID) throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, categoryID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_BY_COMPANY_ID_AND_CATEGORY, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }

    @Override
    public List<Coupon> getAllCouponsByCompanyIDAndPrice(int companyID, double maxPrice) throws CouponSystemException, SQLException, InterruptedException {
        List<Coupon> results = new ArrayList<>();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, maxPrice);
           List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL_BY_COMPANY_ID_AND_PRICE, params);
        for (Object object : rows) {
            results.add(ResultsUtils.couponFromRow((HashMap<String, Object>) object));
        }
        return results;
    }


    @Override
    public Coupon getOneCoupon(int couponID) throws CouponSystemException {
        Coupon result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.couponFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponId) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponId);
        JDBCUtils.executeQuery(QUERY_INSERT_COUPON_PURCHASE, params);
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponId) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponId);
        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_PURCHASE, params);
    }

    @Override
    public void deleteCouponPurchaseByCustomerID(int customerID) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_PURCHASE_BY_CUSTOMER_ID, params);
    }

    @Override
    public boolean isCustomerCoupon(int customerId, int couponId) throws CouponSystemException {
        boolean resualts = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS_CUSTOMER_COUPON, params);
        resualts = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return resualts;
    }

    @Override
    public boolean isCouponExists(int companyID, String couponTitle) throws CouponSystemException {
        boolean resualts = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, couponTitle);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS_COUPON_NAME, params);
        resualts = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return resualts;
    }

    @Override
    public void deleteCouponPurchaseByCouponId(int couponID) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        JDBCUtils.executeQuery(QUERY_DELETE_COUPON_BY_COUPON_ID_PURCHASE, params);
    }
}
