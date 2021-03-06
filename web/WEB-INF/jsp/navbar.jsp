<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tooltip.js"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/standardLayout.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/searchCarpool.css" rel="stylesheet">
    <c:choose>
        <c:when test="<%=request.getParameter(\"title\") != null %>"><title><%= request.getParameter("title") %>
        </title></c:when>
        <c:otherwise><title>Welcome to Smartpool</title></c:otherwise>
    </c:choose>
    <c:if test="<%=request.getParameter(\"css\") != null %>">
        <c:forEach var="cssFile" items="<%= request.getParameter(\"css\").split(\",\",2)%>" varStatus="counter">
            <link href="${pageContext.request.contextPath}/css/${cssFile}" rel="stylesheet"/>
        </c:forEach>
    </c:if>

</head>
<body onload="setOptionInDropDown('${searchQuery}')">

<c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>'/>
<c:set var="ldapUserName" value='<%=request.getSession().getAttribute("ldapUserName")%>'/>
<c:set var="appName" value="${pageContext.request.contextPath}"/>
<c:set var="isAdmin" value='<%= ((Boolean) request.getSession().getAttribute("isAdmin")) %>'/>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <img src="${pageContext.request.contextPath}/css/img/taxi-white.png" class="navbar-logo"/>
            <a class="brand" href="${appName}/">SmartPool</a>
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <c:if test='${!isAdmin}'>
                    <li><a href="${appName}/carpool/create" id="createCarpool">Create Carpool</a></li>
                    <li><a href="${appName}/carpool/search" id="listCarpools">Carpools</a></li>
                    <li><a href="${appName}/buddyProfile" id="myProfile">My Profile</a></li>
                    <li><a href="#FAQ">FAQ</a></li>
                    <li>
                        <form action="${appName}/carpool/search" method="GET" class="searchForm">

                            <c:choose>
                                <c:when test="${empty searchQuery}">
                                    <input type="text" name="query" placeholder="Enter Location" id="searchBox"
                                           class="searchText" onclick="this.value=''"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" name="query" placeholder="Enter Location" value="${searchQuery}"
                                           id="searchBox" class="searchText"/>
                                </c:otherwise>
                            </c:choose>

                            <button type="submit" class="btn">Search</button>
                        </form>
                    </li>

                </ul>
                </c:if>

                <form class="navbar-form pull-right" id="logoutForm" action="${appName}/logout" method="GET">
                   <span id="casName"> ${ldapUserName}</span>
                    <button type="submit" class="btn">Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>
