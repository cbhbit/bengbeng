package com.bengbeng.cbhbit.dao;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bengbeng.cbhbit.test.Test;

public class BaseDao {
	protected SqlSession session=null;
	
	public SqlSession getSession(){
		String resource = "conf.xml";
		InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		session = sessionFactory.openSession();
		return session;
	}
	public void closeSession(){
		if (session!=null)
			session.close();
	}

}
