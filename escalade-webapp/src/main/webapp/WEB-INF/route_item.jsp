<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-voie</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>
<div class="container">
    <h1>${ route.name }</h1>

    <!-- Length CREATE -->
 <!--    <c:if test="${ !empty sessionScope.user && route.type == 'route' }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter une longueur
            </button>
        </p>
        <form:form method="post" action="route/${route.id}/${route.sectorId}" class="form-horizontal collapse collapse-menu" modelAttribute="length">
            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a length name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="height">Hauteur :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="height" placeholder="Enter a length height" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="quotation">Cotation :</form:label>
                <div class="col-sm-10">
                    <form:select path="quotation">
                        <c:forEach var="quotation_range" items="${ quotations }">
                            <form:option value="${ quotation_range.id }">${ quotation_range.name }</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="laititude">laititude :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="laititude" placeholder="Enter a length laititude" value="44.500052" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="longitude">Longitude :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="longitude" placeholder="Enter a length longitude" value="5.933146" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="pointsNum">Nombre de points :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="pointsNum" placeholder="Enter a length points number" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form:form>
    </c:if>
--> 
    <h4>Description</h4>
    <p>${ route.name } -> ${ route.height } metres, ${ route.pointsNum } points</p>

 <!--    <c:if test="${ listLength.size() > 0 }">
        <h4>Liste des longueurs</h4>

      
        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Voie</th>
                <th>Hauteur</th>
                <th>Points</th>
                <th>Quotation</th>
                <c:if test="${ !empty sessionScope.user }">
                    <th>Options</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="length" items="${ listLength }">
                <tr>
                    <td><c:out value="${ length.name }" /></td>
                    <td><c:out value="${ length.height }" /></td>
                    <td><c:out value="${ length.pointsNum}" /></td>
                    <td><c:out value="${ length.quotation }" /></td>

                    <c:if test="${ !empty sessionScope.user }">
                        <td class="text-center">
                            <c:if test="${ sessionScope.user.role == 'admin' }">
                                <form:form method="post" action="route/${route.id}/${length.id}/delete" class="publication-delete${ length.id }" modelAttribute="length"/>
                            </c:if>

                            <div class="btn-group btn-group-xs">
                                <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ length.id }" data-backdrop="static" data-keyboard="false">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a>

                                
                                <c:if test="${ sessionScope.user.role == 'admin' }">
                                    <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ length.id }').submit();">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </a>
                                </c:if>
                            </div>
                        </td>
                    </c:if>
                </tr>

            
                <c:if test="${ !empty sessionScope.user }">
                    <div class="modal fade modal-menu${ length.id }">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Mise à jour</h4>
                                </div>

                                <div class="modal-body">
                                    <form:form method="post" action="route/${route.id}/${length.id}/update" class="form-horizontal publication-update" modelAttribute="length">
                                        <div class="form-group">
                                            <label for="name_update">Nom :</label>
                                            <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a route name" value="${ length.name }" />
                                        </div>

                                        <div class="form-group">
                                            <label for="height_update">Hauteur :</label>
                                            <form:input type="number" class="form-control" path="height" id="height_update" placeholder="Enter a route height" min="0" max="60" value="${ length.height }"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="quotation_update">Cotation :</label>
                                            <form:select path="quotation" id="quotation_update">
                                                <form:option value="${ length.quotation }">--</form:option>
							                        <c:forEach var="quotation_range" items="${ quotations }">
							                            <form:option value="${ quotation_range.id }">${ quotation_range.name }</form:option>
							                        </c:forEach>
                                            </form:select>
                                        </div>

                                        <div class="form-group">
                                            <label for="laititude_update">laititude :</label>
                                            <form:input type="text" class="form-control" path="laititude" id="laititude_update" placeholder="Enter a route laititude" value="${ length.laititude }" />
                                        </div>

                                        <div class="form-group">
                                            <label for="longitude_update">Longitude :</label>
                                            <form:input type="text" class="form-control" path="longitude" id="longitude_update" placeholder="Enter a route longitude" value="${ length.longitude }" />
                                        </div>

                                        <div class="form-group">
                                            <label for="points_number_update">Nombre de points :</label>
                                            <form:input type="number" class="form-control" path="pointsNum" id="points_number_update" placeholder="Enter a route points number" min="0" max="60" value="${ length.pointsNum }"/>
                                        </div>
                                    </form:form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" onclick="$('.publication-update').submit();">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
-->
</div>

</body>
</html>