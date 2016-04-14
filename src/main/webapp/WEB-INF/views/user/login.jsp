<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <%--<script>--%>
        <%--alert(${param.state == '1001'})--%>
    <%--</script>--%>
</head>
<body>
<%@ include file="../include/header.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i> 登录</span>
        </div>
        <c:choose>
            <c:when test="${param.state == '1001'}">
                <div class="alert alert-success">你已安全退出</div>
            </c:when>
            <c:when test="${param.state == '1002'}">
                <div class="alert alert-error">请登录后再操作</div>
            </c:when>
        </c:choose>
        <form action="" class="form-horizontal" id="loginForm">
            <div class="control-group">
                <label class="control-label">用户名</label>
                <div class="controls">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="password" name="password" >
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                    <a href="/forget/password.do">忘记密码</a>
                </div>
            </div>

            <div class="form-actions">
                <button class="btn btn-primary" type="button" id="loginBtn">登录</button>

                <a class="pull-right" href="/register.do">注册账号</a>
            </div>

        </form>

    </div>
    <!--box end-->
</div>
<!--container end-->
<script src="/static/js/jquery-2.2.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function(){
        $("#loginBtn").click(function(){
            $("#loginForm").submit();
        });

        $("#loginForm").validate({
            errorClass:"text-error",
            errorElement:"span",

            rules:{
                username:{
                    required:true
                },
                password:{
                    required:true
                }
            },

            messages: {
                username: {
                    required: "请输入用户名"
                },
                password: {
                    required: "请输入密码"
                }
            },
            submitHandler:function(form){
                var $btn = $("#loginBtn");
                $.ajax({
                    url:"/login.do",
                    type:"post",
                    data:$(form).serialize(),
                    beforeSend:function(){
                        $btn.text("登录中...").attr("disabled","disabled");
                    },
                    success:function(json){
                        if(json.state == 'error'){
                            alert(json.message)
                        }else {
                            window.location.href = "/index.do"
                        }
                    },
                    error:function(){
                        alert("服务器错误，请稍后再试")
                    },
                    complete:function(){
                        $btn.text("登录").removeAttr("disabled");
                    }
                });
            }
        });
    })
</script>
</body>
</html>