<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="title" value="SmartPool Profile"/>
    <jsp:param name="css" value="profileLayout.css"/>
</jsp:include>
<div class="container">
    <h1>SmartPool Profile</h1>

    <div class="mainContainer">
        <div class="subContainer">
            <span class="fieldName">Name: </span>

            <div class="fieldValue" id="profileName">${buddyProfile.getName()} </div>
        </div>
        <div class="subContainer">
            <span class="fieldName">Contact Number:</span>
            <span class="fieldValue" id="profilePhoneNumber">${buddyProfile.getContactNumber()}</span>
        </div>

        <div class="subContainer">
            <span class="fieldName">Email: </span>
            <span class="fieldValue" id="profileEmail">${buddyProfile.getEmailId()} </span>
        </div>

        <div class="subContainer">
            <span class="fieldName">Preferred Pickup Point: </span>

            <div class="fieldValue"> ${buddyProfile.getPreferredPickupPoint() == null ? "Not set" : buddyProfile.getPreferredPickupPoint()} </div>
        </div>

        <div class="subContainer" style="height: 100px">
            <%--<form id="carpoolList" action="${appName}/buddyProfile" method="POST">--%>
            <span>My Carpools:</span>
            <c:forEach var="carpool" items="${carpools}" varStatus="typeStatus">
                <ul>
                    <li>
                        <input name="carpool" type="hidden" value="${carpool}">
                        <a href="../carpool/${carpool.getName()}" id="${carpool.getName()}">${carpool.getName()}</a>
                        <span id="status${typeStatus.count}">${carpool.getStatus()}</span>
                            <%--<span><button type="submit" value="Drop">Drop from carpool</button></span>--%>
                    </li>
                </ul>
            </c:forEach>
            <%--</form>--%>
        </div>

        <div id="profileButton">
            <c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>'/>
            <c:choose>
                <c:when test="${casUserName eq buddyProfile.userName}">
                    <div class="buttonContainer">
                        <button>Edit</button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="buttonContainer">
                        <button>Contact</button>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>
<%@ include file="../footer.jsp" %>
