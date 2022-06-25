package Facade;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import exception.CouponSystemException;
import exception.ErrMsg;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade {

    protected String email = "admin@admin.com";
    protected String password = "admin";

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Customer customer;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            this.setCustomer(customerDAO.getCustomerByEmail("admin@admin.com"));
            System.out.println("Login successfully as admin");
            return true;
        }
        throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);
    }

    public void addCompany(Company newCompany) throws CouponSystemException, SQLException, InterruptedException {

        if (companyDAO.getCompanyByName(newCompany.getName()) != null) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXISTS);
        }
        if (companyDAO.getCompanyByEmail(newCompany.getEmail()) != null) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXISTS);
        } else {
            companyDAO.addCompany(newCompany);
        }
    }

    public void updateCompany(Company companyUpdate) throws CouponSystemException {
        Company company = companyDAO.getOneCompany(companyUpdate.getId());
        if (company.getName().equals(companyUpdate.getName()) == false) {
            throw new CouponSystemException(ErrMsg.CANNOT_UPDATE_COMPANY_NAME);
        }
        companyDAO.updateCompany(companyUpdate);
    }

    public void deleteCompany(int companyID) throws CouponSystemException, SQLException, InterruptedException {
        for (Coupon coupon : couponDAO.getAllCouponsByCompanyID(companyID)
        ) {
            couponDAO.deleteCouponPurchaseByCouponId(coupon.getId());
            couponDAO.deleteCoupon(coupon.getId());
        }
        companyDAO.deleteCompany(companyID);
    }

    public List<Company> getAllCompanies() throws CouponSystemException, SQLException, InterruptedException {
        return companyDAO.getAllCompanies();
    }

    public Company getCompanyByID(int companyID) throws CouponSystemException {
        return companyDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer newCustomer) throws CouponSystemException {
        if (customerDAO.isEmailExists(newCustomer.getEmail())) {
            throw new CouponSystemException(ErrMsg.EMAIL_ALREADY_EXISTS);
        }
        customerDAO.addCustomer(newCustomer);
    }

    public void updateCustomer(Customer updateCustomer) throws CouponSystemException {
        customerDAO.updateCustomer(updateCustomer);
    }

    public void deleteCustomer(int customerID) throws CouponSystemException {
        couponDAO.deleteCouponPurchaseByCustomerID(customerID);
        customerDAO.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws CouponSystemException, SQLException, InterruptedException {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomer(int customerID) throws CouponSystemException {
        return customerDAO.getOneCustomer(customerID);
    }


}