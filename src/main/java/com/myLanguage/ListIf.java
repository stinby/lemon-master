package com.myLanguage;

import java.util.HashSet;
import java.util.Set;

public class ListIf {
	public void addCondition(Boolean condition,Object mythis,String funName){
		conditionMyIfs.add(new ConditionMyIf(condition,mythis,funName)); 
	}
	private Set<ConditionMyIf> conditionMyIfs=new HashSet<ConditionMyIf>();

	public Set<ConditionMyIf> getConditionMyIfs() {
		return conditionMyIfs;
	}

	public void setConditionMyIfs(Set<ConditionMyIf> conditionMyIfs) {
		this.conditionMyIfs = conditionMyIfs;
	}
	
	
}
