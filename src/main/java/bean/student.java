package bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Id;

@TableName("student")
public class student {

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", classno=" + classno +
                ", classnaem='" + classnaem + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getClassno() {
        return classno;
    }

    public void setClassno(Integer classno) {
        this.classno = classno;
    }

    public String getClassnaem() {
        return classnaem;
    }

    public void setClassnaem(String classnaem) {
        this.classnaem = classnaem;
    }

    @TableId(value = "id",type =IdType.AUTO)
    private  Integer id;

    private  String name;
    private  String sex;
    private  Integer classno;
    @TableField(exist = false)
    private  String classnaem;

    /*public bean.classes getClasses() {
        return classes;
    }

    public void setClasses(bean.classes classes) {
        this.classes = classes;
    }

    @TableField(exist = false)
    private  classes classes;*/
}
