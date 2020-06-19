package com.study.model.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.study.commons.db.DBManager;

//오직, 데이터베이스 관련한 CRUD만을 수행하는 객체를 DAO라고 한다!
//기술 및 플랫폼 중릭접이어야 한다.
public class NewsDAO {
	DBManager manager = DBManager.getInstance();
	
	//뉴스 등록
	public int insert(News news) {
		int result=0; //결과 봔환값
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql="insert into news(news_id,title,writer,content)";
		sql+=" values(seq_news.nextval,?,?,?)";
		
		con=manager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			
			//쿼리 수행!!
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con,pstmt);
		}
		return result;
	}
}
