package dao;

import beans.Company;
import exception.CouponSystemException;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDAO {

    void addCompany(Company company) throws CouponSystemException;

    List<Company> getAllCompanies() throws CouponSystemException, SQLException, InterruptedException;

    boolean isCompanyExists(String email, String password) throws CouponSystemException, SQLException, InterruptedException;

    void updateCompany(Company company) throws CouponSystemException;

    void deleteCompany(int companyId) throws CouponSystemException;

    Company getOneCompany(int companyId) throws CouponSystemException;

    Company getCompanyByName(String companyName) throws CouponSystemException;

    Company getCompanyByEmail(String companyEmail) throws CouponSystemException;
}
