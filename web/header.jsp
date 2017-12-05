<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery-1.8.3.min.js"></script>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<a:if test="${!empty user }">
				<li><a href="#">${user.username}</a></li>
				<li><a href="#">退出</a></li>
			</a:if>
			<a:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</a:if>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="order_list.jsp">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
					<li><a href="#">电脑办公</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group" style="position: relative">
						<input id="search" type="text" class="form-control" placeholder="Search" onkeyup="searchWord(this)">
					</div>
					<div id="showdiv" style="width: 173px; background:white; border: 1px solid #fff; position: absolute;z-index: 1000">

					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>

                <script>
                    <%--完成异步搜索--%>
                    function searchWord(obj) {
//                        获得输入框的值
                        var word = obj.value;
                        var content = "";
                        $.get(
                            "${pageContext.request.contextPath}/searchWord",
                            {"word":word},
                            function (data) {
                                for (var i=0;i<data.length;i++) {
                                    content +="<div onclick='clickFun(this)'  onmouseover='overFun(this)' onmouseout='outFun(this)' style='padding: 5px; cursor: pointer'>"+data[i].pname+"</div>"
                                }
                                $("#showdiv").html(content);
                            },
                            "json"
                        );
//
                    }
                    function overFun(obj) {
                        $(obj).css("backgroundColor", "red");
                    }
                    function outFun(obj) {
                        $(obj).css("backgroundColor", "white");
                    }

                    function clickFun(obj) {
                        $("#search").val($(obj).html());
                        $("#showdiv").css("display", "none");

                    }

                </script>
			</div>
		</div>
	</nav>
</div>