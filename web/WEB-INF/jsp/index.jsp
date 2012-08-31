<%@ include file="include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SmartPool</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="#">SmartPool</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li class="active"><a href="#">Welcome</a></li>
                        <li><a href="#about">Search</a></li>
                        <li><a href="#contact">Profile</a></li>
                        <li><a href="#contact">Notifications</a></li>

                    </ul>
                    <form class="navbar-form pull-right">
                        username
                        <button type="submit" class="btn">Logout</button>
                    </form>
                </div><!--/.nav-collapse -->
            </div>
        </div>
    </div>

    <div class="container">

        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
            <h1>Hello, SmartPool!</h1>
            <p>    Hello!!!  Anna and Ali are working...DON'T Disturb :P</p>
            <%--<p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>--%>
        </div>



        <hr>

        <footer>
            <p>&copy; Anna/Ali</p>
        </footer>

    </div> <!-- /container -->





</body>
</html>