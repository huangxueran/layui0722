package com.aaa.config;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Author: 陈建
 * @Date: 2019/7/18 0018 16:57
 * @Version 1.0
 */
@Configuration
@ComponentScan(basePackages = {"com.aaa.controller"})
@EnableWebMvc
public  class SpringMvcConfig  implements WebMvcConfigurer {
    /**
     *     前端控制器  视图解析器
     *     之前我们在写项目的时候，需要写大规模的配置文件，这是第一阶段
     *      业务指的是控制层、dao层,其他全部都是配置文件
     * @return
     */
        @Bean
        public InternalResourceViewResolver internalResourceViewResolver(){
            InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
            internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
            internalResourceViewResolver.setSuffix(".jsp");
            return internalResourceViewResolver;
        }


    /**
     * 注册拦截器
     */

   /*
        @Bean
        public MyIntercepter myIntercepter(){
            MyIntercepter myIntercepter= new MyIntercepter();
            return myIntercepter;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor( myIntercepter());
        }
   */


}