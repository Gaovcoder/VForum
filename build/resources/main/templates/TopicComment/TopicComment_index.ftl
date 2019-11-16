<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>添加 Topic 评论的页面</title>
</head>
<form action="/TopicComment/add" method="POST">
    <input name="topicId" placeholder="topicId" value="${topicId}" hidden>
    <br>
  <#--  <input name="userId" placeholder="userId" value="${userId}" hidden>
    <br>-->
    <input type="text" name="content" placeholder="请输入评论">
    <br>
    <button type="submit">添加提交评论</button>
</form>
</html>
