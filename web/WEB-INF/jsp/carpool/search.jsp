<%@ include file="../navbar.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carpool.css"/>

<script type="text/javascript">
        function searchByRoutePoint(){
            var index = document.getElementById('routePointList').selectedIndex;
            var value = document.getElementById('routePointList').options[index].text;

            document.location.href = '../carpool/search?query='+value;
        }
    </script>

        <div class="containerWrapper">
            <div class="leftContent">
            <c:choose>
            <c:when test="${searchResult ne null}">
                <h1>Search Carpool</h1>
                <h3> You  have ${searchResult.size()} result{s}</h3>
                <table class="carpools" style="border: 1px;" cellspacing="20px">
                    <tr>
                        <th>Name</th>
                        <th>Start Point</th>
                        <th>Start Time</th>
                        <th>Buddy Count</th>
                        <th>Vacancy</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                        <tr>
                            <td><a href="../carpool/${carpool.getName()}">${carpool.getName()}</a></td>
                            <td>${carpool.getStartPoint()}</td>
                            <td>${carpool.getStartTime().toString("h:mm a")}</td>
                            <td>${carpool.getBuddyCount()}</td>
                            <td>${carpool.getVacancy()}</td>
                            <td>${carpool.getStatus()}</td>
                        </tr>
                   </c:forEach>
                </table>

            </c:when>
            <c:otherwise>
                <h1> You  have 0 results</h1>
            </c:otherwise>
            </c:choose>
            </div>
            <div class="rightContent">
                <h2>Route Points</h2>
                <select id="routePointList" onchange="searchByRoutePoint()">
                    <option value=""></option>
                    <c:forEach var="routePoint" items="${routePoints}" varStatus="typeStatus">
                        <option value=${routePoint}>${routePoint}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

<%@ include file="../footer.jsp" %>
