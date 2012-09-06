<%@ include file="../navbar.jsp" %>

<html>
<head>
    <title>View Carpool: ${carpool.getName()}</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/view.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carpool.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/standardLayout.css"/>
</head>

<body>
<div>
    <label id="carpoolName" class="header">Carpool ${carpool.getName()}</label>
    <div>
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
                    <tr class="seat-occupied">
                        <td class="buddy-sequence">${sequence.index + 1}</td>
                        <td><a href="../buddyProfile/${buddy.getUserName()}" id="${buddy.getUserName()}">${buddy.getName()}</a></td>
                        <td>${buddy.getPickupPoint()}</td>
                        <td>${buddy.getPickupTime().toString("h:mm a")}</td>
                    </tr>
                </c:forEach>
                <c:forEach var="i" begin="${carpool.getBuddies().size()+1}" end="${carpool.getCapacity()}">
                    <tr class="seat-available">
                        <td class="buddy-sequence">${i}</td>
                        <td>(SEAT AVAILABLE)</td>
                        <td>----</td>
                        <td>--:-- --</td>
                    </tr>
                </c:forEach>

            </table>
            <p><strong>Cab Type:</strong> 
            <c:choose>
                <c:when test="${carpool.getCabType() == PERSONAL}"><span title="Someone's car">${carpool.getCabType()}</span></p></c:when>
                <c:when test="${carpool.getCabType() == COMPANY}"><span title="Company's cab">${carpool.getCabType()}</span></p></c:when>
            </c:choose>

            <p><strong>Office Arrival Time:</strong> ${carpool.getOfficeETA().toString("h:mm a")}</p>

            <p><strong>Office Pickup Time:</strong> ${carpool.getOfficeETD().toString("h:mm a")}</p>

            <p><strong>Status:</strong>
            <c:choose>
                <c:when test="${carpool.getStatus() == PENDING}"> Not Started</p></c:when>
                <c:when test="${carpool.getStatus() == RUNNING}"> Active</p></c:when>
                <c:when test="${carpool.getStatus() == DROPPED}"> Dropped</p></c:when>
            </c:choose>

            <c:choose>
                <c:when test="${(carpool.getBuddies().size() < carpool.getCapacity()) && !buddyIsInCarpool}">
                    <form method="post" class="joinCarpoolButton">
                        <button type="submit" value="Join" >Join Carpool</button>
                    </form>
                </c:when>
                <c:when test="${(carpool.getBuddies().size() == carpool.getCapacity()) && !buddyIsInCarpool}">
                    <span class="warning">NO SEATS AVAILABLE</span>
                </c:when>
            </c:choose>

        </div>

        <div class="rightContent">
            <a href="javascript:void(0)" class="moreDetailsButton">More Details</a>
            <div class="hiddenContent">
                <h2>Areas Covered By Carpool</h2>

                <div id="routePoints">
                    <ol>
                        <c:forEach var="routePoint" items="${carpool.getRoutePoints()}" varStatus="sequence">
                            <li>${routePoint}</li>
                        </c:forEach>
                    </ol>
                </div>
            </div>

            <div class="hiddenContent moreDetailsContent">
                <c:if test="${carpool.getStatus().toString().equals(\"PENDING\")}">
                    <p><strong>Proposed Start Date:<br/> </strong> ${carpool.getStartDate().toString("dd MMM Y")}</p>
                </c:if>

                <p><strong>Capacity: </strong>
                    <c:choose>
                        <c:when test="${carpool.getCapacity() > 0}">${carpool.getCapacity()}</c:when>
                        <c:otherwise>To be set</c:otherwise>
                    </c:choose>
                </p>

                <p><strong>Total Cab Charges: </strong>
                    <c:choose>
                        <c:when test="${carpool.getTotalCabCharges() > 0}">${carpool.getTotalCabCharges()}</c:when>
                        <c:otherwise>To be set</c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>
    </div>

</div>

</body>

</html>
