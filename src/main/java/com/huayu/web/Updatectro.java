package com.huayu.web;

import bean.student;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class Updatectro {
   public static String bb= "添加成功";
    @Autowired
    Studentserves studentserves;
    @RequestMapping("/update.do")
    public String insert(student student) throws Exception{
       studentserves.update(student);
        if (student.getId()!=null){
            return "添加成功";
        }
        return "添加失败";
    }
}
