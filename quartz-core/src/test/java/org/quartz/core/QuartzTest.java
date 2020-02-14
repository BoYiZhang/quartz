package org.quartz.core;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class QuartzTest {

    public static void main(String[] args) throws  Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1").build();

        org.quartz.Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/2 * * * * ?")
                        .withMisfireHandlingInstructionFireAndProceed()
                        // .withMisfireHandlingInstructionIgnoreMisfires()  # MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY 忽略所有的超时状态，按照触发器的策略执行。
                        // .withMisfireHandlingInstructionFireAndProceed()  # [默认] MISFIRE_INSTRUCTION_FIRE_ONCE_NOW   立刻执行一次，然后就按照正常的计划执行。
                        // .withMisfireHandlingInstructionDoNothing()   # MISFIRE_INSTRUCTION_DO_NOTHING           目前不执行，然后就按照正常的计划执行。
                )
                .build();

        HolidayCalendar holidayCalendar = new HolidayCalendar();

        GregorianCalendar calendar = new GregorianCalendar(2020, 2, 23);    // 2017年11月1日
        holidayCalendar.addExcludedDate(calendar.getTime());

        calendar = new GregorianCalendar(2020, 2, 21);            // 2018年11月2日
        holidayCalendar.addExcludedDate(calendar.getTime());


        sched.addCalendar("holidays", holidayCalendar, false, false);      // 节假日加入schedule调度器

        sched.scheduleJob(job, trigger);



        sched.start();

    }





}

