package com.aaa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 此处配置spring容器   这个对应的spring.xml文件
 */
@Configuration
@ComponentScan(basePackages = {"com.aaa.dao", "com.aaa.service"})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@EnableScheduling
public class SpringConfig {

    /**
     *  定义数据源
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/jsp");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;

    }

      // 定义jdbc的模板
     /**
          @Bean
          public JdbcTemplate jdbcTemplate(){
            JdbcTemplate jdbcTemplate=new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource());
            return jdbcTemplate();
          }
      */

     //配置事务管理
     /**
         @Bean
         public DataSourceTransactionManager dataSourceTransactionManager(){
             DataSourceTransactionManager ds=new DataSourceTransactionManager();
             ds.setDataSource(dataSource());
             return  ds;
         }
     */

     /**
      *       整合要两个步骤：     这是之前的做法    现在修改成零配置，也就是不用配置文件
      *      1. 配置文件的整合
      *        扫描包
      *      <context:component=scan  base-package="com.aaa"></context:component-scan>
      *        配置jdbc文件
      *        <context:property-placeholder location="classpath:config.properties"/>
      *         配置数据源     第一步    加载数据源的配置文件    四大金刚 分别是 url username password   driverClassName
      *         <bean id="datasource" class="org.apache.commons.dbcp.BasicDataSouece">
      *                <property id="driverClassName"><value>${jdbc.driverClassName}</value>
      *                </property>
      *                <property name="url">
      *                     <value>${jdbc.url}</value>
      *                </property>
      *                 <property name="usename">
      *                       <value>${jdbc.username}</value>
      *                  </property>
      *                   <property name="password">
      *                            <value>${jdbc.password}</value>
      *                     </property>
      *         </bean >
      *
      *           创建两个bean     1.sqlSessionFactory  2.mapperLocations指的是告诉我你的隐射放在哪个位置
      *           typeAlises  别名
      *         <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactiryBean">
      *              <property name="dataSource" ref="dataSource"></property>
      *              mybatis配置文件名
      *
      *                configLocation是加载mybatis的总配置文件（mybatisConfig.xml） 其他都是映射文件
      *
      *               <peoperty name="configLocation" value="classpath=mybatisConfig.xml"></peoperty>
      *               扫描所有的映射文件
      *                <property name="mapperLocations" value="classpath:com/aaa/mapper/*.xml" />
      *                 <property name="typeAlises" value=""></property>  这句不用写，其实这句放在mybatisConfig.xml里面了
      *          </bean>
      *
      *          自动扫描,将Mapper接口生成代理注入到String中     这句主要告诉我接口放在哪里
      *          <bean  class ="org.mybats.spring.mapper.MapperScannerConfig">
      *               <property name="basePackage" value="com.aaa.mapper"/>
      *               <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
      *          </bean>
      * **/


    /**
     * 配置mybatis SqlSessionFactoryBean的文件
     */

    /**
     *    xxxx是bean的id和名字，如果不写，那么bean的id和名字默认是方法名getSqlSessionFactoryBean
     * @return
     */
    @Bean("getSqlSessionFactoryBean")     // @bean是service层
    /**
     * getSqlSessionFactoryBean指的是获取SqlSessionFactoryBean的对象，其实这个对象直接new就行了
     *  如果获取叫这个SqlSessionFactoryBean名字的话，
     *  下面的setSqlSessionFactoryBeanName("getSqlSessionFactoryBean");括号里面的要改
     *  @Bean("getSqlSessionFactoryBean")这个括号里面的get要与
     *  setSqlSessionFactoryBeanName("getSqlSessionFactoryBean");这个括号里面的get一致
     *  如果把@bean修改成xxxx的话，那就把setSqlSessionFactoryBeanName("getSqlSessionFactoryBean");
     *  这括号里面的get修改成xxxx。
     */
    public SqlSessionFactoryBean getSqlSessionFactoryBean(){

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource());

        // 设置环境为开发模式
        sqlSessionFactoryBean.setEnvironment("development");

        /**
         *   设置事务管理方式  transactionManager
         *   JdbcTemple jdbcTemple =new  jdbcTemple();
         *   sqlSessionFactoryBean.setTransactionFactory(jdbcTransacrionFactory);
         */


        /**
         *    设置别名
              sqlSessionFactoryBean.setTypeAliasesPackage("com.aaa.entity");
         */


        // 扫描mybatis的mapper映射文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //mapper文件所在的路径    resources其实就是classpath
        String locationPattern = "mapper/*.xml";   //把locationPattern的值注入进来
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));
        } catch (IOException e) {
            e.printStackTrace();   //这个相当于上面的sqlSessionFactory的83行
        }

        return sqlSessionFactoryBean;
    }


    /**
     * 自动扫描使用jdk动态代理 将Mapper接口生成代理注入到Spring 容器中
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        /**
         *    接口是放在dao层里边  setSqlSessionFactoryBeanName
         *    设置sqlsessionFactoryBeanName的名称要跟@Bean("getSqlSessionFactoryBean")这个保持一致
         */
        mapperScannerConfigurer.setBasePackage("com.aaa.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("getSqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}