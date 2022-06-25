package Facade;

import dao.*;
import exception.CouponSystemException;
import exception.ErrMsg;

import java.sql.SQLException;

public abstract class ClientFacade {

    protected CompanyDAO companyDAO = new CompanyDAOImpl();
    protected CustomerDAO customerDAO = new CustomerDAOImpl();
    protected CouponDAO couponDAO = new CouponDAOImpl();

    public boolean login(String email, String password) throws CouponSystemException, SQLException, InterruptedException {
        return (customerDAO.isCustomerExists(email, password));

    }

}
