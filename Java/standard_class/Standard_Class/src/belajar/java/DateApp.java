package belajar.java;

import java.util.Calendar;
import java.util.Date;

public class DateApp {
    public static void main(String[] args) {

        Date date = new Date(23587200000L);

        System.out.println(date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.add(Calendar.YEAR, -10);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        calendar.set(Calendar.HOUR_OF_DAY, 10);

//        System.out.println(calendar.getTime());
        Date result = calendar.getTime();
        System.out.println(result);
    }
}
