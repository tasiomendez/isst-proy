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

<title>Admin</title>
</head>
<body>

<c:if test="${role != 0}">
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
                <li>Already have an account?</li>
                <li style="padding: 0 !important"><a href="${pageContext.request.contextPath}" role="button" id="sign-up">Sign in</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container login-form">
    <div class="row">
        <div class="col-sm-5 login-wrapper">
        		<h3 class="text-center">Sign up</h3>
        		<c:if test="${not empty user_error}">
					<div class="alert alert-danger" role="alert"><b>Error!</b> ${user_error}</div>
					<%
						session.removeAttribute("user_error");
					%>
				</c:if>
				<c:if test="${not empty user_success}">
					<div class="alert alert-success" role="alert"><b>Success!</b> ${user_success}</div>
					<%
						session.removeAttribute("user_success");
					%>
				</c:if>
        		
                <form class="form-signin" role="form" method="post" action="AdminServlet" data-toggle="validator">
                	<div class="form-group">
	                	<label for="name">Name</label>
	                	<input type="text" name="name" id="name" class="form-control" placeholder="Type your name" required>
	                </div>
                	
                	<div class="form-group">
	                	<label for="email">Email</label>
	                	<input type="text" name="email" id="email" class="form-control" placeholder="Type your email" required>
					</div>
                	
                	<div class="form-group">
	                	<label for="password">Password</label>
	                	<input type="password" name="password" id="password" class="form-control" placeholder="Type your password" required>
	                </div>
                	
                	<div class="form-group">
	                	<label for="confirmPassword">Confirm password</label>
	                	<input type="password" id="confirmPassword" data-match="#password" data-match-error="Passwords don't match." class="form-control" placeholder="Confirm your password" required>
	                	<div class="help-block with-errors"></div>
	                </div>
                	
                	<div class="form-group">
	                	<label for="role" style="width: 100%;">Role</label>
	                    <select name="role" id="role" style="width: 100%;" required>
	                    	<option value="" disabled selected>Select role</option>
							<option value="0">RRHH</option>
							<option value="1">Project Manager</option>
	                    </select>
	                </div>
	                                	
                	<button class="btn btn-lg btn-primary btn-block disabled" type="submit">Register</button>
                </form>
        </div>
    </div>
</div>

</body>
</html>