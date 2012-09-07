<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tooltip.js"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/searchCarpool.css" rel="stylesheet">
    <c:choose>
        <c:when test="<%=request.getParameter(\"title\") != null %>"><title><%= request.getParameter("title") %></title></c:when>
        <c:otherwise><title>Welcome to Smartpool</title></c:otherwise>
    </c:choose>
    <c:if test="<%=request.getParameter(\"css\") != null %>">
        <c:forEach var="cssFile" items="<%= request.getParameter(\"css\").split(\",\",2)%>" varStatus="counter">
            <link href="${pageContext.request.contextPath}/css/${cssFile}" rel="stylesheet" />
        </c:forEach>
    </c:if>

</head>
<body onload="setOptionInDropDown('${searchQuery}')">

<c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>' />
<c:set var="appName" value="${pageContext.request.contextPath}" />

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <img src="${pageContext.request.contextPath}/css/img/navbarLogo.jpg" border="0px;" heigh="100px" class="navbar-logo"/>
            <a class="brand" href="${appName}/">SmartPool</a>
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="${appName}/carpool/create" id="createCarpool">Create Carpool</a></li>
                    <li><a href="${appName}/carpool/search" id="listCarpools">Carpools</a></li>
                    <li><a href="${appName}/buddyProfile" id="myProfile">My Profile</a></li>
                    <li><a href="#notifications">Notifications</a></li>
                    <li>
                        <form action="${appName}/carpool/search" method="GET" class="searchForm">

                            <c:choose>
                                <c:when test="${empty searchQuery}">
                                    <input type="text" name="query" value="Enter Location" id="searchBox" class="searchText" onclick="this.value=''"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" name="query" value="${searchQuery}" id="searchBox" class="searchText" />
                                </c:otherwise>
                            </c:choose>

                                <button type="submit" class="btn searchButton" value="Search"/>Search</button>
                        </form>
                    </li>

                </ul>
                <form class="navbar-form pull-right" id="logoutForm" action="${appName}/logout" method="GET">
                    ${casUserName}
                    <button type="submit" class="btn btn-small" style="margin-top: 4px">Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>
