<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
	position: relative;
	left:0px;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
.pageStyle{
	font-size:15pt;
	font-weight:bold;
	color:blue;
}
a{text-decoration:none;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

</script>
<title>Insert title here</title>
<script>
</script>
</head>
<body>
	<h1>상담게시판</h1>
	<table>
		<tr>
			<th width="5%">no</th>
			<th width="50%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">등록일</th>
			<th width="10%">조회수</th>
		</tr>
		<%for(int i=1; i<=10; i++){ %>
		<tr>
			<td>ㅇㅇ</td>
			<td>ㅇㅇ</td>
			<td>ㅇㅇ</td>
			<td>ㅇㅇ</td>
			<td>ㅇㅇ</td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5">
				<button>글등록</button>
			</td>
		</tr>
		<tr>
			<td colspan="5" style="text-align:center;">
				<a href="/reboard/list.jsp">◀</a>
				<%for(int i=1; i<=10; i++){ %>
				
				<a <%if(i==1){%>class="pageStyle"<%}%> href="/reboard/list.jsp?currentPage=<%=i %>">[<%=i %>]</a>
				<%} %>
				<a href="/reboard/list.jsp">▶</a>
			</td>
		</tr>
	</table>
</body>
</html>