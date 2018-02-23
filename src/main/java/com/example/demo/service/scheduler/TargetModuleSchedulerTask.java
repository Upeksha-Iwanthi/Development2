package com.example.demo.service.scheduler;

import com.example.demo.service.TargetModuleSchedulerService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/**
 * Runs the periodic update of functional areas from JIRA issues
 */
@Component
public class TargetModuleSchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(TargetModuleSchedulerTask.class);

    @Autowired
    private TargetModuleSchedulerService targetModuleSchedulerService;

    @Scheduled(cron = "${update.table.for.targetModule}")
    public void execute() {
        try {
            log.info("Scheduler started to update tables for target modules " + new DateTime());
            targetModuleSchedulerService.updateTablesForTargetModules();
            log.info("Completed update tables for target modules " + new DateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
