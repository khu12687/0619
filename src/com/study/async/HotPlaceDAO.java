package com.study.async;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.DBManager;

public class HotPlaceDAO  {
	DBManager dbManager =DBManager.getInstance();
	
	public int regist(HotPlace hotPlace) {
		int result =0;
		Connection con = null;
		PreparedStatement pstmt =null;
		String sql = "insert into hotplace(HotPlace_id,name,tel,addr,food)";
		sql+=" values(seq_hotplace.nextval,?,?,?,?)";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hotPlace.getName());
			pstmt.setString(2, hotPlace.getTel());
			pstmt.setString(3, hotPlace.getAddr());
			pstmt.setString(4, hotPlace.getFood());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	public List selectAll() {
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from HotPlace order by hotplace_id desc";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HotPlace hotPlace = new HotPlace();
				hotPlace.setHotplace_id(rs.getInt("hotplace_id"));
				hotPlace.setName(rs.getString("name"));
				hotPlace.setTel(rs.getString("tel"));
				hotPlace.setAddr(rs.getString("addr"));
				hotPlace.setFood(rs.getString("food"));
				list.add(hotPlace);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	public HotPlace select(int hotplace_id) {
		HotPlace hotPlace=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from HotPlace where hotplace_id =?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotplace_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				hotPlace = new HotPlace();
				hotPlace.setName(rs.getString("name"));
				hotPlace.setTel(rs.getString("tel"));
				hotPlace.setAddr(rs.getString("addr"));
				hotPlace.setFood(rs.getString("food"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt, rs);
		}
		
		return hotPlace;
	}
}
