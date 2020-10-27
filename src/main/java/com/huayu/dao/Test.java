package com.huayu.dao;

import bean.student;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.IPage;
import com.huayu.servers.Studentserves;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentMapper studentMapper=applicationContext.getBean(StudentMapper.class);
            student student = new student();
            student.setId(42);
        Page<student> page1 = new Page<student>(1,5);
        QueryWrapper b = new QueryWrapper();
        b.like("name","张三");

           List<student> student1= studentMapper.dd(page1).getRecords();
            for (student student2:student1){
                System.out.println(student2);
            }
    }
}
