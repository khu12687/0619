package com.study.controller.store;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.study.model.store2.Icons;
import com.study.model.store2.Store;
import com.study.model.store2.StoreDAO;

//클라이언트가 multipart/form-data로 전송할때는
//해당 파라미터를 request 객체로 직접 받을 수 없다!!
//따라서 업로드 컴포넌트를 이용해야 한다..
//MultipartRequest(오래일리 출판사)
//ServletFileUpload(apache org)
public class JsonUploadServlet extends HttpServlet{
	StoreDAO storeDAO = new StoreDAO();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업로드 관련 설정정보 객체
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//용량, 저장위치 설정..
		//jsp 에서의 application 내장객체의 자료형은?
		ServletContext context = request.getServletContext();
		String saveDir=context.getRealPath("/data");
		File saveFile = new File(saveDir); //저장 경로를 가진 파객체
		factory.setRepository(saveFile); //저장 위치 설정완료!!
		
		//업로드를 처리하는 객체
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);
		
		//클라이언트가 전송한 파라미터가 여러개 있을 수 있으므로, 
		//파라미터를 집합으로 보유한 FileItem을 분석하자!!
		try {
			List<FileItem> itemList = upload.parseRequest(request); //요청 파라미터 분석 시작!!
			for(int i=0; i<itemList.size(); i++) {
				//텍스트 필드인지 아닌지 구분하여 , 아닌 경우는 파일업로드 처리!!
				FileItem item = itemList.get(i);
				
				if(item.isFormField()) {
					//vo
				}else { //텍스트박스가 아니라면... 파일이다!!
					System.out.println("파일업로드 발견!!"+item.getName());
					
					//스트림으로 파일을 읽어보자
					BufferedReader buffr = null;
					buffr =new BufferedReader( new InputStreamReader(item.getInputStream()));
					
					//읽기
					String data =null;
					StringBuilder sb = new StringBuilder();
					
					while(true) {
						data=buffr.readLine();
						if(data==null) break;
						sb.append(data);
						//System.out.println(data);
					}
					System.out.println(sb.toString().trim()); //모아진 데이터!!
					if(buffr!=null) {
						try {
							buffr.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					int result = parseJson(sb.toString());
					PrintWriter out = response.getWriter();
					out.print(result);
					System.out.println("업로드 성공 여부 = "+result);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	//제이슨 파싱 후, 오라클에 넣기!!
	public int parseJson(String data) {
		int result = 0;
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(data);
			JSONArray jsonArray =(JSONArray)obj.get("storeList");
			
			//반복문 돌리면서 DAO.insert 호출!!
			for(int i=0; i<jsonArray.size(); i++) {
				JSONObject json = (JSONObject)jsonArray.get(i);
				System.out.println(json.get("name"));
				
				//Store VO
				Store store = new Store();
				Icons icons = new Icons();
				//json --> store로 옮기자!!
				store.setName((String)json.get("name"));
				store.setAddr((String)json.get("addr"));
				store.setLati((Double)json.get("lati"));
				store.setLongi((Double)json.get("longi"));
				store.setMemo((String)json.get("memo"));
				long icons_id =(Long)json.get("icons_id");
				icons.setIcons_id((int)icons_id);
				
				//vo결합
				store.setIcons(icons);
				
				storeDAO.insert(store);
			}
			result=1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result; //성공여부를 알려주기 위함!!
	}
}
