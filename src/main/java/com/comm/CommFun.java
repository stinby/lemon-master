package com.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mossle.auth.rs.AuthResource;

public class CommFun {
	
	public static Boolean usernameIsNullWriteLoger(String username,String msg,
			String value,int view){
		Logger logger = LoggerFactory.getLogger(AuthResource.class);
        if (username == null) {
        		if(view==0)
        			logger.error(msg);
        		else if(view ==1)
        			logger.warn(msg);
        		else if(view ==2)
        			logger.warn(msg,value);
            return true;
        }
        return false;
	}
	public static Boolean usernameIsNullWriteLoger(String username,String errMsg){
		return usernameIsNullWriteLoger(username,errMsg,"",0);
	}
	public static Boolean usernameIsNullWriteLogerWarn(String username,String msg){
		return usernameIsNullWriteLoger(username,msg,"",1);
	}
	public static Boolean usernameIsNullWriteLogerWarnByValue(String username,String msg,String value){
		return usernameIsNullWriteLoger(username,msg,value,2);
	}
}
