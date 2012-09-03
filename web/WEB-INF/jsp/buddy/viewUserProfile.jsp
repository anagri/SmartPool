<%@ include file="../navbar.jsp" %>

<html>
<head>
    <title>${buddyProfile.getName()}'s Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profileLayout.css"/>
</head>
<body>
<form name="view_profile">
<div class="mainContainer">
        <div class="subContainer" >
                    <span id="nameLabel">
                        <label>Name</label>
                    </span>
                    <span id="nameValueLabel">
                        <label>${buddyProfile.getName()}</label>
                    </span>
        </div>


        <div class="subContainer" >
                    <span id="contactLabel">
                        <label>Contact Number</label>
                    </span>
            <span id="contactValueLabel">
                        <label>${buddyProfile.getContactNumber()}</label>
                    </span>
        </div>

        <div class="subContainer">
                    <span id="emailIdLabel">
                        <label>Email</label>
                    </span>
            <span id="emailIdValueLabel">
                        <label>${buddyProfile.getEmailId()}</label>
                    </span>
        </div>

        <div class="subContainer">
                <span id="pickupPointLabel">
                    <label>Preferred Pickup Point</label>
                </span>
            <span id="pickupPointValueLabel">
                        <label>${buddyProfile.getPickupPoint()}</label>
                    </span>
        </div>
    <div class="buttonContainer">
        <button>Edit</button>
    </div>

</div>
    <%--<div style="margin-left:300px;top:300px">--%>
    <%--<button>Contact ${buddyProfile.getName()}</button>--%>
    <%--</div>--%>

</form>
</body>
</html>
