<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp">
    <jsp:param name="title" value="View Carpool: ${carpool.getName()}"/>
    <jsp:param name="css" value="carpool.css" />
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
    <h1 id="carpoolName" class="header">
        Carpool ${carpool.getFrom()}
        <img src="${pageContext.request.contextPath}/css/img/arrow-sign.jpg" class="big-arrow-sign"/>
         ${carpool.getTo()}
    </h1>

    <div>
        <h2 style="font-weight: normal;">Buddy List</h2>

        <div class="leftContent">
            <table class='buddy-table' style="margin-bottom: 4px;">
                <thead>
                <tr>
                    <th class="center">Pickup Sequence</th>
                    <th>Buddy Name</th>
                    <th>Pickup Point</th>
                    <th class="center">Pickup Time</th>
                </tr>
                </thead>
                <c:forEach var="carpoolBuddy" items="${carpool.getCarpoolBuddies()}" varStatus="sequence">
                    <c:set var="buddy" value="${carpoolBuddy.getBuddy()}" />
                    <tr class="seat-occupied">
                        <td class="buddy-sequence">${sequence.index + 1}</td>
                        <td><a href="../buddyProfile/${buddy.getUserName()}" id="${buddy.getUserName()}">
                        <c:if test="${sequence.index==0 && carpool.getCabType() == PERSONAL}"><img src="${pageContext.request.contextPath}/css/img/key.png"  class="car-owner" /></c:if>
                        ${buddy.getName()}</a></td>
                        <td>${carpoolBuddy.getPickupPoint()}</td>
                        <td class="center">${carpoolBuddy.getPickupTime().toString("h:mm a")}</td>
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

            <c:if test="${carpool.getCabType() == PERSONAL}">
            <span style="font-size: smaller;"><img src="${pageContext.request.contextPath}/css/img/key.png"  class="car-owner" />:carpool lead</span>
            </c:if>

            <p style="margin-top:23px;margin-bottom: 6px;">Cab Type:
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

            <p>Office Arrival Time: ${carpool.getOfficeETA().toString("h:mm a")}</p>

            <p>Office Pickup Time: ${carpool.getOfficeETD().toString("h:mm a")}</p>

            <p>Status: ${carpool.getStatusAsString()}</p>

            <c:choose>
                <c:when test="${!hasEnoughSpace && !buddyIsInCarpool}">
                    <span class="warning">NO SEATS AVAILABLE</span>
                </c:when>
                <c:when test="${buddyIsInCarpool}">
                    <span class="status-message">YOU ARE CURRENTLY A MEMBER OF THIS CARPOOL.</span>
                </c:when>
                <c:when test="${isRequestSent}">
                    <span class="status-message">JOIN REQUEST PENDING</span>
                </c:when>
                <c:otherwise>
                    <form method="get" action="join/${carpool.getName()}" class="joinCarpoolButton" action="<c:url value="/carpool/join/${carpool.name}"/>">
                        <button type="submit" id="joinRequestButton" value="Join">Join Carpool</button>
                    </form>
                </c:otherwise>
            </c:choose>

        </div>

        <div class="rightContent">
            <a href="javascript:void(0)" class="moreDetailsButton">More Details</a>

            <div class="hiddenContent">
                <h4 style="font-weight: normal;">Route Plan</h4>

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
                    <p>Proposed Start Date:<br/> ${carpool.getStartDate().toString("dd MMM Y")}</p>
                </c:if>

                <p>Capacity:
                    <c:choose>
                        <c:when test="${carpool.getCapacity() > 0}"><span id="carpoolCapacity">${carpool.getCapacity()}</span></c:when>
                        <c:otherwise>To be set</c:otherwise>
                    </c:choose>
                </p>

                <p>Total Cab Charges:
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
