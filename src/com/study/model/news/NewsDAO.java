package com.study.model.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//머든 글 가져오기  - CRUD 중 Read에 해당!!
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select n.news_id as news_id,title,writer,regdate,hit, count(comments_id) as cnt");
		sb.append(" from news n left outer join comments c");
		sb.append(" on n.news_id = c.news_id");
		sb.append(" group by n.news_id,title,writer,regdate,hit");
		
		String sql="select * from news order by news_id desc";
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				//news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				news.setCnt(rs.getInt("cnt"));
				
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	//글 한건 간져오기 - CRUD 중 Read에 해당!!
	public News select(int news_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		News news = null;
		
		String sql="select * from news where news_id=?";
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				news = new News();
				
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return news;
	}
	
	public int edit(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result =0;
		String sql="update news set title=?, writer=?, content=?";
		sql+=" where news_id=?";
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			pstmt.setInt(4, news.getNews_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return  result;
	}
	
	public int delete(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result =0;
		String news_id = Integer.toString(news.getNews_id());
		String sql = "delete from news where news_id ="+news_id;
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
}
