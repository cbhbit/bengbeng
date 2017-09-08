package com.bengbeng.cbhbit.service;

import com.bengbeng.cbhbit.dao.impl.RealnumberDaoImpl;
import com.bengbeng.cbhbit.dao.impl.RemainderDaoImpl;
import com.bengbeng.cbhbit.dao.impl.StatisticsDaoImpl;
import com.bengbeng.cbhbit.dao.impl.WayDaoImpl;
import com.bengbeng.cbhbit.dao.impl.WayMaxSameCountImpl;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;
import com.bengbeng.cbhbit.spider.Spider;

public class Database{
		
 	public void insert(Settings set) throws Throwable{
 		RealnumberDaoImpl realnumberDaoImpl=new RealnumberDaoImpl();
		RemainderDaoImpl reDaoImpl=new RemainderDaoImpl();
		StatisticsDaoImpl stDaoImpl=new StatisticsDaoImpl();
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
			stDaoImpl.doAddStatistics(user, set);
			realnumberDaoImpl.doAddRealnumber(user, set);			
			for(int k=0;k<=set.getWAYMAX();k++){
				wayDaoImpl.doInsertWayByID(user.getId(), k,set);
				wayDaoImpl.doUpdateWayByID(user.getId(), k,set);
			}								
			System.out.println("更新"+set.getremark()+"第"+user.getId()+"期数据。");
		}	
		
	}
 	
 	public void initMaxSameCount(Settings set){
 		WayMaxSameCountImpl wayMaxSameCountImpl=new WayMaxSameCountImpl();
 		WayDaoImpl wayDaoImpl=new WayDaoImpl();
 		for(int i=0;i<=set.getWAYMAX();i++){
 			for(int j=1;j<=set.getSAMECOUNT_MAX();j++){
 				if(wayMaxSameCountImpl.getMaxSameCount(j, i, set)<wayDaoImpl.getMaxSameCount(j, i, set)){
 					wayMaxSameCountImpl.updateMaxSameCount(j, wayDaoImpl.getMaxSameCount(j, i, set), i, set);
 					System.out.println("更新了最大期数");
 				}
 			}
 		}
 	}
 	public void initDataBase(Settings set){
 		RealnumberDaoImpl realnumberDaoImpl=new RealnumberDaoImpl();
		RemainderDaoImpl reDaoImpl=new RemainderDaoImpl();
		StatisticsDaoImpl stDaoImpl=new StatisticsDaoImpl();
 		WayDaoImpl wayDaoImpl=new WayDaoImpl();
 		int min;
 		int[] abc=new int[set.getWAYMAX()+4];
 		for(int i=0;i<=set.getWAYMAX();i++){
 			abc[i]=wayDaoImpl.selectMaxId(i, set);
 		}
 		abc[set.getWAYMAX()+1]=realnumberDaoImpl.selectMaxId(set);
 		abc[set.getWAYMAX()+2]=reDaoImpl.selectMaxId(set);
 		abc[set.getWAYMAX()+3]=stDaoImpl.selectMaxId(set);
 		
 		min=abc[0];
 		for(int i=1;i<abc.length;i++){
 			if(min<abc[i]){
 				min=abc[i];
 				System.out.println("初始化数据，请等待！");
 			}
 		}
 		
 		realnumberDaoImpl.deleteById(min,set);
 		reDaoImpl.deleteById(min,set);
 		stDaoImpl.deleteById(min,set);
 		for(int i=0;i<=set.getWAYMAX();i++){
 			wayDaoImpl.deleteById(min,i,set);
 		}
 	}

}

