/*
package com.example.blogjpa.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<Filter> filterOne() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new UrlPrintFilter());
        filter.setOrder(1);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterTwo() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new AddTraceIdFilter());
        filter.setOrder(2);
        filter.addUrlPatterns("/filter/*");
        return filter;
    }
}*/
