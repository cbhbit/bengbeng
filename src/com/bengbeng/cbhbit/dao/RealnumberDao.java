package com.bengbeng.cbhbit.dao;

import java.util.List;

import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public interface RealnumberDao {
	public void doAddRealnumber(Remainders re,Settings set);
	public int doGetPercent(int num,Settings set);
	public void insertRealnumber(Realnumber re,Settings set);
	public Realnumber getMaxRecentRealnumber(Settings set);
	public void deleteById(int id,Settings set);
	public int selectMaxId(Settings set);
	public int selectRealNumberByID(int id,Settings set);
	public List<Realnumber> selectRealNumberTest(int id,int a,int b,int c,int d,Settings set);
	
	public List<Realnumber> selectRealNumberTestThree(int id,int a,int b,int c,Settings set);
	
}
