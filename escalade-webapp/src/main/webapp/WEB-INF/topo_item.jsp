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
    
    <c:if test="${ !empty topo.image }">
        <div>
            <img class="spot-img option-cursor" width="60%" src="${pageContext.request.contextPath}/${ topo.image }">
        </div>
    </c:if>

    <c:if test="${ !empty sessionScope.user }">
        <c:if test="${ sessionScope.user.role == 'admin' }">
            <form:form method="post" action="topo/${topo.id}/delete" modelAttribute="topo">
                <input hidden name="picture" title="picture" value="${ topo.image }" />
                <button type="submit" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> Supprimer le topo
                </button>
            </form:form>
        </c:if>


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
        <c:if test="${ notRelatedUser == false }">
            <form hidden method="post" action="user-topo/${ topo.id }" class="user-topo-add"></form>
            <button type="button" class="btn btn-warning btn-xs" onclick="$('.user-topo-add').submit();">
                <span class="glyphicon glyphicon-ok"></span> Je poss�de ce topo
            </button>
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
                    <c:if test="${ sessionScope.user.role == 'admin' }">
                        <form hidden method="post" action="deletespot/${ spot.id }" class="spot-delete${ spot.id }">
                            <input hidden name="topoId" title="topo_id" value="${ topo.id }" />
                        </form>
                        <span class="comment-point"> � </span>
                        <a title="Delete" class="option-cursor" onclick="$('.spot-delete${ spot.id }').submit();">
                            <span class="glyphicon glyphicon-remove"></span>
                        </a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <!-- UserHasTopo READ -->
 <!--   <c:if test="${ userHasTopos.size() > 0 }">
        <h4>Topo disponibles � l'emprunt</h4>
        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Grimpeur</th>
                <th>Disponibilit�</th>
                <th>Date d'emprunt</th>
                <th>Date de retour</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userHasTopo" items="${ userHasTopos }">
                <tr>
                    <td><c:out value="${ userHasTopo.pseudo }"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${ !empty sessionScope.user && userHasTopo.id == sessionScope.user.id }">
                                <a title="Modify" class="option-cursor" data-toggle="modal" data-target=".modal-menu" data-backdrop="static" data-keyboard="false">
                                    <c:out value="${ userHasTopo.topo.loaned == true ? 'emprunt�' : 'disponible' }"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${ userHasTopo.topo.loaned == true ? 'emprunt�' : 'disponible' }"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:if test="${ !empty userHasTopo.topo.borrowingDateString }"><c:out value="${ userHasTopo.topo.borrowingDateString }"/></c:if></td>
                    <td><c:if test="${ !empty userHasTopo.topo.returnDateString }"><c:out value="${ userHasTopo.topo.returnDateString }"/></c:if></td>
                </tr>

                <!-- UserHasTopo UPDATE -->
             <!--    <c:if test="${ !empty sessionScope.user && userHasTopo.id == sessionScope.user.id }">
                    <div class="modal fade modal-menu">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Mise � jour</h4>
                                </div>

                                <div class="modal-body">
                                    <!-- UserHasTopo DELETE -->
                                <!--    <form hidden method="post" action="user-topo/${topo.id}/delete" class="publication-delete"></form>

                                    <form method="post" action="user-topo/${ topo.id }/update" class="form-horizontal publication-update">
                                        <div class="checkbox">
                                            <b>Disponibilit� : </b>
                                            <label>
                                                <input type="checkbox" name="loaned" value="${ userHasTopo.topo.loaned }" ${ userHasTopo.topo.loaned ? 'checked' : '' }/>
                                                est emprunt�
                                            </label>
                                        </div>

                                        <div class="form-group">
                                            <label for="borrowing_date_update">Date d'emprunt :</label>
                                            <input type="date" class="form-control" name="borrowing_date" id="borrowing_date_update"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="return_date_update">Date de retour :</label>
                                            <input type="date" class="form-control" name="return_date" id="return_date_update"/>
                                        </div>
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-danger" onclick="$('.publication-delete').submit();">Delete</button>
                                    <button type="button" class="btn btn-primary" onclick="$('.publication-update').submit();">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:if>-->

    <%@include file="comments.jsp"%>
</div>

<%@ include file="include/footer.jsp" %>
</body>
</html>