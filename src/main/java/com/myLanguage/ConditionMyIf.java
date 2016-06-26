package com.myLanguage;

public class ConditionMyIf {
	private Boolean condition;
	private Object mythis;
	private String funName;
	public ConditionMyIf(Boolean condition,Object mythis,String funName){
		this.condition=condition;
		this.mythis=mythis;
		this.funName=funName;
	}
	public Boolean getCondition() {
		return condition;
	}
	public void setCondition(Boolean condition) {
		this.condition = condition;
	}
	public Object getMythis() {
		return mythis;
	}
	public void setMythis(Object mythis) {
		this.mythis = mythis;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
}
