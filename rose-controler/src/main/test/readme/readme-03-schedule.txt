
// 备注
// 1、每隔20分钟执行一次：scheduled.task.cron：0 2/20 * * * *
// 2、Application 添加 @EnableScheduling

package com.rose.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 功能：定时任务
 */
@Slf4j
@Component
public class ScheduledTasks {

    @Value("${scheduled.task.enable}")
    private boolean scheduledTaskEnable;

    @Scheduled(cron = "${scheduled.task.cron}")
    public void scheduledTask() {
        if (scheduledTaskEnable) {
            // 定时任务内容
        }
    }
}