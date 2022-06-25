package Facade;

import beans.Coupon;
import exception.CouponSystemException;
import exception.ErrMsg;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade {

    private int companyID;

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public boolean login(String email, String password) throws CouponSystemException, SQLException, InterruptedException {
        if (companyDAO.isCompanyExists(email, password)) {
            this.setCompanyID(companyDAO.getCompanyByEmail(email).getId());
            System.out.println("Login successfully as company");
            return true;
        }
        throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);
    }

    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (couponDAO.isCouponExists(coupon.getCompanyID(), coupon.getTitle())) {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_EXISTS);
        }
        couponDAO.addCoupon(coupon);

    }

    public void updateCoupon(Coupon couponUpdate) throws CouponSystemException {
        Coupon coupon = couponDAO.getOneCoupon(couponUpdate.getId());
        if (coupon.getCompanyID() != couponUpdate.getCompanyID()) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COUPON_COMPANY);
        }
        couponDAO.updateCoupon(couponUpdate);
    }

    public void deleteCoupon(int couponID) throws CouponSystemException {
        couponDAO.deleteCouponPurchaseByCouponId(this.getCompanyID());
        couponDAO.deleteCoupon(couponID);
    }

    public List<Coupon> getAllCouponsByCompanyID(int companyID) throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getAllCouponsByCompanyID(this.getCompanyID());
    }

    public void printCompanyDetails() throws CouponSystemException, SQLException, InterruptedException {
        System.out.println(companyDAO.getOneCompany(this.getCompanyID()));
        System.out.println("Company coupons");
        this.getAllCouponsByCompanyID(this.getCompanyID()).forEach(System.out::println);
    }

    public Coupon getCouponByID(int couponID) throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getOneCoupon(couponID);
    }


    public List<Coupon> getAllCouponsByCompanyIDAndCategory(int companyID,int categoryID) throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getAllCouponsByCompanyIDAndCategory(this.getCompanyID(),categoryID);
    }


    public List<Coupon> getAllCouponsByCompanyIDAndPrice(int companyID,double maxPrice) throws CouponSystemException, SQLException, InterruptedException {
        return couponDAO.getAllCouponsByCompanyIDAndPrice(this.getCompanyID(),maxPrice);
    }




}
