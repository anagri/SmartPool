<html>
    <head>
        <title>Create Carpool</title>
    </head>
    <body>
        Hello Create Carpool
        <form action="create" method="post">
            Name: <input type="text" name="name" required/><br/>
            Proposed Start Date(DD/MM/YYYY): <input type="text" name="startDate" required/><br/>
            Pickup Point <input type="text" name="pickupPoint" /><br/>
            Pickup Time(HH:MM) <input type="text" name="pickupTime" /><br/>
            CabType <select name="cabType"><option value="PERSONAL">Personal</option><option value="COMPANY">Company</option></select><br/>
            Office ETA(HH:MM) <input type="text" name="officeETA" /><br/>
            Office ETD(HH:MM) <input type="text" name="officeETD" /><br/>
            Status <select name="status"><option value="RUNNING">Running</option><option value="PENDING">Pending</option></select><br/>
            <input type="submit" value="submit" />
        </form>
    </body>
</html>

