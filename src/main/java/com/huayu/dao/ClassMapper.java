package com.huayu.dao;


import bean.classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<classes> {

    @Select("select * from class")
    public List<classes> selectall();



    @Select("select * from class where id = #{id}")
    public classes  selectid(classes classes);

    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before =false , resultType =int.class )
    @Insert("insert into class(classno,classname) values (#{classno},#{classname})")
    public int insert(classes classes);

    @Update("update set class name=#{name},sex=#{sex},classno=#{classno} where id =#{id}")
    public void update(classes classes);

    @Delete("delete from class where id = #{id}")
    public  void delete (classes classes);


}
