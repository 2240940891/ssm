package com.huayu.dao;

import bean.student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper extends  BaseMapper<student> {
    @Results({
            @Result(column = "classname",property = "classes.classname"),
            @Result(column = "classno",property = "classes.classno")
    })
    @Select("select * from student s inner join class c on s.classno=c.classno")
    public List<student>  selectall();


    @Results({
            @Result(column = "classname",property = "classes.classname"),
            @Result(column = "classno",property = "classes.classno")
    })
    @SelectProvider(type =Demo.class,method ="demo")
    public  List<student>  selectid(student student);

    @Results({
            @Result(column = "classname",property = "classes.classname"),
            @Result(column = "classno",property = "classes.classno")
    })
    @Select("select * from student s inner join class c on s.classno=c.classno where c.classno=#{id}")
    public student selectdd(Integer  id);


    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before =false , resultType =int.class )
    @Insert("insert into student(name,sex,classno) values (#{name},#{sex},#{classno})")
    public int insert(student student);


    @Update("update set student name=#{name},sex=#{sex},classno=#{classno} where id =#{id}")
    public void update(student student);

    @Delete("delete from student where id = #{id}")
    public  void delete (student student);

    @Results({
            @Result(column = "classname",property = "classnaem"),
    })

    @Select("select s.id,s.name,s.sex,c.classno,c.classname from student s inner join class c on s.classno=c.classno")
    public IPage<student> dd(Page<student> page);


}
