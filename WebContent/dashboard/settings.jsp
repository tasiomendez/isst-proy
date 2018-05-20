
<div class="col-sm-8 col-sm-offset-2 wrapper">
	<div class="col-sm-10 col-sm-offset-1 form-wrapper">
			<div class="name">
				<!-- <span class="username">${user.nombre}</span> --> 
				<c:if test="${user.rol==1 }">
					<span class="userrole">Project Manager</span>
				</c:if>
				<c:if test="${user.rol==2 }">
					<span class="userrole">Human Resources</span>
				</c:if>
				<c:if test="${user.rol==3 }">
					<span class="userrole">Team member</span>
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
			
			<button class="btn btn-lg btn-primary btn-block disabled" type="submit">Update</button>
		
		</form>
	</div>
</div>