<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<!-- let's have bootstrap styling -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
<c:set var="pageTitle" value="Login" />
<title>${pageTitle}</title>
</head>
<body onload='document.loginForm.username.focus();'>

	<div class="container">
		<div class="page-header">
			<h1>${pageTitle}</h1>
		</div>

		<!-- Migrating Spring Security 3.x to 4.x:
			The form-login@login-processing-url attribute default value changed from /j_spring_security_check 
			to POST /login. If an application explicitly provides the attribute, no action is required for the migration. -->
		<form name='loginForm' action="<c:url value='login' />" method='POST' class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">User</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="username" />
					<p class="help-block">input "user" to continue..</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password" />
					<p class="help-block">input "password" to continue..</p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button name="submit" type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Login
					</button>
					<p class="help-block">come one, please input correct account, validation or error message is not implemented..</p>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>