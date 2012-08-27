
<html>
<head>
    <link rel="stylesheet" type="text/css" href="layout.css" />
</head>
<body>
<form name="view_profile" action="." method="GET">

    <div class="mainContainer">
        <div class="subContainer">
                    <span id="nameLabel">
                        <label>Name</label>
                    </span>
                    <span id="nameTextBox">
                        <input name="name" type="textbox" value=${buddy.getName()} readonly /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="addressLabel">
                        <label>Address</label>
                    </span>
                    <span id="addressTextBox">
                        <textarea name="address" rows="5" cols="30" value=${buddy.getAddress()} readonly> </textarea> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="contactNumberLabel">
                        <label>Contact Number</label>
                     </span>
                     <span id="contactNumberTextBox">
                        <input name="contactNumber" type="textbox" value=${buddy.getContactNumber()} readonly /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="emailIdLabel">
                        <label>Email ID</label>
                    </span>
                    <span id="emailIdTextBox">
                        <input name="emailId" type="textbox" value=${buddy.getEmailId()} readonly/> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="pickupPointLabel">
                        <label>Preferred Pick Up Point</label>
                    </span>
                    <span id="pickupPointTextBox">
                        <input name="pickupTime" type="textbox" /> <br />
                    </span>
        </div>
        <div class="subContainer">
                    <span id="pickupTimeLabel">
                        <label>Pick Up Time</label>
                    </span>
                    <span id="pickupTimeTextBox">
                        <input name="pickupTime" type="textbox" /> <br />
                    </span>
        </div>
        <div class="buttonContainer">
            <button align="right" name="submit" method="POST">Submit</button>
            <button align="right" disabled="disabled">EDIT</button>
        </div>
    </div>
</form>
</body>
</html>

