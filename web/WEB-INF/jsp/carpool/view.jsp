<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp">
    <jsp:param name="title" value="View Carpool: ${carpool.getName()}"/>
    <jsp:param name="css" value="carpool.css,standardLayout.css" />
</jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/view.js"></script>
<script type="text/javascript">
    $(function() {
        $('.more-info .info').tooltip({
            bodyHandler:function () {
                return $($(this).parent().find('.more-info-message')).html();
            },
            showURL: false
        });
    });
</script>
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
                <c:forEach var="carpoolBuddy" items="${carpool.getCarpoolBuddies()}" varStatus="sequence">
                    <c:set var="buddy" value="${carpoolBuddy.getBuddy()}" />
                    <tr class="seat-occupied">
                        <td class="buddy-sequence">${sequence.index + 1}</td>
                        <td><a href="../buddyProfile/${buddy.getUserName()}" id="${buddy.getUserName()}">${buddy.getName()}</a></td>
                        <td>${carpoolBuddy.getPickupPoint()}</td>
                        <td>${carpoolBuddy.getPickupTime().toString("h:mm a")}</td>
                    </tr>
                </c:forEach>
                <c:forEach var="i" begin="${carpool.getCarpoolBuddies().size()+1}" end="${carpool.getCapacity()}">
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
                <c:when test="${carpool.getCabType() == PERSONAL}">
                ${carpool.getCabType()}
                <span class="more-info">
                    <img src="${pageContext.request.contextPath}/css/img/moreinfo.png" class="info"/>
                        <span style="display: none;" class="more-info-message tooltip">
                            Vehicle belongs and is operated by ${carpool.getCarpoolBuddies().get(0).getBuddy().getName()}. 50% of fuel cost is paid by ThoughtWorks.
                        </span>
                    </span>
                </c:when>
                <c:when test="${carpool.getCabType() == COMPANY}">
                    ${carpool.getCabType()}
                    <span class="more-info">
                    <img src="${pageContext.request.contextPath}/css/img/moreinfo.png" class="info"/>
                        <span style="display: none;" class="more-info-message tooltip">
                            Vehicle is a cab owned and operated by a cab agency. 50% of cab charges are paid by ThoughtWorks.
                        </span>
                    </span>
                </c:when>
            </c:choose>
            </p>

            <p><strong>Office Arrival Time:</strong> ${carpool.getOfficeETA().toString("h:mm a")}</p>

            <p><strong>Office Pickup Time:</strong> ${carpool.getOfficeETD().toString("h:mm a")}</p>

            <p><strong>Status:</strong> ${carpool.getStatus()}</p>

            <%--<c:choose>--%>
                <%--<c:when test="${!hasEnoughSpace}">--%>
                    <%--<label>This carpool is already full.</label>--%>
                    <%--<button disabled="disabled">Carpool Full</button>--%>
                <%--</c:when>--%>
                <%--<c:when test="${alreadyInCarpool}">--%>
                    <%--<label>You are already in this carpool.</label>--%>
                    <%--<button disabled="disabled">Already Joined</button>--%>

                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<form method="get" action="${carpool.getName()}/join" class="joinCarpoolButton" action="<c:url value="/carpool/join/${carpool.name}"/>">--%>
                        <%--<button type="submit" value="Join">Join Carpool</button>--%>
                    <%--</form>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>


            <c:choose>
                <c:when test="${(carpool.getCarpoolBuddies().size() < carpool.getCapacity()) && !buddyIsInCarpool}">
                    <form method="get" action="join/${carpool.getName()}" class="joinCarpoolButton" action="<c:url value="/carpool/join/${carpool.name}"/>">
                        <button id="joinRequestButton" type="submit" value="Join">Join Carpool</button>
                    </form>
                </c:when>
                <c:when test="${(carpool.getCarpoolBuddies().size() == carpool.getCapacity()) && !buddyIsInCarpool}">
                    <span class="warning">NO SEATS AVAILABLE</span>
                </c:when>
            </c:choose>

        </div>

        <div class="rightContent">
            <a href="javascript:void(0)" class="moreDetailsButton">More Details</a>

            <div class="hiddenContent">
                <h2>Route Plan</h2>

                <div id="routePoints">
                    <ol>
                        <c:forEach var="routePoint" items="${carpool.getRoutePoints()}" varStatus="sequence">
                            <li>${routePoint}</li>
                        </c:forEach>
                    </ol>
                </div>
            </div>

            <div class="hiddenContent moreDetailsContent">
                <c:if test="${carpool.getStatus().toString().equals(\"NOT_STARTED\")}">
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

<%@ include file="../footer.jsp" %>
