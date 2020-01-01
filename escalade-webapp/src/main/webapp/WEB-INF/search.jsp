<!DOCTYPE html>
<html lang="fr">
<head>
   <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name} - Recherche</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Recherche</h1>

    <form method="post" action="search">
        <div class="form-group">
            <label for="type_publication">Type publication :</label>
            <select name="type_publication" id="type_publication">
                <option value="spot">Spot</option>
                <option value="sector">Secteur</option>
                <option value="route">Voie</option>
                <option value="topo">Topo</option>
            </select>
        </div>

        <div class="form-group">
            <label for="text_name_description">Texte :</label>
            <input type="text" class="form-control" name="text_name_description" id="text_name_description"/>
        </div>

        <div class="form-group">
            <label for="height">Hauteur maximum :</label>
            <input type="number" class="form-control" name="height" id="height" min="0" max="50"/>
        </div>

        <div class="form-group">
            <label for="quotation">Cotation :</label>
                <select name="quotation"  id="quotation">
                    <c:forEach var="quotation_range" items="${ quotations }">
                        <option value="${ quotation_range.id }">${ quotation_range.name }</option>
                    </c:forEach>
             </select>
        </div>

        <div class="form-group">
            <label for="points_number">Nombre de points :</label>
            <input type="number" class="form-control" name="points_number" id="points_number"/>
        </div>
        <button type="submit" class="btn btn-primary">Rechercher</button>
    </form>
<br>
    <c:if test="${ publicationList.size() > 0 }">
        <h4>Resultat</h4>
        <ul>
            <c:choose>
                <c:when test="${ typePublication == 'spot' }">
                    <c:forEach var="publication" items="${ publicationList }">
                        <li><a href="${pageContext.request.contextPath}/spot/${ publication.id }"><c:out value="${ publication.name }"/></a></li>
                    </c:forEach>
                </c:when>
                <c:when test="${ typePublication == 'sector' }">
                    <c:forEach var="publication" items="${ publicationList }">
                        <li><a href="${pageContext.request.contextPath}/spot/${ publication.spotId }"><c:out value="${ publication.name }"/></a></li>
                    </c:forEach>
                </c:when>
                <c:when test="${ typePublication == 'route' }">
                    <c:forEach var="publication" items="${ publicationList }">
                        <li><a href="${pageContext.request.contextPath}/route/${ publication.id }"><c:out value="${ publication.name }"/></a></li>
                    </c:forEach>
                </c:when>
                <c:when test="${ typePublication == 'topo' }">
                    <c:forEach var="publication" items="${ publicationList }">
                        <li><a href="${pageContext.request.contextPath}/topo/${ publication.id }"><c:out value="${ publication.name }"/></a></li>
                    </c:forEach>
                </c:when>
            </c:choose>
        </ul>
    </c:if>
</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>
