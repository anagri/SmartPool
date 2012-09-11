<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="carpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Search Carpool" />
</jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/search.js"></script>

<div>
    <h1 class="header">Search Carpool</h1>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script type="text/javascript">
    function searchByRoutePoint(){
        var index = document.getElementById('routePointList').selectedIndex;
        var value = document.getElementById('routePointList').options[index].text;

        document.location.href = '../carpool/search?query=' + value;
    }

    function setOptionInDropDown(query) {
        var list = document.getElementById("routePointList");
        for ( var i = 0; i < list.options.length; i++ ) {
            if ( list.options[i].text == query ) {
                list.options[i].selected = true;
                return;
            }
        }
    }
</script>
        <div class="containerWrapper">
            <div class="leftContent">
                <h3 style="font-weight: normal;" id="resultsMessage">Your search for "${searchQuery}" returned ${searchResult == null ? 0 : searchResult.size()} result{s}</h3>
                <c:if test="${searchResult ne null}">
                    <table id='search-result'>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Start Point</th>
                                <th class="center">Start Time</th>
                                <th class="center">Buddy Count</th>
                                <th class="center">Vacancy</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                            <tr>
                                <td><a href="../carpool/${carpool.getName()}" id="${carpool.getName()}">
                                    ${carpool.getFrom()}
                                    <img src="${pageContext.request.contextPath}/css/img/arrow-sign.jpg" class="small-arrow-sign"/>
                                    ${carpool.getTo()}
                                </a></td>
                                <td>${carpool.getStartPoint()}</td>
                                <td class="center">${carpool.getStartTime().toString("h:mm a")}</td>
                                <td class="center">${carpool.getBuddyCount()}</td>
                                <td class="center"><c:choose>
                                    <c:when test="${carpool.getVacancy()<0}">-</c:when>
                                    <c:otherwise> ${carpool.getVacancy()}</c:otherwise>
                                </c:choose>
                               </td>
                                <td id="status${typeStatus.count}">${carpool.getStatusAsString()}</td>
                            </tr>
                       </c:forEach>
                    </table>
                </c:if>
            </div>
            <div class="routePoints">
                <div class="marginTop60"><h4 style="font-weight: normal;">Route Points</h4></div>
                <select id="routePointList" onchange="searchByRoutePoint()">
                    <option value=""></option>
                    <c:forEach var="routePoint" items="${routePoints}" varStatus="typeStatus">
                        <option value="${routePoint}">${routePoint}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
<%@ include file="../footer.jsp" %>
