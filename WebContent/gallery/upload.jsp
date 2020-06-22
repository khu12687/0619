<%@page import="com.study.model.gboard.Gboard"%>
<%@page import="com.study.model.gboard.GboardDAO"%>
<%@page import="java.io.File"%>
<%@page import="com.study.commons.file.FileManager"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! GboardDAO gboardDAO = new GboardDAO(); %>
<%
	//파라미터들을 넘겨받아, 파일의 경우엔 서버에 저장하고,
	//텍스트 데이터의 경우엔 오라클 넣자!!
	//application 내장 객체의 getRealPath()을 이용하면 현재의 웹사이트가 어떤 환경에서 실행되고 있는지에 상관없이
	//프로그래밍적으로 물리적 하드경로를 반환해준다!!
	//ex)윈도우의 경우 d: /~~~ 리눅스의 경우 사용자/ ~~
	String saveDir=application.getRealPath("/data");
	int maxSize=3*1024*1024; //계산하지 말자!
	String encoding="utf-8";
	
	MultipartRequest multi=null;
	
	//생성자에서 업로드가 발생!!
	try{
	multi = new MultipartRequest(request,saveDir,maxSize,encoding);
	
	//파일명 교체
	String ori = multi.getOriginalFileName("imgFile");
	
	//새로운 이름 생성
	String dest = FileManager.getFileRename(ori);
	
	//업로드된 바로 그 파일을 반환받음
	File file = multi.getFile("imgFile");
	File destFile = new File(saveDir+"/"+dest); //새로운 파일생성
	
	if(file.renameTo(destFile)){ //파일명을 제대로 바꿧다면 true
		//오라클에 넣기!!
		Gboard gboard = new Gboard();
		gboard.setTitle(multi.getParameter("title"));
		gboard.setWriter(multi.getParameter("writer"));
		gboard.setContent(multi.getParameter("content"));
		gboard.setFilename(dest); //2221212312312.jsp
		
		int result = gboardDAO.insert(gboard);
		if(result ==0){
			//이미지를 처음부터 없었던 것처럼 싹 지우자!!
			destFile.delete();
		}else{
			out.print("<script>");
			out.print("alert('업로드성공');");
			out.print("location.href='/gallery/list.jsp';");
			out.print("</script>");
		}
	}else{
		System.out.print("변경실패");
	}
	//file.renameTo(destFile);
	}catch(IOException e){
		e.printStackTrace(); //개발자를 위한 로그 기록
		out.print("<script>");
		//사용자를 위한 메시지
		out.print("alert('이미지 용량을 확인해주세요');");
		out.print("history.back()");
		out.print("</script>");
	}
%>