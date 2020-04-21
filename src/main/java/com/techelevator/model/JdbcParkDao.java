package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<>();
		String sql = "SELECT * FROM park;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		while (result.next()) {
			parks.add(mapRowToPark(result));
		}
		return parks;
	}

	private Park mapRowToPark(SqlRowSet result) {
		Park p = new Park();
			p.setParkCode(result.getString("parkcode"));
			p.setParkName(result.getString("parkname"));
			p.setState(result.getString("state"));
			p.setAcreage(result.getInt("acreage"));
			p.setElevationInFeet(result.getInt("elevationinfeet"));
			p.setMilesOfTrail(result.getDouble("milesoftrail"));
			p.setNumberOfCampsites(result.getInt("numberofcampsites"));
			p.setClimate(result.getString("climate"));
			p.setYearFounded(result.getInt("yearfounded"));
			p.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
			p.setInspirationalQuote(result.getString("inspirationalquote"));
			p.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
			p.setParkDescription(result.getString("parkdescription"));
			p.setEntryFee(result.getInt("entryfee"));
			p.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));
			p.setLatitude(result.getDouble("latitude"));
			p.setLongitude(result.getDouble("longitude"));
		return p;
	}

	@Override
	public Park getParkByCode(String parkCode) {
		String sql = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkCode);
		result.next();
		Park p = mapRowToPark(result);
		return p;
	}

}
