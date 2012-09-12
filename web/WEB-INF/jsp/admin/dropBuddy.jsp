<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="carpoolBuddy" items="${requestScope.carpoolBuddies}" varStatus="buddyIndex">
    <c:set var="buddy" value="${carpoolBuddy.getBuddy()}" />
    <li>${buddy.getName()}</li>
</c:forEach>