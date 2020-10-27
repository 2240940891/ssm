package com.huayu.web;

import bean.student;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class Piliangdelete {
    @Autowired
    Studentserves studentserves;

    @ResponseBody
    @RequestMapping("/pidelete.do")
    public String insert(@RequestBody String []checkids){
        student student1 =new student();
        for (int b=0;b<checkids.length;b++){
            student1.setId(Integer.parseInt(checkids[b]));
            studentserves.delete(student1);
        }
        if (student1.getId()!=null){
            return "添加成功";
        }
        return "添加失败";
    }
}
