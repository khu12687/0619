<%@page import="com.study.model.store.Icons"%>
<%@page import="com.study.model.store.Store"%>
<%@page import="com.study.model.store.StoreDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<%
	//맛집 정보 파라미터들을 넘겨받아 오라클에 넣기!!
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String addr= request.getParameter("addr");
	String lati = request.getParameter("lati");
	String longi = request.getParameter("longi");
	String icons_id = request.getParameter("icons_id");
	String memo = request.getParameter("memo");
	
	System.out.println("name="+name);
	System.out.println("addr="+addr);
	System.out.println("lati="+lati);
	System.out.println("longi="+longi);
	System.out.println("icons_id="+icons_id);
	System.out.println("memo="+memo);
	
	Store store = new Store(); //Empty 상태
	store.setName(name);
	store.setAddr(addr);
	store.setLati(Double.parseDouble(lati));
	store.setLongi(Double.parseDouble(longi));
	Icons icons = new Icons(); //empty 상태
	icons.setIcons_id(Integer.parseInt(icons_id)); //fk대입
	store.setIcons(icons); //vo 간 결합
	store.setMemo(memo);
	
	int result = storeDAO.insert(store);
	System.out.println("수행결과 "+result); //개발자를 위한 디버깅
	out.print(result); //결과를 보내자!! 클라이언트를 위한 출력
%>