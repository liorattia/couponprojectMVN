package dao;

import beans.Customer;
import db.JDBCUtils;
import db.ResultsUtils;
import exception.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String QUERY_INSERT = "INSERT INTO `coupon`.`customers`(`firstName`,`lastName`,`email`,`password`)VALUES(?,?,?,?);\n";
    private static final String QUERY_UPDATE = "UPDATE `coupon`.`customers` SET\n" +
            "`firstName` = ?,\n" +
            "`lastName` = ?,\n" +
            "`email` = ?,\n" +
            "`password` = ?\n" +
            "WHERE `id` = ?;";

    private static final String QUERY_DELETE = "DELETE FROM `coupon`.`customers` WHERE `customers`.`id` = ?;";
    private static final String QUERY_GET_ALL = "SELECT `customers`.`id`,    `customers`.`firstName`,    `customers`.`lastName`,    `customers`.`email`,    `customers`.`password` FROM `coupon`.`customers`;";
    private static final String QUERY_GET_ONE = "SELECT `customers`.`id`,    `customers`.`firstName`,    `customers`.`lastName`,    `customers`.`email`,    `customers`.`password` FROM `coupon`.`customers` Where (`customers`.`id` = ?)";
    private static final String QUERY_GET_ONE_BY_EMAIL = "SELECT `customers`.`id`,    `customers`.`firstName`,    `customers`.`lastName`,    `customers`.`email`,    `customers`.`password` FROM `coupon`.`customers` Where (`customers`.`email` = ?)";
    private static final String QUERY_IS_EXISTS = "Select Exists(Select * From `coupon`.`customers` Where (`email` = ? And `password` = ?)) As res";
    private static final String QUERY_IS_EMAIL_EXISTS = "Select Exists(Select * From `coupon`.`customers` Where (`email` = ?)) As res";

    @Override
    public boolean isCustomerExists(String email, String password) throws CouponSystemException {
        boolean resualts = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS, params);
        resualts = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return resualts;
    }

    @Override
    public boolean isEmailExists(String email) throws CouponSystemException {
        boolean resualts = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EMAIL_EXISTS, params);
        resualts = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return resualts;
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        params.put(5, customer.getId());
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public List<Customer> getAllCustomers() throws CouponSystemException, SQLException, InterruptedException {
        ArrayList<Customer> results = new ArrayList<>();
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Object object : rows) {
            results.add(ResultsUtils.customerFromRow((HashMap<String, Object>) object));
        }
        return results;
    }

    @Override
    public Customer getOneCustomer(int customerid) throws CouponSystemException {
        Customer result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerid);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.customerFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;

    }

    @Override
    public Customer getCustomerByEmail(String customerEmail) throws CouponSystemException {
        Customer result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerEmail);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE_BY_EMAIL, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.customerFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }
}
