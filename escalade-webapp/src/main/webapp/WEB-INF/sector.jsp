<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
       <title>${application.name} - Secteur</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>
<div class="container">
    <h1>Liste des secteurs</h1>

    <!-- Sector CREATE -->
    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter un secteur
            </button>
        </p>
        <form:form method="post" action="sector" class="form-horizontal collapse collapse-menu" modelAttribute="sector">
            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a sector name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="description">Description :</form:label>
                <div class="col-sm-10">
                    <form:textarea class="form-control" path="description" placeholder="description" />     
                </div>
            </div>
            
           <c:if test="${ spots.size() > 0 }">
                <div class="form-group">
                    <label for="spots">Lier un site </label>
                    <select name="spotId" id="spots">
                        <c:forEach var="spot" items="${ spots }">
                            <option value="${ spot.id }">${ spot.name }</option>
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

    <!-- Sector READ -->
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Secteur</th>
            <th>Description</th>
            <c:if test="${ !empty sessionScope.user }">
                <th>Options</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sector" items="${ sectorList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/spot/${sector.spotId}"><c:out value="${ sector.name }" /></a></td>
                <td><c:out value="${ sector.description}" /></td>

                <c:if test="${ !empty sessionScope.user }">
                    <td class="text-center">
                        <c:if test="${ sessionScope.user.role == 'admin' or sessionScope.user.id == sector.userId}">
                            <form:form method="post" action="sector/${sector.id}/delete" class="publication-delete${ sector.id }" modelAttribute="sector"/>
                            <div class="btn-group btn-group-xs">
                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ sector.id }" data-backdrop="static" data-keyboard="false">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>
                            <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ sector.id }').submit();">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a>
                        </div>
                        </c:if>
                    </td>
                </c:if>
            </tr>

            <!-- Sector UPDATE -->
            <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ sector.id }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">            
                                <h4 class="modal-title">Mettre a jour</h4>
                                 <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <div class="modal-body">
                                <form:form method="post" action="sector/${sector.id}/update" class="form-horizontal publication-update" modelAttribute="sector">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a topo name" value="${ sector.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="description_update">Description :</label>
                                       <textarea class="form-control" name="description" id="description_update" placeholder="Enter a spot description">${ spot.description }</textarea>   
                                    </div>
                                   <c:if test="${ spots.size() > 0 }">
						                <div class="form-group">
						                    <label for="spots">Lier un site </label>
						                    <select name="spotId" id="spots">
						                        <c:forEach var="spot" items="${ spots }">
						                            <option value="${ spot.id }">${ spot.name }</option>
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