package com.example.ProductService.service;

import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.scheduler.info.TimerInfo;
import com.example.ProductService.scheduler.job.CronJob;
import com.example.ProductService.scheduler.timerservice.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronJobService {
    private final SchedulerService scheduler;

    @Autowired
    private ProductRepository productRepository;

    public CronJobService(final SchedulerService scheduler){
        this.scheduler=scheduler;
    }

    public void runCronJob(){
        final TimerInfo info=new TimerInfo();
        info.setTotalFireCount(1);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMinutes(10);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("My callback data");
//        info.setRunForever(true);
        scheduler.scheduled(CronJob.class,info);
    }

    public Boolean deleteTimer(final String timerId){
        return scheduler.deleteTimer(timerId);
    }

    // Fetch the list of running Timers
    public List<TimerInfo> getAllRunningTimers(){
        return scheduler.getAllRunningTimers();
    }

    public TimerInfo getRunningTimer(String timerId) {
        return scheduler.getRunningTimer(timerId);
    }
}
