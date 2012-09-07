<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="profileLayout.css,standardLayout.css"/>
    <jsp:param name="title" value="${buddyProfile.getName()}'s Profile" />
</jsp:include>

<div>
    <label class="header">SmartPool Profile</label>
</div>

<div class="mainContainer">
    <div class="subContainer">
        <span><label>Name:</label></span>
        <span><label id="myProfileName">${buddyProfile.getName()}</label></span>
    </div>
    <div class="subContainer">
        <span><label>Contact Number:</label></span>
        <span><label>${buddyProfile.getContactNumber()}</label></span>
    </div>

    <div class="subContainer">
        <span><label>Email:</label></span>
        <span><label>${buddyProfile.getEmailId()}</label></span>
    </div>

    <div class="subContainer">
        <span><label>Preferred Pickup Point:</label></span>
        <span><label>${buddyProfile.getPreferredPickupPoint()}</label></span>
    </div>
    <c:set var="casUserName" value='<%=request.getSession().getAttribute(CASFilter.CAS_FILTER_USER)%>' />
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
<%@ include file="../footer.jsp" %>
