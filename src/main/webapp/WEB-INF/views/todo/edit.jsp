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

<script type="text/javascript">

function toFileUpload(formName, url, method, model)
 {
   var f = document.forms[formName];
   f.method = method;
   f.action = url;
   f.modelAttribute = model;
   f.submit();
   return true;
 }
</script>

<body>
	<h1>Todo Detail</h1>
	
	<!-- ファイル名表示 -->
	<c:choose>
		<c:when test="${todo.fileNm != null}">
			<c:set var="linkNm" value="${f:h(todo.fileNm)}" />
		</c:when>
		<c:otherwise>
			<c:set var="linkNm" value="File Upload" />
		</c:otherwise>
	</c:choose>

		<form:form name="editForm" 
			action="${pageContext.request.contextPath}/todo/edit"
			method="post"
			modelAttribute="todoForm">
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
							    <form:options items="${CL_PRIORITY}" />
							</form:select>
						</td></tr>
					<!-- remarks -->
					<tr><td>
							Remarks:
						</td><td>
							<form:input path="cmt" value="${f:h(todo.cmt)}"/>
						</td></tr>
					<tr><td>
							file:
						</td><td>
							<a href="#" onClick="return toFileUpload('detailForm',
							'${pageContext.request.contextPath}/todo/fileUpload',
							'GET')">${linkNm}</a>
						</td></tr>
				</table>
				<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
			</div>
		</form:form>

		<div class="container">
			<div style="float:left">
				<form:form name="detailForm" 
					action="${pageContext.request.contextPath}/todo/detail"
					method="get" 
					modelAttribute="todoForm">
					<form:hidden path="todoId" value="${f:h(todo.todoId)}" />
				<div>
					<input type="submit" value="Back" class="btn btn-success" />
				</div>
				</form:form>
			</div>
			<div style="float:left;margin-left:1%">
				<input type="button" value="Save" class="btn btn-success" 
				onClick="return toFileUpload('editForm',
							'${pageContext.request.contextPath}/todo/edit',
							'POST', 'todoForm')"/>
				
			</div>
		</div>
</body>
</html>