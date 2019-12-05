<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">${application.name}</a>
        </div>
          <ul class="nav navbar-nav">
                <li><a class="active" href="${pageContext.request.contextPath}/">Accueil</a></li>
                <li><a href="${pageContext.request.contextPath}/sites">Les Sites</a></li>
                <li><a href="${pageContext.request.contextPath}/topos">Les Topos</a></li>
                <li><a href="${pageContext.request.contextPath}/search">Recherche</a></li>
           </ul>          

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${ empty sessionScope.user }">
                        <li><a href="${pageContext.request.contextPath}/inscription"><span class="glyphicon glyphicon-user"></span> Creer un compte</a></li>
                        <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Se connecter</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/account"><span class="glyphicon glyphicon-user"></span> ${ sessionScope.user.pseudo } </a></li>
                        <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Se deconnecter</a></li>

                    </c:otherwise>
                </c:choose>
            </ul>
        </div>


</nav>