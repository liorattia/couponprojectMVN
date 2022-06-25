import db.DatabaseManager;
import exception.CouponSystemException;
import jobs.DailyJob;
import test.Test;

import java.sql.SQLException;

public class CouponApplication {
    public static void main(String[] args) throws CouponSystemException, SQLException, InterruptedException {
        System.out.println("------------------------ Start ------------------------");

        DatabaseManager.getInstance().dropCreateStrategy();

        Test.testAll();


        System.out.println("------------------------ End ------------------------");
    }
}
