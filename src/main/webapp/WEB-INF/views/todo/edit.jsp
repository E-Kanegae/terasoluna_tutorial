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
			<form:form name="editForm" 
				action="${pageContext.request.contextPath}/todo/edit"
				method="post"
				modelAttribute="todoForm">
				<table class="table table-striped table-bordered">
					<tr><td>
							Category: 
						</td><td>
							<form:select path="todoCategory">
							    <form:option value="${f:h(todo.todoCategoryName)}" />
							    <form:options items="${CL_CATEGORY}" />
							</form:select>
						</td>
					<!-- Task Name -->
					<tr><td>
							Task:
						</td><td>
							<form:input path="todoTitle" value="${f:h(todo.todoTitle)}"/>
						</td></tr>
					<!-- person in charge -->
					<tr><td>
							First Name:
						</td><td>
							<form:input path="firstName" value="${f:h(todo.firstName)}"/>
						</td></tr>
						<tr><td>
							Last Name:
						</td><td>
							<form:input path="lastName" value="${f:h(todo.lastName)}"/>
						</td></tr>
					<!-- priority -->
					<tr><td>		
							Priority:
						</td><td>
							<form:select path="todoPriority">
							    <form:option value="${f:h(todo.todoPriority)}" />
							    <form:options items="${CL_PRIORITY}" />
							</form:select>
						</td></tr>
					<!-- remarks -->
					<tr><td>
							Remarks:
						</td><td>
							<form:input path="cmt" value="${f:h(todo.cmt)}"/>
						</td></tr>
				</table>
				<br/>
				<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
				<div style="text-align:right">
					<input type="submit" value="Save" class="btn btn-success" />
				</div>
			</form:form>
			<div style="float:left">
				<form:form name="detailForm" 
					action="${pageContext.request.contextPath}/todo/detail"
					method="get" 
					modelAttribute="todoForm">>
					<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
				<div>
					<input type="submit" value="Back" class="btn btn-success" />
				</div>
				</form:form>
			</div>
		</div>