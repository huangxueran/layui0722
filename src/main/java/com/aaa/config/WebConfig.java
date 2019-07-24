package com.aaa.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *    这个对应的是web.xml文件
 */
public class WebConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        //初始化spring的容器
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        //加载配置类
        ac.register(SpringConfig.class,SpringMvcConfig.class);
        //设置servlet上下文
        ac.setServletContext(servletContext);
        //设置前端控制器
        //Dynamic addServlet(String var1, Class<? extends Servlet> var2);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("xxx", new DispatcherServlet(ac));
        //设置启动顺序
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("*.do");
        //添加servlet上下文监听
         servletContext.addListener(new RequestContextListener());
        servletContext.setInitParameter("webAppRootKey","webapp.root");
        servletContext.setInitParameter("log4jConfigLocation","WEB-INF/classes/log4j.properties");
        WebAppRootListener webAppRootListener= new WebAppRootListener();
         servletContext.addListener(webAppRootListener);
        //字符编码过滤器     第一种设置编码的格式
        javax.servlet.FilterRegistration.Dynamic addFilter = servletContext.addFilter("filterEncoding", new CharacterEncodingFilter());
		/*
		    第二种编码的格式
		 * <init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>*/
        Map<String,String> map = new HashMap<String,String>();
        map.put("encoding", "UTF-8");
        map.put("forceEncoding", "true");
        //添加初始化参数
        addFilter.setInitParameters(map);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet
                .allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        //设置过滤请求的范围
        addFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}
