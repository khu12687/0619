<%@page import="com.study.model.news.News"%>
<%@page import="com.study.model.news.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); %>
<%
	//news_id 파라미터를 넘겨받아, 상세보기 메서드 호출!!
	String news_id =request.getParameter("news_id");
	News news = newsDAO.select(Integer.parseInt(news_id));
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
<script>
$(function(){
	CKEDITOR.replace("content");
	//등록버튼
	$($("input[type='button']")[0]).click(function(){
		//글 등록을 서버에 요청한다!!(jsp or servlet)
		alert("등록?");
		$("form").attr({
			"action":"/comments/regist.jsp",
			"method":"post"
		});
		$("form").submit();
	});
	//목록버튼
		$($("input[type='button']")[1]).click(function(){
		alert("목록?");
		location.href="/comments/list.jsp";
	});
});

</script>
</head>
<body>

	<h3>상세보기</h3>

	<div class="container">
		<form>
			 <input type="text" id="fname"name="title" value="<%=news.getTitle()%>"> 
			 <input type="text" id="fname"name="writer" value="<%=news.getWriter()%>"> 
			<textarea id="content" name="content" style="height: 200px"><%=news.getContent()%></textarea>
			<input type="button" value="등록">
			<input type="button" value="목록">
		</form>
	</div>

</body>
</html>
