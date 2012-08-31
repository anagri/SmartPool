<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ include file="include.jsp" %>

<html>
<head>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
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
                    <li class="active"><a href="${appName}">Home</a></li>
                    <li><a href="${appName}/searchCarpool/">Carpool Search</a></li>
                    <li><a href="${appName}/buddyProfile/${casUserName}">User Profile</a></li>
                    <li><a href="#contact">Notifications</a></li>
                </ul>
                <form class="navbar-form pull-right">
                    ${username}
                    <button type="submit" class="btn" ref="castest.thoughtworks.com/cas/logout">Logout</button>
                </form>
            </div>
        </div>
    </div>

</div>

<div>
    <br>
    <br>
    <br>
</div>



</body>
</html>