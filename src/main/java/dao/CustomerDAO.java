package dao;

import beans.Customer;
import exception.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    boolean isCustomerExists(String email, String password) throws CouponSystemException;

    boolean isEmailExists(String email) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(Customer customer) throws CouponSystemException;

    void deleteCustomer(int customerID) throws CouponSystemException;

    List<Customer> getAllCustomers() throws CouponSystemException, SQLException, InterruptedException;

    Customer getOneCustomer(int customerid) throws CouponSystemException;

    Customer getCustomerByEmail(String customerEmail) throws CouponSystemException;
}
