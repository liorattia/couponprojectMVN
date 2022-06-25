package dao;

import beans.Category;
import exception.CouponSystemException;

public interface CategoryDAO {
    void addCategory (String category) throws CouponSystemException;
}
