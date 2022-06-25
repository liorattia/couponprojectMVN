package test;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.CompanyDAOImpl;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import dao.CustomerDAOImpl;
import db.DatabaseManager;
import exception.CouponSystemException;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class CouponTest {

    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {
        System.out.println("------------------------ Start ------------------------");

        DatabaseManager.getInstance().dropCreateStrategy();

        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        Company company1 = new Company("comp1", "comp1@gmail.com", "1234");
        companyDAO.addCompany(company1);
        Company company2 = new Company("comp2", "comp2@gmail.com", "1234");
        companyDAO.addCompany(company2);
        System.out.println(companyDAO.getAllCompanies());

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        Customer cust1 = new Customer("cust1 fname", "cust1 lname", "cust1@gmail.com", "1234");
        customerDAO.addCustomer(cust1);
        Customer cust2 = new Customer("cust2 fname", "cust2 lname", "cust2@gmail.com", "1234");
        customerDAO.addCustomer(cust2);
        System.out.println(customerDAO.getAllCustomers());

        System.out.println("Add coupons");
        CouponDAOImpl couponDAO = new CouponDAOImpl();
        Coupon coupon1 = new Coupon(1, 1, "Food", "Magazino", Date.valueOf(LocalDate.of(2022, 6, 1)), Date.valueOf(LocalDate.of(2022, 6, 10)), 10, 1500, "");
        couponDAO.addCoupon(coupon1);
        Coupon coupon2 = new Coupon(1, 2, "Computer", "MacBook", Date.valueOf(LocalDate.of(2022, 6, 6)), Date.valueOf(LocalDate.of(2022, 6, 20)), 5, 7000, "");
        couponDAO.addCoupon(coupon2);
        System.out.println(couponDAO.getAllCoupons());

        System.out.println("Update coupon id = 1");
        Coupon couponUpdate = couponDAO.getOneCoupon(1);
        couponUpdate.setAmount(20);
        couponUpdate.setDescription("New Description");
        couponDAO.updateCoupon(couponUpdate);
        System.out.println(couponDAO.getAllCoupons());

//        System.out.println("Delete coupon id = 1");
//        couponDAO.deleteCoupon(1);
//        System.out.println(couponDAO.getAllCoupons());

        System.out.println("Get one coupon id = 2");
        System.out.println(couponDAO.getOneCoupon(2));

        System.out.println("add Coupon Purchase");
        couponDAO.addCouponPurchase(1,1);
        couponDAO.addCouponPurchase(2,1);
        couponDAO.addCouponPurchase(1,2);
        couponDAO.addCouponPurchase(2,2);

        System.out.println("delete Coupon Purchase");
        couponDAO.deleteCouponPurchase(1,1);



        System.out.println("------------------------ End ------------------------");

    }

}
