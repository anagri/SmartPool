<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="carpool.css,standardLayout.css"/>
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/carpool/search.js"></script>

<div>
    <h1 class="header">Dashboard</h1>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script type="text/javascript">
    function searchByRoutePoint() {
        var index = document.getElementById('routePointList').selectedIndex;
        var value = document.getElementById('routePointList').options[index].text;

        document.location.href = '../carpool/search?query=' + value;
    }

    function setOptionInDropDown(query) {
        var list = document.getElementById("routePointList");
        for (var i = 0; i < list.options.length; i++) {
            if (list.options[i].text == query) {
                list.options[i].selected = true;
                return;
            }
        }
    }
</script>
<div class="containerWrapper">
    <div class="leftContent">
        <c:if test="${searchResult ne null}">
            <table id='search-result'>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Buddies</th>
                    <th>Status</th>
                    <th>Charges</th>
                    <th>Capacity</th>
                </tr>
                </thead>
                <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/carpool/${carpool.getName()}"
                               id="${carpool.getName()}">
                                ${carpool.getFrom()}
                            <img src="${pageContext.request.contextPath}/css/img/arrow-sign.jpg"
                                 class="small-arrow-sign"/>
                                ${carpool.getTo()}
                        </a></td>

                        <td>
                            <ul>
                                <c:forEach var="carpoolBuddy" items="${carpool.getCarpoolBuddies()}" varStatus="buddyIndex">
                                    <c:set var="buddy" value="${carpoolBuddy.getBuddy()}" />
                                    <li>${buddy.getName()}</li>
                                </c:forEach>
                            </ul>
                        </td>

                        <td id="status${typeStatus.count}">
                            <select id="status-${typeStatus.count}" name="status">
                                <option value="ACTIVE">Active</option>
                                <option value="NOT_STARTED">Not Started</option>
                                <option value="DROPPED">Dropped</option>
                            </select>
                            <script type="text/javascript">
                                $("#status-" + ${typeStatus.count} + " option").each(function (index, select) {
                                    if (select.value == '${carpool.getStatus().toString()}') select.selected = true
                                });
                            </script>
                        </td>
                        <td>
                            <input type="text" style="text-align: right; max-width: 10em" name="totalCabCharges" value="${carpool.getTotalCabCharges()}" />
                        </td>
                        <td>
                            <input type="number" name="capacity" style="text-align: right; max-width: 3em" value="${carpool.getCapacity()}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>
