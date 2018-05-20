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

<!-- Dropzone JS -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.4.0/dropzone.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.4.0/dropzone.css" rel="stylesheet" />

<!-- Validator -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

<!-- Datepicker -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment-with-locales.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/eonasdan-bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/eonasdan-bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />

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

<!-- Modal de error al importar tareas -->
<!--<c:if test="${not empty error_tareas_trabajador || not empty error_tareas_vacias}">
	<div class="modal fade in" id="error-import" tabindex="-1" role="dialog" aria-labelledby="error-import" style="display: block;">
		 <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content" style="background-color: transparent; border:none;" >
		      
				<div class="alert alert-danger" role="alert" style="margin-bottom:0px ">
					<c:if test="${not empty error_tareas_trabajador}">
						<b>
							Error: no se han podido importar las siguientes tareas ya que el email introducido no corresponde a ningun trabajador de este proyecto:
						</b> 
						<c:forEach items="${error_tareas_trabajador}" var="tarea">
							<li>${tarea }</li>
						</c:forEach>
					</c:if>
					<c:if test="${not empty error_tareas_vacias}">
						<b>
							Error: no se han podido importar las siguientes tareas debido a que alguno de los campos no estan rellenados:
						</b>
						<c:forEach items="${error_tareas_vacias}" var="tarea">
							<li>${tarea }</li>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%
		session.removeAttribute("error_tareas_trabajador");
		session.removeAttribute("error_tareas_vacias");
	%>
 </c:if>-->

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
								<div class="task-user">${tareai.usuario.nombre} (${tareai.usuario.email})</div>
								<c:if test="${role == 1}">
									<div class="worked-hours">
										<table>
											<tr><td>Worked</td><td style="text-align: right;">${tareai.worked_hours}</td>
											<tr><td>Planned</td><td style="text-align: right;">${tareai.planned_hours}</td>
										</table>
									</div>
								</c:if>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del usuario logueado --%>
					<c:forEach items="${tareas_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'todo'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<c:choose>
								    <c:when test="${role == 1}">
								       	<table>
											<tr><td>Worked</td><td style="text-align: right;">${tareai.worked_hours}</td>
											<tr><td>Planned</td><td style="text-align: right;">${tareai.planned_hours}</td>
										</table>
								    </c:when>   
								    <c:when test="${tareai.worked_hours==0}">
								    	<form id="form-worked-hours" class="form-worked-hours" role="form">
											<input type="hidden" name="id" value="${tareai.id}" />
											<input type="number" name="worked-hours" placeholder="Hours..." required />
											<button type="submit">Send</button>
										</form>
								    </c:when>  
								    <c:otherwise>
								        <div class="worked-hours">
											<div class="worked-hours-submitted">${tareai.worked_hours}</div>
										</div>
								    </c:otherwise>
								</c:choose>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del otros usuarios --%>
					<c:forEach items="${tareas_no_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'todo'}">
							<div draggable="false" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card no-draggable">
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
								<div class="task-user">${tareai.usuario.nombre} (${tareai.usuario.email})</div>
								<c:if test="${role == 1}">
									<div class="worked-hours">
										<table>
											<tr><td>Worked</td><td style="text-align: right;">${tareai.worked_hours}</td>
											<tr><td>Planned</td><td style="text-align: right;">${tareai.planned_hours}</td>
										</table>
									</div>
								</c:if>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del usuario logueado --%>
					<c:forEach items="${tareas_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'doing'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<c:choose>
								    <c:when test="${role == 1}">
								       	<table>
											<tr><td>Worked</td><td style="text-align: right;">${tareai.worked_hours}</td>
											<tr><td>Planned</td><td style="text-align: right;">${tareai.planned_hours}</td>
										</table>
								    </c:when>   
								    <c:when test="${tareai.worked_hours==0}">
								    	<form id="form-worked-hours" class="form-worked-hours" role="form">
											<input type="hidden" name="id" value="${tareai.id}" />
											<input type="number" name="worked-hours" placeholder="Hours..." required />
											<button type="submit">Send</button>
										</form>
								    </c:when>  
								    <c:otherwise>
								        <div class="worked-hours">
											<div class="worked-hours-submitted">${tareai.worked_hours}</div>
										</div>
								    </c:otherwise>
								</c:choose>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del otros usuarios --%>
					<c:forEach items="${tareas_no_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'doing'}">
							<div draggable="false" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card no-draggable">
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
								<div class="task-user">${tareai.usuario.nombre} (${tareai.usuario.email})</div>
								<c:if test="${role == 1}">
									<div class="worked-hours">
										<table>
											<tr><td>Worked</td><td style="text-align: right;">${tareai.worked_hours}</td>
											<tr><td>Planned</td><td style="text-align: right;">${tareai.planned_hours}</td>
										</table>
									</div>
								</c:if>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del usuario logueado --%>
					<c:forEach items="${tareas_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'done'}">
							<div draggable="true" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card">
								<div class="task-title">${tareai.titulo}</div>
								<div class="task-description">${tareai.descripcion}</div>
								<c:choose>
								    <c:when test="${role == 1}">
								       	<table>
											<tr><td>Worked</td><td style="text-align: right;">${tareai.worked_hours}</td>
											<tr><td>Planned</td><td style="text-align: right;">${tareai.planned_hours}</td>
										</table>
								    </c:when>   
								    <c:when test="${tareai.worked_hours==0}">
								    	<form id="form-worked-hours" class="form-worked-hours" role="form">
											<input type="hidden" name="id" value="${tareai.id}" />
											<input type="number" name="worked-hours" placeholder="Hours..." required />
											<button type="submit">Send</button>
										</form>
								    </c:when>  
								    <c:otherwise>
								        <div class="worked-hours">
											<div class="worked-hours-submitted">${tareai.worked_hours}</div>
										</div>
								    </c:otherwise>
								</c:choose>
							</div>
						</c:if>
					</c:forEach>
					
					<%-- Lista de tareas del otros usuarios --%>
					<c:forEach items="${tareas_no_user_list}" var="tareai">
						<c:if test="${tareai.estado == 'done'}">
							<div draggable="false" data-id="${tareai.id}" data--state="${tareai.estado}" id="${tareai.id}" class="card no-draggable">
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
						<label for="title">Tí­tulo</label> 
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
		    		
		    		<div class="form-group" style="padding: 0;">
						<label for="finalDate">Fecha límite de la tarea</label>
				        <div class='input-group date' id='initialEventDate'>
				            <input type='text' name="finalDate" class="form-control" placeholder="Fecha límite" onclick="$('#initialEventDate').datetimepicker('show');" required/>
				            <span class="input-group-addon">
				                <span class="glyphicon glyphicon-calendar"></span>
				            </span>
				        </div>
				    </div>
				    
					<script type="text/javascript">
					    $(function () {
					        $('#initialEventDate').datetimepicker({
					        	viewMode: 'years',
					        	locale: 'es',
					        	format: 'DD-MM-YYYY HH:mm'
					        });
					    });
					</script>
							
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
		
		
		<!-- Import tasks button and modal -->
		<div class="import-tasks">
			<span class="glyphicon glyphicon-import"></span>
		</div>
		
		<script type="text/javascript">
			$('.import-tasks').on('click', function() {
				$('#import-tasks').modal('show')
			});
		</script>
		
		<div class="modal fade" id="import-tasks" tabindex="-1" role="dialog" aria-labelledby="add-task">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Import new tasks</h4>
		      </div>
		      
			  <div class="modal-body">
			  		<form id="dropzone" class="dropzone" action="ImportTareasServlet" method="post" role="form" data-toggle="validator" enctype="multipart/form-data">
					</form>					
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary btn-block" type="submit" data-action="send-dropzone">Import tasks</button>
				</div>
			  
		    </div>
		  </div>
		</div>
		
		<script type="text/javascript">
			$('#import-tasks').on('hidden.bs.modal', function (e) {
				$('#dropzone #file').val('');
			});
			
			Dropzone.options.dropzone = {
			  	init: function() {
				    this.on("addedfile", function() {
			      		if (this.files[1] != null){
			        		this.removeFile(this.files[0]);
			      		}
			    	});
				    myDropzone = this;
				    $('.btn[data-action="send-dropzone"]').on("click", function() {
			        	myDropzone.processQueue(); // Tell Dropzone to process all queued files.
			      	});
				},
				success: function() {
					location.reload(); 
				},
				autoProcessQueue: false
			}
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
