package com.bengbeng.cbhbit.service;

import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bengbeng.cbhbit.dao.impl.RealnumberDaoImpl;
import com.bengbeng.cbhbit.dao.impl.RemainderDaoImpl;
import com.bengbeng.cbhbit.dao.impl.SettingsDaoImpl;
import com.bengbeng.cbhbit.dao.impl.WayDaoImpl;
import com.bengbeng.cbhbit.dao.impl.WayMaxSameCountImpl;
import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;
import com.bengbeng.cbhbit.domain.Way;

public class Result {
	public void saySingleOut(int i,Way way,int wayid,Settings set){
		WayDaoImpl waydaoimpl=new WayDaoImpl();
		String wayName;
		if (wayid<10)
			wayName="way_0";
		else
			wayName="way_";
		int num=0;
		switch (i){
		case 1: num=way.getSame_count1(); break;
		case 2: num=way.getSame_count2(); break;
		case 3: num=way.getSame_count3(); break;
		case 4: num=way.getSame_count4(); break;
		case 5: num=way.getSame_count5(); break;
		case 6: num=way.getSame_count6(); break;
		case 7: num=way.getSame_count7(); break;
		case 8: num=way.getSame_count8(); break;
		case 9: num=way.getSame_count9(); break;
		case 10: num=way.getSame_count10(); break;
		case 11: num=way.getSame_count11(); break;
		case 12: num=way.getSame_count12(); break;
		}
		WayMaxSameCountImpl waymaxsamecount=new WayMaxSameCountImpl();
		if(num>=waydaoimpl.doGetMaxRepeat(i, wayid,set)-2){
			Toolkit.getDefaultToolkit().beep();
			System.out.print("已连续出现Same_count"+i+" "+num+"次,历史最大次数是"+waydaoimpl.doGetMaxRepeat(i, wayid,set));
			System.out.println("第"+(way.getId()+1)+" 期应投"+wayName+wayid+"_"+waydaoimpl.getWayByID(way.getId()-i+1, wayid,set).getWay());
			if(num>waymaxsamecount.getMaxSameCount(i,wayid, set)){
				System.out.print("更新前最大值是"+waymaxsamecount.getMaxSameCount(i,wayid, set));
				waymaxsamecount.updateMaxSameCount(i,num, wayid, set);
				System.out.println("更新后最大值是"+waymaxsamecount.getMaxSameCount(i,wayid, set));
			}
		}
	}
	
	public void sayOut(Way way,int wayid,Settings set){
		for(int i=1;i<=set.getSAMECOUNT_MAX();i++){
			saySingleOut(i,way,wayid,set);
		}
	}

	public void doTestOne(int t) throws Throwable{
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		Database db=new Database();
		//db.initDataBase(set);
		db.insert(set);
		RemainderDaoImpl reDaoImpl=new RemainderDaoImpl();
		
		Remainders user = reDaoImpl.getMaxRemainders(set);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		System.out.print(time+" "+set.getremark());
        System.out.println(user); 
        
        Result result=new Result();
        WayDaoImpl waydaoimpl=new WayDaoImpl();
        for(int i=0;i<=set.getWAYMAX();i++){
        	result.sayOut(waydaoimpl.getWayByID(user.getId(), i,set), i,set);
        }
	}
	public void doNotUpdateTest(int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		//Database db=new Database();
		//db.initDataBase(set);
		//db.insert(set);
		RemainderDaoImpl reDaoImpl=new RemainderDaoImpl();
		
		Remainders user = reDaoImpl.getMaxRemainders(set);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		System.out.print(time+" "+set.getremark());
        System.out.println(user); 
        
        Result result=new Result();
        WayDaoImpl waydaoimpl=new WayDaoImpl();
        for(int i=0;i<=set.getWAYMAX();i++){
        	result.sayOut(waydaoimpl.getWayByID(user.getId(), i,set), i,set);
        }
	}
	
	public void testStatistics(int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		
		for(int j=0;j<=set.getWAYMAX();j++){
		int a=0,b=0,c=0;
		WayDaoImpl waydaoimpl=new WayDaoImpl();
		for(int i=409646;i<=437100;i++){
			int[][] finalST=testOneStatistics(i,set);		
			if (finalST[j][0]==0){
				//System.out.println("没有结果");
				a++;
			}
			else{
				if(waydaoimpl.getWayByID(i+1, 0, set).getWay()==finalST[j][1])
					b++;
				else
					c++;
				//System.out.println(finalST[1]);
			}
		}
		
		if(j<10)
			System.out.print("Way_0"+j+" ");
		else
			System.out.print("Way_"+j+" ");
		
		System.out.print("共统计"+(a+b+c)+"期数据：");
		System.out.println(a+"期没有结果；"+b+"期结果错误；"+c+"期结果正确。");
		
		}
		
	}
	
	public void buFanBei(int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		
		for(int j=0;j<=set.getWAYMAX();j++){
			WayDaoImpl waydaoimpl=new WayDaoImpl();
			int[][] finalST=testOneStatistics(waydaoimpl.selectMaxId(j, set),set);
			
			if(finalST[j][0]==1)
				System.out.println((waydaoimpl.selectMaxId(j, set)+1)+"期有可能出way_"+j+"_"+finalST[j][1]);
		}				
		
	}
	
	public int[][] testOneStatistics(int idCurrent,Settings set){
		int NMAX=7;
		int sameCountStart=1;
		int sameCountStop=12;
		
		int[][][] st=new int[set.getWAYMAX()+1][sameCountStop-sameCountStart+1][2];
		WayDaoImpl waydaoimpl=new WayDaoImpl();
		int[][] finalST=new int[set.getWAYMAX()+1][2];
		
		for(int i=0;i<=set.getWAYMAX();i++){
			for(int j=sameCountStart;j<=sameCountStop;j++){
				st[i][j-sameCountStart][0]=waydaoimpl.getWayByID(idCurrent-j+1, i, set).getId();
				st[i][j-sameCountStart][1]=waydaoimpl.getWayByID(idCurrent-j+1, i, set).getWay();
				//System.out.println(st[j-sameCountStart][0]+" "+st[j-sameCountStart][1]);
			}
						
			int a=0,b=0,c=0;
			for(int m=0;m<st[i].length;m++){
				if(st[i][m][1]==0)
					a++;
				else if(st[i][m][1]==1)
					b++;
				else if(st[i][m][1]==2)
					c++;
			}
			if(a>=NMAX){
				//System.out.println("0 "+a);
				finalST[i][0]=1;
				finalST[i][1]=0;
			}
			if(b>=NMAX){
				//System.out.println("1 "+b);
				finalST[i][0]=1;
				finalST[i][1]=1;
			}
			if(c>=NMAX){
				//System.out.println("2 "+c);
				finalST[i][0]=1;
				finalST[i][1]=2;
			}
			if(a<NMAX && b<NMAX && c<NMAX){
				//System.out.println("未有合适结果");
				finalST[i][0]=0;
				finalST[i][1]=-1;
			}
		}
		
		
		
		return finalST;
	}
	
	public void testFour(int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		
		int smax=0,s=0;
		int countright=0,countwrong=0,countnone=0;
		RealnumberDaoImpl redaoimpl=new RealnumberDaoImpl();
		for(int i=410629;i<=434187;i++){
			List<Realnumber> re=new ArrayList<Realnumber>();
			re=redaoimpl.selectRealNumberTest(i,redaoimpl.selectRealNumberByID(i-3, set), redaoimpl.selectRealNumberByID(i-2, set), redaoimpl.selectRealNumberByID(i-1, set), redaoimpl.selectRealNumberByID(i, set), set);
			int real=redaoimpl.selectRealNumberByID(i+1, set);
			
			int yuce=0;
			if(re.isEmpty())
				countnone++;
			else {
				Realnumber r=new Realnumber();
				r=re.get(re.size()-1);
				yuce=r.getRealnumber();
				//System.out.println(yuce);
				if(yuce%3==real%3){
					countwrong++;
					s++;
					if(s>smax)
						smax=s;
				}
				else{
					countright++;
					s=0;
				}
			}
				
		}
		
		System.out.print("共统计"+(countright+countwrong+countnone)+"期数据");
		System.out.print(" "+countright+"期正确 "+countwrong+"期错误"+countnone+"未有结果");
		System.out.println(";最大错误连续数为"+smax);
	}
	
	public void testThree(int t,int NNN){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
				
		int smax=0,s=0;
		int countright=0,countwrong=0,countnone=0;
		RealnumberDaoImpl redaoimpl=new RealnumberDaoImpl();
		for(int i=430629;i<=430629+9;i++){
			List<Realnumber> re=new ArrayList<Realnumber>();
			re=redaoimpl.selectRealNumberTestThree(i, redaoimpl.selectRealNumberByID(i-2, set), redaoimpl.selectRealNumberByID(i-1, set), redaoimpl.selectRealNumberByID(i, set), set);
			int real=redaoimpl.selectRealNumberByID(i+1, set);
			/**
			System.out.print(re.get(re.size()-3).getRealnumber()+" ");
			System.out.print(re.get(re.size()-2).getRealnumber()+" ");
			System.out.println(re.get(re.size()-1).getRealnumber());
			**/
			int yuce=0;
			//int bb=0;
			for(int kk=0;re.size()<3;kk++){//for(int kk=0;kk<NNN;kk++){
				int a=0,b=0,c=0;
				
				if(re.isEmpty()||(re.size()<3))//if(re.isEmpty()||(re.size()<3))
					;//bb++;
				else {
					a=re.get(re.size()-3).getRealnumber();
					b=re.get(re.size()-2).getRealnumber();
					c=re.get(re.size()-1).getRealnumber();
					re=redaoimpl.selectRealNumberTestThree(i,a,b,c,set);
					//System.out.print(re.get(re.size()-3).getRealnumber()+" ");
					//System.out.print(re.get(re.size()-2).getRealnumber()+" ");
					//System.out.println(re.get(re.size()-1).getRealnumber());
				}
			}
			if(re.isEmpty())
				countnone++;
			else {
				yuce=re.get(re.size()-1).getRealnumber();
				System.out.println(yuce);
				if(yuce%3==real%3){
					countwrong++;
					s++;
					if(s>smax)
						smax=s;
				}
				else{
					countright++;
					s=0;
				}
			}
				
		}
		
		System.out.print("共统计"+(countright+countwrong+countnone)+"期数据");
		System.out.print(" "+countright+"期正确 "+countwrong+"期错误"+countnone+"未有结果");
		System.out.println(";最大错误连续数为"+smax);
	}
	
	public void testWaySix(int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		WayDaoImpl waydaoimpl=new WayDaoImpl();
		
		int smax=0,s=0;
		int wayid=6;
		int countright=0,countwrong=0,countnone=0;
		for(int i=418629;i<=418629+9;i++){//434187
			List<Way> re=new ArrayList<Way>();
			re=waydaoimpl.selectWayTestSix(wayid, i, 
					waydaoimpl.getWayByID(i-8, wayid, set).getWay(),waydaoimpl.getWayByID(i-7, wayid, set).getWay(),
					waydaoimpl.getWayByID(i-6, wayid, set).getWay(),waydaoimpl.getWayByID(i-5, wayid, set).getWay(),
					waydaoimpl.getWayByID(i-4, wayid, set).getWay(), waydaoimpl.getWayByID(i-3, wayid, set).getWay(), 
					waydaoimpl.getWayByID(i-2, wayid, set).getWay(), waydaoimpl.getWayByID(i-1, wayid, set).getWay(), 
					waydaoimpl.getWayByID(i, wayid, set).getWay(), set);

			int real=waydaoimpl.getWayByID(i+1, wayid,set).getWay();
			
			int yuce=0;
			if(re.isEmpty())
				countnone++;
			else {
				yuce=re.get(re.size()-1).getWay();
				//System.out.println(yuce);
				if(yuce%3==real%3){
					countwrong++;
					s++;
					if(s>smax)
						smax=s;
				}
				else{
					countright++;
					s=0;
				}
			}
				
		}
		
		System.out.print("共统计"+(countright+countwrong+countnone)+"期数据");
		System.out.print(" "+countright+"期正确 "+countwrong+"期错误"+countnone+"未有结果");
		System.out.println(";最大错误连续数为"+smax);
	}
	
	public void testNine(int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		WayDaoImpl waydaoimpl=new WayDaoImpl();
		
		int smax=0,s=0;
		int wayid=6;
		int countright=0,countwrong=0,countnone=0;
		for(int i=434187-1;i<=434187;i++){//
			List<Way> re=new ArrayList<Way>();
			re=waydaoimpl.selectWayTestNine(wayid, 
					waydaoimpl.getWayByID(i-8, wayid, set).getWay(),waydaoimpl.getWayByID(i-7, wayid, set).getWay(),
					waydaoimpl.getWayByID(i-6, wayid, set).getWay(),waydaoimpl.getWayByID(i-5, wayid, set).getWay(),
					waydaoimpl.getWayByID(i-4, wayid, set).getWay(), waydaoimpl.getWayByID(i-3, wayid, set).getWay(), 
					waydaoimpl.getWayByID(i-2, wayid, set).getWay(), waydaoimpl.getWayByID(i-1, wayid, set).getWay(), 
					waydaoimpl.getWayByID(i, wayid, set).getWay(), set);

			int real=waydaoimpl.getWayByID(i+1, wayid,set).getWay();
			
			int yuce=0;
			if(re.isEmpty())
				countnone++;
			else {
				yuce=re.get(re.size()-1).getWay();
				if(yuce==real){
					countwrong++;
					s++;
					if(s>smax)
						smax=s;
				}
				else{
					countright++;
					s=0;
				}
			}
				
		}
		
		System.out.print("共统计"+(countright+countwrong+countnone)+"期数据");
		System.out.print(" "+countright+"期正确 "+countwrong+"期错误"+countnone+"未有结果");
		System.out.println(";最大错误连续数为"+smax);
	}
	
	public void testResultNine(int i,int t){
		SettingsDaoImpl set_dao_impl=new SettingsDaoImpl();
		Settings set=set_dao_impl.doGetSettings(t);
		WayDaoImpl waydaoimpl=new WayDaoImpl();		
		
		//int i=remainderdaoimpl.selectMaxId(set);
		int wayid=6;
		List<Way> re=new ArrayList<Way>();
		re=waydaoimpl.selectWayTestNine(wayid, 
				waydaoimpl.getWayByID(i-8, wayid, set).getWay(),waydaoimpl.getWayByID(i-7, wayid, set).getWay(),
				waydaoimpl.getWayByID(i-6, wayid, set).getWay(),waydaoimpl.getWayByID(i-5, wayid, set).getWay(),
				waydaoimpl.getWayByID(i-4, wayid, set).getWay(), waydaoimpl.getWayByID(i-3, wayid, set).getWay(), 
				waydaoimpl.getWayByID(i-2, wayid, set).getWay(), waydaoimpl.getWayByID(i-1, wayid, set).getWay(), 
				waydaoimpl.getWayByID(i, wayid, set).getWay(), set);
		if(re.isEmpty())
			System.out.println("没有预测出结果");
		else{
			System.out.println("第"+(i+1)+"期预测出现3余"+re.get(re.size()-1).getWay());
		}
	}
}
