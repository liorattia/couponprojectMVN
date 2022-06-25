package db;

import beans.Category;
import beans.Company;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.CompanyDAOImpl;
import exception.CouponSystemException;

import java.util.List;

public class DatabaseManager {
    private static final DatabaseManager instance = new DatabaseManager();

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    CategoryDAO categoryDAO = new CategoryDAOImpl();

    private static final String QUERY_CREATE_SCHEMA = "CREATE SCHEMA `coupon` ;";
    private static final String QUERY_DROP_SCHEMA = "DROP SCHEMA `coupon`;";

    private static final String QUERY_CREATE_TABLE_COMPANIES = "CREATE TABLE `coupon`.`companies` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(45) DEFAULT NULL,\n" +
            "  `email` varchar(45) DEFAULT NULL,\n" +
            "  `password` varchar(45) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`))\n";

    private static final String QUERY_CREATE_TABLE_CUSTOMERS = "CREATE TABLE `coupon`.`customers` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `firstName` VARCHAR(45) NULL,\n" +
            "  `lastName` VARCHAR(45) NULL,\n" +
            "  `email` VARCHAR(45) NULL,\n" +
            "  `password` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`id`));\n";

    private static final String QUERY_CREATE_TABLE_CATEGORIES = "CREATE TABLE `coupon`.`categories` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`id`));\n";

    private static final String QUERY_CREATE_TABLE_COUPONS = "CREATE TABLE `coupon`.`coupons` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `companyID` INT NULL,\n" +
            "  `categoryID` INT NULL,\n" +
            "  `title` VARCHAR(45) NULL,\n" +
            "  `description` VARCHAR(45) NULL,\n" +
            "  `startDate` DATE NULL,\n" +
            "  `endDate` DATE NULL,\n" +
            "  `amount` INT NULL,\n" +
            "  `price` DOUBLE NULL,\n" +
            "  `image` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  INDEX `coupons_companies_idx` (`companyID` ASC) VISIBLE,\n" +
            "  INDEX `coupons_categories_idx` (`categoryID` ASC) VISIBLE,\n" +
            "  CONSTRAINT `coupons_companies`\n" +
            "    FOREIGN KEY (`companyID`)\n" +
            "    REFERENCES `coupon`.`companies` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `coupons_categories`\n" +
            "    FOREIGN KEY (`categoryID`)\n" +
            "    REFERENCES `coupon`.`categories` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);";

    private static final String QUERY_CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `coupon`.`customers_vs_coupons` (\n" +
            "  `customerID` INT NOT NULL,\n" +
            "  `couponID` INT NOT NULL,\n" +
            "  PRIMARY KEY (`customerID`, `couponID`),\n" +
            "  INDEX `ccVSCoupons_idx` (`couponID` ASC) VISIBLE,\n" +
            "  CONSTRAINT `ccVSCustomers`\n" +
            "    FOREIGN KEY (`customerID`)\n" +
            "    REFERENCES `coupon`.`customers` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `ccVSCoupons`\n" +
            "    FOREIGN KEY (`couponID`)\n" +
            "    REFERENCES `coupon`.`coupons` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";


    public void dropCreateStrategy() {

        try {
            JDBCUtils.executeQuery(QUERY_DROP_SCHEMA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            JDBCUtils.executeQuery(QUERY_CREATE_SCHEMA);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COMPANIES);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CUSTOMERS);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CATEGORIES);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_COUPONS);
            JDBCUtils.executeQuery(QUERY_CREATE_TABLE_CUSTOMERS_VS_COUPONS);
            insertCategories();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertCategories() throws CouponSystemException {
        for (Category cat : Category.values()
        ) {
            String catName = cat.name();
            categoryDAO.addCategory(catName);
        }
    }
}
