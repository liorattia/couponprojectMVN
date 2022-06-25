package dao;

import beans.Company;
import db.JDBCUtils;
import db.ResultsUtils;
import exception.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CompanyDAOImpl implements CompanyDAO {

    private static final String QUERY_INSERT = "INSERT INTO `coupon`.`companies` (`name`, `email`, `password`) VALUES (?,?,?);\n";
    private static final String QUERY_UPDATE = "UPDATE `coupon`.`companies`\n" +
            "SET\n" +
            "`name` = ?,\n" +
            "`email` = ?,\n" +
            "`password` = ?\n" +
            "WHERE `id` = ?;";
    private static final String QUERY_DELETE = "DELETE FROM `coupon`.`companies` WHERE (`id` = ?);";
    private static final String QUERY_GET_ALL = "Select * From `coupon`.`companies`";
    private static final String QUERY_GET_ONE = "Select * From `coupon`.`companies` Where (`id` = ?)";
    private static final String QUERY_IS_EXISTS = "Select Exists(Select * From `coupon`.`companies` Where (`email` = ? And `password` = ?)) As res";
    private static final String QUERY_IS_EXISTS_BY_NAME = "SELECT `companies`.`id`,`companies`.`name`,`companies`.`email`,`companies`.`password`FROM `coupon`.`companies`Where (`name` = ?)";
    private static final String QUERY_IS_EXISTS_BY_EMAIL = "SELECT `companies`.`id`,`companies`.`name`,`companies`.`email`,`companies`.`password`FROM `coupon`.`companies` Where(`email` =?)";


    @Override
    public void addCompany(Company company) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

    @Override
    public List<Company> getAllCompanies() throws CouponSystemException, SQLException, InterruptedException {
        List<Company> results = new ArrayList<>();
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ALL);
        for (Object object : rows) {
            results.add(ResultsUtils.companyFromRow((HashMap<String, Object>) object));
        }
        return results;
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws CouponSystemException, SQLException, InterruptedException {
        boolean resualts = false;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS, params);
        resualts = ResultsUtils.booleanFromRow((HashMap<String, Object>) rows.get(0));
        return resualts;
    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, company.getId());
        JDBCUtils.executeQuery(QUERY_UPDATE, params);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        JDBCUtils.executeQuery(QUERY_DELETE, params);
    }

    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        Company result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_GET_ONE, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.companyFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

    @Override
    public Company getCompanyByName(String companyName) throws CouponSystemException {
        Company result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyName);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS_BY_NAME, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.companyFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;

    }

    @Override
    public Company getCompanyByEmail(String companyEmail) throws CouponSystemException {
        Company result = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyEmail);
        List<?> rows = JDBCUtils.executeQueryWithResults(QUERY_IS_EXISTS_BY_EMAIL, params);
        if (rows.isEmpty()) {
            result = null;
        } else {
            result = ResultsUtils.companyFromRow((HashMap<String, Object>) (rows.get(0)));
        }
        return result;
    }

}
