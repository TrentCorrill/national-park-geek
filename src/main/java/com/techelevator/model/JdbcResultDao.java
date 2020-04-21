package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcResultDao implements ResultDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcResultDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Result> getResults() {
		List<Result> order = new ArrayList<>();
		
		String sql ="SELECT  park.parkname, COUNT(survey_result.parkcode) votes "+ 
					"FROM survey_result " +
					"JOIN park ON survey_result.parkcode = park.parkcode " +
					"GROUP BY survey_result.parkcode, park.parkname " +
			        "HAVING COUNT(survey_result.parkcode) > 0 " +
					"ORDER BY votes desc ";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
			while (results.next()) {
				order.add(mapRowToResult(results));
			}
		return order;
	}
	
	private Result mapRowToResult(SqlRowSet results) {
		Result r = new Result();
		r.setCount(results.getInt("votes"));
		r.setName(results.getString("parkname"));
		return r;
	}
	
}
