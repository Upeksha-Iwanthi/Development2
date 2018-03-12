package com.example.demo.service.scheduler;

import com.example.demo.service.SourceModuleSchedulerService;
import com.example.demo.service.TargetModuleSchedulerService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SourceModuleSchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(TargetModuleSchedulerTask.class);

    @Autowired
    private SourceModuleSchedulerService sourceModuleSchedulerService;

    @Scheduled(cron = "${update.table.for.sourceModule}")
    public void execute() {
        try {
            log.info("Scheduler started to update tables for target modules " + new DateTime());
//            sourceModuleSchedulerService.updateTablesForSourceModules();
            log.info("Completed update tables for target modules " + new DateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

