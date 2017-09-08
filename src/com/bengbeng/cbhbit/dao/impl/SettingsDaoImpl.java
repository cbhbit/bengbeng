package com.bengbeng.cbhbit.dao.impl;

import com.bengbeng.cbhbit.dao.BaseDao;
import com.bengbeng.cbhbit.dao.SettingsDao;
import com.bengbeng.cbhbit.domain.Settings;

public class SettingsDaoImpl extends BaseDao implements SettingsDao {

	@Override
	public Settings doGetSettings(int id) {
		// TODO Auto-generated method stub
		String statement = "com.bengbeng.cbhbit.mapping.settings.getSettingsFromId";
		getSession();				
		Settings set = session.selectOne(statement,id);
		closeSession();
		
		return set;
	}

}
