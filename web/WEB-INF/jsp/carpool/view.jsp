<%@ include file="../navbar.jsp" %>
<%@ include file="../include.jsp" %>

<html>

<head>
    <title>View Carpool: ${carpool.getName()} | SmartPool</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/view.js"></script>
</head>

<body>
    <h1>Carpool ${carpool.getName()}</h1>
    <div style="width: 700px;">
        <div style="width: 80%; float: left;">
            <h2>Buddy List</h2>
            <table>
                <thead>
                <tr>
                    <th>Pickup Sequence</th>
                    <th>Buddy Name</th>
                    <th>Pickup Point</th>
                    <th>Pickup Time</th>
                </tr>
                </thead>

                <c:forEach var="buddy" items="${carpool.getBuddies()}" varStatus="sequence">
                    <tr>
                        <td>${sequence.index + 1}</td>
                        <td><a href="../buddyProfile/${buddy.getUserName()}">${buddy.getName()}</a></td>
                        <td>${buddy.getPickupPoint()}</td>
                        <td>${buddy.getPickupTime()}</td>
                    </tr>
                </c:forEach>

            </table>
            <p><span>Cab Type:</span> ${carpool.getCabType()}</p>
            <p><span>Office ETA:</span> ${carpool.getOfficeETA().toString("h:mm a")}</p>
            <p><span>Office ETD:</span> ${carpool.getOfficeETD().toString("h:mm a")}</p>
            <p><span>Status:</span> ${carpool.getStatus()}</p>

            <a href="javascript:void(0)" id="moreDetailsButton">More Details</a>
            <p class="hiddenContent"><span>Date Started: </span> ${carpool.getStartDate().toString("d-M-Y")}</p>
            <p class="hiddenContent"><span>Capacity: </span><c:if test="${carpool.getCapacity() > 0}">${carpool.getCapacity()}</c:if></p>
            <p class="hiddenContent"><span>Total Cab Charges: </span> <c:if test="${carpool.getTotalCabCharges() > 0}">${carpool.getTotalCabCharges()}</c:if></p>
        </div>
        <div class="hiddenContent" style="width: 20%; float: right;">
            <p>Route Plan</p>
            <ol>
                <c:forEach var="routePoint" items="${carpool.getRoutePlan()}" varStatus="sequence">
                    <li>${routePoint}</li>
                </c:forEach>
            </ol>
        </div>
        <input type="button" value="Join"/>
    </div>

</body>

</html>