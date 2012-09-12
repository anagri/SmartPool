<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="title" value="Create Profile"/>
</jsp:include>
<div class="container">
    <h1>Create Profile</h1>

    <div class="mainContainer">
        <form:form action="submit" commandName="createProfileForm">
        <span>
            <label>Name:</label>
            ${createProfileForm.name}
        </span>
        <span>
            <label>Address:</label>
            <textarea rows="3" cols="30" name="address" value="${createProfileForm.address}"></textarea>
        </span>

        <span>
            <label>Contact Number:</label>
            <input type="text" placeholder="Example: 9999999999" name="contactNumber" value="${createProfileForm.contactNumber}"/>
            <form:errors path="contactNumber" cssClass="error"/>
        </span>
        <span>
            <label>Email:</label>
            ${createProfileForm.email}
        </span>
        <span>
            <label>Preferred Pickup Point:</label>
            <input type="text" name="preferredPickupPoint" value="${createProfileForm.preferredPickupPoint}"/>
        </span>
        <span>
            <label>Preferred Pickup Time:</label>
            <input type="text" placeholder="Example: 10:00" name="preferredPickupTime" value="${createProfileForm.preferredPickupTime}"/>
            <form:errors path="preferredPickupTime" cssClass="error"/>
            <label class="example">(Example: 10:00)</label>
        </span>

            <div class="buttonContainer">
                <button type="submit" name="submit">Submit</button>
            </div>
        </form:form>
    </div>
</div>
<%@ include file="../footer.jsp" %>
