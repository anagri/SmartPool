<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profileLayout.css"/>
</head>
<body>
<form name="view_profile" action="">

    <div class="mainContainer">
        <div class="subContainer">
                    <span id="nameLabel">
                        <label>Name</label>
                    </span>
                    <span id="nameTextBox">
                        <input name="name" type="textbox" value="${buddyProfile.userName()}"/> <br/>
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
