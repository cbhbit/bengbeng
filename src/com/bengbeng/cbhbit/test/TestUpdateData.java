package com.bengbeng.cbhbit.test;

import com.bengbeng.cbhbit.dao.impl.SettingsDaoImpl;
import com.bengbeng.cbhbit.service.BaseDataBase;
import com.bengbeng.cbhbit.service.Database;

public class TestUpdateData {

	public static void main(String[] args) throws Throwable {
		SettingsDaoImpl setDaoImpl=new SettingsDaoImpl();
		BaseDataBase bdb=new BaseDataBase();
		bdb.insertFromImg(setDaoImpl.doGetSettings(4));
		
		Database db=new Database();
		db.initMaxSameCount(setDaoImpl.doGetSettings(1));
		db.initMaxSameCount(setDaoImpl.doGetSettings(2));
		db.initMaxSameCount(setDaoImpl.doGetSettings(3));
	}

}
