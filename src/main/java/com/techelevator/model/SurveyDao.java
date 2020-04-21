package com.techelevator.model;

import java.util.List;

public interface SurveyDao {
	
	public void saveSurvey(Survey survey);
	
	public List<Survey> getAllSurveys();
	
	public boolean validateSurveyInputs(Survey survey);

}
