package com.huayu.web;

import bean.student;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class Insertctro {

    @Autowired
    Studentserves studentserves;

    @RequestMapping("/insert.do")
    @ResponseBody
    public String insert(student student) throws Exception{
        studentserves.insertstu(student);
        if (student.getId()!=null){
            return "添加成功";
        }
        return "添加失败";
    }

}
