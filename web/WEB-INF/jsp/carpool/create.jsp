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
        font-weight: bold;
    }
</style>
    <div>
        <h1 class="header">Create Carpool</h1>
    </div>
<div class="mainContainer">
    <form:form commandName="createCarpoolForm" htmlEscape="true">
        <div>
            <spring:hasBindErrors name="createCarpoolForm">
                <spring:message code="errors.exist" />
            </spring:hasBindErrors>
        </div>
        <span>
            <label>From*</label>
            <input type="text" name="from" />
            <span><form:errors path="from" /> </span>
        </span>
        <span>
            <label>To*</label>
            <input type="text" name="to" required/>
            <span><form:errors cssClass="error" path="to" /> </span>
        </span>
        <span>
            <label>Proposed Start Date*:</label>
            <input type="text" name="proposedStartDate" placeholder="DD/MM/YYYY" required/>
            <span><form:errors cssClass="error" path="proposedStartDate" /> </span>
        </span>
        <span>
            <label>Pickup Point*:</label>
            <input type="text" name="pickupPoint" placeholder="Example: Diamond District" required/>
            <span><form:errors cssClass="error" path="pickupPoint" /> </span>
        </span>
        <span>
            <label>Pickup Time*:</label>
            <input type="text" name="pickupTime" placeholder="Example: 09:00" required/>
            <span><form:errors cssClass="error" path="pickupTime" /> </span>
        </span>
        <span>
            <label>Cab Type*:</label>
            <select name="cabType">
                <option value="PERSONAL">Personal</option>
                <option value="COMPANY">Company</option>
            </select>
            <span><form:errors cssClass="error" path="cabType" /> </span>
        </span>
        <span id="capacity">
            <label>Capacity*</label>
            <input type="text" name="capacity" value="0" />
            <span><form:errors cssClass="error" path="capacity" /> </span>
        </span>
        <span>
            <label>Office Arrival Time*:</label>
            <input type="text" name="officeArrivalTime" placeholder="Example: 10:00" required/>
            <span><form:errors cssClass="error" path="officeArrivalTime" /> </span>
        </span>
        <span>
            <label>Office Departure Time*:</label>
            <input type="text" name="officeDepartureTime" placeholder="Example: 18:00" required/>
            <span><form:errors cssClass="error" path="officeDepartureTime" /> </span>
        </span>
        <span>
            <label>Route Points:</label>
            <input type="text" name="routePoints" placeholder="Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla" />
            <span><form:errors cssClass="error" path="routePoints" /> </span>
        </span>
        <div class="buttonContainer">
            <button type="submit" name="submit">Submit</button>
        </div>
    </form:form>
</div>
<%@ include file="../footer.jsp" %>

