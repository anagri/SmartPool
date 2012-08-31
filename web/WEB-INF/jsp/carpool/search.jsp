<%@ include file="../include.jsp" %>

<html>

    <head>
        Carpool Search
    </head>

    <script type="text/javascript">
        function searchByRoutePoint(){
            var index = document.getElementById('routePointList').selectedIndex;
            var value = document.getElementById('routePointList').options[index].text;

            document.location.href = '../carpool/search?query='+value;
        }
    </script>

    <body>
        <form action="./search" method="GET">
            <input type="text" name="query" />
            <input type="submit" value="Search" />
        </form>
        <c:choose>
        <c:when test="${searchResult ne null}">
        <h3> You  have ${searchResult.size()} result{s}</h3>
        <c:if test="${searchResult.size() ne 0}">
            <table class="carpools" style="border: 1px;" cellspacing="20px">
                <tr>
                    <td>Name</td>
                    <td>Start Point</td>
                    <td>Start Time</td>
                    <td>Buddy Count</td>
                    <td>Status</td>
                </tr>
                <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                    <tr>
                        <td><a href="../carpool/${carpool.getName()}">${carpool.getName()}</a></td>
                        <td>${carpool.getStartPoint()}</td>
                        <td>${carpool.getStartTime()}</td>
                        <td>${carpool.getBuddyCount()}</td>
                        <td>${carpool.getStatus()}</td>
                    </tr>
               </c:forEach>
            </table>
        </c:if>
        </c:when>
        <c:otherwise>
            <h1> You  have 0 results</h1>
        </c:otherwise>
        </c:choose>
        <div>
            <select id="routePointList">
                <c:forEach var="routePoint" items="${routePoints}" varStatus="typeStatus">
                    <option value=${routePoint} onclick="searchByRoutePoint()">${routePoint}</option>
                </c:forEach>
            </select>
        </div>
    </body>

</html>