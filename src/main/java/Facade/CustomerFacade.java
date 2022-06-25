package Facade;

import beans.Coupon;
import beans.Customer;
import exception.CouponSystemException;
import exception.ErrMsg;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CustomerFacade extends ClientFacade {

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    private int customerID;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (customerDAO.isCustomerExists(email, password)) {
            this.setCustomerID(customerDAO.getCustomerByEmail(email).getId());
            System.out.println("Login successfully as customer");
            return true;
        }
        throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);
    }

    public void purchaseCoupon(int couponId) throws CouponSystemException {
        Coupon coupon = couponDAO.getOneCoupon(couponId);
        if (coupon.getAmount() <= 0) {
            throw new CouponSystemException(ErrMsg.NO_COUPON_LEFT);
        }
        if (coupon.getEndDate().before(Date.valueOf((LocalDate.now())))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        if (couponDAO.isCustomerCoupon(this.getCustomerID(), couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASE_BY_CUSTOMER);
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponDAO.updateCoupon(coupon);

        couponDAO.addCouponPurchase(this.getCustomerID(), couponId);
    }

    public List<Coupon> getAllCoupon() throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getAllCouponsByCustomer(this.getCustomerID());
    }

    public List<Coupon> getCouponByCategory(int categoryID) throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getAllCouponsByCustomerAndCategory(this.getCustomerID(), categoryID);
    }

    public List<Coupon> getCouponByMaxPrice(double price) throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getAllCouponsByCustomerByPrice(this.getCustomerID(), price);
    }

    public void printCustomerDetails() throws CouponSystemException, SQLException, InterruptedException {
        System.out.println(customerDAO.getOneCustomer(this.customerID));
        System.out.println("customer coupons:");
        for (Coupon coupon : couponDAO.getAllCouponsByCustomer(this.customerID)) {
            System.out.println(coupon);
        }
    }



}
