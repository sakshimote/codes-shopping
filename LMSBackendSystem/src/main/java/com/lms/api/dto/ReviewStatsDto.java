package com.lms.api.dto;

public class ReviewStatsDto {
	private double totalRating;
private double fiveStarrating;
private double fourStarRating;
private double threeStarRating;
private double twoStarRating;
private double oneStarRating;
public double getFiveStarrating() {
	return fiveStarrating;
}
public void setFiveStarrating(double fiveStarrating) {
	this.fiveStarrating = fiveStarrating;
}
public double getFourStarRating() {
	return fourStarRating;
}
public double getTotalRating() {
	return totalRating;
}
public void setTotalRating(double totalRating) {
	this.totalRating = totalRating;
}
public void setFourStarRating(double fourStarRating) {
	this.fourStarRating = fourStarRating;
}
public double getThreeStarRating() {
	return threeStarRating;
}
public void setThreeStarRating(double threeStarRating) {
	this.threeStarRating = threeStarRating;
}
public double getTwoStarRating() {
	return twoStarRating;
}
public void setTwoStarRating(double twoStarRating) {
	this.twoStarRating = twoStarRating;
}
public double getOneStarRating() {
	return oneStarRating;
}
public void setOneStarRating(double oneStarRating) {
	this.oneStarRating = oneStarRating;
}


}
