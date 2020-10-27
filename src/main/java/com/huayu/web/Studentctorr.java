package com.huayu.web;

import bean.classes;
import bean.student;
import com.github.pagehelper.PageHelper;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class Studentctorr {


    @Autowired
    Studentserves studentserves;

    @RequestMapping("/qq.do")
    public ModelAndView select(Integer id,Integer pp){
        ModelAndView modelAndView = new ModelAndView();
       /* if (id!=null){
            if (id==2){
                student student=studentserves.select(pp);
                modelAndView.addObject("list",student);
                modelAndView.setViewName("/update");
                return modelAndView;
            }
            if (id==1){
                List<classes> classes = studentserves.selectclass();
                modelAndView.addObject("list",classes);
                modelAndView.setViewName("/insert");
                return modelAndView;
            }
        }*/
        PageHelper.startPage(1,15);
        List<student> list= studentserves.selectall();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("/test");
        return  modelAndView;
    }
}
