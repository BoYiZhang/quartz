package org.quartz.core;

import org.quartz.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Long time = 1581672640021L ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(1581672640021L);

        System.out.println(sdf.format(date));


    }
}
