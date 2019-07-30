package com.conpany.project;


import com.company.project.Application;
import com.company.project.configurer.MybatisConfigurer;
import org.apache.ibatis.session.SqlSession;
import org.junit.runner.RunWith;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试继承该类即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
public abstract class Tester {

    public void select(){
        SqlSession sqlSession = null;

        try{
            sqlSession = MybatisConfigurer.getSqlSession(true);


        }catch(Exception e){}
    }

}



