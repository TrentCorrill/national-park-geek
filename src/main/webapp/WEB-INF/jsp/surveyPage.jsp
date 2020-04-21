<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<style>
.error {
	color: red;
}
</style>

<form:form id="survey" modelAttribute="surveyData" method="POST">

	<div class="form-group">
		<label for="parkCode">Select Park</label>
		<form:select class="form-control" id="parkCode" name="parkCode"
			path="parkCode">
			<c:forEach items="${parks}" var="park">
				<form:option value="${park.parkCode}">${park.parkName}</form:option>
			</c:forEach>
		</form:select>

		<form:errors path="parkCode" class="error" />
	</div>

	<div class="form-group">
		<label for="emailInput">Email address</label>
		<form:input type="text" class="form-control" id="emailInput"
			aria-describedby="emailHelp" placeholder="example@email.com"
			path="emailAddress" name="emailAddress" required="required"/>
		<small id="emailHelp" class="form-text text-muted">We'll never
			share your email with anyone else.</small>
		<form:errors path="emailAddress" class="error" />

	</div>

	<div class="form-group">
		<label for="selectState">State of Residence</label> <select
			class="form-control" id="exampleFormControlSelect1" name="state">
			<c:forEach items="${states}" var="state">
				<form:option value="${state.name}">${state.name}</form:option>
			</c:forEach>

		</select>
	</div>
	<div id="activityForm">
		<h5>Select a Personal Activity Level</h5>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				id="activityInactive" value="inactive" checked> <label
				class="form-check-label" for="activityInactive">Inactive</label>
		</div>

		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				id="activitySedentary" value="sedentary"> <label
				class="form-check-label" for="activitySedentary">Sedentary</label>
		</div>

		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				id="activityActive" value="active"> <label
				class="form-check-label" for="activityActive">Active</label>
		</div>

		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="activityLevel"
				id="activityExtremelyActive" value="extremely active"> <label
				class="form-check-label" for="activityExtremelyActive">Extremely
				Active</label>
		</div>
	</div>

	<button type="submit" class="btn btn-primary">Submit</button>

</form:form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />