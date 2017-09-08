package com.bengbeng.cbhbit.service;

import com.bengbeng.cbhbit.dao.impl.RealnumberDaoImpl;
import com.bengbeng.cbhbit.dao.impl.RemainderDaoImpl;
import com.bengbeng.cbhbit.dao.impl.WayDaoImpl;
import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;
import com.bengbeng.cbhbit.spider.Spider;

public class BaseDataBase{
	
	public void insert(Settings set) throws Throwable{
		RemainderDaoImpl reDaoImpl=new RemainderDaoImpl();
		WayDaoImpl wayDaoImpl=new WayDaoImpl();
		Remainders user = new Remainders();
		Remainders[] re=new Remainders[500];
		re=Spider.getResourceRemainders(set);
		Remainders max_user=reDaoImpl.getMaxRemainders(set);
		int j=0;
		for(;j<re.length;j++){
			if(re[j].getId()==max_user.getId())
				break;
		}
		for(int i=j-1;i>=0;i--){
			user.setId(re[i].getId());
			user.setThree(re[i].getThree());
			user.setFour(re[i].getFour());
			user.setFive(re[i].getFive());
			reDaoImpl.doAddRemainders(user,set);
			for(int k=0;k<=set.getWAYMAX();k++){
				wayDaoImpl.doInsertWayByID(user.getId(), k,set);
				wayDaoImpl.doUpdateWayByID(user.getId(), k,set);
			}								
			System.out.println("更新"+set.getremark()+"第"+user.getId()+"期数据。");
		}
	}
	public void insertFromImg(Settings set) throws Throwable{
		RealnumberDaoImpl reDaoImpl=new RealnumberDaoImpl();
		Realnumber[] re=Spider.getResourceRealnumberFromImg(set);
		Realnumber reMax=reDaoImpl.getMaxRecentRealnumber(set);
		/**for(int kk=0;kk<4;kk++){
			System.out.println(re[kk].getId()+" "+re[kk].getRealnumber()+" "+re[kk].getPercent());
		}**/
		//System.out.println(reMax.getId());
		int j=0;
		for(;j<re.length;j++){
			if(re[j].getId()==reMax.getId())
				break;
		}
		for(int i=j-1;i>=0;i--){
			//System.out.println(re[j].getId()+" "+re[j].getRealnumber()+" "+re[j].getPercent());
			reDaoImpl.insertRealnumber(re[i], set);
			System.out.println("更新"+set.getremark()+"第"+re[i].getId()+"期数据。");
		}
	}

}
