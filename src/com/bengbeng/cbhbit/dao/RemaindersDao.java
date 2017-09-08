package com.bengbeng.cbhbit.dao;

import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public interface RemaindersDao {
	public void doAddRemainders(Remainders re,Settings set);
	public Remainders getMaxRemainders(Settings set);
	public void doAddRemaindersFromRealnumber(Realnumber re,Settings set);
	public void deleteById(int id,Settings set);
	public int selectMaxId(Settings set);
}
