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
        	<li><a href="${pageContext.request.contextPath}/todo/list">Todo List</a></li>
        </ul>
    </div>
</body>
</html>