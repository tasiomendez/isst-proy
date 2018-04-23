<div class="row" style="height: 100%;">
	<div class="col-sm-12" style="height: 100%;">
		<button id="calendar-show-today">Today</button>
		<div id="caleandar" style="height: 100%;"></div>
	</div>
</div>

<c:choose>
    <c:when test="${events != null}">
       	<script type="text/javascript"> 
			var events = ${events},
				settings = {},
				element = document.getElementById('caleandar');
			caleandar(element, events, settings);
			$('#calendar-show-today').on('click', function() {
				$('#caleandar').empty();
				caleandar(element, events, settings);
			});
		</script>
    </c:when>    
    <c:otherwise>
       	<script type="text/javascript"> 
			var events = [],
				settings = {},
				element = document.getElementById('caleandar');
			caleandar(element, events, settings);
			$('#calendar-show-today').on('click', function() {
				$('#caleandar').empty();
				caleandar(element, events, settings);
			});
		</script>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${calendar_id != null}">
       	<!-- Add project button and modal -->
		<div class="add-event">
			<span class="glyphicon glyphicon-plus"></span>
		</div>
		<div class="modal fade" id="add-event-calendar" tabindex="-1" role="dialog" aria-labelledby="add-event-calendar">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add new event</h4>
					</div>
					<form id="select-calendar-form" action="CreateEventServlet" method="post" role="form" data-toggle="validator">
						<div class="modal-body">
							<div class="form-group">
			                	<label for="title">Título</label>
			                	<input type="text" class="form-control" name="title" id="tile" placeholder="Título" required>
							</div>
							<div class="form-group">
			                	<label for="description">Descripción</label>
			                	<textarea rows="4" cols="50" class="form-control" name="description" id="description" placeholder="Descripción del evento" style="resize: none;"></textarea> 
							</div>
							<div class="form-group" style="padding: 0;">
								<label for="initialEventDate">Fecha y hora del evento</label>
						        <div class='input-group date' id='initialEventDate'>
						            <input type='text' name="initialEventDate" class="form-control" placeholder="Fecha de inicio" onclick="$('#initialEventDate').datetimepicker('show');" required/>
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
							<button class="btn btn-primary btn-block" type="submit">Add event</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$('.add-event').on('click', function() {
				$('#add-event-calendar').modal('show');
			});
			$('#add-event-calendar').on('hidden.bs.modal', function (e) {
				$('#add-event-calendar #title').val('');
		        $('#add-event-calendar textarea').empty();
			});
		</script>
    </c:when>    
    <c:otherwise>
       	<div class="sync-event">
			<span class="glyphicon glyphicon-refresh"></span>
		</div>
		<script type="text/javascript">
			$('.sync-event').on('click', function() {
				window.location = "${pageContext.request.contextPath}/CalendarServlet"
			});
		</script>
    </c:otherwise>
</c:choose>

<c:if test="${calendar_list != null}">

	<div class="modal fade" id="select-calendar" tabindex="-1" role="dialog" aria-labelledby="select-calendar">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Select your calendar</h4>
				</div>
				<form id="select-calendar-form" action="CreateCalendarServlet" method="post" role="form">
					<div class="modal-body">
						<p>Selecciona el calendario con el que sincronizar los eventos.</p>
						<div class="form-group">
							<select name="calendar" id="calendar" style="width: 100%">
								<option value="" disabled selected">Elija un calendario</option>
								<c:forEach items="${calendar_list}" var="calendari">
									<option value=${calendari.getId()}>${calendari.getSummary()}</option>
								</c:forEach>
							</select>
						</div>
						<p style="margin-top: 15px;">Si no quieres utilizar uno de los calendarios exsitentes, puedes crear uno nuevo a continuación.</p>
						<div class="form-group">
							<input class="form-control" name="newcalendar" id="newcalendar"
								   placeholder="Introduce el nombre del nuevo calendario" type="text">
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary btn-block" type="submit">Synchronize</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$('#select-calendar').modal('show')
	</script>
</c:if>
	
	
	
	
	
	
	
