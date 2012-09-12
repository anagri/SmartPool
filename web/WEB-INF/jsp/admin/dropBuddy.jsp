<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="carpoolBuddy" items="${requestScope.carpool.getCarpoolBuddies()}" varStatus="buddyIndex">
    <c:set var="buddy" value="${carpoolBuddy.getBuddy()}" />
    <li>
        <span style="display: table-row;">
            <span style="display: table-cell; min-width: 150px;"> ${buddy.getName()} </span>
            <span style="display: table-cell;">
                <a href="${pageContext.request.contextPath}/carpool/${requestScope.carpool.getName()}/${buddy.getUserName()}/delete">
                    <i class="icon-remove-circle"></i>
                </a>
            </span>
        </span>
    </li>

</c:forEach>