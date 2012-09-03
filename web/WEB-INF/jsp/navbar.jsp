<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ include file="include.jsp" %>

<html>
<head>
    <c:choose>
        <c:when test="<%=request.getParameter(\"title\") != null %>"><title><%= request.getParameter("title") %></title></c:when>
        <c:otherwise><title>Welcome to Smartpool</title></c:otherwise>
    </c:choose>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <c:if test="<%=request.getParameter(\"css\") != null %>">
        <c:set var="cssFiles"><%= request.getParameter("css")%></c:set>
        <c:forEach items="${cssFiles}" var="cssFile">
            <link href="${pageContext.request.contextPath}/css/${cssFile}" rel="stylesheet">
        </c:forEach>
    </c:if>
</head>
<body>

<c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>' />
<c:set var="appName" value="${pageContext.request.contextPath}" />

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="${appName}/">SmartPool</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <%--<li class="active"><a href="${appName}">Home</a></li>--%>
                    <li><a href="${appName}/carpool/create">Create Carpool</a></li>
                    <li><a href="${appName}/carpool/carpool-1" >Carpools</a></li>
                    <li><a href="${appName}/searchCarpool/">Carpool Search</a></li>
                    <li><a href="${appName}/buddyProfile/${casUserName}">My Profile</a></li>
                    <li><a href="#notifications">Notifications</a></li>
                </ul>
                <form class="navbar-form pull-right">
                    ${casUserName}
                        <%--<form method="post">--%>
                            <%--<button type="submit" value="https://castest.thoughtworks.com/cas/logout">Logout</button>--%>
                        <%--</form>--%>
                    <button type="button" class="btn" onClick="parent.location='https://castest.thoughtworks.com/cas/logout'">Logout</button>
                </form>
            </div>
        </div>
    </div>
</div>
