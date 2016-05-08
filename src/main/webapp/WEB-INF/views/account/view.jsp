<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div id="wrapper">
        <h1>Account Information</h1>
        <table>
            <tr>
                <th>Username</th>
                <td>${f:h(userAccount.username)}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${f:h(userAccount.firstName)}</td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>${f:h(userAccount.lastName)}</td>
            </tr>
        </table>
        <ul>
        	<li><a href="${pageContext.request.contextPath}/manage/init">Todo Management Top</a></li>
        	<li><input type="button"  value="Todo List(onClick)" onClick="location.href='${pageContext.request.contextPath}/todo/list'"></li>
        	<li>
        		<form:form
					action="${pageContext.request.contextPath}/todo/list"
					method="get" modelAttribute="todoForm">
					<input type="submit" value="Todo List(Form)"  />
				</form:form>
        	</li>
        </ul>
    </div>
</body>
</html>