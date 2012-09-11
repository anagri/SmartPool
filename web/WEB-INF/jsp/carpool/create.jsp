<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="createCarpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Create Carpool" />
</jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/create.js"></script>
<style type="text/css" >
    .error{
        color: #f00;
        font-size: smaller;
    }
</style>
    <div>
        <label class="header">Create Carpool</label>
    </div>
<div class="mainContainer">
    <form:form commandName="createCarpoolForm" htmlEscape="true">
        <div align="center" style="color: red">
            <spring:hasBindErrors name="createCarpoolForm">
                <spring:message code="errors.exist" />
            </spring:hasBindErrors>
        </div>
        <span>
            <label>From*</label>
            <input type="text" name="from" value="${createCarpoolForm.from}"/>
            <form:errors cssClass="error" path="from" />
        </span>
        <span>
            <label>To*</label>
            <input type="text" name="to" value="${createCarpoolForm.to}"/>
            <form:errors cssClass="error" path="to" />
        </span>
        <span>
            <label>Proposed Start Date*:</label>
            <input type="text" name="proposedStartDate" value="${createCarpoolForm.proposedStartDate}"/>
            <form:errors cssClass="error" path="proposedStartDate" />
            <label class="example">(DD/MM/YYYY)</label>
        </span>
        <span>
            <label>Pickup Point*:</label>
            <input type="text" name="pickupPoint" value="${createCarpoolForm.pickupPoint}"/>
            <form:errors cssClass="error" path="pickupPoint" />
            <label class="example">(Example: Diamond District)</label>
        </span>
        <span>
            <label>Pickup Time*:</label>
            <input type="text" name="pickupTime" value="${createCarpoolForm.pickupTime}"/>
            <form:errors cssClass="error" path="pickupTime" />
            <label class="example">(Example: 09:00)</label>
        </span>
        <span>
            <label>Cab Type*:</label>
            <select name="cabType" value="${createCarpoolForm.cabType}">
                <option value="PERSONAL">Personal</option>
                <option value="COMPANY">Company</option>
            </select>
            <form:errors cssClass="error" path="cabType" />
        </span>
        <span id="capacity">
            <label>Capacity*</label>
            <input type="text" name="capacity" value="${createCarpoolForm.capacity}"/>
            <form:errors cssClass="error" path="capacity" />
        </span>
        <span>
            <label>Office Arrival Time*:</label>
            <input type="text" name="officeArrivalTime" value="${createCarpoolForm.officeArrivalTime}"/>
            <form:errors cssClass="error" path="officeArrivalTime" />
            <label class="example">(Example: 10:00)</label>
        </span>
        <span>
            <label>Office Departure Time*:</label>
            <input type="text" name="officeDepartureTime" value="${createCarpoolForm.officeDepartureTime}"/>
            <form:errors cssClass="error" path="officeDepartureTime" />
            <label class="example">(Example: 18:00)</label>
        </span>
        <span>
            <label>Route Points:</label>
            <input type="text" name="routePoints" />
            <form:errors cssClass="error" path="routePoints" />
            <label class="example">(Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla)</label>
        </span>
        <div class="buttonContainer">
            <button type="submit" name="submit">Submit</button>
        </div>
    </form:form>
</div>
<%@ include file="../footer.jsp" %>

