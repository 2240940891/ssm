package bean;

public class classes {
    public Integer getId() {
        return id;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", classno='" + classno + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private  Integer id;
    private  String classname;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getClassno() {
        return classno;
    }

    public void setClassno(Integer classno) {
        this.classno = classno;
    }

    private  Integer classno;
}
