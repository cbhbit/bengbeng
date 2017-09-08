package com.bengbeng.cbhbit.dao;

import java.util.List;

import com.bengbeng.cbhbit.domain.Settings;
import com.bengbeng.cbhbit.domain.Way;

public interface WayDao {
	public Way getWayByID(int id,int wayid,Settings set);
	public void doInsertWayByID(int id,int wayid,Settings set);
	public void doUpdateWayByID(int id,int wayid,Settings set);
	public int doGetMaxRepeat(int i,int wayid,Settings set);
	public int getMaxSameCount(int sameCountId,int wayid,Settings set);
	public void deleteById(int id,int wayid,Settings set);
	public int selectMaxId(int wayid,Settings set);
	public List<Way> selectWayTestSix(int wayid,int id,int a,int b,int c,int d,int e,int f,int g,int h,int i,Settings set);
	
	public List<Way> selectWayTestNine(int wayid,int a,int b,int c,int d,int e,int f,int g,int h,int i,Settings set);
}
