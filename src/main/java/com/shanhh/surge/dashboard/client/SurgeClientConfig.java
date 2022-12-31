package com.shanhh.surge.dashboard.client;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
public class SurgeClientConfig {

    @Value("${app.feign.remote.surge.x-key}")
    private String xKey;


    @Bean
    public RequestInterceptor surgeKey() {
        return template -> template.header("X-Key", xKey);
    }

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
