<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
</head>
<body>
    <h1>注册</h1>
    <img src="/vuce.jpg"/>
    <form action="/user/register" method="post">
        <input type="text" name="username" placeholder="请输入用户名">
        <br>
        <input type="text" name="password" placeholder="请输入密码">
        <br>
        <input type="text" name="mail" placeholder="请输入邮箱">
        <br>
        <button type="submit">注册</button>
    </form>
    <a href='/'>返回主页</a>
<#--    <h3>${user.username}</h3>-->
</body>
</html>