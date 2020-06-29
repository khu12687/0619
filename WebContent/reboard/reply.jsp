<%@page import="com.study.model.reboard.ReBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! ReBoardDAO reboardDAO = new ReBoardDAO(); %>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="reboard" class="com.study.model.reboard.ReBoard"></jsp:useBean>
<jsp:setProperty property="*" name="reboard"/>
<%
	//답변 등록(답변이 들어갈 자리 확보!! + 답변 등록)
	
%>