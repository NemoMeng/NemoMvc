<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<form action="http://localhost:8081/user/test.html" method="POST">
    名称：<input type="text" name="name">
    年龄<input type="text" name="age">
    <input type="submit" name="submit" value="提交">
</form>
</body>
</html>