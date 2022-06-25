package test;

import beans.Customer;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import db.DatabaseManager;
import exception.CouponSystemException;

import java.sql.SQLException;

public class CustomerTest {
    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {
        System.out.println("------------------------ Start ------------------------");

        DatabaseManager.getInstance().dropCreateStrategy();

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        System.out.println("Add customers");
        Customer cust1 = new Customer("cust1 fname", "cust1 lname", "cust1@gmail.com", "1234");
        Customer cust2 = new Customer("cust2 fname", "cust2 lname", "cust2@gmail.com", "1234");
        Customer cust3 = new Customer("cust3 fname", "cust3 lname", "cust3@gmail.com", "1234");
        Customer cust4 = new Customer("cust4 fname", "cust4 lname", "cust4@gmail.com", "1234");
        Customer cust5 = new Customer("cust5 fname", "cust5 lname", "cust5@gmail.com", "1234");

        customerDAO.addCustomer(cust1);
        customerDAO.addCustomer(cust2);
        customerDAO.addCustomer(cust3);
        customerDAO.addCustomer(cust4);
        customerDAO.addCustomer(cust5);
        System.out.println(customerDAO.getAllCustomers());

        System.out.println("Update customer id = 2");
        Customer updatecust = new Customer(2, "new first name", "new last name", "email", "1234");
        customerDAO.updateCustomer(updatecust);
        System.out.println(customerDAO.getAllCustomers());

        System.out.println("Delete customer id = 2");
        customerDAO.deleteCustomer(2);
        System.out.println(customerDAO.getAllCustomers());

        System.out.println("Get one customer where id = 3");
        System.out.println(customerDAO.getOneCustomer(3));

        System.out.println("------------------------ End ------------------------");

    }
}
