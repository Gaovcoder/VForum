<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>修改 Topic 的页面</title>
</head>
<body>
    <h1>修改 Topic</h1>
    <form action="/topic/update" method="post">
<#--        <h3>id</h3>-->
        <input name="id" placeholder="id" value="${topic.id}" hidden>
        <br>
        <h3>标题</h3>
        <input name="title" placeholder="title" value="${topic.title}">
        <br>
        <h3>内容</h3>
        <input name="content" placeholder="content" value="${topic.content}">
        <br>
        <button type="submit">提交修改</button>
    </form>
</body>
</html>
