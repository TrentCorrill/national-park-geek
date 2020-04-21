package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.ParkDao;
import com.techelevator.model.Park;
import com.techelevator.model.RestWeatherDao;
import com.techelevator.model.Result;
import com.techelevator.model.ResultDao;
import com.techelevator.model.Survey;
import com.techelevator.model.SurveyDao;
import com.techelevator.model.enums.State;

@Controller
public class HomeController {

	@Autowired
	ParkDao parkDao;

	@Autowired
	SurveyDao surveyDao;

	@Autowired
	ResultDao resultDao;

	@Autowired
	RestWeatherDao restWeatherDao;

	@RequestMapping("/")
	public String displayHomePage(ModelMap modelMap) {
		List<Park> parks = parkDao.getAllParks();
		modelMap.addAttribute("parks", parks);

		return "homePage";
	}

	@RequestMapping(path = "/details")
	public String displayDetailPage(ModelMap modelMap, @RequestParam String parkCode, HttpSession session) {
		if (session.getAttribute("tempPreference") == null) {
			session.setAttribute("tempPreference", 0);
		}
		System.out.println(session.getAttribute("tempPreference"));
		Park park = parkDao.getParkByCode(parkCode);
		modelMap.addAttribute("park", park);
		modelMap.addAttribute("weatherList", restWeatherDao.getDailyWeather(park.getLatitude(), park.getLongitude()));
		return "detailPage";
	}

	@RequestMapping(path = "/updateTempPref", method = RequestMethod.POST)
	public String changePreference(@RequestParam String parkCode, @RequestParam int tempPreference,
			HttpSession session) {
		session.setAttribute("tempPreference", tempPreference);
		System.out.println(tempPreference);
		return "redirect:/details?parkCode=" + parkCode;
	}

	@RequestMapping("/survey")
	public String displaySurveyPage(ModelMap modelMap, HttpSession session) {
		List<Park> parks = parkDao.getAllParks();

		if (session.getAttribute("states") == null) {
			List<State> states = new ArrayList<>();
			for (State s : State.values()) {
				states.add(s);
			}

			session.setAttribute("states", states);
		}
		if (!modelMap.containsAttribute("surveyData")) {
			Survey survey = new Survey();
			survey.setParks(parks);
			modelMap.put("surveyData", survey);
		}
		modelMap.addAttribute("parks", parks);
		return "surveyPage";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute Survey survey, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors() || surveyDao.validateSurveyInputs(survey)==false ) {
			ra.addFlashAttribute("surveyData", survey);
			ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyData", result);
			return "redirect:/survey";
		} else {
			surveyDao.saveSurvey(survey);
			return "redirect:/results";
		}
	}

	@RequestMapping("results")
	public String showResults(ModelMap modelMap) {
		List<Result> results = resultDao.getResults();
		modelMap.addAttribute("results", results);
		return "surveyResults";
	}
}
