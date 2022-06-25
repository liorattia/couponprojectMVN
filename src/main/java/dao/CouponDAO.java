package dao;

import beans.Company;
import beans.Coupon;
import exception.CouponSystemException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface CouponDAO {

    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponID) throws CouponSystemException;

    List<Coupon> getAllCoupons() throws CouponSystemException, SQLException, InterruptedException;

    List<Coupon> getAllCouponsByCompanyID(int companyID) throws CouponSystemException, SQLException, InterruptedException;

    List<Coupon> getAllCouponsByCustomer(int customerID) throws CouponSystemException, SQLException, InterruptedException;

    List<Coupon> getAllCouponsByCustomerAndCategory(int customerID, int categoryID) throws CouponSystemException, SQLException, InterruptedException;

    List<Coupon> getAllCouponsByCustomerByPrice(int customerID, double price) throws CouponSystemException, SQLException, InterruptedException;

    List<Coupon> getAllCouponsByCompanyIDAndCategory(int companyID, int categoryID) throws CouponSystemException, SQLException, InterruptedException;

    List<Coupon> getAllCouponsByCompanyIDAndPrice(int companyID, double maxPrice) throws CouponSystemException, SQLException, InterruptedException;

    Coupon getOneCoupon(int couponID) throws CouponSystemException;

    void addCouponPurchase(int customerID, int couponId) throws CouponSystemException;

    void deleteCouponPurchase(int customerID, int couponId) throws CouponSystemException;

    void deleteCouponExpired(LocalDateTime endDate) throws CouponSystemException;

    boolean isCustomerCoupon(int customerId, int couponId) throws CouponSystemException;

    boolean isCouponExists(int companyID, String couponTitle) throws CouponSystemException;

    void deleteCouponPurchaseByCouponId(int couponID) throws CouponSystemException;

    void deleteCouponPurchaseByCustomerID(int customerID) throws CouponSystemException;

}
