package test;

import jobs.DailyJob;

public class DailyLoginTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DailyJob());
        t1.start();
    }
}
