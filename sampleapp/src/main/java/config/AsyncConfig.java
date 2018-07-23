package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;

@Configuration
@EnableWebMvc // 注意：Spring Bootの場合は、@EnableWebMvcはつけちゃダメ！！
@EnableAsync // @Asyncの有効化
public class AsyncConfig {
    @Bean
    public AsyncTaskExecutor taskExecutor() { // デフォルトだと"taskExecutor"という名前のBeanが利用される
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        return executor;
    }
}