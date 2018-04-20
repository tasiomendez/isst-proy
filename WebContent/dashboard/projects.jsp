<div class="row header">
	<div class="col-sm-12">
		<h3 class="text-center"><b><em>Current Projects List</em></b></h3>
		<form class="form-filter col-sm-3" role="form">
	    	<div class="input-group">
				<input type="text" class="form-control" placeholder="Introduce palabras clave" aria-describedby="basic-addon2" />
				<span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-search"></span></span>
			</div>
		</form>
	</div>
</div>

<!-- Lista dinámica de projectos -->
<div class="row">
	<div class="col-sm-12 project-item">
		<img class="project-img" src="img/logo.png" />
		<div class="project-info">
			<h4><strong>Título del proyecto</strong></h4>
			<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. </p>
		</div>
		<div class="chart-info">
			<span style="color: #ffb400;">60%</span> of 100
		</div>
		<div id="donutchart1" style="width: 150px; height: 150px;"></div>
		<script type="text/javascript">
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
			  	var data = google.visualization.arrayToDataTable([
				    ['Completed', 	'Percentage'],
				    ['Completed', 	60],
				    ['Uncompleted', 40]
			  	]);
			
			  	var options = {
			    	pieHole: 0.5,
			    	legend: 'none',
			    	pieSliceText: 'none',
			    	enableInteractivity: false,
			    	chartArea: {left:0,top:0,width:'100%',height:'100%'},
			    	width: 150,
			    	colors:['#ffb400','#ffb986'],
			  	};
		
			  	var chart = new google.visualization.PieChart(document.getElementById('donutchart1'));
			  	chart.draw(data, options);
			}
    	</script>
	</div>
	<div class="col-sm-12 project-actions">
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default">Generar informe</button>
			<button type="button" class="btn btn-default">Ver estadísticas</button>
			<button type="button" class="btn btn-default">Entregables</button>
			<button type="button" class="btn btn-default">Editar proyecto</button>
			<button type="button" class="btn btn-default">Ver proyecto</button>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-12 project-item">
		<img class="project-img" src="img/logo.png" />
		<div class="project-info">
			<h4><strong>Título del proyecto</strong></h4>
			<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. </p>
		</div>
		<div class="chart-info">
			<span style="color: #001eff;">30%</span> of 100
		</div>
		<div id="donutchart2" style="width: 150px; height: 150px;"></div>
		<script type="text/javascript">
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
			  	var data = google.visualization.arrayToDataTable([
				    ['Completed', 	'Percentage'],
				    ['Completed', 	30],
				    ['Uncompleted', 70]
			  	]);
			
			  	var options = {
			    	pieHole: 0.5,
			    	legend: 'none',
			    	pieSliceText: 'none',
			    	enableInteractivity: false,
			    	chartArea: {left:0,top:0,width:'100%',height:'100%'},
			    	width: 150,
			    	colors:['#001986','#001eff'],
			  	};
		
			  	var chart = new google.visualization.PieChart(document.getElementById('donutchart2'));
			  	chart.draw(data, options);
			}
    	</script>
	</div>
	<div class="col-sm-12 project-actions">
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default">Generar informe</button>
			<button type="button" class="btn btn-default">Ver estadísticas</button>
			<button type="button" class="btn btn-default">Entregables</button>
			<button type="button" class="btn btn-default">Editar proyecto</button>
			<button type="button" class="btn btn-default">Ver proyecto</button>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-12 project-item">
		<img class="project-img" src="img/logo.png" />
		<div class="project-info">
			<h4><strong>Título del proyecto</strong></h4>
			<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. </p>
		</div>
		<div class="chart-info">
			<span style="color: #ffb400;">50%</span> of 100
		</div>
		<div id="donutchart3" style="width: 150px; height: 150px;"></div>
		<script type="text/javascript">
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
			  	var data = google.visualization.arrayToDataTable([
				    ['Completed', 	'Percentage'],
				    ['Completed', 	50],
				    ['Uncompleted', 50]
			  	]);
			
			  	var options = {
			    	pieHole: 0.5,
			    	legend: 'none',
			    	pieSliceText: 'none',
			    	enableInteractivity: false,
			    	chartArea: {left:0,top:0,width:'100%',height:'100%'},
			    	width: 150,
			    	colors:['#ffb400','#ffb986'],
			  	};
		
			  	var chart = new google.visualization.PieChart(document.getElementById('donutchart3'));
			  	chart.draw(data, options);
			}
    	</script>
	</div>
	<div class="col-sm-12 project-actions">
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default">Generar informe</button>
			<button type="button" class="btn btn-default">Ver estadísticas</button>
			<button type="button" class="btn btn-default">Entregables</button>
			<button type="button" class="btn btn-default">Editar proyecto</button>
			<button type="button" class="btn btn-default">Ver proyecto</button>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-12 project-item">
		<img class="project-img" src="img/logo.png" />
		<div class="project-info">
			<h4><strong>Título del proyecto</strong></h4>
			<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat tincidunt semper. Integer facilisis egestas mauris, imperdiet eleifend felis tristique non. Nullam interdum bibendum tincidunt. Mauris sit amet eros vel sapien convallis ornare. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean euismod eget justo ut tincidunt. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam turpis orci, pulvinar vitae orci sed, pharetra vestibulum mi. Suspendisse potenti. Aenean id felis fermentum, mattis ipsum ut, malesuada ex. Praesent tristique luctus lorem, elementum faucibus orci maximus ut. Cras sit amet accumsan tortor. Quisque non nisi vitae enim congue ullamcorper. </p>
		</div>
		<div class="chart-info">
			<span style="color: #001eff;">90%</span> of 100
		</div>
		<div id="donutchart4" style="width: 150px; height: 150px;"></div>
		<script type="text/javascript">
			google.charts.load("current", {packages:["corechart"]});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
			  	var data = google.visualization.arrayToDataTable([
				    ['Completed', 	'Percentage'],
				    ['Completed', 	90],
				    ['Uncompleted', 10]
			  	]);
			
			  	var options = {
			    	pieHole: 0.5,
			    	legend: 'none',
			    	pieSliceText: 'none',
			    	enableInteractivity: false,
			    	chartArea: {left:0,top:0,width:'100%',height:'100%'},
			    	width: 150,
			    	colors:['#001986','#001eff'],
			  	};
		
			  	var chart = new google.visualization.PieChart(document.getElementById('donutchart4'));
			  	chart.draw(data, options);
			}
    	</script>
	</div>
	<div class="col-sm-12 project-actions">
		<div class="btn-group" role="group" aria-label="...">
			<button type="button" class="btn btn-default">Generar informe</button>
			<button type="button" class="btn btn-default">Ver estadísticas</button>
			<button type="button" class="btn btn-default">Entregables</button>
			<button type="button" class="btn btn-default">Editar proyecto</button>
			<button type="button" class="btn btn-default">Ver proyecto</button>
		</div>
	</div>
</div>
