package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.enums.State;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Survey> getAllSurveys() {
		List<Survey> surveys = new ArrayList<>();
		String sql = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			surveys.add(mapRowToSurvey(results));
		}
		return surveys;
	}

	@Override
	public void saveSurvey(Survey survey) {
			String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
					+ "VALUES(?,?,?,?)";
			jdbcTemplate.update(sql, survey.getParkCode(), survey.getEmailAddress(), survey.getState(),
					survey.getActivityLevel());
	}
	
	@Override
	public boolean validateSurveyInputs(Survey survey) {
		boolean result = false;
		if (validateActivityLevel(survey.getActivityLevel()) && validatePark(survey.getParkCode()) && validateState(survey.getState())) {
			result = true;
		}
		return result;
	}

	private Survey mapRowToSurvey(SqlRowSet results) {
		Survey s = new Survey();
		s.setSurveyId(results.getInt("surveyid"));
		s.setParkCode(results.getString("parkcode"));
		s.setEmailAddress(results.getString("emailaddress"));
		s.setState(results.getString("state"));
		s.setActivityLevel(results.getString("activitylevel"));
		return s;
	}



	private boolean validatePark(String parkCode) {
		boolean result = false;
		String sql = "SELECT * FROM park WHERE parkcode = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		if (results.next()) {
			result = true;
		} else {
			System.out.println("park couldn't be validated");
		}
		return result;
	}

	private boolean validateState(String state) {
		boolean result = false;
		List<String> stateNames = new ArrayList<>();
		for (State s : State.values()) {
			stateNames.add(s.getName());
		}
		if (stateNames.contains(state)) {
			result = true;
		} else {
			System.out.println("state couldn't be validated");
		}
		return result;
	}

	private boolean validateActivityLevel(String activityLevel) {
		boolean result = false;
		String[] levels = { "active", "inactive", "extremely active", "sedentary" };
		for (String s : levels) {
			if (s.equals(activityLevel)) {
				result = true;
			}
		}
		if (!result) {
			System.out.println("activity level couldn't be validated");
		}
		return result;
	}

}
