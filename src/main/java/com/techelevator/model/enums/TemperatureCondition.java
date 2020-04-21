package com.techelevator.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum TemperatureCondition {
	HOT("Bring an extra gallon of water!\n"), 
	VARIABLE("Wear breathable layers!\n"),
	COLD("Wear extra socks!\n");

	private String suggestion;

	private TemperatureCondition(String suggestion) {
		this.suggestion = suggestion;
	}

	public static List<String> suggestions(int low, int high) {
		List<String> results=new ArrayList<>();
		
		if (high>75) {
			results.add(HOT.suggestion);
		}
		if ((high-low)>20) {
			results.add(VARIABLE.suggestion);
		}
		if (low<20) {
			results.add(COLD.suggestion);
		}
		return results;
	}
}
