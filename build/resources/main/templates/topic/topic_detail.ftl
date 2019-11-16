<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>${topic.title}</title>
</head>

    <h1>${topic.title}</h1>

    文章作者:<a href="/user/profile?id=${user.id}&userId=${user.id}">${user.username}</a>

    <p>${topic.content}</p>
    <br>
    <div>
        <h3>评论</h3>
        <#list topic.commentList as c>
            <div>
                ${c.id}. ${c.user.username} : ${c.content}
            </div>
        </#list>
    </div>
    <a href="/TopicComment?topicId=${id}">评论</a>
    <a href='/topic'>返回论坛</a>

</body>
</html>
