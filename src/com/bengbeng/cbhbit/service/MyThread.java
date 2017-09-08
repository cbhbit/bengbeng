package com.bengbeng.cbhbit.service;

import java.util.Timer;
import java.util.TimerTask;

public class MyThread extends Thread{
	private int timebetween;
	private int i;
	
	public MyThread(int i,int timebetween){
		this.i=i;
		this.timebetween=timebetween;
	}
	@Override
	public void run(){
		final Result re=new Result();		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	try {
		        		System.out.println("======================================");
						re.doTestOne(i);			
					} catch (Throwable e) {
						e.printStackTrace();
					}					
		        }
		}, 0 , timebetween);
	}

}
