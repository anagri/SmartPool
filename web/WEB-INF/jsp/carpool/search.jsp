<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="carpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Search Carpool" />
</jsp:include>

<div>
    <label class="header">Search Carpool</label>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script type="text/javascript">
    function searchByRoutePoint(){
        var index = document.getElementById('routePointList').selectedIndex;
        var value = document.getElementById('routePointList').options[index].text;

        document.location.href = '../carpool/search?query=' + value;
    }
</script>
        <div class="containerWrapper">
            <div class="leftContent">
                <h3 id="resultsMessage">Your search for "${searchQuery}" returned ${searchResult == null ? 0 : searchResult.size()} result{s}</h3>
                <c:if test="${searchResult ne null}">
                    <table id='buddy-table'>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Start Point</th>
                                <th>Start Time</th>
                                <th>Buddy Count</th>
                                <th>Vacancy</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                            <tr>
                                <td><a href="../carpool/${carpool.getName()}" id="${carpool.getName()}">${carpool.getName()}</a></td>
                                <td>${carpool.getStartPoint()}</td>
                                <td>${carpool.getStartTime().toString("h:mm a")}</td>
                                <td>${carpool.getBuddyCount()}</td>
                                <td>${carpool.getVacancy()}</td>
                                <td>${carpool.getStatus()}</td>
                            </tr>
                       </c:forEach>
                    </table>
                </c:if>
            </div>
            <div class="rightContent">
                <div class="marginTop150"><h3>Route Points</h3></div>
                <select id="routePointList" onchange="searchByRoutePoint()">
                    <option value=""></option>
                    <c:forEach var="routePoint" items="${routePoints}" varStatus="typeStatus">
                        <option value="${routePoint}">${routePoint}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
<%@ include file="../footer.jsp" %>
