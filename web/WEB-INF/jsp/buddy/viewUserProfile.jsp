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
        <span><label>${buddyProfile.getName()}</label></span>
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
        <span><label>${buddyProfile.getPickupPoint()}</label></span>
    </div>
    <div class="buttonContainer">
        <button>Edit</button>
    </div>
</div>
<%@ include file="../footer.jsp" %>
