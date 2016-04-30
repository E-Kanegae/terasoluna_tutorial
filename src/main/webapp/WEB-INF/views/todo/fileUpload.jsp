<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
<style type="text/css"></style>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap-3.3.1-dist/css/bootstrap.css"
    type="text/css" media="screen, projection">
</head>

<body>
	<h1>File Upload</h1>

	<form:form
	  action="${pageContext.request.contextPath}/todo/uploadFiles" method="post"
	  modelAttribute="multiFileUploadForm" enctype="multipart/form-data">
	  <table>
	    <tr>
	      <th width="35%">File to upload</th>
	      <td width="65%">
	        <form:input type="file" path="fileUploadForms[0].file" />
	        <form:hidden path="fileUploadForms[0].todoId" value="${f:h(todoId)}"/>
	        <form:errors path="fileUploadForms[0].file" />
	      </td>
	    </tr>
	    <tr>
	      <th width="35%">Description</th>
	      <td width="65%">
	        <form:input path="fileUploadForms[0].description" />
	        <form:errors  path="fileUploadForms[0].description" />
	      </td>
	    </tr>
	  </table>
	  <div>
	    <form:button>Upload</form:button>
	  </div>
	</form:form>
</body>
</html>