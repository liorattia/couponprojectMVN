package test;

import beans.Category;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import exception.CouponSystemException;

public class CategoryTest {

    public static void main(String[] args) throws CouponSystemException {

        System.out.println("------------------------ Start ------------------------");
        CategoryDAO categoryDAO = new CategoryDAOImpl();

        for (Category cat : Category.values()
        ) {
            String catName = cat.name();
            categoryDAO.addCategory(catName);
        }

        System.out.println("------------------------ end ------------------------");
    }
}
