<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="joinRequest.css,standardLayout.css"/>
    <jsp:param name="title" value="Join SmartPool: ${carpoolName}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container">
    <div class="header">Join Smart Pool: ${carpoolName}</div>
    <c:if test="${isRequestSent}">
    <span class="warning">You have already sent the request for this carpool</span>
    </c:if>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;

        }
    </style>
    <c:if test="${!isRequestSent}">
    <form:form commandName="joinRequestForm" htmlEscape="true">
    <spring:hasBindErrors name="joinRequestForm">
        <spring:message code="errors.exist"/>
    </spring:hasBindErrors>

    <div class="mainContainer">
            <span>
                <label>Name*</label>
                ${buddy.getName()}
            </span>

            <span>
                <label>Address</label>
                <textarea id="addressTextBox" name="address" rows="3" cols="30"
                          value="${joinRequestForm.address}"> </textarea>
            </span>

            <span>
                <label>Contact Number*</label>
                <input id="contactNumberTextBox" name="contactNumber" type="textbox" required="required"
                       value="${joinRequestForm.contactNumber}"/>
                 <form:errors path="contactNumber" cssClass="error"/>
            </span>

            <span>
                <label>Email*</label>
                ${buddy.getEmailId()}
            </span>

            <span>
                <label>Preferred Pick Up Point*</label>
                <input id="pickupPointTextBox" name="pickupPoint" required="required" type="textbox"
                       value="${joinRequestForm.pickupPoint}"/>
                <form:errors path="pickupPoint" cssClass="error"/>
            </span>
            <span>
                <label>Pick Up Time (hh:mm)</label>
                <input id="pickupTimeTextBox" name="preferredPickupTime" type="textbox"
                       value="${joinRequestForm.preferredPickupTime}"/>
                <form:errors path="preferredPickupTime" cssClass="error"/>
                <label class="example"><spring:message code="pickupTimeExample"/></label>
            </span>

        <div class="buttonContainer">
            <c:set var="isDisabled" value="${isRequestSent ? 'disabled' : ''}"/>
            <a href="<c:url value="/carpool/${carpoolName}" />">Cancel</a>
            <button id="submitFormButton" name="submit" type="submit" ${isDisabled}>Submit</button>
        </div>
    </div>
    </form:form>
    </c:if>
<%@ include file="../footer.jsp" %>
