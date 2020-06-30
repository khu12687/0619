package com.study.model.reboard;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.MybatisConfigManager;

public class MybatisReBoardDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("test.selectAll");
		manager.closeSession(sqlSession);
		return list;
	}
	
	//답변이 아닌 원글이다!!
	public int insert(ReBoard reboard) {
		int result =0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.insert("test.insert",reboard);
		sqlSession.commit(); //DML만..
		manager.closeSession(sqlSession);
		return result;
	}
	
	public ReBoard select(int reboard_id) {
		ReBoard reboard = null;
		SqlSession sqlSession = manager.getSqlSession();
		reboard = (ReBoard)sqlSession.selectOne("test.select",reboard_id);
		manager.closeSession(sqlSession);
		return reboard;
	}
	
	//답변 = 기존자리확보 + 답변등록
	
	//답변이 들어갈 자리 확보하는 메서드!
	//주의 아래의 쿼리는 
	public void updateRank(ReBoard reboard) {
		SqlSession sqlSession = manager.getSqlSession();
		sqlSession.update("test.updateRank",reboard);
		sqlSession.commit();
		manager.closeSession(sqlSession);
	}
	//답변 등록 메서드
	public int reply(ReBoard reboard) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.insert("test.reply",reboard);
		sqlSession.commit();
		manager.closeSession(sqlSession);		
		return result;
	}
	
	public int delete(ReBoard reboard) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.insert("test.delete",reboard);
		sqlSession.commit();
		manager.closeSession(sqlSession);		
		return result;
	}

	/*
	public int update(ReBoard reboard) {
	
		return result;
	}*/
}
