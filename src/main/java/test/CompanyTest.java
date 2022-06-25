package test;

import beans.Company;
import dao.CompanyDAOImpl;
import db.DatabaseManager;
import exception.CouponSystemException;

import java.sql.SQLException;

public class CompanyTest {

    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {

        System.out.println("------------------------ Start ------------------------");

        DatabaseManager.getInstance().dropCreateStrategy();

        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        Company company1 = new Company("comp1", "comp1@gmail.com", "1234");
        Company company2 = new Company("comp2", "comp2@gmail.com", "1234");
        Company company3 = new Company("comp3", "comp3@gmail.com", "1234");
        companyDAO.addCompany(company1);
        companyDAO.addCompany(company2);
        companyDAO.addCompany(company3);
        System.out.println(companyDAO.getAllCompanies());

        System.out.println("Is company exists?");
        System.out.println(companyDAO.isCompanyExists("comp1@gmail.com", "1234"));

        System.out.println("Update company 1");
        Company company = new Company(1, "commm", "comm@g,mail.com", "1234");
        companyDAO.updateCompany(company);


        System.out.println("Delete company id = 1");
//        companyDAO.deleteCompany(1);
        companyDAO.getAllCompanies().forEach(System.out::println);

        System.out.println("Get company Where id = 3");
        System.out.println(companyDAO.getOneCompany(3));

        System.out.println("Is company name exists");
        System.out.println(companyDAO.getCompanyByName("comp1"));

        companyDAO.getAllCompanies().forEach(System.out::println);


        System.out.println("------------------------ End ------------------------");

    }
}