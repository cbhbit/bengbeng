package com.bengbeng.cbhbit.dao;

import com.bengbeng.cbhbit.domain.Settings;

public interface WayMaxSameCountDao {
	public int getMaxSameCount(int samecountid,int wayid,Settings set);
	public void updateMaxSameCount(int samecountid,int max_same_count,int wayid,Settings set);

}
