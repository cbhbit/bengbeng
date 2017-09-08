package com.bengbeng.cbhbit.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.bengbeng.cbhbit.dao.BaseDao;
import com.bengbeng.cbhbit.dao.RemaindersDao;
import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public class RemainderDaoImpl extends BaseDao implements RemaindersDao {

	@Override
	public void doAddRemainders(Remainders re,Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String remainders=null;
		remainders="remainders"+set.getsuffix();
		
		parms.put("remainders",remainders );
		parms.put("id",re.getId());
		parms.put("three",re.getThree());
		parms.put("four",re.getFour());
		parms.put("five",re.getFive());
		
		String statement = "com.bengbeng.cbhbit.mapping.insert.addRemainders";		
		getSession();
		session.insert(statement,parms);
		session.commit();
		closeSession();
	}

	@Override
	public Remainders getMaxRemainders(Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String remainders=null;
		remainders="remainders"+set.getsuffix();
		
		parms.put("remainders",remainders );
		
		String statement = "com.bengbeng.cbhbit.mapping.select.getMaxId";
		getSession();				
		Remainders user = session.selectOne(statement,parms);
		closeSession();
		
		return user;
	}

	@Override
	public void doAddRemaindersFromRealnumber(Realnumber re, Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		
		parms.put("remainders","remainders"+set.getsuffix());
		parms.put("realnumber","realnumber"+set.getsuffix());
		parms.put("real_to_percent","real_to_percent"+set.getsuffix());
		parms.put("id",re.getId());
		
		String statement = "com.bengbeng.cbhbit.mapping.insert.addRemaindersFromRealnumber";		
		getSession();
		session.insert(statement,parms);
		session.commit();
		closeSession();
	}

	@Override
	public void deleteById(int id, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("remainders","remainders"+set.getsuffix());
		parms.put("id",id);
		
		String statement = "com.bengbeng.cbhbit.mapping.delete.deleteremaindersmorethanid";
		getSession();
		session.delete(statement, parms);
		closeSession();
	}

	@Override
	public int selectMaxId(Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("remainders","remainders"+set.getsuffix());
		
		String statement = "com.bengbeng.cbhbit.mapping.select.selectRemaindersMaxId";
		getSession();
		int re=session.selectOne(statement,parms);
		closeSession();
		return re;
	}

}
