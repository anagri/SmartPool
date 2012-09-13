<%@ page import="edu.yale.its.tp.cas.client.filter.CASFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="../navbar.jsp" flush="true">
    <jsp:param name="css" value="dashboard.css,standardLayout.css"/>
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
        <div id="dashboard">
            <c:if test="${searchResult ne null}">
                <span class="carpoolHeaders">
                    <div>
                        <span class="carpoolNameColumn">Name</span>
                        <span class="buddyListColumn">Buddies</span>
                        <span class="statusColumn">Status</span>
                        <span class="chargesColumn">Charges</span>
                        <span class="capacityColumn">Capacity</span>
                    </div>
                </span>
                <c:forEach var="carpool" items="${searchResult}" varStatus="typeStatus">
                    <c:set var="carpool" value="${carpool}" scope="request"/>
                    <c:set var="thisEdit" value="${carpool.getName().equals(carpoolName)}" scope="request"/>

                    <c:if test="${errors}">
                        <c:set var="charges" value="${updateCarpoolForm.charges}"/>
                        <c:set var="status" value="${updateCarpoolForm.status}"/>
                        <c:set var="capacity" value="${updateCarpoolForm.capacity}"/>
                    </c:if>
                    <c:if test="${thisEdit eq false}">
                        <c:set var="charges" value="${carpool.totalCabCharges}"/>
                        <c:set var="status" value="${carpool.getStatus().toString()}"/>
                        <c:set var="capacity" value="${carpool.getCapacity()}"/>
                    </c:if>

                    <div class="carpoolRow">
                        <form:form commandName="updateCarpoolForm" method="post"
                                   action="${pageContext.request.contextPath}/dashboard/${carpool.getName()}/update/"
                                   htmlEscape="true">
                            <div>

                        <span class="carpoolNameColumn"><a
                                href="${pageContext.request.contextPath}/carpool/${carpool.getName()}"
                                id="${carpool.getName()}">
                                ${carpool.getFrom()}
                            <img src="${pageContext.request.contextPath}/css/img/arrow-sign.jpg"
                                 class="small-arrow-sign"/>
                                ${carpool.getTo()}
                        </a></span>

                            <span class="buddyListColumn">
                                <ul>
                                    <jsp:include page="dropBuddy.jsp" flush="true"/>
                                </ul>
                            </span>
                            <span class="statusColumn" id="status${typeStatus.count}">
                                <select class="carpoolStatusDropdown" id="status-${typeStatus.count}" name="status">
                                    <option value="ACTIVE">Active</option>
                                    <option value="NOT_STARTED">Not Started</option>
                                    <option value="DROPPED">Dropped</option>
                                </select>
                                <span class="errorText"><c:if test="${thisEdit}"><form:errors path="status"/></c:if>&nbsp;</span>
                                <script type="text/javascript">

                                    $("#status-" + ${typeStatus.count} +" option").each(function (index, select) {
                                        if (select.value == '${status}') select.selected = true
                                    });
                                </script>
                            </span>
                            <span class="chargesColumn">
                                <input type="text" style="text-align: right; max-width: 10em" name="charges"
                                       value="${charges}"/>
                                <span class="errorText"><c:if test="${thisEdit}"><form:errors path="charges"/></c:if>&nbsp;</span>


                            </span>
                            <span class="capacityColumn">

                                <input type="number" name="capacity" style="text-align: right; max-width: 3em"
                                       value="${capacity}"/>
                                <span class="errorText"><c:if test="${thisEdit}"><form:errors path="capacity"/></c:if>&nbsp;</span>


                            </span>
                                <span class="update">
                                    <c:if test="${thisEdit}">
                                        <span class="errors">
                                         <spring:hasBindErrors name="updateCarpoolForm">
                                             <spring:message code="errors.exist"/>
                                         </spring:hasBindErrors>
                                        </span>
                                    </c:if>
                                    <button type="submit">Update</button>

                                </span>

                            </div>

                        </form:form>
                    </div>

                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
