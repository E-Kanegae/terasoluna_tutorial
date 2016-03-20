<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>

<body>
	<div id="wrapper">
		<h3>Login with Username and Password</h3>
		
		<c:if test="${param.error}">
			<t:messagesPanel messagesType="error" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
		</c:if>
		
		<form:form action="${pageContext.request.contextPath}/login" method="POST">
			<table>
				<tr>
					<td><label for="username">UserId:</label></td>
					<td><input type="text" id="username" name="username" value=""></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><input type="password" id="password" name="password" value="" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input name="submit" type="submit" value="Login" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>