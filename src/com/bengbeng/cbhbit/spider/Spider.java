package com.bengbeng.cbhbit.spider;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.bengbeng.cbhbit.dao.impl.RealnumberDaoImpl;
import com.bengbeng.cbhbit.domain.Realnumber;
import com.bengbeng.cbhbit.domain.Remainders;
import com.bengbeng.cbhbit.domain.Settings;

public class Spider {
	
	public  Document getDocument (String url){
        try {
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static Remainders[] getResourceRemainders(Settings set) {
		Remainders[] me = new Remainders[500];
        Spider sp = new Spider();
        
        Document doc = sp.getDocument(set.getURL());
        // 获取目标HTML代码
        Elements elements = doc.select("table").select("tr");
        for (int i = set.getTABLE_HEAD(); i < set.getTABLE_MAX(); i++) {
        	//获取每一行的列
        	Elements tds = elements.get(i).select("td"); 
        	me[i-set.getTABLE_HEAD()]=new Remainders();
        	me[i-set.getTABLE_HEAD()].setId(Integer.parseInt(tds.get(0).text()));         	
        	me[i-set.getTABLE_HEAD()].setThree(Integer.parseInt(tds.get(set.getTABLE_THREE()).text()));
        	me[i-set.getTABLE_HEAD()].setFour(Integer.parseInt(tds.get(set.getTABLE_FOUR()).text()));
        	me[i-set.getTABLE_HEAD()].setFive(Integer.parseInt(tds.get(set.getTABLE_FIVE()).text()));      	      	
        }
        return me;
    }
	
	public static Realnumber[] getResourceRealnumber(Settings set) {
		Realnumber[] re=new Realnumber[500];
		Spider sp = new Spider();
        
		RealnumberDaoImpl reDaoImpl=new RealnumberDaoImpl();
		
        Document doc = sp.getDocument(set.getURL());
        // 获取目标HTML代码
        Elements elements = doc.select("table").select("tr");
        for(int i = set.getTABLE_HEAD(); i < set.getTABLE_MAX(); i++){
			 Elements tds = elements.get(i).select("td");
			 re[i-set.getTABLE_HEAD()]=new Realnumber();
			 re[i-set.getTABLE_HEAD()].setId(Integer.parseInt(tds.get(0).text())); 
			 for(int j=set.getTABLE_START();j<=set.getTABLE_END();j++){				 
				 if(tds.get(j).text().length()>0)
					 re[i-set.getTABLE_HEAD()].setRealnumber(Integer.parseInt(tds.get(j).text())) ;
			 }
			 re[i-set.getTABLE_HEAD()].setPercent(reDaoImpl.doGetPercent(re[i-set.getTABLE_HEAD()].getRealnumber(), set));
		 }
		return re;
		
	}
	public static Realnumber[] getResourceRealnumberFromImg(Settings set){
		Realnumber[] re=new Realnumber[500];
		Spider sp = new Spider();
		Document doc = sp.getDocument(set.getURL());
		Elements elements = doc.select("table").select("tr");
		RealnumberDaoImpl reDaoImpl=new RealnumberDaoImpl();
		for(int i = set.getTABLE_HEAD(); i < set.getTABLE_MAX(); i++){
			Elements tds = elements.get(i).select("td");
			re[i-set.getTABLE_HEAD()]=new Realnumber();
			re[i-set.getTABLE_HEAD()].setId(Integer.parseInt(tds.get(0).text())); 
        	String str=null;
        	for(int j=set.getTABLE_START();j<=set.getTABLE_END();j++){
        		str+=tds.get(j).html();
        	}
        	re[i-set.getTABLE_HEAD()].setRealnumber(Integer.parseInt(str.substring(str.indexOf(set.getIMG_START())+set.getIMG_NUM(),str.indexOf(set.getIMG_END()))));
        	re[i-set.getTABLE_HEAD()].setPercent(reDaoImpl.doGetPercent(re[i-set.getTABLE_HEAD()].getRealnumber(), set));
		}
		return re;
	}

}
