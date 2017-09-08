package com.bengbeng.cbhbit.domain;

public class Remainders {
	private int id;
	private int three;
	private int four;
	private int five;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getThree() {
		return three;
	}
	public void setThree(int three) {
		this.three = three;
	}
	public int getFour() {
		return four;
	}
	public void setFour(int four) {
		this.four = four;
	}
	public int getFive() {
		return five;
	}
	public void setFive(int five) {
		this.five = five;
	}
	@Override
	public String toString(){
		return "ตฺ"+id+"ฦฺ3ำเ"+three+",4ำเ"+four+",5ำเ"+five;
	}

}
