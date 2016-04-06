<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo List</title>
<script type="text/javascript">
 function toDetail(formName, url, method)
 {
   var f = document.forms[formName];
   f.method = method;
   f.action = url; 
   f.submit();
   return true;
 }
 </script>
<style type="text/css">
.strike {
text-decoration: line-through;
}

.alert {
border: 1px solid;
}

.alert-error {
background-color: #c60f13;
border-color: #970b0e;
color: white;
}

.alert-success {
background-color: #5da423;
border-color: #457a1a;
color: white;
}

.text-error {
color: #c60f13;
}
</style>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap-3.3.1-dist/css/bootstrap.css"
    type="text/css" media="screen, projection">
</head>

<body>
	<h1>Todo List</h1>
<div id="todoForm">
	<t:messagesPanel />
<form:form
	action="${pageContext.request.contextPath}/todo/create"
	method="post" modelAttribute="todoForm">
	<form:errors path="todoTitle" cssClass="text-error"/>
	<div class="container">
		<table class="table table-striped table-bordered">
			<!-- Category -->
			<tr><td>
					Category: 
				</td><td>
					<form:select path="todoCategory">
					    <form:option value="" label="--Select--" />
					    <form:options items="${CL_CATEGORY}" />
					</form:select>
				</td>
			<!-- Task Name -->
			<tr><td>
					Task:
				</td><td>
					<form:input path="todoTitle" />
				</td></tr>
			<!-- person in charge -->
			<tr><td>
					First Name:
				</td><td>
					<form:input path="firstName" />
				</td></tr>
			<tr><td>
					Last Name:
				</td><td>
					<form:input path="lastName" />
				</td></tr>
			<!-- priority -->
			<tr><td>		
					Priority:
				</td><td>
					<form:select path="todoPriority">
					    <form:option value="" label="--Select--" />
					    <form:options items="${CL_PRIORITY}" />
					</form:select>
				</td></tr>
			<!-- remarks -->
			<tr><td>
					Remarks:
				</td><td>
					<form:textarea path="cmt" />
				</td></tr>
		</table>
	</div><br/>
	<div style="text-align:right">
		<input type="submit" value="Create Todo" class="btn btn-success" />
	</div>
</form:form>
</div>
<hr />
<div id="todoList">
<!-- 0件の場合、ヘッダ行から表示しない -->
<c:choose>
<c:when test="${todos != null && todos.totalPages != 0}">
	<div class="container" style="">
		<table class="table table-condensed table-striped tables-hover-rows">
			<thead>
				<tr>
					<td>Todo Title</td>
					<td>Category</td>
					<td>Priority</td>
					<td>Onwer</td>
					<td>Finished</td>
					<td>Deleted</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos.content}" var="todo">
				<tr>
					<td>
						<c:choose>
							<c:when test="${todo.finished}">
								<span class="strike">
									${f:h(todo.todoTitle)}
								</span>
							</c:when>
							<c:otherwise>
								<form:form name="${f:h(todo.todoId)}" 
									action="${pageContext.request.contextPath}/todo/detail"
									method="get"
									modelAttribute="todoForm">
									<input type="hidden" name="todoId" value="${f:h(todo.todoId)}" />
									<input type="hidden" name="page" value="${todos.number}" />
									<input type="hidden" name="size" value="${todos.size}" />
									<a href="#" onClick="return toDetail('${f:h(todo.todoId)}','${pageContext.request.contextPath}/todo/detail','GET')">${f:h(todo.todoTitle)}</a>
								</form:form>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						${f:h(todo.todoCategoryName)}
					</td>
					<td>
						${f:h(todo.todoPriority)}
					</td>
					<td>
						${f:h(todo.fullName)}
					</td>
					<td>
						<c:choose>
							<c:when test="${todo.finished}">
							</c:when>
							<c:otherwise>
								<form:form
									action="${pageContext.request.contextPath}/todo/finish"
									method="post"
									modelAttribute="todoForm"
									cssStyle="display: inline-block;">
									<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
									<input type="submit" name="finish" value="Finish" />
								</form:form>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<form:form action="${pageContext.request.contextPath}/todo/delete"
							method="post" modelAttribute="todoForm" cssStyle="display: inline-block;">
							<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
							<input type="submit" value="Delete" />
						</form:form>
					</td>
				<tr>
				</c:forEach>	
			</tbody>
		</table>
	</div>
	<!-- ページネーション　リンク -->
	<div style="text-align:center">
		<t:pagination page="${todos}" outerElementClass="pagination" />
	</div>
</c:when>
</c:choose>
</div>
</body>
</html>