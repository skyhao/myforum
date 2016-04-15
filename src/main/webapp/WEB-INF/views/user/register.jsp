<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册用户</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<%@ include file="../include/header.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <div class="box-header">
            <span class="title"><i class="fa fa-sign-in"></i> 注册账号</span>
        </div>

        <form class="form-horizontal" id="regForm">
            <div class="control-group">
                <label class="control-label">账号</label>
                <div class="controls">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">密码</label>
                <div class="controls">
                    <input type="password" name="password" id="password">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">重复密码</label>
                <div class="controls">
                    <input type="password" name="repassword">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">电子邮件</label>
                <div class="controls">
                    <input type="text" name="email">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">验证码</label>
                <div class="controls">
                    <input type="text" name="code">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"></label>
                <div class="controls">
                    <a href="javascript:;" id="changePic"><img src="/captcha.png" id="img"></a>
                    <span>点击图片换一张</span>
                </div>
            </div>
            <div class="form-actions">
                <button type="button" id="regBtn" class="btn btn-primary">注册</button>
                <span id="regMsg" class="hide">注册成功,<span class="sec">3</span>秒后自动跳转到登录页面</span>
                <a class="pull-right" href="/login.do">登录</a>
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

        $("#regBtn").click(function(){
            $("#regForm").submit();
        });

        $("#changePic").click(function(){
           $("#img").attr("src", "/captcha.png?_="+new Date().getTime());
        });

        $("#regForm").validate({
            errorClass:'text-error',
            errorElement:'span',
            rules:{
                username:{
                    required:true,
                    rangelength:[2,10],
                    remote:'/validate/name.do'
                },
                password:{
                    required:true,
                    rangelength:[6,15]
                },
                repassword:{
                    required:true,
                    equalTo:'#password'
                },
                email:{
                    required:true,
                    email:true,
                    remote:'/validate/email.do'
                },
                code:{
                    required:true,
                    remote:'/validate/captcha.do'
                }
            },
            messages:{
                username:{
                    required:"请输入用户名",
                    rangelength:"用户名长度为2-10个字符",
                    remote:"用户名已存在"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"密码长度为6-15个字符"
                },
                repassword:{
                    required:"请确认密码",
                    equalTo:"两次密码不一致"
                },
                email:{
                    required:"请输入邮箱地址",
                    email:"该邮箱格式错误",
                    remote:"该邮箱已被注册"
                },
                code:{
                    required:"请输入验证码",
                    remote:"验证码错误"
                }
            },

            submitHandler:function(form){
                $.post("/register.do",$(form).serialize()).done(function(result){
                    if(result.state == "error"){
                        alert(result.message);
                    }else {
                        $("#regMsg").removeClass("hide");
                        var sec = 3;
                        setInterval(function(){
                            sec--;
                            if(sec == 0){
                                window.location.href = "/login.do";
                                return;
                            }
                            $(".sec").text(sec);
                        },1000);
                    }
                }).fail(function(){
                    alert("服务器异常,请稍后再试");
                });
            }

        });




    })
</script>
</body>
</html>