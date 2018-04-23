<%@page import="com.google.apphosting.utils.remoteapi.RemoteApiPb.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="template/head.jsp" %>
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" />

<!-- Validator -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

<title>Login</title>
</head>
<body>

<c:if test="${email != null}">
	<%
	    String redirectURL = request.getContextPath();
	    response.sendRedirect(redirectURL);
	%>
</c:if>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Gestión de Proyectos de Ingeniería</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>Don't have an account?</li>
                <li style="padding: 0 !important"><a href="${pageContext.request.contextPath}/signup.jsp" role="button" id="sign-up">Sign up</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container login-form">
    <div class="row">
        <div class="col-sm-5 login-wrapper">
        		<img src="${pageContext.request.contextPath}/img/logo.png" alt="logo"/>
				
                <form class="form-signin" action="LoginServlet" method="post" data-toggle="validator" role="form">
                	<div class="form-group">
	                	<label for="email">Email</label>
	                	<input type="text" class="form-control" name="email" id="email" placeholder="Email" required autofocus>
					</div>
                	
                	<div class="form-group">
	                	<label for="password">Password</label>
	                	<input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
	                </div>
                	
                	<button class="btn btn-lg btn-primary btn-block disabled" type="submit">Sign in</button>
                	<label class="checkbox pull-right remember-me">
                    	<input type="checkbox" name="remember-me" value="remember-me">Remember me
                	</label>
                </form>
        </div>
    </div>
</div>

</body>
</html>