<%@page import="com.study.model.reboard.ReBoardDAO"%>
<%@page import="com.study.model.reboard.ReBoard"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! ReBoardDAO reboardDAO = new ReBoardDAO();%>
<%
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	int reboard_id = Integer.parseInt(request.getParameter("reboard_id"));
	
	ReBoard reboard = new ReBoard();
	
	reboard.setTitle(title);
	reboard.setWriter(writer);
	reboard.setContent(content);
	reboard.setReboard_id(reboard_id);
	
	int result = reboardDAO.delete(reboard);
	if(result!=0){
		out.print("<script>");
		out.print("location.href='/reboard/content.jsp';");
		out.print("</script>");
	}else{
		out.print("<script>");	
		out.print("alert('삭제 실패');");
		out.print("history.back();");
		out.print("</script>");
	}
	
	
%>