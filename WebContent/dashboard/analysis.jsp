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
		$.ajax({
		    url: 'SearchProjectServlet',
		    data: {
		    	project_code: code,
		    },
		    method: 'POST',
		    success: function(response) {
				$(this).addClass('active');		
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
		        console.eror(error);
		        background-color: #f5c5c5;
		    }
		});
	})
</script>
