<%@page import="com.study.model.reboard.ReBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! ReBoardDAO reboardDAO = new ReBoardDAO(); %>
<!-- 아래와 같이 태그이되, 서버에서만 실행될 수 있는 테그를 가리켜 빈즈 태그라한다!! bean태그를 이용하면,
		코드량이 줄일수 있다
 -->
 <!-- 아래의 태그는 ReBoard reboard = new ReBoard() -->
<jsp:useBean id="reboard" class="com.study.model.reboard.ReBoard"></jsp:useBean>
<%request.setCharacterEncoding("utf-8"); %>
<!-- 아래의 태그는 vo를 생성한 후 setter 로 파라미터를 넣는 작업과 같다
		reboard.setReboard_id(rs.getInt("reboard_id"));
		아래의 *가 동작하려면, 반드시 html 파라미터명과 vo의 맴버변수명이 같아야한다!
 -->
<jsp:setProperty property="*" name="reboard"/>
<!-- 아래의 태그는 out.print(reBoard.getTitle()); 동일 -->
<%
	//파라미터를 넘겨받아 오라클에 넣기!
	int result = reboardDAO.insert(reboard);

	System.out.println(result);
	
	//지정한 url 로 재접속을 시도해라!!
	//여기서 실행부가 응답을 하는 것이 아니라, 톰켓에게 준비된 응답객체를 전달하면,
	//톰켓이 이 response객체를 이용하여 응답을 하게 된다..
	response.sendRedirect("/reboard/list.jsp");
	
%>