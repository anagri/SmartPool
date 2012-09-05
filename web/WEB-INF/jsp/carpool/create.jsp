<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="createCarpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Create Carpool" />
</jsp:include>

<div class="mainContainer">
    <div>
        <label class="header">Create Carpool</label>
    </div>
    <form action="create" method="post">
        <span>
            <label>Name: </label>
            <input type="text" name="name" required/>
        </span>
        <span>
            <label>Proposed Start Date:</label>
            <input type="text" name="startDateForm" required/>
            <label class="example">(DD/MM/YYYY)</label>
        </span>
        <span>
            <label>Pickup Point:</label>
            <input type="text" name="pickupPoint" required/>
            <label class="example">(Example: Diamond District)</label>
        </span>
        <span>
            <label>Pickup Time:</label>
            <input type="text" name="pickupTime" required/>
            <label class="example">(HH:MM)</label>
        </span>
        <span>
            <label>CabType:</label>
            <select name="cabType">
                <option value="PERSONAL">Personal</option>
                <option value="COMPANY">Company</option>
            </select>
        </span>
        <span>
            <label>Office ETA:</label>
            <input type="text" name="officeETAForm" required/>
            <label class="example">(HH:MM)</label>
        </span>
        <span>
            <label>Office ETD:</label>
            <input type="text" name="officeETDForm" required/>
            <label class="example">(HH:MM)</label>
        </span>
        <span>
            <label>Route Points:</label>
            <input type="text" name="routePointForm" />
            <label class="example">(Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla)</label>
        </span>
        <div class="buttonContainer">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>
<%@ include file="../footer.jsp" %>

