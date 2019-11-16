<!DOCTYPE HTML>
<html>
<head>
    <title>Topic</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<form action="/topic/add" method="POST">
    <input type="text" name="title" placeholder="请输入标题">
    <br>
    <br>
    <textarea cols="21" rows="3" style = "OVERFLOW:hidden" type="text" name="content" placeholder="请吐槽" > </textarea>
    <br>
    <button type="submit">添加</button>
</form>

<div>
    <#list topics as t>
    <h3>
        <a href="/topic/detail/${t.id}">${t.id}: ${t.title}</a>
        <br>创建日期${t.createdTime}
        <br>更新日期${t.updatedTime}
    </h3>
    <a href="/topic/edit?id=${t.id}">编辑</a>
    <a href="/topic/delete?id=${t.id}&token=${token}">删除</a>
    </#list>
</div>
    <a href='/'>返回主页</a>
</body>
</html>