<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="navbar.jsp" flush="true">
    <jsp:param name="css" value="homepageLayout.css"/>
    <jsp:param name="title" value="SmartPool"/>
</jsp:include>


<c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>'/>
<c:set var="appName" value="${pageContext.request.contextPath}"/>

<div class="mainContainer">
    <span id="welcomeMessage" class="welcomeContainer">
        <img src="${pageContext.request.contextPath}/css/img/taxi.png" alt="" class="imageContainer"/>
        <h1>SmartPool</h1>
    </span>

    <form action="${pageContext.request.contextPath}/carpool/search" method="GET" class="searchBar">
        <input type="text" name="query" placeholder="Search by location" id="searchBox" class="searchText"
               onclick="this.value=''"/>
        <button type="submit" class="btn searchButton" value="Search"/>
        <i class="icon-search"></i></button>
    </form>
    <div class="helpText">Looking For a Carpool?</div>
    <div class="helpText">Type the location to search list of carpools available.</div>

    <div class="links">
        <p><a href="http://bprao.wordpress.com/2007/12/18/benefits-of-carpooling/">Why carpool?</a></p>

        <p><a href="http://www.publictransportation.org/tools/Pages/default.aspx">How much do I really save?</a></p>

        <p><a href="http://www.marc.org/rideshare/carpooltips.htm#etiquette">Carpool etiquette</a></p>
    </div>
    <div align="center">
        <br>
        <br>
    <c:forEach var="adminUserName" items="${adminUserNames}">
        <c:choose>
            <c:when test="${casUserName eq adminUserName}">
                <form method="get" action="${appName}/admin/dashboard">
                        <%--action="<c:url value="admin/dashboard"/>">--%>
                    <button type="submit" id="dashBoardButton">Go To Admin Dashboard</button>
                </form>
            </c:when>
        </c:choose>
    </div>
    </c:forEach>
</div>




<%@ include file="footer.jsp" %>