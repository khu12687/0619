package com.study.controller.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.study.commons.file.FileManager;
import com.study.model.store2.Icons;
import com.study.model.store2.Store;
import com.study.model.store2.StoreDAO;

//엑셀 파일 업로드 요청 처리 서블릿 : Apache upload + Apache POI
public class ExcelUploadServlet extends HttpServlet{
	StoreDAO storeDAO = new StoreDAO();
	PrintWriter out;
	int result = 0; //성공 실패 여부를 판단하는 변수
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
		
		//1단계 : 업로드
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//application 내장객체의 자료형은 ServletContext 이다!!
		//ServletContext ? 어플리케이션의 전역 정보를 가진 객체
		ServletContext context = request.getServletContext();
		String saveDir = context.getRealPath("/data/");
		File saveFilePath = new File(saveDir); //파일이 아닌 저장될 디렉토리!!
		factory.setRepository(saveFilePath);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> itemList = upload.parseRequest(request); //요청 해석!!
			for(int i=0; i<itemList.size(); i++) {
				FileItem item = itemList.get(i);
				if(item.isFormField()) { //업로드 컴포넌트가 아니라면.. 일반 텍스트라면
					
				}else {
					System.out.println("업로드된 파일명"+item.getName());
					//서버측의 메모리로 로드된 엑셀파일을, 실제 파일로 저장
					File file = new File(saveDir+FileManager.getFileRename(item.getName()));
					item.write(file); //저장 시점!!
					
					//저장된 엑셀을 해석
					parseExcel(file);
					
					result=1;
					out.print(result);
				}
			}
			
			System.out.println("수행 결과 = "+result);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	//2단계 : 업로드된 엑셀 해석!!
	public void parseExcel(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			HSSFWorkbook book = new HSSFWorkbook(fis);
			HSSFSheet sheet = book.getSheetAt(0); //첫번째 쉬트
			
			//쉬트안의 로우 접근
			int total = sheet.getPhysicalNumberOfRows();
			System.out.println("총 row수는"+total);
			
			for(int i=0; i<total; i++) {
				HSSFRow row = sheet.getRow(i);
				System.out.println(row.getCell(0)); //name
				System.out.println(row.getCell(1)); //addr
				System.out.println(row.getCell(2)); //lati
				System.out.println(row.getCell(3)); //longi
				System.out.println(row.getCell(4)); //memo
				System.out.println(row.getCell(5)); //icons_id
				
				Store store = new Store();
				Icons icons = new Icons();
				
				store.setName(row.getCell(0).getStringCellValue());
				store.setAddr(row.getCell(1).getStringCellValue());
				store.setLati(row.getCell(2).getNumericCellValue());
				store.setLongi(row.getCell(3).getNumericCellValue());
				store.setMemo(row.getCell(4).getStringCellValue());
				icons.setIcons_id((int)row.getCell(5).getNumericCellValue());
				
				store.setIcons(icons); //vo결합
				storeDAO.insert(store);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
