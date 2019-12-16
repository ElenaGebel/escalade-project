
<nav class="navbar navbar-expand-lg navbar-light bg-light">
   <a class="navbar-brand" href="${pageContext.request.contextPath}/">${application.name}</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item ">
          <a class="nav-link" href="${pageContext.request.contextPath}/">Accueil</a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/spot">Les Sites</a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/topo">Les Topos</a>
      </li>
            <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/sector">Les Secteurs</a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/route">Les Voies</a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/search">Recherche</a>
      </li>
    </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${ empty sessionScope.user }">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/inscription"><span class="glyphicon glyphicon-user"></span> Creer un compte</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Se connecter</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/account"><span class="glyphicon glyphicon-user"></span> ${ sessionScope.user.pseudo } </a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Se deconnecter</a></li>

                    </c:otherwise>
                </c:choose>
            </ul>
  </div>
</nav>