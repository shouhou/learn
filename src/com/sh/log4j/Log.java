package com.sh.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log {
	public static int getInterval(){
		String bankid="";
		if(bankid==null)
			return -1;
		if("".equals(bankid)){
			return -2;
		}
		int interval;
		try {
			interval = Integer.parseInt(bankid);
			return interval;
		} catch (Exception e) {
			return -3;
		}
	}
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		Logger log=Logger.getLogger(Log.class);
		log.info("hello");
		System.out.println(getInterval());
	}
}
