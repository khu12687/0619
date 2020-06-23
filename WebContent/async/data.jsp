<%@page import="com.study.async.HotPlace"%>
<%@page import="java.util.List"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! HotPlaceDAO hotPlaceDAO = new HotPlaceDAO(); %>
<%
	List<HotPlace> total = hotPlaceDAO.selectAll();	
	
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"storeList\":[");
	for(int i=0; i<total.size(); i++){
		HotPlace hotPlace = total.get(i);
		sb.append("{");
		sb.append("\"hotplace_id\":\""+hotPlace.getHotplace_id()+"\",");
		sb.append("\"name\":\""+hotPlace.getName()+"\",");
		sb.append("\"tel\":\""+hotPlace.getTel()+"\",");
		sb.append("\"addr\":\""+hotPlace.getAddr()+"\",");
		sb.append("\"food\":\""+hotPlace.getFood()+"\"");
		if(i<total.size()-1){ 
			sb.append("},");
		}else{
			sb.append("}");
		}
	}
	sb.append("]");
	sb.append("}");
	
	out.print(sb.toString());
%>