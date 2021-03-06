package com.flow.web.filter;

import com.alibaba.druid.support.http.WebStatFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * Created by Exception on 2017/5/27.
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean druidStatFilterBean() {
        FilterRegistrationBean druidStatFilterBean=new FilterRegistrationBean(new WebStatFilter());
        List<String> urlPattern=new ArrayList<>();
        urlPattern.add("/*");
        druidStatFilterBean.setUrlPatterns(urlPattern);
        Map<String,String> initParams=new HashMap<>();
        initParams.put("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        druidStatFilterBean.setInitParameters(initParams);
        return druidStatFilterBean;
    }

    @Bean
    public Filter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        return filter;
    }
}
