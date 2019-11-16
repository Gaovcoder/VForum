<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
    <a href='/index'>主页</a>
    <a href="/login">登录</a>
    <a href="/register">用户注册</a>
    <a href="/todo">待办事项</a>
    <a href="/mail/index">邮局</a>
    <a href="/topic">论坛</a>
    <a href="/user/profile?id=${currentUser.id}&userId=${currentUser.id}">我的信息</a>
    <br>
    我是个主页
    <br>

    <h3>欢迎 ${currentUser.username}</h3>
    <img src="/welcome.jpg"/>
</body>
</html>
