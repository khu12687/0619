package com.study.model.reboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.PoolManager;

public class ReBoardDAO {
	PoolManager manager = PoolManager.getInstance();
	
	public List selectAll() {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//동물 올림픽 원릴와 같다!!!
		String sql = "select * from reboard order by team desc, rank asc";

		List<ReBoard> list = new ArrayList();
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReBoard reBoard = new ReBoard();
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setRank(rs.getInt("rank"));
				reBoard.setDepth(rs.getInt("depth"));
				list.add(reBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	public ReBoard select(int reboard_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReBoard reboard = null;
		String sql = "select * from reboard where reboard_id=?";
		con = manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reboard_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				reboard = new ReBoard();
				reboard.setReboard_id(rs.getInt("reboard_id"));
				reboard.setTitle(rs.getString("title"));
				reboard.setWriter(rs.getString("writer"));
				reboard.setContent(rs.getString("content"));
				reboard.setRegdate(rs.getString("regdate"));
				reboard.setHit(rs.getInt("hit"));
				reboard.setTeam(rs.getInt("team"));
				reboard.setRank(rs.getInt("rank"));
				reboard.setDepth(rs.getInt("depth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		
		return reboard;
	}
	
	//답변 = 기존자리확보 + 답변등록
	
	//답변이 들어갈 자리 확보하는 메서드!
	//주의 아래의 쿼리는 
	public void updateRank(ReBoard reboard) {
		Connection con=null;
		PreparedStatement pstmt = null;
		con =manager.getConnection();
		String sql="update reboard set rank=rank+1";
		sql+=" where team=? and rank>?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboard.getTeam());
			pstmt.setInt(2, reboard.getRank());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
	}
	//답변 등록 메서드
	public int reply(ReBoard reboard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result =0;
		con =manager.getConnection();
		String sql = "insert into reboard(reboard_id, title, writer, content,team,rank,depth)";
		sql+=" values(seq_reboard.nextval,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reboard.getTitle());
			pstmt.setString(2, reboard.getWriter());
			pstmt.setString(3, reboard.getContent());
			pstmt.setInt(4, reboard.getTeam());
			pstmt.setInt(5, reboard.getRank()+1); //답변이라서
			pstmt.setInt(6, reboard.getDepth()+1); //답변이라서

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}

		
		//team : 내본글 team
		//rank : 내본글 rank+1
		//depth : 내본글 depth+1
		return result;
	}
	
	//답변이 아닌 원글이다!!
	public int insert(ReBoard reboard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result =0;
		String sql = "insert into reboard(reboard_id,title,writer,content,team)";
		sql+=" values(seq_reboard.nextval,?,?,?,seq_reboard.nextval)";
		con =manager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reboard.getTitle());
			pstmt.setString(2, reboard.getWriter());
			pstmt.setString(3, reboard.getContent());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	public int update(ReBoard reboard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result =0;
		con=manager.getConnection();
		
		String sql = "update reboard set title=?, writer=?, content=?";
		sql+=" where reboard_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reboard.getTitle());
			pstmt.setString(2, reboard.getWriter());
			pstmt.setString(3, reboard.getContent());
			pstmt.setInt(4, reboard.getReboard_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	public int delete(ReBoard reboard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int result =0;
		con = manager.getConnection();
		
		String sql = "delete from reboard where reboard_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reboard.getReboard_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
