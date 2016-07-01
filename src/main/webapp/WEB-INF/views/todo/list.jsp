<%@taglib prefix="botDetect" uri="botDetect"%>
<%@ taglib uri="http://localhost:8080/tags/todo5" prefix="td5"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Ajax用 -->
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<!-- Ajax用 -->

<title>Todo List</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui-1.11.4.custom\jquery-ui.js"></script>
<script type="text/javascript">
var contextPath = $("meta[name='contextPath']").attr("content");

function toDetail(formName, url, method)
 {
   var f = document.forms[formName];
   f.method = method;
   f.action = url; 
   f.submit();
   return true;
 }
 
function finishTodo() {
    $.ajax(contextPath + "/todo/finish", {
        type : "GET",
        data : $("#finishForm").serialize(),
        dataType : "json", 
        
    }).done(function(json) {
		var rmTitle;
		var mkStrike;
		var isFinished;
		var rmFinishButton;
		
		isFinished = json.finished;
		if(isFinished == true){
			//対象タスク削除
			rmTitle = document.getElementById(json.todoId);
			var rmTitle_parent = rmTitle.parentNode;
			rmTitle_parent.removeChild(rmTitle);
			
			//innerHTMLで新たに作成
			var element = document.createElement('span'); 
			element.innerHTML = json.todoTitle;
			element.style.textDecoration = "line-through"; 
			
			 var objBody = document.getElementById("td_" + json.todoId);
			 objBody.appendChild(element);
			//Finishボタンを削除
			rmFinishButton = document.getElementById("finish_" + json.todoId);
			var rmFinish_parent = rmFinishButton.parentNode;
			rmFinish_parent.removeChild(rmFinishButton);
			 
		}else{
			return false;
		}
    }).fail(function(xhr) {
    	//何かエラー処理を実装する。一時的にアラート表示するだけ。
    	alert("サーバサイド処理失敗");
    	return false;
    });
    return false;
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
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/jquery-ui-1.11.4.custom\jquery-ui.css"
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
					    <form:option value="" label="" />
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
					<c:set var="codelistAll"><spring:message code="label.codelist.all"/></c:set>
					<form:select path="todoPriority" >
					    <%-- <form:option value="" label="${codelistAll}" /> --%>
					    <form:option value="1"><jsp:attribute name="label"><spring:message code="label.codelist.all"/></jsp:attribute></form:option>
					    <%-- <option value="1" selected ><spring:message code="label.codelist.all"/></option> --%>
					    <form:options items="${CL_PRIORITY}" />
					</form:select>
				</td></tr>
			<!-- 起票日-->
			<tr><td>		
					Create Date:
				</td><td>
					<form:input path="createdAt" />
				</td></tr>
			<!-- 期限 -->
			<tr><td>		
					Expiry Date:
				</td><td>
					<form:input path="expiryDate" />
				</td></tr>
			<!-- remarks -->
			<tr><td>
					Remarks:
				</td><td>
					<form:textarea path="cmt" />
			
				</td></tr>
			<!-- captcha -->
			<tr><td>
					Image Authentication:
				</td><td>
					<botDetect:captcha id="basicExampleCaptcha"/>
					<div class="validationDiv">
				    	<input id="captchaCodeTextBox" type="text"
				           name="captchaCodeTextBox" value=""/>
					</div>
				</td></tr>
		</table>
	</div><br/>
	
	<div style="text-align:right;margin-right:10%">
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
					<td id="td_${f:h(todo.todoId)}">
						<c:choose>
							<c:when test="${todo.finished}">
								<span class="strike">
									${f:h(todo.todoTitle)}
								</span>
							</c:when>
							<c:otherwise>
								<div id = "${f:h(todo.todoId)}">
									<form:form name="${f:h(todo.todoId)}" 
										action="${pageContext.request.contextPath}/todo/detail"
										method="get"
										modelAttribute="todoForm">
										<input type="hidden" name="todoId" value="${f:h(todo.todoId)}" />
										<input type="hidden" name="page" value="${todos.number}" />
										<input type="hidden" name="size" value="${todos.size}" />
										<a href="#" onClick="return toDetail('${f:h(todo.todoId)}','${pageContext.request.contextPath}/todo/detail','GET')">${f:h(todo.todoTitle)}</a>
									</form:form>
								</div>
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
						<c:choose>
							<c:when test="${todo.finished}">
							</c:when>
							<c:otherwise>
								<div id = "finish_${f:h(todo.todoId)}" >
								<form id="finishForm">
									<input type="hidden" name="todoId" value="${f:h(todo.todoId)}" />
									<input type="hidden" name="todoTitle" value="${f:h(todo.todoTitle)}" />
									<button onclick="return finishTodo()">Finish</button>
								</form>
								</div>
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