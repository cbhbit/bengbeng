package com.bengbeng.cbhbit.test;

import com.bengbeng.cbhbit.dao.impl.RemainderDaoImpl;
import com.bengbeng.cbhbit.dao.impl.SettingsDaoImpl;
import com.bengbeng.cbhbit.domain.Settings;
import com.bengbeng.cbhbit.service.Result;

public class Test {  

	public static void main(String[] args) throws Throwable{
		final Result re=new Result();
		/**
		for(int i=0;i<10;i++){
			re.testThree(1,i);
		} **/
		//SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		//Settings set=set_dao_impl.doGetSettings(1);
		//RemainderDaoImpl remainderdaoimpl=new RemainderDaoImpl();		
		//re.testResultNine(remainderdaoimpl.selectMaxId(set),1);
		re.testWaySix(1);
	}
}
