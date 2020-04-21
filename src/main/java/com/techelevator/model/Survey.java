package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.techelevator.model.enums.State;

public class Survey {
	private int surveyId;
	
	
	private String parkCode;
	
	@NotBlank
	@Email
	private String emailAddress;
	
	private String state;
	
	private String activityLevel;
	

	private List<Park> parks;
	

	public int getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}


	public String getParkCode() {
		return parkCode;
	}


	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getActivityLevel() {
		return activityLevel;
	}


	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public List<Park> getParks() {
		return parks;
	}

	public void setParks(List<Park> parks) {
		this.parks = parks;
	}

}