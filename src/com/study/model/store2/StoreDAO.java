package com.study.model.store2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.commons.db.DBManager;

public class StoreDAO {
	//모두 가져오기 Read
	DBManager dbManager = DBManager.getInstance();
	public List selectAll() {
		List list = new ArrayList();
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		con = dbManager.getConnection();
		String sql = "select * from store s, icons i";
		sql +=" where s.icons_id=i.icons_id";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Icons icons = new Icons();				
				icons.setIcons_id(rs.getInt("icons_id"));
				icons.setTitle(rs.getString("title"));
				icons.setFilename(rs.getString("filename"));
				
				Store store = new Store();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setAddr(rs.getString("addr"));
				store.setLati(rs.getDouble("lati"));
				store.setLongi(rs.getDouble("longi"));
				store.setMemo(rs.getString("memo"));
				
				store.setIcons(icons); //결합				
				list.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	//한건만 가져오기 Read
	public Store select(int store_id) {
		Store store = null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//String sql = "select * from store where store_id=?";
		String sql = "select * from store s, icons i";
		sql +=" where s.icons_id=i.icons_id and s.store_id=?";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, store_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				Icons icons = new Icons();				
				icons.setIcons_id(rs.getInt("icons_id"));
				icons.setTitle(rs.getString("title"));
				icons.setFilename(rs.getString("filename"));
				store = new Store();
				store.setStore_id(rs.getInt("store_id"));
				store.setName(rs.getString("name"));
				store.setAddr(rs.getString("addr"));
				store.setLati(rs.getDouble("lati"));
				store.setLongi(rs.getDouble("longi"));
				store.setMemo(rs.getString("memo"));		
				
				store.setIcons(icons); //결합!!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt, rs);
		}
		
		return store;
	}
	//등록하기 Create
	public int insert(Store store) {
		int result =0;
		Connection con =null;
		PreparedStatement pstmt =null;
		String sql ="insert into store(store_id,name,addr,lati,longi,icons_id,memo)";
		sql +=" values(seq_store.nextval,?,?,?,?,?,?)";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,store.getName());
			pstmt.setString(2,store.getAddr());
			pstmt.setDouble(3, store.getLati());
			pstmt.setDouble(4, store.getLongi());
			pstmt.setInt(5, store.getIcons().getIcons_id());
			pstmt.setString(6, store.getMemo());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt);
		}
		return result;
	}
	//수정하기 Update
	public int update(Store store) {
		Connection con = null;
		PreparedStatement pstmt =null;
		int result =0;
		con = dbManager.getConnection();
		
		String sql = "update store set name =?,addr=?,lati=?, longi=?, icons_id=?,memo=?";
		sql +=" where store_id=?";
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1,store.getName() );
			pstmt.setString(2,store.getAddr() );
			pstmt.setDouble(3, store.getLati());
			pstmt.setDouble(4, store.getLongi());
			pstmt.setInt(5, store.getIcons().getIcons_id());
			pstmt.setString(6, store.getMemo());
			pstmt.setInt(7, store.getStore_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt);
		}
		return result;
	}
	//삭제하기 Delete
	public int delete(int store_id) {
		Connection con =null;
		PreparedStatement pstmt =null;
		int result = 0;
		
		con = dbManager.getConnection();
		String sql="delete from store where store_id=?";
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, store_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.freeConnection(con, pstmt);
		}
		return result;
	}
}
