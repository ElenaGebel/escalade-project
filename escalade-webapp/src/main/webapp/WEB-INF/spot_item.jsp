<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-spot</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>${ spot.name }</h1>

    <c:if test="${ !empty spot.image }">
        <div>
            <img class="spot-img option-cursor" width="60%" src="${pageContext.request.contextPath}/${ spot.image }">
        </div>
    </c:if>

    <c:if test="${ !empty sessionScope.user }">
        <c:if test="${ sessionScope.user.role == 'admin' }">
            <form:form method="post" action="spot/${spot.id}/delete" modelAttribute="spot">
                <input hidden name="picture" title="picture" value="${ spot.image }" />
                <button type="submit" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> Supprimer le spot
                </button>
            </form:form>
        </c:if>

    </c:if>

    <h4>Description</h4>
    <p>${ spot.description }</p>
    <!-- TopoHasSpot READ -->
    <c:if test="${!empty topo.id}">
        <h4>Topo - <a href="${pageContext.request.contextPath}/topo/${ topo.id }"><c:out value="${ topo.name }"/></a></h4>        
    </c:if>

</div>

</body>
</html>