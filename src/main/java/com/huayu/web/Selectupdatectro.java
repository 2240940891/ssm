package com.huayu.web;

import bean.student;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class Selectupdatectro {

    @Autowired
    Studentserves studentserves;

    @ResponseBody
    @RequestMapping("/selectt.do")
    public ModelAndView select(Integer id, Integer pp){
        ModelAndView modelAndView = new ModelAndView();
        student student=studentserves.select(pp);
        modelAndView.addObject("list",student);
        modelAndView.setViewName("/update");
        System.out.println("66666666666");
        return modelAndView;
    }
}
