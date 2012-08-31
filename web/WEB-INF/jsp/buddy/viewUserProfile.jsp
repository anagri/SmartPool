<%@ include file="../navbar.jsp" %>

<html>
<head>
    <title>My Profile</title>
</head>
<body>
<form name="view_profile">
    <div class="container">
        <div>
            <table>
                <tbody>
                <tr>
                    <td>User Name</td>
                    <td>${buddyProfile.getUserName()}</td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td>${buddyProfile.getName()}</td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>${buddyProfile.getAddress()}</td>
                </tr>
                <tr>
                    <td>Contact Number</td>
                    <td>${buddyProfile.getContactNumber()}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${buddyProfile.getEmailId()}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <br>
        <br>
        <div style="margin-left:300px;top:300px">
            <button>Edit</button>
        </div>

    </div>
</form>
</body>
</html>
