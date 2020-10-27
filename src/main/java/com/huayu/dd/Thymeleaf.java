package com.huayu.dd;


import bean.student;
import com.huayu.servers.Studentserves;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/th")
@Api(tags = "用户信息")
public class Thymeleaf {

    @Autowired
    Studentserves studentserves;


    @RequestMapping(value = "/pp.do",method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "系统错误"),
            @ApiResponse(code = 200, message = "删除成功", response = String.class) })
    @ApiOperation(httpMethod = "post", value = "所有用户信息")//swagger 当前接口注解
    public student bbb( ){
       return studentserves.getById(30);
    }
}
