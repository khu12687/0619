<%@page import="com.study.async.HotPlace"%>
<%@page import="com.study.async.HotPlaceDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! HotPlaceDAO hotPlaceDAO = new HotPlaceDAO(); %>
<%
	//클라이언트가 전송한 파라미터값들을 이용하여 오라클에 넣기!
	//서버측에서는 클라이언트의 통신방식이 비동기/동기 관심없다!!
	request.setCharacterEncoding("utf-8"); //파라미터 안깨지게
	String name = request.getParameter("name");
	String tel= request.getParameter("tel");
	String addr= request.getParameter("addr");
	String food = request.getParameter("food");
	
	//System.out.println(name);
	//System.out.println(tel);
	//System.out.println(addr);
	//System.out.println(food);
	
	HotPlace hotPlace = new HotPlace();
	hotPlace.setName(name);
	hotPlace.setTel(tel);
	hotPlace.setAddr(addr);
	hotPlace.setFood(food);
	
	int result = hotPlaceDAO.regist(hotPlace);
	
	if(result!=0){
		out.print("<script>");
		out.print("alert('등록 성공');");
		out.print("location.href='/async/main.html';");
		out.print("</script>");
	}else{
		out.print("<script>");	
		out.print("alert('등록 실패');");
		out.print("history.back();");
		out.print("</script>");
	}
	
	
%>