package com.company.scma.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    
    @Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        
    }
}
