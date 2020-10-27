<%--
  Created by IntelliJ IDEA.
  User: 22409
  Date: 2020/9/28
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <script type="text/javascript" src="/jquery-3.3.1.min.js" ></script>
    <script src="/layui/layui.js" charset="utf-8"></script>

    <script>
            //表单
        layui.use('form', function(){
            var form = layui.form;

            //监听提交
            form.on('submit(*)', function(data){

                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。*/
                $.ajax({
                    url: '/student/qur.do',
                    type: 'post',
                    data:data.field,
                    dataType:"json", //后台的数据类型
                    success: function (data1) {
                        console.log(1111111111111111);
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                        parent.location.reload();
                        if (data1==="1"){
                            layer.msg("删除成功");
                        }
                        if(data1==="2"){
                            layer.msg("删除失败");
                        }
                    }
                });
            });

            //下拉框所有班级查询
            $(function(){
                $.ajax({
                    url: '/student/cc.do',
                    type: 'post',
                    contentType:"application/json",
                    success: function (data,textStatus) {
                        var obj =eval(data);
                        for(var b=0;b<obj.length;b++){
                            var q=$("#p1").append("<option name='classno' value='"+obj[b].classno+"'>"+obj[b].classname+"</option>");
                        }
                        form.render("select");
                        //重新渲染 固定写法
                    }
                });
            });

        });


    </script>




</head>
<body>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<form class="layui-form"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
    <div class="layui-form-item">
        <label class="layui-form-label">名字</label>
        <div class="layui-input-block">
            <input type="text" name="name" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <select name="classno" lay-filter="aihao" id="p1">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" checked>
            <input type="radio" name="sex" value="女" title="女" >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
</html>
