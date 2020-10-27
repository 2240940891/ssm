<%--
  Created by IntelliJ IDEA.
  User: 22409
  Date: 2020/9/23
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" ></script>
    <script type="application/javascript">
        var inputs =  document.getElementsByTagName("input");
        function selectAll() {
            for (var i = 0; i < inputs.length; i++) {
                inputs[i].checked = true;
            }
        }
            function unSelectAll() {
                for (var i = 0; i < inputs.length; i++) {
                    inputs[i].checked = false;
                }
            }

            function converSelectAll() {
                for (var i = 0; i < inputs.length; i++) {
                    if (inputs[i].checked) {
                        inputs[i].checked = false;
                    } else {
                        inputs[i].checked = true;
                    }
                }
            }

        function select() {
            var name=$("#name").val();
            var sex =$("#sex").val();
            var b  = JSON.stringify({name:name,classnaem:sex});
            $.ajax({
                type:'post',
                url:"/student/mohu.do",
                data:b,
                dataType:"json",
                contentType:"application/json",
                success: function (data,textStatus) {
                    var l=eval(data);
                    var tq = $("#tbody").empty();
                    for(var b=0;b<l.length;b++){
                        var q0=$("<td>").html("<input type='checkbox' />"+data[b].id);
                        var q2=$("<td>").text(data[b].name);
                        var q3=$("<td>").text(data[b].sex);
                        var q4=$("<td>").text(data[b].classes.classname);
                        var q5=$("<td>").html("<button onclick='upda(data[b].id)'>修改</button> "+"<button onclick='dele(data[b].id)' >删除</button>");
                        var pp=$("<tr>");
                        pp.append(q0);
                        pp.append(q2);
                        pp.append(q3);
                        pp.append(q4);
                        pp.append(q5);
                        tq.append(pp);
                    }
                }
            });
        }


        function upda(id) {
            var p=id;
            location.href="/student/selectt.do?id=2&pp="+p+"";
        }


        function dele(id) {
            $.post("${pageContext.request.contextPath}/student/delete.do",{id:id},function(data,status) {

            });
        }


        function pidele() {
            var checkids = [];
            $("input[name='checkbox']:checked").each(function(i){
                checkids[i] = $(this).val();
                alert(checkids[i]);
            });
           var b=JSON.stringify(checkids);
            $.ajax({
                type:'post',
                url:"/student/pidelete.do",
                data:b,
                dataType:"json",
                contentType:"application/json",
                success: function (data,textStatus) {
                    location.href="/student/qq.do";
                }
            });
        }


        function insert(id) {
            location.href="/student/qq.do?id=1";
        }


    </script>
</head>
<body>
<div align="center">
    <input type="button" value="添加" onclick="insert()" id="insert">
    名字:<input type="text" id="name"/>班级:<input type="text" id="sex"/>
    <button onclick="select()" >查询</button>
    <button onclick="pidele()">批量删除</button>
    <input type="button"  onclick="selectAll()" value="全选"/>
    <input type="button" onclick="unSelectAll()" value="全不选"/>
    <input type="button" onclick="converSelectAll()" value="反选">
    </div>
<div align="center">
    <form>
        <table id="table" border="2" width="600px"height="150px" >
            <thead>
            <tr>
                <td>编号</td><td>姓名</td><td>性别</td><<td>班级</td><td>操作</td>
            </tr>
            </thead>
        <tbody id="tbody">
        <c:forEach  items="${list}" var="list">
            <tr>
                <td><input type="checkbox" value="${list.id}" name="checkbox"/>
                ${list.id}</td>
                <td>${list.name}</td>
                <td>${list.sex}</td>
                <td>${list.classes.classname}</td>
                <td><button onclick="dele(${list.id})">删除</button><button onclick="upda(${list.id})" >修改</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </form>
</div>
</body>
</html>
