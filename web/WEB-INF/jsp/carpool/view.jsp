<%@ include file="../navbar.jsp" %>

<html>
<head>
    <title>View Carpool: ${carpool.getName()} | SmartPool</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/view.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carpool.css"/>
</head>

<body>
<div class="container">
    <h1 class="carpoolName">Carpool ${carpool.getName()}</h1>
    <div>
        <a href="javascript:void(0)" class="moreDetailsButton">More Details</a>

        <h2>Buddy List</h2>
        <div class="leftContent">
            <table class='buddy-table'>
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
                        <td class="buddy-sequence">${sequence.index + 1}</td>
                        <td><a href="../buddyProfile/${buddy.getUserName()}">${buddy.getName()}</a></td>
                        <td>${buddy.getPickupPoint()}</td>
                        <td>${buddy.getPickupTime().toString("h:mm a")}</td>
                    </tr>
                </c:forEach>

            </table>
            <p><strong>Cab Type:</strong> ${carpool.getCabType()}</p>

            <p><strong>Office Arrival Time:</strong> ${carpool.getOfficeETA().toString("h:mm a")}</p>

            <p><strong>Office Pickup Time:</strong> ${carpool.getOfficeETD().toString("h:mm a")}</p>

            <p><strong>Status:</strong> ${carpool.getStatus()}</p>

            <c:if test="${!buddyIsInCarpool}">
                <form method="post" class="joinCarpoolButton">
                    <button type="submit" value="Join">Join Carpool</button>
                </form>
            </c:if>
        </div>

        <div class="rightContent">
            <div class="hiddenContent">
                <h2>Route Plan</h2>

                <div class="routePlan">
                    <ol>
                        <li>Diamond District</li>
                        <li>Bla</li>
                        <li>ble</li>
                        <li>bli</li>
                        <li>blo</li>
                        <li>blu</li>
                        <li>ThoughtWorks</li>
                        <c:forEach var="routePoint" items="${carpool.getRoutePlan()}" varStatus="sequence">
                            <li>${routePoint}</li>
                        </c:forEach>
                    </ol>
                </div>
            </div>

            <div class="hiddenContent moreDetailsContent">
                <c:if test="${carpool.getStatus() == Status.PENDING}">
                    <p><strong>Date Started: </strong> ${carpool.getStartDate().toString("dd/MMM/Y")}</p>
                </c:if>

                <p><strong>Capacity: </strong>
                    <c:if test="${carpool.getCapacity() > 0}">${carpool.getCapacity()}</c:if>
                </p>

                <p><strong>Total Cab Charges: </strong>
                    <c:if test="${carpool.getTotalCabCharges() > 0}">${carpool.getTotalCabCharges()}</c:if>
                </p>
            </div>
        </div>
    </div>

</div>

</body>

</html>