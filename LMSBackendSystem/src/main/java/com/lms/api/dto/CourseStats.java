package com.lms.api.dto;



public class CourseStats {
private long numberOfModules;
private long numberOfVideos;
private String contentDuration;





public String getContentDuration() {
	return contentDuration;
}
public void setContentDuration(String contentDuration) {
	this.contentDuration = contentDuration;
}
public long getNumberOfModules() {
	return numberOfModules;
}
public void setNumberOfModules(long numberOfModules) {
	this.numberOfModules = numberOfModules;
}
public long getNumberOfVideos() {
	return numberOfVideos;
}
public void setNumberOfVideos(long numberOfVideos) {
	this.numberOfVideos = numberOfVideos;
}



}
