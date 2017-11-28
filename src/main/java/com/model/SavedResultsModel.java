package com.model;

import java.util.List;

public class SavedResultsModel {
	public List<FrameworkResultsModel> frm;
	public List<PerformanceExpectationsResultModel> perm;
	public String title;
	public String lessonPlan;
	public int lid;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLessonPlan() {
		return lessonPlan;
	}
	public void setLessonPlan(String lessonPlan) {
		this.lessonPlan = lessonPlan;
	}
	public List<FrameworkResultsModel> getFrm() {
		return frm;
	}
	public void setFrm(List<FrameworkResultsModel> frm) {
		this.frm = frm;
	}
	public List<PerformanceExpectationsResultModel> getPerm() {
		return perm;
	}
	public void setPerm(List<PerformanceExpectationsResultModel> perm) {
		this.perm = perm;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
	

}
