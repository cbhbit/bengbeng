package com.bengbeng.cbhbit.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bengbeng.cbhbit.dao.BaseDao;
import com.bengbeng.cbhbit.dao.WayDao;
import com.bengbeng.cbhbit.domain.Settings;
import com.bengbeng.cbhbit.domain.Way;

public class WayDaoImpl extends BaseDao implements WayDao {

	@Override
	public Way getWayByID(int id, int wayid,Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("id", id);
		
		String statement="com.bengbeng.cbhbit.mapping.select.getwayFromId";
		getSession();
		Way way = session.selectOne(statement,parms);
		closeSession();
		
		return way;
	}

	@Override
	public void doUpdateWayByID(int id,int wayid,Settings set) {
		// TODO Auto-generated method stub
		Way waymax=new Way();
		Way waymin=new Way();
		Way waymax1=new Way();
		Way wayset=new Way();
		RemainderDaoImpl reDaoImpl=new RemainderDaoImpl();
		waymax=getWayByID(reDaoImpl.getMaxRemainders(set).getId(),wayid,set);
		waymax1=getWayByID(reDaoImpl.getMaxRemainders(set).getId()-1,wayid,set);
		
		for(int i=1;i<=set.getSAMECOUNT_MAX();i++){
			waymin=getWayByID(reDaoImpl.getMaxRemainders(set).getId()-i,wayid,set);
			switch (i)  {
			case 1:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count1(waymax1.getSame_count1()+1);
				else
					wayset.setSame_count1(0);
			break;
			case 2:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count2(waymax1.getSame_count2()+1);
				else
					wayset.setSame_count2(0);
			break;
			case 3:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count3(waymax1.getSame_count3()+1);
				else
					wayset.setSame_count3(0);
			break;
			case 4:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count4(waymax1.getSame_count4()+1);
				else
					wayset.setSame_count4(0);
			break;
			case 5:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count5(waymax1.getSame_count5()+1);
				else
					wayset.setSame_count5(0);
			break;
			case 6:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count6(waymax1.getSame_count6()+1);
				else
					wayset.setSame_count6(0);
			break;
			case 7:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count7(waymax1.getSame_count7()+1);
				else
					wayset.setSame_count7(0);
			break;
			case 8:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count8(waymax1.getSame_count8()+1);
				else
					wayset.setSame_count8(0);
			break;
			case 9:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count9(waymax1.getSame_count9()+1);
				else
					wayset.setSame_count9(0);
			break;
			case 10:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count10(waymax1.getSame_count10()+1);
				else
					wayset.setSame_count10(0);
			break;
			case 11:
				if(waymax.getWay()==waymin.getWay())
					{
						wayset.setSame_count11(waymax1.getSame_count11()+1);
						//System.out.println(waymax.getWay()+" "+waymin.getWay()+"same_count11更新"+wayset.getSame_count11());
					}
				else
					wayset.setSame_count11(0);
			break;
			case 12:
				if(waymax.getWay()==waymin.getWay())
					wayset.setSame_count12(waymax1.getSame_count12()+1);
				else
					wayset.setSame_count12(0);
			break;
			default : break;
			}
			//if(wayset.getSame_count11()!=0)
			//System.out.println(waymax.getWay()+" "+waymin.getWay()+"same_count11更新"+wayset.getSame_count11());
		}
		wayset.setId(reDaoImpl.getMaxRemainders(set).getId());
		
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("id", id);
		/**
		for(int p=1;p<=set.getSAMECOUNT_MAX();p++){
			String same_count="same_count"+p;
			parms.put(same_count,wayset.getSame_count1());
		}
		**/
		parms.put("same_count1",wayset.getSame_count1());
		parms.put("same_count2",wayset.getSame_count2());
		parms.put("same_count3",wayset.getSame_count3());
		parms.put("same_count4",wayset.getSame_count4());
		parms.put("same_count5",wayset.getSame_count5());
		parms.put("same_count6",wayset.getSame_count6());
		parms.put("same_count7",wayset.getSame_count7());
		parms.put("same_count8",wayset.getSame_count8());
		parms.put("same_count9",wayset.getSame_count9());
		parms.put("same_count10",wayset.getSame_count10());
		parms.put("same_count11",wayset.getSame_count11());
		parms.put("same_count12",wayset.getSame_count12());
		
		String statement="com.bengbeng.cbhbit.mapping.update.updateway";
		getSession();				
		session.update(statement,parms);
		session.commit();
		closeSession();

	}

	@Override
	public void doInsertWayByID(int id, int wayid,Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		String columnName=null;
		if (wayid<10){
			wayName="way_0"+wayid+set.getsuffix();
			columnName="way_0"+wayid;
		}
		else{
			wayName="way_"+wayid+set.getsuffix();
			columnName="way_"+wayid;
		}
		String remainders=null;
		String real_to_percent=null;
		remainders="remainders"+set.getsuffix();
		real_to_percent="real_to_percent"+set.getsuffix();
		
		parms.put("remainders",remainders );
		parms.put("real_to_percent",real_to_percent);
		parms.put("way", wayName);
		parms.put("columnName", columnName);
		parms.put("id", id);
		
		String statement="com.bengbeng.cbhbit.mapping.insert.addWay";
		getSession();				
		session.insert(statement,parms);
		session.commit();
		closeSession();

	}

	@Override
	public int doGetMaxRepeat(int i, int wayid,Settings set) {
		// TODO Auto-generated method stub
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		String same_count="same_count"+i;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("same_count", same_count);
		
		String statement="com.bengbeng.cbhbit.mapping.select.getMaxRepeat";
		getSession();
		int max = session.selectOne(statement,parms);
		closeSession();
		return max;
	}

	@Override
	public int getMaxSameCount(int sameCountId,int wayid,Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		String same_count="same_count"+sameCountId;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("same_count", same_count);
		
		String statement="com.bengbeng.cbhbit.mapping.select.getWayMaxSameCount";
		getSession();
		int max = session.selectOne(statement,parms);
		closeSession();
		
		return max;
	}

	@Override
	public void deleteById(int id, int wayid,Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("id",id);
		
		String statement = "com.bengbeng.cbhbit.mapping.delete.deletewaymorethanid";
		getSession();
		session.delete(statement, parms);
		closeSession();
	}

	@Override
	public int selectMaxId(int wayid, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);

		String statement = "com.bengbeng.cbhbit.mapping.select.selectWayMaxId";
		getSession();
		int re=session.selectOne(statement,parms);
		closeSession();
		return re;
	}

	@Override
	public List<Way> selectWayTestSix(int wayid, int id, int a, int b, int c, int d, int e, int f,int g,int h,int i, Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("id", id);
		parms.put("a", a);
		parms.put("b", b);
		parms.put("c", c);
		parms.put("d", d);
		parms.put("e", e);
		parms.put("f", f);
		
		parms.put("g", g);
		parms.put("h", h);
		parms.put("i", i);
		
		String statement="com.bengbeng.cbhbit.mapping.select.selectWayTestSix";
		getSession();
		List<Way> way =new ArrayList<Way>();
		way = session.selectList(statement,parms);
		closeSession();
		
		return way;
	}

	@Override
	public List<Way> selectWayTestNine(int wayid, int a, int b, int c, int d, int e, int f, int g, int h, int i,
			Settings set) {
		Map<String, Object> parms = new HashMap<String, Object>();
		String wayName=null;
		if (wayid<10)
			wayName="way_0"+wayid+set.getsuffix();
		else
			wayName="way_"+wayid+set.getsuffix();
		parms.put("way", wayName);
		parms.put("a", a);
		parms.put("b", b);
		parms.put("c", c);
		parms.put("d", d);
		parms.put("e", e);
		parms.put("f", f);		
		parms.put("g", g);
		parms.put("h", h);
		parms.put("i", i);
		
		String statement="com.bengbeng.cbhbit.mapping.select.selectWayTestNine";
		getSession();
		List<Way> way =new ArrayList<Way>();
		way = session.selectList(statement,parms);
		closeSession();
		
		return way;
	}

}
