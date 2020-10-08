package com.welearn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description : 短信发送线程池
 * Created by Setsuna Jin on 2018/11/22.
 */
@Slf4j
@Configuration
public class SmsSendExecutorConfig {

    @Value("${executor.sms.core-pool-size}")
    private int corePoolSize;

    @Value("${executor.sms.max-pool-size}")
    private int maxPoolSize;

    @Value("${executor.sms.queue-capacity}")
    private int queueCapacity;

    @Value("${executor.sms.alive-seconds}")
    private int aliveSeconds;

    @Value("${executor.sms.name-prefix}")
    private String namePrefix;

    @Bean(name = "smsSendExecutor")
    public Executor smsSendExecutor() {
        log.info("短信发送 SmsSend Executor Init Start");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 配置队列大小
        executor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(aliveSeconds);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
