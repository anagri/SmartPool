<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="createCarpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Create Carpool"/>
</jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/create.js"></script>
<div class="container">
    <h1>Create Carpool</h1>

    <div class="mainContainer">
        <form:form commandName="createCarpoolForm" htmlEscape="true">
            <div align="center" style="color: red">
                <spring:hasBindErrors name="createCarpoolForm">
                    <spring:message code="errors.exist"/>
                </spring:hasBindErrors>
            </div>
        <span>
            <label>From*</label>
            <input type="text" name="from" value="${createCarpoolForm.from}"/>
            <form:errors cssClass="error" path="from"/>
        </span>
        <span>
            <label>To*</label>
            <input type="text" name="to" value="${createCarpoolForm.to}"/>
            <form:errors cssClass="error" path="to"/>
        </span>
        <span>
            <label>Proposed Start Date*:</label>
            <input type="text" name="proposedStartDate" placeholder="DD/MM/YYYY"
                   value="${createCarpoolForm.proposedStartDate}"/>
            <form:errors cssClass="error" path="proposedStartDate"/>
        </span>
        <span>
            <label>Pickup Point*:</label>
            <input type="text" name="pickupPoint" placeholder="Example: Diamond District"
                   value="${createCarpoolForm.pickupPoint}"/>
            <form:errors cssClass="error" path="pickupPoint"/>
        </span>
        <span>
            <label>Pickup Time*:</label>
            <input type="text" name="pickupTime" placeholder="Example: 09:00" value="${createCarpoolForm.pickupTime}"/>
            <form:errors cssClass="error" path="pickupTime"/>
        </span>
        <span>
            <label>Cab Type*:</label>
            <select name="cabType" style="margin-top: 10px;margin-bottom: 0px;">
                <option value="PERSONAL">Personal</option>
                <option value="COMPANY">Company</option>
            </select>
            <form:errors cssClass="error" path="cabType"/>
        </span>
        <span id="capacity">
            <label>Capacity*</label>
            <input type="text" name="capacity" value="0" value="${createCarpoolForm.capacity}"/>
            <form:errors cssClass="error" path="capacity"/>
        </span>
        <span>
            <label>Office Arrival Time*:</label>
            <input type="text" name="officeArrivalTime" placeholder="Example: 10:00"
                   value="${createCarpoolForm.officeArrivalTime}"/>
            <form:errors cssClass="error" path="officeArrivalTime"/>
        </span>
        <span>
            <label>Office Departure Time*:</label>
            <input type="text" name="officeDepartureTime" placeholder="Example: 18:00"
                   value="${createCarpoolForm.officeDepartureTime}"/>
            <form:errors cssClass="error" path="officeDepartureTime"/>
        </span>
        <span>
            <label>Route Points:</label>
            <input type="text" name="routePoints"
                   placeholder="Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla"
                   value="${createCarpoolForm.routePoints}"/>
            <form:errors cssClass="error" path="routePoints"/>
        </span>

            <div class="buttonContainer">
                <button type="submit" name="submit">Submit</button>
            </div>
        </form:form>
    </div>
</div>
<%@ include file="../footer.jsp" %>

