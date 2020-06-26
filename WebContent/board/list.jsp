<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//데이터를 나누어서 보여주는 기법을 페이징 처리라 하며,
	//이 처리에 필요한 로직!!
	int currentPage=1; //현재 페이지
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	int totalRecord = 26; //총 레코드 수
	int pageSize = 10; //한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize); //총 페이지 수
	int blockSize=10; //한 블럭당 보여질 페이지 수
	int firstPage=currentPage-(currentPage-1)%blockSize; //블럭의 시작 페이지
	int lastPage=firstPage+(blockSize-1); //각 블럭의 마지막 페이지
	int num=totalRecord-(currentPage-1)*pageSize; //페이지당 시작 번호!!
%>
<%= "totalPage: "+(int)Math.ceil((float)totalRecord/pageSize)+"<br>" %>
<%out.print("현재"+currentPage+"페이지입니다"); %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
a{
	text-decoration:none;
}
.pageNum{
	font-weight: bold;
	color: #c00;
}
</style>
</head>
<body>

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%for(int i=1; i<=pageSize; i++){ %>
  <tr>
  	<%if(num<1) break; %>
    <td><%=num-- %></td>
    <td>제목입니다</td>
    <td>홍길동</td>
    <td>2020-08-14</td>
    <td>456</td>
  </tr>
  <%}%>
  <tr>
  	<td colspan="5" align ="center" style="text-align:center">
  		
  		<%if(firstPage-1>1){%>
  		<a href="/board/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
  		<%}else{ %>
  		<a href="javascript:alert('이전 페이지가 없습니다')">◀</a>
  		<%} %>
  		<%for(int i=firstPage; i<=lastPage; i++){ %>
  		<%if(i>totalPage) break; %>
  		<a <%if(i==currentPage){ %>class="pageNum"<%}%>href="/board/list.jsp?currentPage=<%=i %>">[<%=i %>] </a>
  		<%} %>
  		<%if(lastPage+1>=totalPage){ %>
  		<a href="javascript:alert('마지막 페이지입니다')">▶</a>
  		<%}else{ %>
  		<a href="/board/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
  		<%} %>
  	</td>
  	
  </tr>
</table>

</body>
</html>
