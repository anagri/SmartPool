<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="joinRequest.css,standardLayout.css"/>
    <jsp:param name="title" value="Join SmartPool: ${carpoolName}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container">
    <div class="headerContainer">
        <h1>Join Smart Pool: ${carpoolName}</h1>
    </div>
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

    <form:form commandName="joinRequestForm">
    <spring:hasBindErrors name="joinRequestForm">
        <spring:message code="errors.exist"/>
    </spring:hasBindErrors>

    <div class="mainContainer">

        <div class="subContainer">
                    <span id="nameLabel">
                        <label>Name*</label>
                    </span>
                    <span id="nameTextBox">
                        ${buddy.getName()}<br/>
                    </span>
        </div>
        <div class="subContainer">
                    <span id="addressLabel">
                        <label>Address</label>
                    </span>
                    <span id="addressTextBox">
                        <textarea name="address" rows="3" cols="30"
                                  value="${joinRequestForm.address}"> </textarea> <br/>
                    </span>
        </div>
        <div class="subContainer">
                    <span id="contactNumberLabel">
                        <label>Contact Number*</label>
                     </span>
                     <span id="contactNumberTextBox">
                        <input name="contactNumber" type="textbox" required="required"
                               value="${joinRequestForm.contactNumber}"/> <br/>
                    </span>
        </div>
        <div class="subContainer">
                    <span id="emailIdLabel">
                        <label>Email*</label>
                    </span>
            <span id="emailIdTextBox">${buddy.getEmailId()}</span>
        </div>
        <div class="subContainer">
                    <span id="pickupPointLabel">
                        <label>Preferred Pick Up Point*</label>
                    </span>
                    <span id="pickupPointTextBox">
                        <input name="pickupPoint" required="required" type="textbox"
                               value="${joinRequestForm.pickupPoint}"/> <br/>
                        <form:errors path="pickupPoint" cssClass="error"/>
                    </span>
        </div>
        <div class="subContainer">
                    <span id="pickupTimeLabel">
                        <label>Pick Up Time (hh:mm)</label>
                    </span>
                    <span id="pickupTimeTextBox">
                        <input name="preferredPickupTime" type="text"
                               value="${joinRequestForm.preferredPickupTime}"/> <br/>
                        <form:errors path="preferredPickupTime" cssClass="error"/>
                    </span>
        </div>
        <div class="buttonContainer">
            <c:set var="isDisabled" value="${isRequestSent ? 'disabled' : ''}"/>
            <button align="right" name="submit" type="submit" ${isDisabled}>Submit</button>
            <button align="right" disabled="disabled">EDIT</button>
        </div>
    </div>
    </form:form>
    <%@ include file="../footer.jsp" %>
