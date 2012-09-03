<%@ include file="../include.jsp" %>
<%@ include file="../navbar.jsp" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profileLayout.css"/>
    <title>Join SmartPool: ${carpoolName}</title>
</head>
<body>

<h1>Join Smart Pool: ${carpoolName}</h1>
<c:if test="${isRequestSent}">
    <font color="red">You have already sent the request for this carpool</font>
</c:if>
<form name="joinRequest" method="post" onsubmit="alert('Join Carpool Request Sent Successfully'); return true">

    <div class="mainContainer">

        <div class="subContainer">
                    <span id="nameLabel">
                        <label>Name*</label>
                    </span>
                    <span id="nameTextBox">
                        <input name="name" type="textbox" required="required" value=${buddy.getName()} readonly /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="addressLabel">
                        <label>Address</label>
                    </span>
                    <span id="addressTextBox">
                        <textarea name="address" rows="5" cols="30" value=${buddy.getAddress()} > </textarea> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="contactNumberLabel">
                        <label>Contact Number*</label>
                     </span>
                     <span id="contactNumberTextBox">
                        <input name="contactNumber" type="textbox" required="required" value=${buddy.getContactNumber()}  /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="emailIdLabel">
                        <label>Email*</label>
                    </span>
                    <span id="emailIdTextBox">
                        <input name="emailId" type="textbox" required="required" value=${buddy.getEmailId()} readonly /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="pickupPointLabel">
                        <label>Preferred Pick Up Point*</label>
                    </span>
                    <span id="pickupPointTextBox">
                        <input name="pickupPoint" required="required" type="textbox" /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="pickupTimeLabel">
                        <label>Pick Up Time (hh:mm)</label>
                    </span>
                    <span id="pickupTimeTextBox">
                        <input name="pickupTime" type="textbox" /> <br />
                    </span>
        </div>
        <div class="buttonContainer">
            <c:set var="isDisabled" value="${isRequestSent ? 'disabled' : ''}"/>
            <button align="right" name="submit" type="submit" ${isDisabled}>Submit</button>
            <button align="right" disabled="disabled">EDIT</button>
        </div>
    </div>
</form>
</body>
</html>

