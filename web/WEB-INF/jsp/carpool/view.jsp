<%@ include file="../include.jsp" %>

<html>

<head>

</head>

<body>
    <h1>Carpool ${carpool.getName()}</h1>

    <h2>Date Started: ${carpool.getStartDate().toString("dd/MM/yyyy") }</h2>

    <h2>Buddy List</h2>
    <table>
        <thead>
        <tr>
            <th>Pickup Sequence |</th>
            <th>Buddy Name |</th>
            <th>Pickup Point |</th>
            <th>Pickup Time</th>
        </tr>
        </thead>

        <c:forEach var="buddy" items="${carpool.getBuddyProfiles()}" varStatus="sequence">
            <tr>
                <td>${sequence.index + 1}</td>
                <td>${buddy.getName()}</td>
                <td>${buddy.getPickupPoint()}</td>
                <td>${buddy.getPickupTime()}</td>
            </tr>
        </c:forEach>

    </table>
    <h2>Cab Type: ${carpool.getCabType()}</h2>
    <h2>Totoal Cab Charges: ${carpool.getTotalCharge()}</h2>
    <h2>Office Pick-up Time: ${carpool.getOfficePickupTime().toString("hh:mma")}</h2>
    <h2>Status: ${carpool.getStatus()}</h2>


<input type="button" value="Join Carpool"/>
</body>

</html>