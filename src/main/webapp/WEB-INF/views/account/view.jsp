<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/resources/jquery/jquery-2.2.4.min.js">
</script>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap-3.3.1-dist/js/bootstrap.min.js">
</script>

<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/bootstrap/honoka/css/bootstrap.css"
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
        		<!-- モーダルウィンドウを呼び出すボタン -->
				<button type="button" class="btn btn-default" data-toggle="modal" 
				data-target="#staticModal">search画面（モーダル）</button>
				 
			  <!-- リモートモーダルダイアログ の読込先 -->
			  <div class="modal" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" 
			  aria-hidden="true" data-show="true" data-keyboard="false" data-backdrop="static">
			    <div class="modal-dialog">
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">
			            <span aria-hidden="true">&#215;</span><span class="sr-only">閉じる</span>
			          </button>
			          <h4 class="modal-title">Static Modal タイトル</h4>
			        </div><!-- /modal-header -->
			        <div class="modal-body">
			          <p class="recipient">本文</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
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
					    // ダイアログ表示前にJavaScriptで操作する
					    $('#staticModal').on('show.bs.modal', function(event) {
					      var button = $(event.relatedTarget);
					      var recipient = button.data('whatever');
					      var modal = $(this);
					      modal.find('.modal-body .recipient').text(recipient);
					      //modal.find('.modal-body input').val(recipient);
					    });
					    // ダイアログ表示直後にフォーカスを設定する
					    $('#staticModal').on('shown.bs.modal', function(event) {
					      $(this).find('.modal-footer .btn-default').focus();
					    });
					  });
			</script>
        	</li>
        </ul>
    </div>
</body>
</html>