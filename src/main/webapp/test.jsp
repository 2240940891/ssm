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
        layui.use('table', function(){
            var table = layui.table;
            var layer = layui.layer;
            //表头
            table.render({
                elem: '#demo'       //赋值到那个table
                ,height:300
                ,width:1400
                ,url:'/student/pp.do' // 数据接口
                ,page:true //开启分页
                ,limit:3
                ,toolbar: '#toolbarDemo'
                ,defaultToolbar: ['filter', 'print', 'exports']
                ,limits:[1,3,5]
                ,cols: [[   //表头
                    {type: 'checkbox', fixed: 'left'}
                    ,{field:'id', width:300, title: 'ID', sort: true}
                    ,{field:'name', width:350, title: '用户名'}
                    ,{field:'sex', width:350, title: '性别', sort: true}
                    ,{field:'classes.classname', width:250, title: '班级',templet:'<div>{{d.classnaem}}</div>'}
                    ,{fixed: 'right', width:150, title: '操作',align:'center', toolbar: '#barDemo'}
                ]]
            });

            /*复选框监听事件*/
            table.on('checkbox(test)', function(obj){
                console.log(obj.checked); //当前是否选中状态
                console.log(obj.data); //选中行的相关数据
                console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
            });


            //头工具栏监听
            table.on('toolbar(test)', function(obj){
                switch(obj.event){
                    case 'delete':
                        //获取选中状态
                        var checkStatus = table.checkStatus(obj.config.id);
                        //获取选中数量
                        var selectCount = checkStatus.data.length;
                        if(selectCount == 0){
                            layer.msg('批量删除至少选中一项数据',function(){});
                            return false;
                        }
                        layer.confirm('真的删除行么', function (index) {
                            var bb = [];
                            for (var i=0;i<selectCount;i++){
                                bb[i]= checkStatus.data[i].id;
                            }
                            var b=JSON.stringify(bb);
                            $.ajax({
                                url: '/student/ll.do',
                                type: 'post',
                                data:b,
                                dataType:"json",
                                contentType:"application/json",
                                success: function (data1) {
                                if (data1=="1"){
                                       layer.msg("删除成功");
                                   }
                                if(data1=="2"){
                                    layer.msg("删除失败");
                                }
                                }
                            });
                            layer.close(index);
                        });
                        break;
                    case 'add':
                        //添加页面弹出层
                        layer.open({
                            type: 2
                            ,content: '/insert.jsp' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                            ,area: ['1000px', '500px']
                            ,end: function(index, layero){
                              location.reload();
                            }
                        });

                        break;
                };
            });

            //监听行
            table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

                if(layEvent === 'edit'){ //编辑
                    layer.open({
                        type: 2
                        ,content: '/upda.jsp?id='+data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                        ,area: ['1000px', '500px']
                        ,end: function(index, layero){
                            location.reload();
                        }
                    });
                } else if(layEvent === 'del') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        layer.close(index);
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    });
                }
            });
        });
            //表单
        layui.use('form', function(){
            var form = layui.form;

            $(function(){
                $.ajax({
                    url: '/student/cc.do',
                    type: 'post',
                    contentType:"application/json",
                    success: function (data,textStatus) {
                        var obj =eval(data);
                        for(var b=0;b<obj.length;b++){
                            var q=$("#p1").append("<option value='"+obj[b].classno+"'>"+obj[b].classname+"</option>");
                        }
                        form.render("select");
                        //重新渲染 固定写法
                    }
                });
            });

        });
    </script>


   <%-- //工具栏--%>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>


  <%--  工具栏头模板：--%>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            <button class="layui-btn layui-btn-sm" lay-event="delete">批量删除</button>
        </div>
    </script>


</head>
<body>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<form class="layui-form"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
    <div class="layui-form-item">
        <label class="layui-form-label">名字</label>
        <div class="layui-input-block">
            <input type="text" name="" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-block">
            <select name="interest" lay-filter="aihao" id="p1">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="0" title="男">
            <input type="radio" name="sex" value="1" title="女" checked>
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
