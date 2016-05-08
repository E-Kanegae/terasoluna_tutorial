<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Todo Search</title>

<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/bootstrap/honoka/css/bootstrap.css"
    type="text/css" media="screen, projection">

<body>
	<h2>Todo Search</h2>
<div id="searchForm">
	<t:messagesPanel />
<form:form
	action="${pageContext.request.contextPath}/manage/search"
	method="get" modelAttribute="todoManageForm">

	<div class="container">
		<table class="table table-condensed">
			<!-- Category -->
			<tr class="success">
				<td>
					Category: 
				</td><td>
					<form:select path="todoCategory">
					    <form:option value="" label="--Select--" />
					    <form:options items="${CL_CATEGORY}" />
					</form:select>
				</td>
			<!-- Task Name -->
				<td>
					Task:
				</td><td>
					<form:input path="todoTitle" />
				</td>
			<!-- priority -->
				<td>		
					Priority:
				</td><td>
					<form:select path="todoPriority">
					    <form:option value="" label="--Select--" />
					    <form:options items="${CL_PRIORITY}" />
					</form:select>
				</td></tr>
			<!-- person in charge -->
			<tr class="danger"><td>
					First Name:
				</td><td>
					<form:input path="firstName" />
				</td><td>
					Last Name:
				</td><td colspan="3">
					<form:input path="lastName" />
				</td></tr>
			<!-- 起票日-->
			<tr class="info"><td>		
					Create Date:
				</td><td>
					<form:input path="createDate" />
				</td>	
			<!-- 期限 -->
				<td>		
					Expiry Date:
				</td><td colspan="3">
					<form:input path="expiryDate" />
				</td></tr>
		</table>
	</div>
	
	<div style="text-align:right;margin-right:10%">
		<input type="submit" value="Search" class="btn btn-info active" />
	</div>
</form:form>
</div>

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
					<td></td>
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
								${f:h(todo.todoTitle)}
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						${f:h(todo.todoCategoryName)}
					</td>
					<td>
						${f:h(todo.todoPriorityName)}
					</td>
					<td>
						${f:h(todo.fullName)}
					</td>
					<td>
						<form:form action="${pageContext.request.contextPath}/todo/editPage"
							method="post" modelAttribute="todoForm" cssStyle="display: inline-block;">
							<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
							<input type="submit" value="Edit" />
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
	
	<div class="container">
		<div style="float:left;">
			<form:form  action="${pageContext.request.contextPath}/manage/mail" method="get" >
				<input type="submit" value="Mail" class="btn btn-warning active" />
			</form:form>
		</div>
		<div style="float:left;margin-left:1%">
			<input type="submit" value="Download" class="btn btn-success active" />
		</div>
	</div>	
</c:when>
<c:otherwise>
	<div style="text-align:center">
		<p class="bg-info">No result Founds</p>
	</div>
</c:otherwise>
</c:choose>

</body>
</html>