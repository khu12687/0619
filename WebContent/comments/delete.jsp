<%@page import="com.study.model.news.News"%>
<%@page import="com.study.model.news.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO();%>
<%
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	int news_id = Integer.parseInt(request.getParameter("news_id"));
	
	News news = new News();
	
	news.setTitle(title);
	news.setWriter(writer);
	news.setContent(content);
	news.setNews_id(news_id);
	
	int result = newsDAO.delete(news);
	if(result!=0){
		out.print("<script>");
		out.print("location.href='/comments/content.jsp';");
		out.print("</script>");
	}else{
		out.print("<script>");	
		out.print("alert('삭제 실패');");
		out.print("history.back();");
		out.print("</script>");
	}
	
	
%>