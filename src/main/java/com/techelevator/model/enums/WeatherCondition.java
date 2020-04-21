package com.techelevator.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum WeatherCondition {
	SNOW("Pack snowshoes\n", "snow"), PARTLY_CLOUDY("Pack sunblock just in case\n", "partly-cloudy-day"),
	CLOUDY("Pack cloudblock\n", "cloudy"), SUNNY("Pack sunblock\n", "clear-day"),
	RAIN("Pack gear and wear waterproof shoes\n", "rain"),
	THUNDERSTORMS("Seek shelter, avoid exposed ridges\n", "thunderstorm");

	private String suggestion;
	private String forecast;

	private WeatherCondition(String suggestion, String forecast) {
		this.suggestion = suggestion;
		this.forecast = forecast;
	}

	public static List<String> suggestions(String forecast) {
		List<String> results = new ArrayList<>();
		for (WeatherCondition c : WeatherCondition.values()) {
			if (c.forecast.equals(forecast)) {
				results.add(c.suggestion);
			}
		}
		return results;
	}
}
