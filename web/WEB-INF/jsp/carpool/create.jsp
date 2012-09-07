<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="createCarpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Create Carpool" />
</jsp:include>


    <div>
        <label class="header">Create Carpool</label>
    </div>
<div class="mainContainer">
    <form action="create" method="post">
        <span>
            <label>From</label>
            <input type="text" name="from" required/>
        </span>
        <span>
            <label>To</label>
            <input type="text" name="to" required/>
        </span>
        <span>
            <label>Proposed Start Date:</label>
            <input type="text" name="proposedStartDate" required/>
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
            <label class="example">(09:00)</label>
        </span>
        <span>
            <label>CabType:</label>
            <select name="cabType">
                <option value="PERSONAL">Personal</option>
                <option value="COMPANY">Company</option>
            </select>
        </span>
        <span>
            <label>Capacity</label>
            <input type="text" name="capacity" />
        </span>
        <span>
            <label>Office Arrival Time:</label>
            <input type="text" name="officeArrivalTime" required/>
            <label class="example">(10:00)</label>
        </span>
        <span>
            <label>Office Departure Time:</label>
            <input type="text" name="officeDepartureTime" required/>
            <label class="example">(18:00)</label>
        </span>
        <span>
            <label>Route Points:</label>
            <input type="text" name="routePoints" />
            <label class="example">(Example: Whitefield, Ramagondanhalli, Varthar Road, Marathahalli, HAL Airport, Domlur, Koramangla)</label>
        </span>
        <div class="buttonContainer">
            <button type="submit" name="submit">Submit</button>
        </div>
    </form>
</div>
<%@ include file="../footer.jsp" %>

