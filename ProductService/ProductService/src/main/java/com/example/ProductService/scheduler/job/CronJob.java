package com.example.ProductService.scheduler.job;

import com.example.ProductService.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.scheduler.info.TimerInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

public class CronJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(CronJob.class);

    @Autowired
    private MessageChannel output;

    @Autowired
    private ProductRepository productRepository;

    //will be called every time a timer fires
    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap=context.getJobDetail().getJobDataMap();

        TimerInfo info=(TimerInfo) jobDataMap.get(CronJob.class.getSimpleName());

        List<Product> products = productRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String mapperProduct = mapper.writeValueAsString(products);

        output.send(MessageBuilder.withPayload(mapperProduct).build());
        log.info("here will do the topic '{}'", mapperProduct + info.getRemainingFireCount());
    }
}
