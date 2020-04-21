<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Park Geek</title>
    <c:url value="/css/nationalParks.css" var="cssHref"/>
    <c:url value="/" var="homePageHref"/>
    <c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" var="bootstrapHref"/>
    <link rel="stylesheet" href="${cssHref}">
    <link rel="stylesheet" href="${bootstrapHref}"/>
</head>

<body>
    <header id="header">
    		<c:url value="/img/logo.png" var="logoSrc" />
        <a href="${homePageHref}">
        		<img id="logo" src="${logoSrc}" alt="National Park Geek" />
        </a>
        <ul id="nav">
	        <li id="home"><a href="${homePageHref}">Home</a></li>
	        <li id="survey"><a href="<c:url value="/survey"/>">Survey</a></li>
	   </ul>
    </header>