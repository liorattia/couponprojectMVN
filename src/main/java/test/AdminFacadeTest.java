package test;

import Facade.AdminFacade;
import beans.Company;
import beans.Customer;
import dao.CompanyDAOImpl;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import db.DatabaseManager;
import exception.CouponSystemException;

import java.sql.SQLException;

public class AdminFacadeTest {

    private static AdminFacade adminFacade = new AdminFacade();

    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {

        CouponDAO couponDAO = new CouponDAOImpl();

        System.out.println("------------------------ Start ------------------------");


        Company newCompany = new Company("TikTok", "tik@tok.com", "1234");
        System.out.println(newCompany);
        adminFacade.addCompany(newCompany);
        System.out.println(newCompany);
        adminFacade.updateCompany(newCompany);

        System.out.println("Login wrong password");
//        adminFacade.login("admin@admin.com", "admin1");

        System.out.println("Login good password");
//        adminFacade.login("admin@admin.com", "admin");

        System.out.println("Add new company with existing name");
        Company company1 = new Company("comp1", "comp12@gmail.com", "1234");
//        adminFacade.addCompany(company1);

        System.out.println("Add new company with existing email");
        Company company2 = new Company("comp15", "comp1@gmail.com", "1234");
//        adminFacade.addCompany(company2);
        Company company3 = new Company("comp16", "comp6@gmail.com", "1234");
//        adminFacade.addCompany(company3);

        System.out.println("Update company");
        Company companyUpdate = adminFacade.getCompanyByID(1);
        companyUpdate.setName("New name");
//        adminFacade.updateCompany(companyUpdate);


//        String[] arguments = new String[]{"123"};//Use coupon test for adding data to db
//        CouponTest.main(arguments);
//        System.out.println("Delete coupon for company id = 1");
//        System.out.println("Before delete");
//        System.out.println(couponDAO.getAllCouponsByCompanyID(1));
//        adminFacade.deleteCompany(1);
//        System.out.println("After delete");
//        System.out.println(couponDAO.getAllCouponsByCompanyID(1));

        System.out.println("Get all companies");
//        adminFacade.getAllCompanies().forEach(System.out::println);


        System.out.println("Get one company");
//        System.out.println(adminFacade.getCompanyByID(1));

        System.out.println("Add customer ");
        Customer cust1 = new Customer("cust1 fname", "cust1 lname", "cust2@gmail.com", "1234");
//        adminFacade.addCustomer(cust1);

        System.out.println("Add customer same email");
//        adminFacade.addCustomer(cust1);

        System.out.println("Delete customer");
//        adminFacade.deleteCustomer(1);

        System.out.println("Get all customer");
//        adminFacade.getAllCustomers().forEach(System.out::println);

        System.out.println("Get all coupons");
//        couponDAO.getAllCoupons().forEach(System.out::println);

        System.out.println("Get customer by id");
//        System.out.println(adminFacade.getCustomer(1));

        System.out.println("Get coupon by customer id");
//        System.out.println(couponDAO.getAllCouponsByCustomer(4));


        System.out.println("------------------------ End ------------------------");

    }
}
