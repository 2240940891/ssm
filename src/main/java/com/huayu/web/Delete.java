package com.huayu.web;

import bean.student;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class Delete {
    @Autowired
    Studentserves studentserves;

    @RequestMapping("/delete.do")
    public String insert(student student){
        studentserves.delete(student);
        if (student.getId()!=null){
            return "添加成功";
        }
        return "添加失败";
    }
}
