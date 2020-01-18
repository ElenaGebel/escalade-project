<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-topos</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">

    <h1>${ topo.name }</h1>
        <c:if test="${ !empty sessionScope.user }">
        <c:if test="${ sessionScope.user.role == 'admin' or sessionScope.user.id == topo.userId }">
            <form:form method="post" action="topo/${topo.id}/delete" modelAttribute="topo">
                <input hidden name="picture" title="picture" value="${ topo.image }" />
                <button type="submit" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> Supprimer le topo
                </button>
            </form:form>
        </c:if>
    </c:if>
    <c:if test="${ !empty topo.image }">
        <div>
            <img class="spot-img option-cursor" width="60%" src="${pageContext.request.contextPath}/${ topo.image }">
        </div>
    </c:if>

    <c:if test="${ !empty sessionScope.user }">
        <c:if test="${ notRelatedSpots.size() > 0 }">
           <br>
            <p>
                <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                    <span class="glyphicon glyphicon-plus"></span> Lier un site
                </button>
            </p>

            <form method="post" action="addspot/${topo.id}" class="form-horizontal collapse collapse-menu">
                <div class="form-group">
                    <label for="spots">Nom :</label>
                    <select name="spotId" id="spots">
                        <c:forEach var="spot" items="${ notRelatedSpots }">
                            <option value="${ spot.id }">${ spot.name }</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </form>
        </c:if>

        <!-- UserHasTopo CREATE -->
        <c:if test="${!topo.reserved}">
            <c:if test="${topo.statusReservation == 0}">
	             <h5>Topo est disponible pour reservation.</h5>
	            <form hidden method="post" action="reserver/${ topo.id }" class="user-topo-add"></form>
	            <button type="button" class="btn btn-warning btn-xs" onclick="$('.user-topo-add').submit();">
	                <span class="glyphicon glyphicon-ok"></span> Reserver
	            </button>           
            </c:if>
            <c:if test="${topo.statusReservation == 2}">
	            <h5>Topo est en cours de reservation..</h5>
            </c:if>
        </c:if>
        <c:if test="${ topo.reserved}">
            <h5>Topo reserve. Date fin de reservation: ${ topo.getReservationEndDate() }.</h5>
	        <c:if test="${ sessionScope.user.id == topo.userId }">	            
	            <form hidden method="post" action="unreserver/${ topo.id}" class="user-topo-delete"></form>
	            <button type="button" class="btn btn-warning btn-xs" onclick="$('.user-topo-delete').submit();">
	                <span class="glyphicon glyphicon-ok"></span> Annuler reservation 
	            </button>
	        </c:if>
        </c:if>
    </c:if>


    <h4>Description</h4>
    <p>${ topo.description }</p>

    <!-- relatedSpots READ -->
    <c:if test="${ relatedSpots.size() > 0 }">
        <h4>${ relatedSpots.size() } site${ relatedSpots.size() > 1 ? 's' : '' }</h4>
        <ul>
            <c:forEach var="spot" items="${ relatedSpots }">
                <li>
                    <a href="${pageContext.request.contextPath}/spot/${ spot.id }"><c:out value="${ spot.name }"/></a>

                    <!-- relatedSpots DELETE -->
                    <c:if test="${ sessionScope.user.role == 'admin' or sessionScope.user.id == topo.userId }">
                        <form hidden method="post" action="deletespot/${ spot.id }" class="spot-delete${ spot.id }">
                            <input hidden name="topoId" title="topo_id" value="${ topo.id }" />
                        </form>
                        <a title="Delete" href="#" class="option-cursor" onclick="$('.spot-delete${ spot.id }').submit();">
                           Supprimer
                        </a>                        
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <%@include file="comments.jsp"%>
</div>

<%@ include file="include/footer.jsp" %>
</body>
</html>