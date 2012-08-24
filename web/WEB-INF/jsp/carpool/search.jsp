<%@ include file="../include.jsp" %>

<html>

    <head>
        Carpool Search
    </head>

    <body>
        <form action="./search" method="GET">
            <input type="text" name="query" />
            <input type="submit" value="Search" />
        </form>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Start Point</td>
                <td>Start Time</td>
                <td>Buddy Count</td>
                <td>Status</td>
            </tr>
            <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                <tr>
                    <td>${carpool.getName()}</td>
                    <td>${carpool.getStartPoint()}</td>
                    <td>${carpool.getStartTime()}</td>
                    <td>${carpool.buddyCount()}</td>
                    <td>${carpool.getStatus()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>

</html>