package com.bengbeng.cbhbit.domain;

public class Settings {
	private int id;
	private String suffix;
	private int WAYMAX;
	private String URL;
	private int TABLE_HEAD;
	private int TABLE_MAX;
	private int TABLE_THREE;
	private int TABLE_FOUR;
	private int TABLE_FIVE;
	private String remark;
	private int TABLE_START;
	private int TABLE_END;
	private String IMG_START;
	private String IMG_END;
	private int IMG_NUM;
	private int SAMECOUNT_MAX;
	
	public int getSAMECOUNT_MAX() {
		return SAMECOUNT_MAX;
	}
	public String getIMG_START() {
		return IMG_START;
	}
	public String getIMG_END() {
		return IMG_END;
	}
	public int getIMG_NUM() {
		return IMG_NUM;
	}
	public int getTABLE_START() {
		return TABLE_START;
	}
	public int getTABLE_END() {
		return TABLE_END;
	}
	public int getid() {
		return id;
	}
	public String getsuffix() {
		return suffix;
	}
	public String getremark() {
		return remark;
	}
	public int getWAYMAX() {
		return WAYMAX;
	}
	public void setWAYMAX(int wAYMAX) {
		WAYMAX = wAYMAX;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getTABLE_HEAD() {
		return TABLE_HEAD;
	}
	public void setTABL_EHEAD(int tABLE_HEAD) {
		TABLE_HEAD = tABLE_HEAD;
	}
	public int getTABLE_MAX() {
		return TABLE_MAX;
	}
	public void setTABLE_MAX(int tABLE_MAX) {
		TABLE_MAX = tABLE_MAX;
	}
	public int getTABLE_THREE() {
		return TABLE_THREE;
	}
	public void setTABLE_THREE(int tABLE_THREE) {
		TABLE_THREE = tABLE_THREE;
	}
	public int getTABLE_FOUR() {
		return TABLE_FOUR;
	}
	public void setTABLE_FOUR(int tABLE_FOUR) {
		TABLE_FOUR = tABLE_FOUR;
	}
	public int getTABLE_FIVE() {
		return TABLE_FIVE;
	}
	public void setTABLE_FIVE(int tABLE_FIVE) {
		TABLE_FIVE = tABLE_FIVE;
	}

}
