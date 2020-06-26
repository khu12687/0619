<%@page import="com.study.model.store2.Icons"%>
<%@page import="com.study.model.store2.Store"%>
<%@page import="com.study.model.store2.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!StoreDAO storeDAO = new StoreDAO();
	   Store store = new Store();
	   Icons icons = new Icons();
%>
<%
	request.setCharacterEncoding("utf-8");
	int store_id = Integer.parseInt(request.getParameter("store_id2"));
	String name = request.getParameter("name2");
	String addr = request.getParameter("addr2");
	String lati = request.getParameter("lati2");
	String longi = request.getParameter("longi2");
	String icons_id = request.getParameter("icons_id2");
	String title = request.getParameter("title2");
	String filename = request.getParameter("filename2");
	String memo = request.getParameter("memo2");
	
	
	store.setStore_id(store_id);
	store.setName(name);
	store.setAddr(addr);
	store.setLati(Double.parseDouble(lati));
	store.setLongi(Double.parseDouble(longi));
	icons.setIcons_id(Integer.parseInt(icons_id));
	icons.setTitle(title);
	icons.setFilename(filename);
	store.setIcons(icons);
	store.setMemo(memo);
	
	int result = storeDAO.update(store);
	if(result!=0){
		out.print("<script>");
		out.print("alert('수정 성공');");
		out.print("</script>");
	}else{
		out.print("<script>");	
		out.print("alert('수정 실패');");
		out.print("</script>");
	}
%>