<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<img id="detail-image" src="${park.imageUrl}" />
<p>${park.inspirationalQuote}-${park.inspirationalQuoteSource}</p>

<h1>${park.parkName}</h1>

<p id="content-1" class="center">${park.parkName} is located in the
	beautiful state of ${park.state} it sits on ${park.acreage} acres
	acres. ${park.parkName} is located ${park.elevationInFeet}ft. above
	ground, which will be at your advantage when you explore the
	${park.milesOfTrail} miles of trails on property. Come stay at one of
	our ${park.numberOfCampsites} campsites, and enjoy this wonderful
	${park.climate} weather.</p>

<p id="content-2">Founded in ${park.yearFounded} ${park.parkName}
	entertains around ${park.annualVisitorCount} guests per year! for only
	$${park.entryFee}.00 you can enjoy all of this, and the more than
	${park.numberOfAnimalSpecies} species of animals located on the
	property!</p>

<div id="weather">
	<c:forEach items="${weatherList}" var="weather" begin="1" end="5">
		<div id="weatherInfo">
			<div>
				<img id="forecastImage"src="${weather.imageUrl}" />
			</div>
			<div id="high">High: ${weather.formatTemp(weather.high, tempPreference)}</div>
			<div id="low">Low: ${weather.formatTemp(weather.low, tempPreference)}</div>
			<div id="forecast">${weather.icon}</div>
			<div id="suggestion">
				<c:forEach items="${weather.suggestions}" var="suggestion">
					<div>${suggestion}</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</div>
<div>
	<form method="POST" action="updateTempPref">
	<input type="hidden" name="parkCode" value="${param.parkCode}"/>
	<label for="tempPreference">Show temperature in:</label>
		<select id="tempPreference" name="tempPreference">
			<option value="0">Fahrenheit</option>
			<option value="1">Celcius</option>
		</select>
		<input type="submit" value="save"/>
	</form>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />