<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-voies</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Liste des voies</h1>

    <!-- Route CREATE -->
    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter une voie
            </button>
        </p>
        <form:form method="post" action="route" class="form-horizontal collapse collapse-menu" modelAttribute="route">
            <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a route name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="height">Hauteur :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="height" placeholder="Enter a route height" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="quotation">Quotation :</form:label>
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
                    <form:input type="text" class="form-control" path="laititude" placeholder="Enter a route laititude" value="44.500052" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="longitude">Longitude :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="longitude" placeholder="Enter a route longitude" value="5.933146" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="pointsNum">Nombre de points :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="pointsNum" placeholder="Enter a route points number" min="0" max="60"/>
                </div>
            </div>
           <c:if test="${ sectors.size() > 0 }">
                <div class="form-group">
                    <label for="sectors">Lier un secteur </label>
                    <select name="sectorId" id="sectors">
                        <c:forEach var="sector" items="${ sectors }">
                            <option value="${ sector.id }">${ sector.name }</option>
                        </c:forEach>
                    </select>
                </div>
          </c:if>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form:form>
    </c:if>

    <!-- Route READ -->
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Voie</th>
            <th>Hauteur</th>
            <th>Points</th>
            <th>Cotation</th>
            <c:if test="${ !empty sessionScope.user }">
                <th>Options</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="route" items="${ routeList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/route/${ route.id }"><c:out value="${ route.name }" /></a></td>
                <td><c:out value="${ route.height }" /></td>
                <td><c:out value="${ route.pointsNum }" /></td>
                <td><c:out value="${ route.quotation }" /></td>

                <c:if test="${ !empty sessionScope.user }">
                    <td class="text-center">
                            <c:if test="${ sessionScope.user.role == 'admin' or sessionScope.user.id == route.userId}">
                            <form:form method="post" action="route/${ route.id }/delete" class="publication-delete${ route.id }" modelAttribute="route"/>
		                         <div class="btn-group btn-group-xs">
		                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ route.id }" data-backdrop="static" data-keyboard="false">
		                                <span class="glyphicon glyphicon-pencil"></span>
		                            </a>
		                             <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ route.id }').submit();">
		                                    <span class="glyphicon glyphicon-remove"></span>
		                             </a>
		                        </div>
                        </c:if>

                       
                    </td>
                </c:if>
            </tr>

            <!-- Route UPDATE -->
            <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ route.id }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">                            
                                <h4 class="modal-title">Mettre a jour</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <div class="modal-body">
                                <form:form method="post" action="route/${route.id}/update" class="form-horizontal publication-update" modelAttribute="route">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a route name" value="${ route.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="height_update">Hauteur :</label>
                                        <form:input type="number" class="form-control" path="height" id="height_update" placeholder="Enter a route height" min="0" max="60" value="${ route.height }"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="quotation_update">Cotation :</label>
                                        <form:select path="quotation" id="quotation_update">
                                            <form:option value="${ route.quotation }">--</form:option>
						                        <c:forEach var="quotation_range" items="${ quotations }">
						                            <form:option value="${ quotation_range.id }">${ quotation_range.name }</form:option>
						                        </c:forEach>
                                        </form:select>
                                    </div>

                                    <div class="form-group">
                                        <label for="laititude_update">laititude :</label>
                                        <form:input type="text" class="form-control" path="laititude" id="laititude_update" placeholder="Laititude" value="${ route.laititude }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="longitude_update">Longitude :</label>
                                        <form:input type="text" class="form-control" path="longitude" id="longitude_update" placeholder="Longitude" value="${ route.longitude }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="points_number_update">Nombre de points :</label>
                                        <form:input type="number" class="form-control" path="pointsNum" id="points_number_update" placeholder="Enter a route points number" min="0" max="60" value="${ route.pointsNum }"/>
                                    </div>
                                    <c:if test="${ sectors.size() > 0 }">
						                <div class="form-group">
						                    <label for="sectors">Lier un secteur </label>
						                    <select name="sectorId" id="sectors">
						                        <c:forEach var="sector" items="${ sectors }">
						                            <option value="${ sector.id }">${ sector.name }</option>
						                        </c:forEach>
						                    </select>
						                </div>
						          </c:if>
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

</div>

<%@ include file="include/footer.jsp" %>
</body>
</html>