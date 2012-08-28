<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profileLayout.css"/>
</head>
<body>
<form name="view_profile" action="">

    <div class="mainContainer">
        <div class="subContainer">
                    <span id="userNameLabel">
                        <label>User Name</label>
                    </span>
                    <span id="userNameTextBox">
                        <input name="userName" type="textbox" value="${buddyProfile.username}" /> <br/>
                    </span>
        </div>
        <div class="subContainer">
                    <span id="nameLabel">
                        <label>Name</label>
                    </span>
                    <span id="nameTextBox">
                        <input name="name" type="textbox" value="${buddyProfile.name}"/> <br/>
                    </span>
        </div>
        <div class="subContainer">
                    <span id="addressLabel">
                        <label>Address</label>
                    </span>
                    <span id="addressTextBox">
                        <textarea name="address" rows="4" cols="15"  value="${buddyProfile.address }">${buddyProfile.address}</textarea><br/>
                    </span>
        </div>

        <div class="subContainer">
                    <span id="contactNumberLabel">
                        <label>Contact Number</label>
                    </span>
                    <span id="contactNumberTextBox">
                        <input name="contactNumber" type="textbox" value="${buddyProfile.contactNumber}"/> <br/>
                    </span>
        </div>

        <div class="subContainer">
                    <span id="emailIdLabel">
                        <label>Email Id</label>
                    </span>
                    <span id="emailIdTextBox">
                        <input name="emailId" type="textbox" value="${buddyProfile.emailId}"/> <br/>
                    </span>
        </div>

        <div class="buttonContainer">
            <button align="right">CONTACT BUDDY</button>
            <button align="right" disabled="disabled">EDIT</button>
        </div>
    </div>
</form>
</body>
</html>
