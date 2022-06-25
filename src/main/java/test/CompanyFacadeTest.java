package test;

import Facade.CompanyFacade;
import beans.Company;
import beans.Coupon;
import dao.CouponDAO;
import exception.CouponSystemException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompanyFacadeTest {
    private static CompanyFacade companyFacade = new CompanyFacade();

    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {

        System.out.println("--------------------------Start---------------------------------");
//        String[] arguments = new String[]{"123"};//Use company test for adding data to db
//        CompanyTest.main(arguments);

        System.out.println("Login correct password");
        companyFacade.login("comp1@gmail.com", "1234");

//        System.out.println("Login wrong email");
//        companyFacade.login("cust155@gmail.com","1234");

//        System.out.println("Login wrong password");
//        companyFacade.login("cust1@gmail.com","12345");

        System.out.println("Delete all coupon for company");
        for (Coupon coupon : companyFacade.getAllCouponsByCompanyID(companyFacade.getCompanyID())
        ) {
//            companyFacade.deleteCoupon(coupon.getId());
        }

        System.out.println("Add coupon");
        Coupon coupon1 = new Coupon(companyFacade.getCompanyID(), 1, "Foodiesss", "Magazino", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 0, 1500, "");
        Coupon coupon2 = new Coupon(companyFacade.getCompanyID(), 2, "Food1", "Magazino1", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 2000, "");
        Coupon coupon3 = new Coupon(companyFacade.getCompanyID(), 2, "Magazino1", "Magazino1", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 1500, "");

       companyFacade.addCoupon(coupon1);
//        companyFacade.addCoupon(coupon2);
//        companyFacade.addCoupon(coupon3);


        System.out.println("Get all coupons by company id");
//        companyFacade.getAllCouponsByCompanyID(companyFacade.getCompanyID()).forEach(System.out::println);

        System.out.println("Update coupon company id");
//        Coupon couponUpdate = companyFacade.getCouponByID(15);
//        couponUpdate.setCompanyID(100);
//        companyFacade.updateCoupon(couponUpdate);

        System.out.println("Get all coupons by company and category = 2");
//        companyFacade.getAllCouponsByCompanyIDAndCategory(companyFacade.getCompanyID(), 2).forEach(System.out::println);

        System.out.println("Get all coupons by company and max price");
//        companyFacade.getAllCouponsByCompanyIDAndPrice(companyFacade.getCompanyID(), 1500).forEach(System.out::println);

        System.out.println("Print company details");
//        companyFacade.printCompanyDetails();

        System.out.println("--------------------------End---------------------------------");


    }
}
