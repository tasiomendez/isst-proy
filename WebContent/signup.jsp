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
<script>
function captcha_onclick(e) {
    $('#recaptchaValidator').val(1);
    $('.form-signin').validator('validate');
}
</script>

<script src='https://www.google.com/recaptcha/api.js'></script>

<title>Register</title>
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
				
                <form class="form-signin" role="form" method="post" action="SignUpServlet" data-toggle="validator">
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
	                	<label for="project_code">Project Code</label>
	                	<input type="text" name="project_code" id="project_code" class="form-control" placeholder="Type project code" required>
	                </div>
                	
                	<div class="form-group">
	        			<input type="text" name="recaptcha" value="" id="recaptchaValidator" pattern="1" style="visibility: hidden; height: 0px;" required>
	                	<div class="g-recaptcha" data-sitekey="6LfCVFMUAAAAAK1YZlFyCiqijsTS3saTyHJBs-dt" data-callback="captcha_onclick"></div>
	                </div>	
                	
                	<button class="btn btn-lg btn-primary btn-block disabled" type="submit">Register</button>
                	<label class="checkbox pull-right remember-me">
                    	<input type="checkbox" name="agreement" value="agreement" required>Accept user agreement
                	</label>
                </form>
        </div>
    </div>
</div>

<div class="container text-info">
	<div class="row">
		<div class="col-sm-5">
			<p class="text-center">Si necesitas permisos de administración de proyectos o para gráficas de análisis, por favor, escriba un correo a <a href="mailto:admin@proy.es">admin@proy.com</a></p>
		</div>
	</div>
</div>

</body>
</html>