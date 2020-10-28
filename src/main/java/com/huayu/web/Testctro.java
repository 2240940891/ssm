package com.huayu.web;

import bean.User;
import bean.classes;
import bean.student;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huayu.dao.ClassMapper;
import com.huayu.servers.Studentserves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/student")
public class Testctro {

    @Autowired
    Studentserves studentserves;

    @Autowired
    private ClassMapper classMapper;

    @RequestMapping("/pp.do")
    @ResponseBody
    public User insert(Integer page ,Integer limit){
        User user = new User();
        user.setCode(0);
        user.setMsg("");
        Page<student> page1 = new Page<student>(page,limit);
        List<student> list = studentserves.dd(page1).getRecords();
        user.setCount(30);
        user.setData(studentserves.dd(page1).getRecords());
        return  user;
    }

    /*
    *
    查询所有班级名
    * */
    @RequestMapping("/cc.do")
    @ResponseBody
    public  List<classes> select(){
        List<classes> list= classMapper.selectall();
        return  list;
    }

    /**
     * 批量删除
     * @param b
     * @return
     */
    @RequestMapping("/ll.do")
    @ResponseBody
    public String  pldelete(@RequestBody Integer [] b) throws  Exception{
        try {
            studentserves.removeByIds(Arrays.asList(b));
        } catch (Exception e) {
            return "2";
        }
        return "1";
    }

    /**
     * 添加
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/qur.do")
    @ResponseBody
    public String  inser( student student){
        try {
            studentserves.save(student);
        } catch (Exception e) {
            return "2";
        }
        return "1";
    }

    /**
     * 通过id查询信息
     * @param id
     * @return
     */
    @RequestMapping("/uu.do")
    @ResponseBody
    public student  aa( Integer id){
        try {
            studentserves.getById(id);
        } catch (Exception e) {
        }
        return studentserves.getById(id);
    }

    /**
     * 修改
     * @param id
     * @return
     */
    @RequestMapping("/yy.do")
    @ResponseBody
    public String  aaa( student id){
        try {
            System.out.println(id);
            studentserves.update(id);
        } catch (Exception e) {
            return "2";
        }
        return "1";
    }

    @RequestMapping("/hh.do")
    @ResponseBody
    public  void getexcal(@RequestParam("file") MultipartFile pictureFile) throws Exception {
        try {
           List<student> studentList=studentserves.getexcal(pictureFile.getInputStream());
            studentserves.saveBatch(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setxcal(@RequestParam("file") MultipartFile pictureFile)throws Exception{
            
    }


}
