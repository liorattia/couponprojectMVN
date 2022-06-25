package beans;


import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import db.DatabaseManager;
import exception.CouponSystemException;

import java.sql.SQLException;

public class LoginManager {
    private static final LoginManager instance = new LoginManager();

    public static LoginManager getInstance() {
        return instance;
    }

    private LoginManager() {
    }

    public ClientFacade Login(String email, String password, ClientType clientType) throws CouponSystemException, SQLException, InterruptedException {

        CustomerDAO customerDAO = new CustomerDAOImpl();
        CompanyDAO companyDAO = new CompanyDAOImpl();


        if (clientType == ClientType.Administrator && email == "admin@admin.com" && password == "admin") {
            System.out.println("login as admin");
            return new AdminFacade();
        }
        if (clientType == clientType.Company && companyDAO.isCompanyExists(email, password)) {
            return new CompanyFacade();
        }
        if (clientType == clientType.Customer && customerDAO.isCustomerExists(email, password)) {
            return new CustomerFacade();
        }
        return null;
    }
}
