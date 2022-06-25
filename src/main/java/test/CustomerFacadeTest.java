package test;

import Facade.ClientFacade;
import Facade.CustomerFacade;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import exception.CouponSystemException;

import java.sql.SQLException;

public class CustomerFacadeTest {

    private static CustomerFacade customerFacade = new CustomerFacade();

    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {

        System.out.println("Customer login");
        customerFacade.login("cust1@gmail.com", "1234");

//        System.out.println("Purchase coupon");
//        customerFacade.purchaseCoupon(5);

//        System.out.println("Print customer details");
//        customerFacade.printCustomerDetails();

//        System.out.println("get coupons by category");
//        customerFacade.getCouponByCategory(1).forEach(System.out::println);

//        System.out.println("Get coupons by max price ");
//        customerFacade.getCouponByMaxPrice(1400).forEach(System.out::println);

        System.out.println("Print customer details");
        customerFacade.printCustomerDetails();
    }
}
