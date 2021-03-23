package com.example.ProductService.scheduler.util;

import com.example.ProductService.scheduler.info.TimerInfo;
import org.quartz.*;

import java.util.Date;

public class TimerUtils {
    private TimerUtils(){}

    public static JobDetail buildJobDetail(final Class jobclass, final TimerInfo info){
        final JobDataMap jobDataMap=new JobDataMap();
        jobDataMap.put(jobclass.getSimpleName(),info);

        return JobBuilder
                .newJob(jobclass)
                .withIdentity(jobclass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobclass, final TimerInfo info){
        SimpleScheduleBuilder builder=SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(info.getRepeatIntervalMinutes());

        if(info.isRunForever()){
            builder.repeatForever();
        }else {
            builder=builder.withRepeatCount(info.getTotalFireCount()-1);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobclass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis()+info.getInitialOffsetMs()))
                .build();
    }
}
