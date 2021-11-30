package com.credorax.poc.service.filter.configuration;

import com.credorax.poc.service.filter.SomeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFiltersConfiguration {

    @Bean
    public FilterRegistrationBean<SomeFilter> someFilter() {
        FilterRegistrationBean<SomeFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new SomeFilter());
//        bean.addUrlPatterns(
//                "/verification",
//                "/api/gets/*");
        return bean;
    }

}
