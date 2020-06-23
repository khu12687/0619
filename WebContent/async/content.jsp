<%@page import="com.study.async.HotPlace"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!HotPlaceDAO hotPlaceDAO = new HotPlaceDAO(); %>
<%
	request.setCharacterEncoding("utf-8"); //파라미터 안깨지게
	String hotplace_id = request.getParameter("hotplace_id");
	
	HotPlace hotPlace = hotPlaceDAO.select(Integer.parseInt(hotplace_id));
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"storeList\":[");
	sb.append("{");
	sb.append("\"hotplace_id\":\""+hotPlace.getHotplace_id()+"\",");
	sb.append("\"name\":\""+hotPlace.getName()+"\",");
	sb.append("\"tel\":\""+hotPlace.getTel()+"\",");
	sb.append("\"addr\":\""+hotPlace.getAddr()+"\",");
	sb.append("\"food\":\""+hotPlace.getFood()+"\"");
	sb.append("}");
	sb.append("]");
	sb.append("}");
	
	out.print(sb.toString());
%>