<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:forEach items="${parks}" var="park">
	<a href="details?parkCode=${park.parkCode}">
		<div class="parkCard">
			<img id="parkImage" src="${park.imageUrl}" />

			<div class="parkInfo">
				<div>
					<div>${park.parkName}</div>
					<div>${park.state}</div>
				</div>

				<div>${park.parkDescription}</div>
			</div>
		</div>
	</a>
</c:forEach>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />