<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>

<%@ include file="template/head.jsp" %>
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet" />

<!-- Validator -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

<!-- Datepicker -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment-with-locales.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/eonasdan-bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/eonasdan-bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />

<!-- Google Charts -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!-- Calendar -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/caleandar.js"></script>

<script type="text/javascript">
	window.onload = function() {
		if (sessionStorage.getItem('menu') != null)
			$('.item#' + sessionStorage.getItem('menu')).click();
		else 
			$('.item#projects').click();
	};
</script>

</head>
<body>

<c:if test="${email == null}">
	<%
	    String redirectURL = request.getContextPath();
	    response.sendRedirect(redirectURL);
	%>
</c:if>

<nav class="navbar navbar-default navbar-fixed-top main">
    <div class="container-fluid">
    	<ul class="nav navbar-nav show-menu">
			<li class="glyphicon glyphicon-menu-hamburger" id="show-menu"></li>
		</ul>
        <div class="navbar-header">
            <a class="navbar-brand" href="#" style="font-weight: bold;">Dashboard</a>
        </div>
        <div class="right-menu">
	        <p class="navbar-text">Signed in as ${name}</p>
	        <ul class="nav navbar-nav">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span></a>
				<ul class="dropdown-menu">
					<li><a href="LogoutServlet">Sign out</a></li>
				</ul>
			</ul>
		</div>
    </div>
</nav>

<div id="sidenav" class="sidenav">
	<%@ include file="template/menu.jsp" %>
</div>

<div id="projects" class="main hidden">
  	<script type="text/javascript">
		$('.item#projects').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#projects').removeClass('hidden');
			$('.navbar-brand').text("Dashboard");
			sessionStorage.setItem('menu', 'projects');
		})
	</script>
  	<%@ include file="dashboard/projects.jsp" %>
</div>

<c:if test="${role == 1 || role == 0}">
<div id="create" class="main hidden">
	<script type="text/javascript">
		$('.item#create').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#create').removeClass('hidden');
			$('.navbar-brand').text("Crear Proyecto");
			sessionStorage.setItem('menu', 'create');
		})
	</script>
  	<%@ include file="dashboard/create.jsp" %>
</div> 
</c:if>

<c:if test="${role == 2 || role == 0}">
<div id="analysis" class="main hidden">
	<script type="text/javascript">
		$('.item#analysis').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#analysis').removeClass('hidden');
			$('.navbar-brand').text("Análisis");
			sessionStorage.setItem('menu', 'analysis');
		})
	</script>
  	<%@ include file="dashboard/analysis.jsp" %>
</div> 
</c:if>

<div id="calendar" class="main hidden">
	<script type="text/javascript">
		$('.item#calendar').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#calendar').removeClass('hidden');
			$('.navbar-brand').text("Calendario");
			sessionStorage.setItem('menu', 'calendar');
		})
	</script>
  	<%@ include file="dashboard/calendar.jsp" %>
</div> 

<c:if test="${role == 1 || role == 0}">
<div id="informs" class="main hidden">
	<script type="text/javascript">
		$('.item#informs').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#informs').removeClass('hidden');
			$('.navbar-brand').text("Informes");
			sessionStorage.setItem('menu', 'informs');
		})
	</script>
  	<%@ include file="dashboard/informs.jsp" %>
</div>
</c:if>

<div id="contacts" class="main hidden">
	<script type="text/javascript">
		$('.item#contacts').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#contacts').removeClass('hidden');
			$('.navbar-brand').text("Contactos");
			sessionStorage.setItem('menu', 'contacts');
		})
	</script>
 	<%@ include file="dashboard/contacts.jsp" %>
</div>

<div id="settings" class="main hidden">
	<script type="text/javascript">
		$('.item#settings').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#settings').removeClass('hidden');
			$('.navbar-brand').text("Ajustes");
			sessionStorage.setItem('menu', 'settings');
		})
	</script>
  	<%@ include file="dashboard/settings.jsp" %>
</div>

<div id="help" class="main hidden">
	<script type="text/javascript">
		$('.item#help').on('click', function() {
			$('.main').addClass('hidden');
			$('.main#help').removeClass('hidden');
			$('.navbar-brand').text("Ayuda");
			sessionStorage.setItem('menu', 'help');
		})
	</script>
  	<%@ include file="dashboard/help.jsp" %>
</div>

</body>
</html>