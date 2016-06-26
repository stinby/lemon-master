package com.myLanguage;

import java.lang.reflect.Method;
import java.util.List;
public class Myif<T> {
	private T yesOrNoRet(ListIf list,int view){
		for(Object obj:list.getConditionMyIfs()){
			ConditionMyIf conditionMyIf=(ConditionMyIf)obj;
			Method getMethod = null;
			try {
				if(conditionMyIf.getCondition()){
					getMethod = conditionMyIf.getMythis().getClass().getMethod(conditionMyIf.getFunName());
					if(view==0)
						return (T)getMethod.invoke(conditionMyIf.getMythis());
					else
						getMethod.invoke(conditionMyIf.getMythis());
				}
			} catch (Exception e) {
			}
		}
		return null;
	}
	public  void myIf(ListIf list){
		yesOrNoRet(list,1);
	}
	public  T myElseIf(ListIf list){
		return yesOrNoRet(list,0);
	}
}
