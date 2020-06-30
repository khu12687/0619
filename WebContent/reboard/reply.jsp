<%@page import="com.study.model.reboard.MybatisReBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! MybatisReBoardDAO reboardDAO = new MybatisReBoardDAO(); %>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="reboard" class="com.study.model.reboard.ReBoard"></jsp:useBean>
<jsp:setProperty property="*" name="reboard"/>
<jsp:setProperty property="team" name="reboard"/>
<jsp:setProperty property="rank" name="reboard"/>
<jsp:setProperty property="depth" name="reboard"/>

<%
	//답변 들어갈 자리만들기
	reboardDAO.updateRank(reboard);

	//답변 등록(답변이 들어갈 자리 확보!! + 답변 등록)
	reboardDAO.reply(reboard);
	
	//추후 응답이 완료된 후에, 클라이언트의 브라우저로 하여금 다시 재접속
	//하라는 명령임..
	response.sendRedirect("/reboard/list.jsp");
%>
