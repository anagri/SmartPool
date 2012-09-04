<%@ include file="../navbar.jsp" %>

<html>
    <head>
        <title>Create Carpool</title>
    </head>
    <body>
        Hello Create Carpool
        <form action="create" method="post">
            Name: <input type="text" name="name" required/><br/>
            Proposed Start Date(DD/MM/YYYY): <input type="text" name="startDateForm" required/><br/>
            Pickup Point <input type="text" name="pickupPoint" required/><br/>
            Pickup Time(HH:MM) <input type="text" name="pickupTime" required/><br/>
            CabType <select name="cabType"><option value="PERSONAL">Personal</option><option value="COMPANY">Company</option></select><br/>
            Office ETA(HH:MM) <input type="text" name="officeETAForm" required/><br/>
            Office ETD(HH:MM) <input type="text" name="officeETDForm" required/><br/>
            <input type="submit" value="submit" />
        </form>
    </body>
</html>

