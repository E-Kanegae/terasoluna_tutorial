<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap-3.3.1-dist/js/bootstrap.min.js"></script>

<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap-3.3.1-dist/css/bootstrap.css"
    type="text/css" media="screen, projection">
</head>
<body>
    <div id="wrapper">
        <h1>Account Information</h1>
        <table>
            <tr>
                <th>Username</th>                <td>${f:h(userAccount.username)}</td>
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
        	<li>
        	<!-- 普通のモーダル -->
	       		<!-- ボタン -->
				<button type="button" class="btn btn-default" id="staticModalButton" >search画面（モーダル）</button>
				 
			  	<!-- モーダルダイアログ -->
			  　　　<div class="modal" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" 
			  		aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
			    	<div class="modal-dialog" style="width:90%">
				      	<div class="modal-content">
				        	<div class="modal-header">
				          		<button type="button" class="close" data-dismiss="modal">
				            		<span aria-hidden="true">&#215;</span><span class="sr-only">閉じる</span>
				          		</button>
				          		<h4 class="modal-title" id = "staticModalLabel">Todo Search</h4>
				        	</div><!-- /modal-header -->
					        <div class="modal-body">
					          
					          
					          <div id="searchForm">
									<t:messagesPanel />
								<form:form
									action="${pageContext.request.contextPath}/modal/search"
									method="get" modelAttribute="modalForm">
								
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
									<c:otherwise>
										<div style="text-align:center">
											<p class="bg-info">No result Founds</p>
										</div>
									</c:otherwise>         
					          </c:choose>
					          
					        </div>
					        <div class="modal-footer">
					          <button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
					        </div>
				      	</div> <!-- /.modal-content -->
			    	</div> <!-- /.modal-dialog -->
			  	</div> <!-- /.modal -->
			  
			<script>
			  $(function() {
			  	// JavaScript で表示
			    $('#staticModalButton').on('click', function() {
			      $('#staticModal').modal();
			    });
			 	// ダイアログ表示前にJavaScriptで操作する
			    $('#staticModal').on('show.bs.modal', function(event) {
			      var button = $(event.relatedTarget);
			      var recipient = button.data('whatever');
			      var modal = $(this);
			      modal.find('.modal-body .recipient').text(recipient);
			      //modal.find('.modal-body input').val(recipient);
			    });
				// ダイアログ表示直後にフォーカスを設定する
			    $('#staticModal').on('shown.bs.modal', function(event) {
			      $(this).find('.modal-footer .btn-default').focus();
			    });
			  });
			</script>
        	</li>
        	<li>
        		<!-- Remoteモーダル -->
        		<button type="button" class="btn btn-default" id="remoteModalButton">
        		Search画面（リモートモーダル）</button>
        		<!-- リモートモーダルダイアログ の読込先 -->
       		    <div class="modal fade" id="remoteModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" 
       		    aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
			      <div class="modal-dialog">
			        <div class="modal-content">
			        </div> <!-- /.modal-content -->
			      </div> <!-- /.modal-dialog -->
			    </div> <!-- /.modal -->
			    
			  <script>
				  $(function() {
				    // JavaScript で表示
				    $('#remoteModalButton').on('click', function() {
				      $('#remoteModal').removeData('bs.modal'); //< 毎回新規に読み込み
				      $('#remoteModal').modal({'remote': 'todo5/account/viewchild.jsp'});
				    });
				  });
				  </script>
        	</li>
        </ul>
    </div>
</body>
</html>