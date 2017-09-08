package com.bengbeng.cbhbit.dao;

import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public interface StatisticsDao {
	public void doAddStatistics(Remainders re,Settings set);
	public void deleteById(int id,Settings set);
	public int selectMaxId(Settings set);
}
