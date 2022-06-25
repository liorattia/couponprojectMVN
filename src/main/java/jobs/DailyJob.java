package jobs;

import dao.CouponDAO;
import dao.CouponDAOImpl;
import exception.CouponSystemException;

import java.time.LocalDateTime;

public class DailyJob implements Runnable {

    private static CouponDAO couponDAO = new CouponDAOImpl();

    private static boolean quit = false;

    @Override
    public void run() {
        quit = true;
        while (quit) {
            try {
                couponDAO.deleteCouponExpired(LocalDateTime.from(LocalDateTime.now()));
            } catch (CouponSystemException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Thread stop");
    }

    public void stop() {
        quit = false;
    }
}
