package com.bengbeng.cbhbit.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.bengbeng.cbhbit.dao.BaseDao;
import com.bengbeng.cbhbit.dao.StatisticsDao;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public class StatisticsDaoImpl extends BaseDao implements StatisticsDao {

	@Override
	public void doAddStatistics(Remainders re,Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String statistics=null;
		statistics="statistics"+set.getsuffix();
		parms.put("statistics", statistics);
		parms.put("id",re.getId());
		
		String statement = "com.bengbeng.cbhbit.mapping.insert.addStatistics";		
		getSession();
		session.insert(statement,parms);
		session.commit();
		closeSession();

	}

	@Override
	public void deleteById(int id, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("statistics","statistics"+set.getsuffix());
		parms.put("id",id);
		
		String statement = "com.bengbeng.cbhbit.mapping.delete.deletestatisticsmorethanid";
		getSession();
		session.delete(statement, parms);
		closeSession();
	}

	@Override
	public int selectMaxId(Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("statistics","statistics"+set.getsuffix());

		String statement = "com.bengbeng.cbhbit.mapping.select.selectStatisticsMaxId";
		getSession();
		int re=session.selectOne(statement,parms);
		closeSession();
		return re;
	}

}
