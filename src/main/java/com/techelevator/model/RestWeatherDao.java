package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestWeatherDao {

	private static class DarkSkyDataPoint {
		public String icon;
		public double temperatureLow;
		public double temperatureHigh;

	}

	private static class DarkSkyDataBlock {
		public List<DarkSkyDataPoint> data;
	}

	private static class DarkSkyForecast {
		public DarkSkyDataBlock daily;
	}

	private static final String BASE_URL = "https://api.darksky.net/forecast/f702b1e6ec01761122e544004653560d/";
	private RestTemplate restTemplate = new RestTemplate();

	public List<Weather> getDailyWeather(double latitude, double longitude) {
		String url = BASE_URL + latitude + "," + longitude;
		DarkSkyForecast forecast = restTemplate.getForObject(url, DarkSkyForecast.class);

		List<Weather> results = new ArrayList<>();
		for (DarkSkyDataPoint dp : forecast.daily.data) {
			Weather w = new Weather();
			w.setHigh(dp.temperatureHigh);
			w.setLow(dp.temperatureLow);
			w.setIcon(dp.icon);
			results.add(w);
		}
		return results;
	}

}
