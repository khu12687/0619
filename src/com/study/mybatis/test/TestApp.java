package com.study.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.study.model.reboard.ReBoard;

//myBatis는 개발자로 하여금 퀴리문에 집중 할 수 있도록 
//jdbc관련 api를 직접 제어하지 않아도 되며, 또한 매피작업을 자체적으로 지원하기 때문에
//코드량에 있어 효율적이다 참고-web,application 모두 사용가능~
public class TestApp {
	public TestApp() {
		String path ="com/study/mybatis/config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(path);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			
			//SqlSession이 바로 쿼리 실행 객체!!
			//즉 쿼리문이 들어있는 mapper를 호출 할 수 있는 객체!!
			SqlSession sqlSession = factory.openSession();
			
			List<ReBoard> list = sqlSession.selectList("test.selectAll");
			for(int i=0; i<list.size(); i++) {
				ReBoard reboard = list.get(i);
				
				System.out.println(reboard.getReboard_id());
				System.out.println(reboard.getTitle());
				System.out.println(reboard.getWriter());
				System.out.println(reboard.getRegdate());
				System.out.println(reboard.getHit());
				System.out.println(reboard.getTeam());
				System.out.println(reboard.getRank());
				System.out.println(reboard.getDepth());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		new TestApp();
	}
}
