package com.huayu.servers;

import bean.classes;
import bean.student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.dao.ClassMapper;
import com.huayu.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentserves")
public class Studentserves  extends ServiceImpl<StudentMapper,student> {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    public IPage<student> dd(Page<student> page){
        return studentMapper.dd(page);
    }


    public List<student> selectall(){
        return studentMapper.selectall();
    }

    public List<student>  selectid(student student){return studentMapper.selectid(student);}


    public List<classes> selectclass(){return classMapper.selectall();}


    public void  insertstu(student student){ studentMapper.insert(student);}


    public  void  delete(student student){studentMapper.delete(student);}


    public student select(Integer student){return studentMapper.selectdd(student);}


    public  void   update(student student){studentMapper.update(student);}


}
