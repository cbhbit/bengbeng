package com.bengbeng.cbhbit.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bengbeng.cbhbit.dao.BaseDao;
import com.bengbeng.cbhbit.dao.RealnumberDao;
import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public class RealnumberDaoImpl extends BaseDao implements RealnumberDao {

	@Override
	public void doAddRealnumber(Remainders re,Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String realnumber=null;
		String remainders=null;
		String real_to_percent=null;
		realnumber="realnumber"+set.getsuffix();
		remainders="remainders"+set.getsuffix();
		real_to_percent="real_to_percent"+set.getsuffix();
		
		parms.put("realnumber",realnumber);
		parms.put("remainders",remainders);
		parms.put("real_to_percent",real_to_percent);
		parms.put("id",re.getId());
		
		String statement = "com.bengbeng.cbhbit.mapping.insert.addRealnumber";		
		getSession();
		session.insert(statement,parms);
		session.commit();
		closeSession();

	}

	@Override
	public int doGetPercent(int num, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("real_to_percent","real_to_percent"+set.getsuffix());
		parms.put("number",num);
		
		String statement = "com.bengbeng.cbhbit.mapping.select.getPercent";
		getSession();
		int percent=session.selectOne(statement,parms);
		closeSession();
		return percent;
	}
	@Override
	public void insertRealnumber(Realnumber re,Settings set){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		parms.put("id",re.getId());
		parms.put("num",re.getRealnumber());
		parms.put("percent",re.getPercent());
		
		String statement = "com.bengbeng.cbhbit.mapping.insert.insertRealnumber";
		getSession();
		session.insert(statement,parms);
		session.commit();
		closeSession();
	}
	@Override
	public Realnumber getMaxRecentRealnumber(Settings set){
		Realnumber re;
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		
		String statement = "com.bengbeng.cbhbit.mapping.select.getMaxIdFromRealnumber";
		getSession();
		re=session.selectOne(statement,parms);
		closeSession();
		return re;
	}

	@Override
	public void deleteById(int id, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		parms.put("id",id);
		
		String statement = "com.bengbeng.cbhbit.mapping.delete.deleterealnumbermorethanid";
		getSession();
		session.delete(statement, parms);
		closeSession();
	}

	@Override
	public int selectMaxId(Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		
		String statement = "com.bengbeng.cbhbit.mapping.select.selectRealnumberMaxId";
		getSession();
		int re=session.selectOne(statement,parms);
		closeSession();
		return re;
	}

	@Override
	public int selectRealNumberByID(int id, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		parms.put("id", id);
		
		String statement = "com.bengbeng.cbhbit.mapping.select.selectRealNumberByID";
		getSession();
		int re=session.selectOne(statement,parms);
		closeSession();
		return re;
	}

	@Override
	public List<Realnumber> selectRealNumberTest(int id,int a,int b,int c,int d, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		parms.put("id", id);
		parms.put("a", a);
		parms.put("b", b);
		parms.put("c", c);
		parms.put("d", d);
		
		String statement = "com.bengbeng.cbhbit.mapping.select.selectRealNumberTest";
		getSession();
		List<Realnumber> re=new ArrayList<Realnumber>();
		re=session.selectList(statement,parms);
		closeSession();
		return re;
	}

	@Override
	public List<Realnumber> selectRealNumberTestThree(int id, int a, int b, int c, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("realnumber","realnumber"+set.getsuffix());
		parms.put("id", id);
		parms.put("a", a);
		parms.put("b", b);
		parms.put("c", c);
		
		String statement = "com.bengbeng.cbhbit.mapping.select.selectRealNumberTestThree";
		getSession();
		List<Realnumber> re=new ArrayList<Realnumber>();
		re=session.selectList(statement,parms);
		closeSession();
		return re;
	}

}
