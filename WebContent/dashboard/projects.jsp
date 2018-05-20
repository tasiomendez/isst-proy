 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<div class="row header">
	<div class="col-sm-12">
		<h3 class="text-center"><b><em>Current Projects List</em></b></h3>
		<!-- <form class="form-filter col-sm-3" role="form">
	    	<div class="input-group">
				<input type="text" class="form-control" placeholder="Introduce palabras clave" aria-describedby="basic-addon2" />
				<span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-search"></span></span>
			</div>
		</form>-->
	</div>
</div>

<script text="text/javascript">
	function getRandomColor(seed) {
		var letters = '0123456789ABCDEF';
		var color = '#';
		for (var i = 0; i < 6; i++) {
			color += letters[Math.floor(randomSeedSin(seed + i)() * 16)];
		}
		return color;
	}
	
	function randomSeedSin(seed) { 
	    // https://stackoverflow.com/a/19303725/1791917
	    return function(){
	      const x = Math.sin(seed++) * 10000
	      return x - Math.floor(x);
	   }
	}
	
	function lightColor(hex, lum) {

		// validate hex string
		hex = String(hex).replace(/[^0-9a-f]/gi, '');
		if (hex.length < 6) {
			hex = hex[0]+hex[0]+hex[1]+hex[1]+hex[2]+hex[2];
		}
		lum = lum || 0;

		// convert to decimal and change luminosity
		var rgb = "#", c, i;
		for (i = 0; i < 3; i++) {
			c = parseInt(hex.substr(i*2,2), 16);
			c = Math.round(Math.min(Math.max(0, c + (c * lum)), 255)).toString(16);
			rgb += ("00"+c).substr(c.length);
		}

		return rgb;
	}
</script>

<c:choose>
    <c:when test="${fn:length(project_list) gt 0}">
    
		<c:forEach items="${project_list}" var="project">
		
			<!-- Lista dinámica de projectos -->
			<div class="row">
				<div class="col-sm-12 project-item">
					<img class="project-img" src="img/logo.png" />
					<div class="project-info">
						<h4><strong>${project.titulo}</strong> (${project.project_code})<br/>
						<em><span class="${project.project_code}-project-date" style="font-size: small;"></span></em></h4>
						<script type="text/javascript">
							function pad(d) {
							    return (d < 10) ? '0' + d.toString() : d.toString();
							}
							var finalDate = new Date(moment("${project.fechaFinal}", "DD-MM-YYYY"));
							var initialDate = new Date(moment("${project.fechaInicio}", "DD-MM-YYYY"));
							var to = pad(finalDate.getDate()) + '-' + pad(finalDate.getMonth() + 1) + '-' + finalDate.getFullYear();
							var from = pad(initialDate.getDate()) + '-' + pad(initialDate.getMonth() + 1) + '-' + initialDate.getFullYear();
							$(".${project.project_code}-project-date").text( "From " + from + " to " + to );  
						</script>
						<p class="description">${project.descripcion}</p>
						<!-- Project Code Form -->
						<form id="project-code-${project.project_code}" class="hidden" method="post" target="_blank" action="KanbanServlet">
		     				<input type="hidden" name="project_code" value="${project.project_code}" />
						</form>
						<div class="project-actions">
							<div class="btn-group" role="group" aria-label="...">
								<c:if test="${role == 0 || role == 1}">
									<button type="button" class="btn btn-default disabled">Generar informe</button>
								</c:if>
								<c:if test="${role == 0 || role == 1}">
									<button type="button" class="btn btn-default disabled hidden-sm">Ver estadísticas</button>
								</c:if>
								<button type="button" class="btn btn-default disabled">Entregables</button>
								<c:if test="${role == 0 || role == 1}">
									<button type="button" class="btn btn-default disabled hidden-sm">Editar proyecto</button>
								</c:if>
								<button type="submit" form="project-code-${project.project_code}" class="btn btn-default">Ver proyecto</button>
							</div>
						</div>
					</div>
					<div class="${project.project_code} chart-info">
						<span>${project.percentage * 100}%</span> of 100
					</div>
					<div id="${project.project_code}" style="width: 150px; height: 150px;"></div>
					<script type="text/javascript">
						google.charts.load("current", {packages:["corechart"]});
						google.charts.setOnLoadCallback(function() {
							var color = getRandomColor(${project.project_code});
							var data = google.visualization.arrayToDataTable([
							    ['Completed', 	'Percentage'],
							    ['Completed', 	100 * ${project.percentage}],
							    ['Uncompleted', 100 - 100 * ${project.percentage}]
						  	]);
						
						  	var options = {
						    	pieHole: 0.5,
						    	legend: 'none',
						    	pieSliceText: 'none',
						    	enableInteractivity: false,
						    	chartArea: {left:0,top:0,width:'100%',height:'100%'},
						    	width: 150,
						    	height: 150,
						    	colors:[color,lightColor(color, 0.5)],
						  	};
					
						  	var chart = new google.visualization.PieChart(document.getElementById('${project.project_code}'));
						  	$('.${project.project_code}.chart-info span').css('color', color).text(Math.round(${project.percentage} * 1000) / 10 + '%');
						  	chart.draw(data, options);
						});
			    	</script>
				</div>
			</div>
			
		</c:forEach>
		
	</c:when>    
    <c:otherwise>
        
        <div class="no-current-projects">No current projects</div>
        
    </c:otherwise>
</c:choose>

<!-- Add project button and modal -->
<div class="add-project">
	<span class="glyphicon glyphicon-plus"></span>
</div>

<script type="text/javascript">
	$('.add-project').on('click', function() {
		$('#search-project').modal('show')
	});
</script>

<div class="modal fade" id="search-project" tabindex="-1" role="dialog" aria-labelledby="search-project">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Join to a new project</h4>
      </div>
      <div class="modal-body">
      	<p>Introduzca el código del proyecto para unirse a él.</p>
        <input class="form-control" name="project-code" id="project-code" placeholder="Código del proyecto" type="text">
      </div>
      <div class="modal-body modal-body-response">
      		<div class="error-response"></div>
      		<div class="success-response">
      			<div class="title-response"></div>
      			<div class="description-response"></div>
      		</div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	$('#search-project').on('hidden.bs.modal', function (e) {
		$('#search-project #project-code').val('');
        $('#search-project .error-response').empty();
        $('#search-project .success-response .title-response').empty();
        $('#search-project .success-response .description-response').empty();
		
	});
	
	var timer_code = null;
	$('#search-project #project-code').on('keyup', function(event) {
		clearTimeout(timer_code);
		if (event.keyCode === 13)
			searchProject($('#search-project #project-code').val());
		timer_code = setTimeout(function() {
            searchProject($('#search-project #project-code').val());
        }, 500);
	});
	
	function searchProject(code) {
		$.ajax({
		    url: 'SearchProjectServlet',
		    data: {
		    	project_code: code,
		    },
		    method: 'POST',
		    success: function(response) {
		        var res = response.split('\\&\\');
		        $('#search-project .error-response').empty();
		        $('#search-project .success-response .title-response').text(res[0]);
		        $('#search-project .success-response .description-response').text(res[1]);
		        var form = $('<form>').attr('action', 'JoinProjectServlet').attr('method', 'post').attr('role', 'form');
		        $('<input>').attr('type', 'hidden').attr('value', code).attr('name', 'project_code').appendTo(form);
		        if (res[2] == "true")
		        	$('<button>').addClass('btn btn-primary btn-block disabled').css('margin-top', '15px')
   			 	 				 .attr('disabled', 'disabled').text('Already joined').appendTo(form);
		        else
		        	$('<button>').addClass('btn btn-primary btn-block').css('margin-top', '15px')
		        			 	 .attr('type', 'submit').text('Join').appendTo(form);
		        form.appendTo($('#search-project .success-response .description-response'))
		    },
		    error: function(error) {
		        console.error(error);
		        $('#search-project .success-response .title-response').empty();
		        $('#search-project .success-response .description-response').empty();
		        if (error.status == 400 || error.status == 404)
		        	$('#search-project .error-response').text('Error: ' + error.responseText);
		        else
		        	$('#search-project .error-response').text('Error: Something went wrong! Sorry for the inconvenience');
		    }
		});

	}
</script>





