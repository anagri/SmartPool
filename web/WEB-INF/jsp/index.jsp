<jsp:include page="navbar.jsp" flush="true">
    <jsp:param name="css" value="homepageLayout.css"/>
    <jsp:param name="title" value="SmartPool" />
</jsp:include>

<div class="mainContainer">
    <span id="welcomeMessage" class="welcomeContainer">
        <img src="${pageContext.request.contextPath}/css/img/taxi.png" alt="" class="imageContainer"/>
        <h1>SmartPool</h1>
    </span>
    <form action="${pageContext.request.contextPath}/carpool/search" method="GET" class="searchBar">
        <input type="text" name="query" placeholder="Search by location" id="searchBox" class="searchText" onclick="this.value=''"/>
        <button type="submit" class="btn searchButton" value="Search"/><i class="icon-search"></i></button>
    </form>
    <div class="helpText">Looking For a Carpool?</div>
    <div class="helpText">Type the location to search list of carpools available.</div>
</div>


<%@ include file="footer.jsp" %>