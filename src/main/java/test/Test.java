package test;

import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import beans.*;
import exception.CouponSystemException;
import jobs.DailyJob;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Test {

    public static void testAll() throws CouponSystemException, SQLException, InterruptedException {

        Thread t1 = new Thread(new DailyJob());
        t1.start();

        System.out.println("-------------------------Admin------------------------");

        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().Login("admin@admin.com", "admin", ClientType.Administrator);
        adminFacade.login("admin@admin.com", "admin");

        System.out.println("Add new company");
        Company newCompany = new Company("TikTok", "tik@tok.com", "1234");
        Company newCompany1 = new Company("Alphabet", "Alphabet@Alphabet.com", "1234");
        Company newCompany2 = new Company("Tesla", "Tesla@Tesla.com", "1234");

        adminFacade.addCompany(newCompany);
        adminFacade.addCompany(newCompany1);
        adminFacade.addCompany(newCompany2);

        System.out.println("Update company");
        Company updateCompany = adminFacade.getCompanyByID(1);
        updateCompany.setEmail("TikTokTuk@tok.com");
        adminFacade.updateCompany(updateCompany);

        System.out.println("Delete Company");
        adminFacade.deleteCompany(1);

        System.out.println("Get All companies");
        adminFacade.getAllCompanies().forEach(System.out::println);

        System.out.println("Get one company");
        System.out.println(adminFacade.getCompanyByID(2));

        System.out.println("Add customer");
        Customer newCustomer = new Customer("Bill", "Gates", "bill@msft.com", "1234");
        Customer newCustomer2 = new Customer("Mark", "zuckerberg", "mark@facebook.com", "1234");
        Customer newCustomer3 = new Customer("Elon", "Musk", "elon@tesla.com", "1234");
        Customer newCustomer4 = new Customer("Sergey", "Brin", "sergey@gmail.com", "1234");

        adminFacade.addCustomer(newCustomer);
        adminFacade.addCustomer(newCustomer2);
        adminFacade.addCustomer(newCustomer3);
        adminFacade.addCustomer(newCustomer4);

        System.out.println("Update customer");
        Customer updateCustomer = adminFacade.getCustomer(1);
        updateCustomer.setFirstName("Bilili");
        adminFacade.updateCustomer(updateCustomer);

        System.out.println("delete customer");
        adminFacade.deleteCustomer(2);

        System.out.println("Get All customers");
        adminFacade.getAllCustomers().forEach(System.out::println);

        System.out.println("Get one customer");
        System.out.println(adminFacade.getCustomer(1));


        System.out.println("-------------------------Company------------------------");

        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().Login("Alphabet@Alphabet.com", "1234", ClientType.Company);
        companyFacade.login("Alphabet@Alphabet.com", "1234");

        System.out.println("Add coupon");
        Coupon newCoupon = new Coupon(2, 1, "Electric", "Dyson vacuum cleaner", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 10, "");
        Coupon newCoupon2 = new Coupon(2, 2, "Food", "Pizza", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 20, "");
        Coupon newCoupon3 = new Coupon(2, 2, "Food1", "More Pizza", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 30, "");
        Coupon newCoupon4 = new Coupon(3, 3, "Alcohol", "Beer", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 40, "");
        Coupon newCoupon5 = new Coupon(3, 3, "Alcohol1", "Wine", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 10, 50, "");
        Coupon newCoupon6 = new Coupon(3, 4, "Vacation", "Far far away", Date.valueOf(LocalDate.of(2022, 7, 1)), Date.valueOf(LocalDate.of(2022, 7, 10)), 60, 60, "");
        companyFacade.addCoupon(newCoupon);
        companyFacade.addCoupon(newCoupon2);
        companyFacade.addCoupon(newCoupon3);
        companyFacade.addCoupon(newCoupon4);
        companyFacade.addCoupon(newCoupon5);
        companyFacade.addCoupon(newCoupon6);

        System.out.println("Update coupon");
        Coupon updateCoupon = companyFacade.getCouponByID(1);
        updateCoupon.setDescription("Jimmy vacuum cleaner");
        companyFacade.updateCoupon(updateCoupon);

        System.out.println("Delete coupon");
        companyFacade.deleteCoupon(6);

        System.out.println("Get company coupons");
        companyFacade.getAllCouponsByCompanyID(2).forEach(System.out::println);

        System.out.println("Get company by category: 2");
        companyFacade.getAllCouponsByCompanyIDAndCategory(2, 2).forEach(System.out::println);

        System.out.println("Get company by max price 25");
        companyFacade.getAllCouponsByCompanyIDAndPrice(2, 25).forEach(System.out::println);

        System.out.println("Print company details");
        companyFacade.printCompanyDetails();

        System.out.println("-------------------------Customer------------------------");
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().Login("bill@msft.com", "1234", ClientType.Customer);
        customerFacade.login("bill@msft.com", "1234");

        System.out.println("Purchase coupon");
        customerFacade.purchaseCoupon(1);
        customerFacade.purchaseCoupon(2);
        customerFacade.purchaseCoupon(3);

        System.out.println("get customer coupon");
        customerFacade.getAllCoupon().forEach(System.out::println);

        System.out.println("get customer coupon by category 2");
        customerFacade.getCouponByCategory(2).forEach(System.out::println);

        System.out.println("get customer coupon by price 15");
        customerFacade.getCouponByMaxPrice(15).forEach(System.out::println);

        System.out.println("Print customer details");
        customerFacade.printCustomerDetails();

        t1.stop();
    }


}
