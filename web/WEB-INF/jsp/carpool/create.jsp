<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="profileLayout.css"/>
    <jsp:param name="title" value="${buddyProfile.getName()}'s Profile" />
</jsp:include>

<div class="headerContainer">
Create Carpool
</div>
<div class="mainContainer">
    <form action="create" method="post">
        <div class="subContainer">
            <span><label>Name: </label></span>
            <span><input type="text" name="name" required/></span>
        </div>
        <div class="subContainer">
            Proposed Start Date(DD/MM/YYYY):
            <input type="text" name="startDateForm" required/><br/>
        </div>
        <div class="subContainer">
        Pickup Point <input type="text" name="pickupPoint" required/><br/>
        </div>
        <div class="subContainer">
            Pickup Time(HH:MM) <input type="text" name="pickupTime" required/><br/>
        </div>
        <div class="subContainer">
            CabType <select name="cabType"><option value="PERSONAL">Personal</option><option value="COMPANY">Company</option></select><br/>
        </div>
        <div class="subContainer">
            Office ETA(HH:MM) <input type="text" name="officeETAForm" required/><br/>
        </div>
        <div class="subContainer">
            Office ETD(HH:MM) <input type="text" name="officeETDForm" required/><br/>
        </div>
        <div class="subContainer">
            The Carpool will Pass By (Comma Seperated): <input type="text" name="routePointForm" /> (Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla)<br/>
        </div>
        <div class="subContainer">
            <button type="submit" value="Submit"/>
        </div>
    </form>
</div>
<%@ include file="../footer.jsp" %>

