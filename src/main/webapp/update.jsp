<%--
  Created by IntelliJ IDEA.
  User: 22409
  Date: 2020/9/24
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" ></script>
    <script>
        function update(id) {
            var name=$("#name").val();
            var sex =$("#sex").val();
            var options = $("select option:selected").val();
            var b  = JSON.stringify({name:name,sex:sex,classno:options});
            $.ajax({
                type:'post',
                url:"/student/update.do",
                data:b,
                dataType:"json",
                contentType:"application/json",
                success: function (data,textStatus) {

                }
            });
        }
    </script>
</head>
<body>
<table align="center">
    班级:<select>
    <c:forEach items="${list}" var="list">
        <option value="${list.classno}">${list.classes.classname}</option>
    </c:forEach>
</select><br/>
    姓名:<input type="text" name="name" value="${list.name}"/><br>
    性别:<input type="text" name="sex" value="${list.sex}"/><br>
    <input type="button" onclick="update()" value="修改">
</table>
</body>
</html>
