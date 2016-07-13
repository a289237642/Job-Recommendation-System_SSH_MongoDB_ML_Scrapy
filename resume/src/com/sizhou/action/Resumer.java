package com.sizhou.action;

import java.util.Arrays;
import java.util.Date;

public class Resumer {
	//性别
	private String sex;
	//研究生排名
	private String pm;
	//熟悉技能
	private String[] skill;
	
	//项目个数
	private String xg;
	//有无大赛经历
	private String flag;
	//论文发表等级
	private String[] level;
	//社团经历
	private String st;
	@Override
	public String toString() {
		return "Resumer [sex=" + sex + ", pm=" + pm + ", skill="
				+ Arrays.toString(skill) + ", xg=" + xg + ", flag=" + flag
				+ ", level=" + Arrays.toString(level) + ", st=" + st + ", sx="
				+ sx + ", cs=" + cs + "]";
	}
	//实习经历
	private String sx;
	//测试经历
	private String cs;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String[] getSkill() {
		return skill;
	}
	public void setSkill(String[] skill) {
		this.skill = skill;
	}
	public String getXg() {
		return xg;
	}
	public void setXg(String xg) {
		this.xg = xg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String[] getLevel() {
		return level;
	}
	public void setLevel(String[] level) {
		this.level = level;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	
	
	
}
