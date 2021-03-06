<%@ page contentType="text/html; charset=UTF-8"%>
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

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
<script>
$(function() {
	CKEDITOR.replace( 'content' );	
});

//파일을 포함한 대량의 데이터를 웹서버 요청시 가져가려면?
//http의 전송방식 중 post를 이용해야한다!!
//전송하는 데이터 중 파일이 포함되어 있을경우(바이넌리 파일)
//form속성에 반드시!!!! multipart/form-data 지정되어 있어야 함
function regist(){
	$("form").attr({
		"method":"post",
		"enctype":"multipart/form-data",
		"action":"/gallery/upload.jsp"
	});
	$("form").submit();
}
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form>
			<input type="text"name="title"placeholder="title"> 
			<input type="text" name="writer" placeholder="Your name..">
			<input type="file" name ="imgFile"/>
			<textarea name="content" placeholder="Write something.."style="height: 200px"></textarea>
			<input type ="button" value ="등록" onClick="regist();"/>
		</form>
	</div>

</body>
</html>
