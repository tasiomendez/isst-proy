
<div id="title" class="menu-title">
	<span class="menu-item">PROY</span>
</div>

<div id="projects" class="item">
	<span class="glyphicon glyphicon-th-large"></span>
	<span class="menu-item">Proyectos</span>
</div>

<c:if test="${role == 0 || role == 1}">
<div id="create" class="item">
	<span class="glyphicon glyphicon-plus"></span>
	<span class="menu-item">Crear</span>
</div>
</c:if>

<c:if test="${role == 0 || role == 2}">
<div id="analysis" class="item">
	<span class="glyphicon glyphicon-stats"></span>
	<span class="menu-item">Análisis</span>
</div>
</c:if>
 
<div id="calendar" class="item">
	<span class="glyphicon glyphicon-calendar"></span>
	<span class="menu-item">Calendario</span>
</div>

<c:if test="${role == 0 || role == 1}">
<div id="informs" class="item">
	<span class="glyphicon glyphicon-duplicate"></span>
	<span class="menu-item">Informes</span>
</div> 
</c:if>

<div id="contacts" class="item">
	<span class="glyphicon glyphicon-user"></span>
	<span class="menu-item">Contactos</span>
</div>    

<div id="settings" class="item">
	<span class="glyphicon glyphicon-cog"></span>
	<span class="menu-item">Ajustes</span>
</div> 

<div id="help" class="item">
	<span class="glyphicon glyphicon-question-sign"></span>
	<span class="menu-item">Ayuda</span>
</div> 

<script type="text/javascript">
	$('.item').on('click', function() {
		$('.item').removeClass('active');
		$(this).addClass('active');
	});
	
	$('#show-menu').click(function() {
		if ($('#sidenav').width() < 100) {
			$('.main').css('margin-left', 250);
			$('#sidenav').css('width', 250);
			setTimeout(function() {
				$('.main').on('click', function() {
					$('.main').css('margin-left', 75);
					$('#sidenav').css('width', 75);
					$('.main').off();
				});
			}, 500);
		} else {
			$('.main').css('margin-left', 75);
			$('.main').off();
			$('#sidenav').css('width', 75);
		}
	});
</script>






