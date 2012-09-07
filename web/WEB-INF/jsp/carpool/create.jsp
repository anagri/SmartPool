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
        <label class="header">Create Carpool</label>
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
            <input type="text" name="proposedStartDate" required/>
            <label class="example">(DD/MM/YYYY)</label>
            <span><form:errors cssClass="error" path="proposedStartDate" /> </span>
        </span>
        <span>
            <label>Pickup Point*:</label>
            <input type="text" name="pickupPoint" required/>
            <label class="example">(Example: Diamond District)</label>
            <span><form:errors cssClass="error" path="pickupPoint" /> </span>
        </span>
        <span>
            <label>Pickup Time*:</label>
            <input type="text" name="pickupTime" required/>
            <label class="example">(Example: 09:00)</label>
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
            <input type="text" name="officeArrivalTime" required/>
            <label class="example">(Example: 10:00)</label>
            <span><form:errors cssClass="error" path="officeArrivalTime" /> </span>
        </span>
        <span>
            <label>Office Departure Time*:</label>
            <input type="text" name="officeDepartureTime" required/>
            <label class="example">(Example: 18:00)</label>
            <span><form:errors cssClass="error" path="officeDepartureTime" /> </span>
        </span>
        <span>
            <label>Route Points:</label>
            <input type="text" name="routePoints" />
            <label class="example">(Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla)</label>
            <span><form:errors cssClass="error" path="routePoints" /> </span>
        </span>
        <div class="buttonContainer">
            <button type="submit" name="submit">Submit</button>
        </div>
    </form:form>
</div>
<%@ include file="../footer.jsp" %>

