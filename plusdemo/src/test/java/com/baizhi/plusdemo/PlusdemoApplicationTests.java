package com.baizhi.plusdemo;

import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlusdemoApplicationTests {

    @Autowired
    private StudentDao studentDao;



    @Test
    public void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> studentList = studentDao.selectList(null);
        Assert.assertEquals(5,studentList.size());
        studentList.forEach(System.out::println);
    }

    @Test
    public   void test1(){
        int result=0;
        int i=1;
        switch (i){
            case 1:
                result=result+i;
                case 2:
                result=result+i*2;
                case 3:
                result=result+i*3;
        }
        System.out.println("最终"+result);
    }

}
