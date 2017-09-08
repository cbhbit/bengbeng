package com.bengbeng.cbhbit.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.bengbeng.cbhbit.dao.BaseDao;
import com.bengbeng.cbhbit.dao.WayMaxSameCountDao;
import com.bengbeng.cbhbit.domain.Settings;

public class WayMaxSameCountImpl extends BaseDao implements WayMaxSameCountDao {

	@Override
	public int getMaxSameCount(int samecountid,int wayid, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
				
		parms.put("same_count","same_count"+samecountid);
		parms.put("way_max_samecount","way_max_samecount"+set.getsuffix());
		parms.put("way_id",wayid);
		
		String statement = "com.bengbeng.cbhbit.mapping.select.getMaxSameCount";
		getSession();
		int max=session.selectOne(statement,parms);
		closeSession();
		
		return max;
	}

	@Override
	public void updateMaxSameCount(int samecountid,int max_same_count, int wayid, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		
		parms.put("same_count","same_count"+samecountid);
		parms.put("way_max_samecount","way_max_samecount"+set.getsuffix());
		parms.put("way_id",wayid);
		parms.put("max_same_count",max_same_count);
		
		String statement = "com.bengbeng.cbhbit.mapping.update.updateMaxSameCount";
		getSession();
		session.update(statement,parms);
		session.commit();
		closeSession();

	}

}
