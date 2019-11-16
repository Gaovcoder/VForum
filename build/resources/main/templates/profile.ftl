<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <!-- meta -->
        <meta charset="utf-8">
        <meta name="description" content="CNode：Node.js专业中文社区">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="nodejs, node, express, connect, socket.io">
        <meta name="referrer" content="always">
        <meta name="author" content="EDP@TaoBao">
        <meta property="wb:webmaster" content="617be6bd946c6b96">
        <link title="RSS" type="application/rss+xml" rel="alternate" href="/rss">
        <link rel="icon" href="//static2.cnodejs.org/public/images/cnode_icon_32.png" type="image/x-icon">
        <!-- style -->
        <link rel="stylesheet" href="//static2.cnodejs.org/public/stylesheets/index.min.23a5b1ca.min.css" media="all">
        <!-- scripts -->
        <script async="" src="https://www.google-analytics.com/analytics.js"></script>
        <script src="//static2.cnodejs.org/public/index.min.f7c13f64.min.js"></script>
        <title>@Gaovcoder 的个人主页</title>
        <meta content="_csrf" name="csrf-param">
        <meta content="hoR7OBJY-n6EUdjkhU7vZb07LHfiCscWl7pc" name="csrf-token">
    </head>
<body>
<div id="sidebar">
<div class="panel">
    <div class="header">
        <span class="col_fade">个人信息</span>
    </div>
    <div class="inner">
        <div class="user_card">
            <div>
                <a class="user_avatar" >
                    <img src="/avatar.png" title="Gaovcoder">
                </a>
                <span class="user_name"><a class="dark">${avatar.username}</a></span>

                <div class="board clearfix">
                    <div class="floor">
                        <span class="big">积分: 0 </span>
                    </div>
                </div>
                <div class="space clearfix"></div>
                <span class="signature">
        “
            这家伙很懒，什么个性签名都没有留下。
        ”
    </span>
            </div>
        </div>
    </div>

</div>
</div>
<div id="content">
    <div class="panel">
        <div class="header">
            <ul class="breadcrumb">
                <li><a href="/">用户主页</a><span class="divider">/</span></li>
            </ul>
        </div>
        <div class="inner userinfo">
            <div class="user_big_avatar">
                <img src="/avatar.png" class="user_avatar" title="Gaovcoder">
            </div>
            <a class="dark">${avatar.username}</a>
            <div class="user_profile">
                <p class="col_fade">注册日期 ${createdTime.createdTime}</p>
            </div>
        </div>
    </div>

    <div class="panel">
        <div class="header">
            <span class="col_fade">最近创建的话题</span>
        </div>

        <div class="inner">
            <#list topics as t>
                <h3>
                    <a href="/topic/detail/${t.id}">${t.id}: ${t.title}</a>
                    <br>创建日期${t.createdTime}
                </h3>
            </#list>
        </div>

    </div>

    <div class="panel">
        <div class="header">
            <span class="col_fade">最近参与的话题</span>
        </div>

        <div class="inner">
<#--            <#list comments as c>
                <h3>
                    <a href="/topic/detail/${c.topicId}">${c.id}: ${c.content}</a>
                    <br>创建日期${c.createdTime}
                </h3>
            </#list>-->
            <#list ComOnTopics as t>
                <h3>
                    <a href="/topic/detail/${t.id}">${t.id}: ${t.title}</a>
                    <br>创建日期${t.createdTime}
                </h3>
            </#list>
        </div>

    </div>
</div>
</body>
</html>