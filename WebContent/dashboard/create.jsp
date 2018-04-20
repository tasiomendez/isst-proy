
<div class="col-sm-8 col-sm-offset-2 wrapper">
	<div class="col-sm-10 col-sm-offset-1">
	
		<form class="form-signin" action="CreateServlet" method="post" data-toggle="validator" role="form">
		
			<div class="form-group">
			    <label for="title">Titulo</label>
				<input type="text" class="form-control" name="title" id="title" placeholder="Título del proyecto" required>
			</div>
			
			<div class="form-group">
			    <label for="description">Descripción</label>
				<textarea rows="8" cols="50" class="form-control" name="description" id="description" placeholder="Descripción del proyecto"></textarea>
			</div>
			
			<div class="form-group col-sm-12" style="padding: 0;">
				<label for="initialDate">Fecha Inicial</label>
		        <div class='input-group date' id='initialDate'>
		            <input type='text' name="initialDate" class="form-control" placeholder="Fecha de inicio" onclick="$('#initialDate').datetimepicker('show');" required/>
		            <span class="input-group-addon">
		                <span class="glyphicon glyphicon-calendar"></span>
		            </span>
		        </div>
		    </div>
		    
		    <div class="form-group col-sm-12" style="padding: 0;">
		    	<label for="finalDate">Fecha Final</label>
			    <div class='input-group date' id='finalDate'>
			        <input type='text' name="finalDate" class="form-control" placeholder="Fecha de fin" onclick="$('#finalDate').datetimepicker('show');" required/>
			        <span class="input-group-addon">
			            <span class="glyphicon glyphicon-calendar"></span>
			        </span>
			    </div>
		    </div>
		    
			<script type="text/javascript">
			    $(function () {
			        $('#initialDate').datetimepicker({
			        	viewMode: 'years',
			        	format: 'DD/MM/YYYY'
			        });
			        $('#finalDate').datetimepicker({
			        	viewMode: 'years',
			        	format: 'DD/MM/YYYY',
			            useCurrent: false //Important! See issue #1075
			        });
			        $("#initialDate").on("dp.change", function (e) {
			        	$('#finalDate').data("DateTimePicker").minDate(e.date);
			        });
			        $("#finalDate").on("dp.change", function (e) {
			        	$('#initialDate').data("DateTimePicker").maxDate(e.date);
			        });
			    });
			</script>
			
			<button class="btn btn-lg btn-primary btn-block disabled" type="submit">Create</button>
		
		</form>
	</div>
</div>