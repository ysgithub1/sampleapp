package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableWebMvc // 注意：Spring Bootの場合は、@EnableWebMvcはつけちゃダメ！！
@ComponentScan
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp(); // JSP用のViewResolverを有効化
//    }
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(AsyncExecutor()); // スレッドプールを利用するようにカスタマイズ
    }

    // ThreadPoolTaskExecutorはDIコンテナ上で管理するのがポイント
    // SpringのDIコンテナのライフサイクルに合わせて、適切なタイミングでinitializeとshutdownメソッドが呼び出される
    @Bean
    public AsyncTaskExecutor AsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        return executor;
    }
    
}