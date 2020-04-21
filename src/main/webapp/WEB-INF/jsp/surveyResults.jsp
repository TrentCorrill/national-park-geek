<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:forEach items="${results}" var="result">

<div class="parkCard">
<h4>${result.name}</h4>
<br>
<h5>${result.count} Votes</h5>
</div>

</c:forEach>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />