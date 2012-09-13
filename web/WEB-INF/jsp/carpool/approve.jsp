<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="title" value="SmartPool"/>
</jsp:include>

<div class="container">
    <span>
        <c:out value="${isUidPresent}"></c:out>
        <c:choose>
            <c:when test="${approve == true}">
            <c:choose>
                <c:when test="${isUidPresent == true}">
                    <h1>${buddy.getName()} has been successfully added to ${carpool.getName()}</h1>
                </c:when>
                <c:otherwise>
                    <h1>Invalid Request.</h1>
                </c:otherwise>
            </c:choose>
        </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${isUidPresent == true}">
                        <h1>${buddy.getName()}'s request to join ${carpool.getName()} has been disapproved</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Invalid Request.</h1>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </span>
</div>


<%@ include file="../footer.jsp" %>