<div id="worker-stats" class="worker-stats" style="display: none;">
	<div class="row">
		<div class="col-sm-4 info">
			<img src="${pageContext.request.contextPath}/img/user.png" alt="profile" />
			<div class="name"></div>
			<div class="email"></div>
		</div>
		<div class="col-sm-8">
			<h4>Lista de proyectos</h4>
			<ul class="projects"></ul>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div id="barChart" style="width: 100%; height: 100%;"></div>
		</div>
		<div class="col-sm-6">
			<div id="pieChart" style="width: 100%; height: 100%;"></div>
			<span id="no-info-pieChart" style="display: none">No tasks completed</span>
		</div>
	</div>
</div>

<div id="worker-stats" class="first-view">
	<p>Select a user</p>
</div>

<div id="workers-list" class="col-sm-3 pull-right">
	<div class="col-sm-12 text-center title">
		<h4 style="font-weight: bolder;">Trabajadores</h4>
		<input placeholder="Buscar trabajador..."/>
	</div>
	<div class="col-sm-12 workers-list">
		<ul>
			<c:forEach items="${trabajador_list}" var="trabajador">
				<a data-name="${trabajador.nombre}" data-email="${trabajador.email}"><li>
					<span class="name">${trabajador.nombre}</span>
					<span class="email">${trabajador.email}</span>
				</li></a>
			</c:forEach>
		</ul>
	</div>
</div>

<script type="text/javascript">
	$('#workers-list .title input').on('keyup', function(event) {
		clearTimeout(timer_code);
		if (event.keyCode === 13)
			searchWorker($('#workers-list .title input').val());
		timer_code = setTimeout(function() {
			searchWorker($('#workers-list .title input').val());
        }, 500);
	});
	
	function searchWorker(name) {
		$('.workers-list ul a').each(function(index) {
			if ($(this).attr('data-name').toLowerCase().includes(name.toLowerCase())
					|| $(this).attr('data-email').toLowerCase().includes(name.toLowerCase())) {
				$(this).show()	
			} else {
				$(this).hide()
			}
		});
	}
	
	$('.workers-list ul a').on('click', function() {
		$('.workers-list ul a').removeClass('active');
		$(this).addClass('active');
		$('#worker-stats.worker-stats').show();
		$('#worker-stats.first-view').hide();
		$.ajax({
		    url: 'AnalysisServlet',
		    data: {
		    	email: $(this).attr('data-email')
		    },
		    method: 'POST',
		    beforeSend: function() {
		    	$('#worker-stats ul.projects').empty();
		    },
		    success: function(response) {
				console.log(response);
				var array = response.split('//&//');
				var info = array[0].split('\\&\\');
				$('.worker-stats .info .name').text(info[0]);
				$('.worker-stats .info .email').text(info[1]);
				
				var projects = array[1].split('\\&\\');
				for (i in projects) {
					var project_info = projects[i].split(',');
					var title = $('<span>').addClass('title-project').text(project_info[0] + " (" + project_info[3] + ")");
					var dates = $('<span>').addClass('dates-project').text("FROM " + project_info[1] + " TO " + project_info[2]);
					var item = $('<li>').append($('<div>').append(title).append(dates));
					$('#worker-stats ul.projects').append(item);
				}
				
				var tasks = array[2].split('\\&\\');
				var tst = [['Project', 'Tasks', { role: 'style' }]];
				for (i in tasks) {
					tst.push([projects[i].split(',')[0], parseInt(tasks[i]), getRandomColor(parseInt(projects[i].split(',')[3]))]);
				}
				// Tasks Chart
				google.charts.load("current", {packages:["corechart"]});
				google.charts.setOnLoadCallback(function() {
					
					var color = getRandomColor(40);
					
					var data = google.visualization.arrayToDataTable(tst);
					
					var options = {
		            	title: 'Total tasks',
				        width: Math.round($('#barChart').width()),
				    	height: Math.round($('#barChart').height()),
				    	legend: { position: "none" },
			        };
			
				  	var chart = new google.visualization.ColumnChart(document.getElementById('barChart'));
				  	chart.draw(data, options);
				});
				
				var performance = array[3].split('\\&\\');
				var total_tasks_done = parseInt(performance[0]) + parseInt(performance[1]);
				// Performance Chart
				google.charts.setOnLoadCallback(function() {
					var data = google.visualization.arrayToDataTable([
					    ['Completed', 	'Percentage'],
					    ['Quick tasks', parseInt(performance[0]) / total_tasks_done],
					    ['Slow tasks', 	parseInt(performance[1]) / total_tasks_done]
				  	]);
				
				  	var options = {
				  		title: 'Performance',
				    	pieHole: 0.3,
				    	legend: {position: 'bottom'},
				    	is3D: true,
				    	width: Math.round($('#pieChart').width()),
				    	height: Math.round($('#pieChart').height()),
				    	colors:['2cc400','f87c7c'],
				    	slices: {  1: {offset: 0.05} },
				  	};
			
				  	var chart = new google.visualization.PieChart(document.getElementById('pieChart'));
				  	chart.draw(data, options);
				});
				
				if (total_tasks_done == 0)
					$('#no-info-pieChart').show();
				else
					$('#no-info-pieChart').hide();
		    },
		    error: function(error) {
		        console.error(error);
		        $(this).css('background-color', '#f5c5c5');
		    }
		});
	})
</script>

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
