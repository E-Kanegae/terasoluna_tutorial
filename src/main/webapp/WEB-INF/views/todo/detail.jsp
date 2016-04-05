<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo List</title>
<style type="text/css"></style>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap-3.3.1-dist/css/bootstrap.css"
    type="text/css" media="screen, projection">
</head>

<body>
	<h1>Todo Detail</h1>
		<div class="container">
			<table class="table table-striped table-bordered">
				<tr><td>
						Category: 
					</td><td>
						${f:h(todo.todoCategoryName)}
					</td>
				<!-- Task Name -->
				<tr><td>
						Task:
					</td><td>
						${f:h(todo.todoTitle)}
					</td></tr>
				<!-- person in charge -->
				<tr><td>
						Owner:
					</td><td>
						${f:h(todo.fullName)}
					</td></tr>
				<!-- priority -->
				<tr><td>		
						Priority:
					</td><td>
						${f:h(todo.todoPriority)}
					</td></tr>
				<!-- Created Date -->
				<tr><td>		
						Created Date:
					</td><td>
						${f:h(todo.createdAt)}
					</td></tr>
				<!-- remarks -->
				<tr><td>
						Remarks:
					</td><td>
						${f:h(todo.cmt)}
					</td></tr>
			</table>
		</div>
		<!-- SpringFrameworkのセッションスコープオブジェクトの取得 -->
		<spring:eval var="pageNumber" expression="@sessionPageObj.page" /> 
		<spring:eval var="pageSize" expression="@sessionPageObj.size" />
		
		<div style="text-align:center">
			<form:form 
				action="${pageContext.request.contextPath}/todo/list"
				method="get">
				<input type="hidden" name="page" value="${pageNumber}" />
				<input type="hidden" name="size" value="${pageSize}" />
				<input type="submit" value="Back" class="btn btn-success" />
			</form:form>
		</div>
		<div style="float:left">
			
		</div>
</body>
</html>