<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>

<!-- JSPに以下の記述をすることでオブジェクトにアクセスできる意味がわからない。-->
<sec:authentication property="principal.userAccount" var="userAccount" />
 
<body>
    <div id="wrapper">
        <h1>Hello world!</h1>
        <p>The time on the server is ${serverTime}.</p>
      	<p>Welcome ${f:h(userAccount.firstName)} ${f:h(userAccount.lastName)} !!</p>
      	<p>
	        <form:form action="${pageContext.request.contextPath}/logout">
	        	<button type="submit">Logout</button>
	        </form:form>
        </p>
        <ul>
        	<li><a href="${pageContext.request.contextPath}/accountInfoView">view account</a></li>
         <li><a href="${pageContext.request.contextPath}/dynamic/init">valification of dynamic</a></li>
        </ul>
    </div>
</body>
</html>
