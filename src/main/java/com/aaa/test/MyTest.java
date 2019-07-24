package com.aaa.test;

import com.aaa.config.SpringConfig;
import com.aaa.dao.Empdao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
//加载测试需要的配置类，为了初始化spring容器
@ContextConfiguration(classes= {SpringConfig.class})
public class MyTest {
        AnnotationConfigApplicationContext ac=null;

        /**
         * 在所有的测试方法之前运行
         */
        @Before
        public  void initContext() {
            ac = new AnnotationConfigApplicationContext();
            ac.register(SpringConfig.class);
            ac.refresh();


        }
        @Test
        public void testMybatis(){
            Empdao empDao = (Empdao) ac.getBean("empDao");
            List<Map<String, Object>> allEmp = empDao.findAllEmp();
            for (Map<String, Object> map : allEmp) {
                System.out.println(map.toString());
            }
        }
}