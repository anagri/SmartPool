<jsp:include page="navbar.jsp" flush="true">
    <jsp:param name="css" value="homepageLayout.css"/>
    <jsp:param name="title" value="SmartPool"/>
</jsp:include>

<div class="mainContainer">
    <span id="welcomeMessage" class="welcomeContainer">
        <img src="${pageContext.request.contextPath}/css/img/taxi.png" alt="" class="imageContainer"/>
        <h1>SmartPool</h1>
    </span>

    <form action="${pageContext.request.contextPath}/carpool/search" method="GET" class="searchBar">
        <input type="text" name="query" placeholder="Search by location" id="searchBox" class="searchText"
               onclick="this.value=''"/>
        <button type="submit" class="btn searchButton" value="Search"/>
        <i class="icon-search"></i></button>
    </form>
    <div class="helpText">Looking For a Carpool?</div>
    <div class="helpText">Type the location to search list of carpools available.</div>

    <div class="links">
        <p><a href="http://bprao.wordpress.com/2007/12/18/benefits-of-carpooling/">Why carpool?</a></p>

        <p><a href="http://www.publictransportation.org/tools/Pages/default.aspx">How much do I really save?</a></p>

        <p><a href="http://www.marc.org/rideshare/carpooltips.htm#etiquette">Carpool etiquette</a></p>
    </div>
</div>

<%@ include file="footer.jsp" %>