package com.huayu.dao;

import bean.student;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository("demo")
public class Demo {
    public String demo(student student){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from student s inner join class c on s.classno=c.classno where 1=1 ");
        if(student.getName() != "" && student.getName() != null){
            sql.append("and s.name like '%"+student.getName()+"%' ");
        }
        if(student.getClassnaem() != "" && student.getClassnaem() != null){
            sql.append("and c.classname like '%"+student.getClassnaem()+"%' ");
        }
        return sql.toString();
    }
}
