package dao;

import beans.Category;
import db.JDBCUtils;
import exception.CouponSystemException;

import java.util.HashMap;
import java.util.Map;

public class CategoryDAOImpl implements CategoryDAO{
    static final String QUERY_INSERT = "INSERT INTO `coupon`.`categories`(`name`)\n" +
            "VALUES(?);\n";

    @Override
    public void addCategory(String category) throws CouponSystemException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, category);
        JDBCUtils.executeQuery(QUERY_INSERT, params);
    }

}


