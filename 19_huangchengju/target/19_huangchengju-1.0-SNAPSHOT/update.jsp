<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/6/28
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h2>修改页面</h2>

        <form action="update.do" type="post">

            <input type="hidden" name="id" value="${userEntity.id}">

            <br/><br/>
            姓名：
            <input type="text" name="name" value="${userEntity.name}">

            <br/><br/>
            密码：
            <input type="text" name="password" value="${userEntity.password}">
            <br/><br/>
            邮箱：
            <input type="text" name="email" value="${userEntity.email}">
            <br/><br/>
            <button type="submit">提交</button>
        </form>
</body>
</html>
