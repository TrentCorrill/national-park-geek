package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.CaseUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.model.enums.TemperatureCondition;
import com.techelevator.model.enums.WeatherCondition;

public class Weather {

	private int low;
	private int high;
	private String icon;

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = (int) low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = (int) high;
	}

	public String getImageUrl() {
		return "img/weather/" + CaseUtils.toCamelCase(icon, false, ' ') + ".png";
	}

	public String getIcon() {
		String result = icon.replace('-', ' ');
		result=result.replace("day", "");
		String capitalized = result.substring(0, 1).toUpperCase();
		result = capitalized + result.substring(1);
		return result;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int convertToCelcius(int temp) {
		double newTemp = temp - 32;
		newTemp *= (0.55555555555);
		return (int) newTemp;

	}

	public String formatTemp(int temp, int tempPreference) {
		String formattedTemp = "";
		if (tempPreference == 0) {
			formattedTemp += temp + "° Fahrenheit";
		} else if (tempPreference == 1) {
			System.out.println(temp + ":" + convertToCelcius(temp));
			formattedTemp = (convertToCelcius(temp)) + "° Celcius";
		}
		return formattedTemp;

	}

	public List<String> getSuggestions() {
		List<String> results = new ArrayList<>();
		results.addAll(WeatherCondition.suggestions(icon));
		results.addAll(TemperatureCondition.suggestions(low, high));
		return results;
	}

}
