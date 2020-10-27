package com.huayu.web;

import bean.student;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class Mohuctro {

    @Autowired
    Studentserves studentserves;

    @RequestMapping("/mohu.do")
    @ResponseBody
    public List<student> select(@RequestBody student student){
        List<student> list= studentserves.selectid(student);
        for (student student1:list){
            System.out.println(student);
        }
        return list;
    }

}
