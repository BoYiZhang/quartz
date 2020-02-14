package org.quartz.core;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//@DisallowConcurrentExecution
public class HelloJob implements Job {
    private int count;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {


        System.out.println("");
        System.out.println("");
        System.out.println(Thread.currentThread().getName()+"   **********  Hello world! : "+ sdf.format(context.getFireTime())+" ===> "+ sdf.format(context.getNextFireTime()));
        System.out.println("");
        System.out.println("");




        try {
            TimeUnit.SECONDS.sleep(130);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        count++;
    }

    public int getCount() {
        return count;
    }
}