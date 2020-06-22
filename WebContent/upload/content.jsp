<%@page import="com.study.model.gboard.Gboard"%>
<%@page import="com.study.model.gboard.GboardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!GboardDAO gboardDAO = new GboardDAO();%>
<%
	int gboard_id = Integer.parseInt(request.getParameter("gboard_id"));
	Gboard gboard = gboardDAO.select(gboard_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
#commentsArea{
	background: yellow;
}
#commentsArea input[name='msg']{
	width:70%;
	float:left;
}
#commentsArea input[name='cwriter']{
	width:10%;
	float:left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
<script>
$(function(){
	CKEDITOR.replace("content");
	//목록버튼
	$($("input[type='button']")[0]).click(function(){
		alert("목록?");
		location.href="/comments/list.jsp";
	});
	//수정버튼
	$($("input[type='button']")[1]).click(function(){
		$($("form")[0]).attr({
			"method":"post",
			"action":"/upload/edit.jsp"
		});
		$($("form")[0]).submit();
	});
	//삭제버튼
	$($("input[type='button']")[2]).click(function(){
		$($("form")[0]).attr({
			"method":"post",
			"action":"/upload/delete.jsp"
		});
		$($("form")[0]).submit();
	});
});

</script>
</head>
<body>

	<h3>상세보기</h3>

	<div class="container">
		<form>
			 <input type="text" id="fname"name="title" value="<%=gboard.getTitle()%>"> 
			 <input type="text" id="fname"name="writer" value="<%=gboard.getWriter()%>"> 
			 <input type ="hidden" name ="gboard_id" value="<%=gboard.getGboard_id() %>"/>
			 
			<textarea id="content" name="content" style="height: 200px"><%=gboard.getContent()%></textarea>
			<input type="button" value="목록">
			<input type="button" value ="수정"/>
			<input type="button" value ="삭제"/>
		</form>
	</div>
</body>
</html>