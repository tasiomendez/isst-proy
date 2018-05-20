
<div class="col-sm-8 col-sm-offset-2 wrapper">
	<div class="col-sm-10 col-sm-offset-1">
			<div class="name">
				${user.nombre} - 
				<c:if test="${user.rol==1 }">
					Project Manager
				</c:if>
				<c:if test="${user.rol==2 }">
					Human Resources
				</c:if>
				<c:if test="${user.rol==3 }">
					Team member
				</c:if>
			</div>
			
	
		<form class="form-signin" action="SettingsServlet" method="post" data-toggle="validator" role="form">
		
			<div class="form-group">
			    <label for="name">Name</label>
				<input type="text" class="form-control" name="name" id="name" value="${user.nombre }" required>
			</div>
			
			<div class="form-group">
			    <label for="email">Email</label>
				<input type="text" class="form-control" name="email" id="email" value="${user.email }" required>
			</div>
			
			<div class="form-group">
			    <label for="password">New Password</label>
				<input type="password" class="form-control" name="password" id="password" placeholder="Type your new password" required>
			</div>
			
			<div class="form-group">
            	<label for="confirmPassword">Confirm password</label>
            	<input type="password" id="confirmPassword" data-match="#password" data-match-error="Passwords don't match." class="form-control" placeholder="Confirm your new password" required>
            	<div class="help-block with-errors"></div>
            </div>
			
			<button class="btn btn-lg btn-primary btn-block disabled" type="submit">Create</button>
		
		</form>
	</div>
</div>