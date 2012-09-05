<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <c:choose>
        <c:when test="<%=request.getParameter(\"title\") != null %>"><title><%= request.getParameter("title") %></title></c:when>
        <c:otherwise><title>Welcome to Smartpool</title></c:otherwise>
    </c:choose>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <c:if test="<%=request.getParameter(\"css\") != null %>">
        <c:forEach var="cssFile" items="<%= request.getParameter(\"css\").split(\",\",2)%>" varStatus="counter">
            <link href="${pageContext.request.contextPath}/css/${cssFile}" rel="stylesheet" />
        </c:forEach>
    </c:if>
</head>
<body>

<c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>' />
<c:set var="appName" value="${pageContext.request.contextPath}" />

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${appName}/">SmartPool</a>
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="${appName}/carpool/create">Create Carpool</a></li>
                    <li><a href="${appName}/carpool/search" id="listCarpools">Carpools</a></li>
                    <li><a href="${appName}/buddyProfile/${casUserName}">My Profile</a></li>
                    <li><a href="#notifications">Notifications</a></li>
                    <li>
                        <form action="${appName}/carpool/search" method="GET">
                            <input type="text" name="query" />
                            <input type="submit" value="Search" />
                        </form>
                    </li>

                </ul>
                <form class="navbar-form pull-right">
                    ${casUserName}
                    <button type="button" class="btn logout" onClick="parent.location='https://castest.thoughtworks.com/cas/logout'">Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>
