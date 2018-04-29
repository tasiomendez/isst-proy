<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kanban - ${project_title}</title>

<%@ include file="../template/head.jsp"%>
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/kanban.css" rel="stylesheet" />

<!-- Kanban JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kanban.js"></script>
<script src="https://cdn.jsdelivr.net/lodash/4/lodash.min.js"></script>

<!-- Validator -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

<script type="text/javascript">
	window.onload = function() {
		if (sessionStorage.getItem('menu') != null)
			$('.item#' + sessionStorage.getItem('menu')).click();
		else 
			$('.item#projects').click();
	};
	
	if (performance.navigation.type == 1) {
		window.location.replace("${pageContext.request.contextPath}/KanbanServlet?project_code=${project_code}");
	}
</script>

</head>

<c:if test="${project_title == null}">
	<%
	    String redirectURL = request.getContextPath();
	    response.sendRedirect(redirectURL);
	%>
</c:if>

<body>

	<div class="app-wrapper">
		<nav class="navbar navbar-default main">
			<div class="navbar-header-brand">
				<a class="navbar-brand" href="#">Kanban</a>
				<small>By proy</small>
			</div>
		<div class="navbar-header">
			<a class="navbar-brand" href="#" style="font-weight: bold;">${project_title}</a>
		</div>
		</nav>

		<div class="container-fluid">
			<div class="board col-sm-12 col-lg-10 col-lg-offset-1" id="board">

				<!-- TO DO -->
				<div data-id="1" id="list_1" class="list">
					<h3 class="listname">Todo</h3>
					
					<%-- Lista de tareas de todo el proyecto --%>
					<c:forEach items="${tareas_list}" var="tareai">
						<c:if test="${tareai.estado == 'todo'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<form class="form-worked-hours" role="form">
									<input type="hidden" name="id" value="${tareai.id}" />
									<input type="number" name="worked-hours" placeholder="Hours..." required />
									<button type="submit">Send</button>
								</form>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del usuario logueado --%>
					<c:forEach items="${tareas_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'todo'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<form class="form-worked-hours" role="form">
									<input type="hidden" name="id" value="${tareai.id}" />
									<input type="number" name="worked-hours" placeholder="Hours..." required />
									<button type="submit">Send</button>
								</form>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del otros usuarios --%>
					<c:forEach items="${tareas_no_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'todo'}">
							<div draggable="false" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card no-draggable">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
							</div>
						</c:if>
					</c:forEach>

				</div>

				<!-- DOING -->
				<div data-id="2" id="list_2" class="list">
					<h3 class="listname">In Progress</h3>
					
					<%-- Lista de tareas de todo el proyecto --%>
					<c:forEach items="${tareas_list}" var="tareai">
						<c:if test="${tareai.estado == 'doing'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<div class="worked-hours">
									<table>
										<tr><td>Worked</td><td style="text-align: right;">5</td>
										<tr><td>Planned</td><td style="text-align: right;">10</td>
									</table>
								</div>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del usuario logueado --%>
					<c:forEach items="${tareas_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'doing'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<div class="worked-hours">
									<table>
										<tr><td>Worked</td><td style="text-align: right;">5</td>
										<tr><td>Planned</td><td style="text-align: right;">10</td>
									</table>
								</div>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del otros usuarios --%>
					<c:forEach items="${tareas_no_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'doing'}">
							<div draggable="false" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card no-draggable">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
							</div>
						</c:if>
					</c:forEach>

				</div>

				<!-- DONE -->
				<div data-id="3" id="list_3" class="list">
					<h3 class="listname">Done</h3>
					
					<%-- Lista de tareas de todo el proyecto --%>
					<c:forEach items="${tareas_list}" var="tareai">
						<c:if test="${tareai.estado == 'done'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<div class="worked-hours">
									<div class="worked-hours-submitted">25</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del usuario logueado --%>
					<c:forEach items="${tareas_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'done'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<div class="worked-hours">
									<div class="worked-hours-submitted">25</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del otros usuarios --%>
					<c:forEach items="${tareas_no_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'done'}">
							<div draggable="false" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card no-draggable">
								<c:if test="${role == 1}">
									<button type="button" class="close" id="buttonCard${tareai.id}"><span aria-hidden="true">&times;</span></button>
								</c:if>
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
							</div>
						</c:if>
					</c:forEach>

				</div>

			</div>
			<!-- //.board -->
		</div>
		<!-- //.container-fluid -->

	</div>

	<c:if test="${role==1 }">
		
		<!-- Add project button and modal -->	
		<div class="add-task">
			<span class="glyphicon glyphicon-plus"></span>
		</div>
		
		<script type="text/javascript">
			$('.add-task').on('click', function() {
				$('#add-task').modal('show')
			});
		</script>
		
		<div class="modal fade" id="add-task" tabindex="-1" role="dialog" aria-labelledby="add-task">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Add new task</h4>
		      </div>
		      <form id="select-calendar-form" action="CreateTareaServlet" method="post" role="form" data-toggle="validator">
				<div class="modal-body">
					<div class="form-group">
						<label for="title">Título</label> 
						<input type="text" name="title" id="title" class="form-control" placeholder="Título de la tarea" required>
					</div>
					
					<div class="form-group">
	                	<label for="description">Descripción</label>
	                	<textarea rows="4" cols="50" class="form-control" name="description" id="description" placeholder="Descripción de la tarea" style="resize: none;"></textarea> 
					</div>
					
					<div class="form-group">
						<label for="trabajador">Asignar tarea</label>
						<select name="trabajador" style="width: 100%;" required="required">
							<option value="">Elija un trabajador</option>
							<c:forEach items="${trabajador_list}" var="trabajadori">
								<option value=${trabajadori.email}>${trabajadori.nombre} (${trabajadori.email})</option>
							</c:forEach>
						</select>
						<div class="help-block with-errors"></div>
					</div>
					<script type="text/javascript">
						$('#add-task select').on('hide.bs.select', function () {
						    $(this).trigger("focusout");
						});
					</script>
					
					<div class="form-group">
						<label for="planned_hours">Horas asignadas a la tarea</label> 
						<input type="number" name="planned_hours" id="planned_hours" class="form-control" placeholder="Número de horas asignadas a la tarea" required>
					</div>
		    
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary btn-block disabled" type="submit">Add task</button>
				</div>
			  </form>
		    </div>
		  </div>
		</div>
		
		<script type="text/javascript">
			$('#add-task').on('hidden.bs.modal', function (e) {
				$('#add-task #title').val('');
		        $('#add-task #description').empty();
		        $('#add-task select').val('');
		        $('#add-task #planned_hours').val('');
			});
		</script>

	</c:if>

	<c:forEach items="${tareas_list}" var="tareai">
		<script type="text/javascript">
			importCard("${tareai.id}", "${tareai.titulo}", "${tareai.descripcion}", 
					"${tareai.planned_hours}", "${tareai.estado}")
		</script>
	</c:forEach>
	<c:forEach items="${tareas_user_list}" var="tareai">
		<script type="text/javascript">
			importCard("${tareai.id}", "${tareai.titulo}", "${tareai.descripcion}", 
					"${tareai.planned_hours}", "${tareai.estado}")
		</script>
	</c:forEach>
	<c:forEach items="${tareas_no_user_list}" var="tareai">
		<script type="text/javascript">
			importCard("${tareai.id}", "${tareai.titulo}", "${tareai.descripcion}", 
					"${tareai.planned_hours}", "${tareai.estado}")
		</script>
	</c:forEach>
	
	<div class="modal fade" id="error-task" tabindex="-1" role="dialog" aria-labelledby="error-task" data-keyboard="false" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	        <h4 class="modal-body" id="myModalLabel">Something went wrong. Sorry for the inconvenience.<br/>Please try again later...</h4>
	    </div>
	  </div>
	</div>	

</body>
</html>
