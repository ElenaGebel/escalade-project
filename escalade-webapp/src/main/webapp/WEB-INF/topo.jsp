<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-topos</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Liste des topo</h1>

 
    <c:if test="${ !empty sessionScope.user }">
    
        <p>
           <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
              <span class="glyphicon glyphicon-plus"></span> Ajouter un topo
          </button>
        </p>
        <div class="collapse" id="collapseExample">
        <form method="post" action="topo" enctype="multipart/form-data" class="form-horizontal" >
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nom :</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" placeholder="Topo name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="description">Description :</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="description" placeholder="Topo description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="topo_image">Photo :</label>
                <div class="col-sm-10">
                    <input type="file" name="file" id="topo_image"/>
                </div>
            </div>         
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form>
         </div>
    </c:if>


   <div class="card-columns">              
          <c:forEach var="topo" items="${topos}" >   
           <div class="card">        
                <div class="card-body" >
                    <img src="${pageContext.request.contextPath}/${ topo.image }" width="80%" id="img"  />
                     <a href="${pageContext.request.contextPath}/topo/${ topo.id }"><h1><c:out value="${ topo.name }" /></h1></a>
                         <p>${ topo.description }</p>
                         
                  <c:if test="${ !empty sessionScope.user }">
                        <c:if test="${ sessionScope.user.role == 'user' }">
                            <form:form method="post" action="topo/${topo.id}/delete" class="publication-delete${ topo.id }" modelAttribute="topo">
                                <input hidden name="picture" title="picture" value="${ topo.image }" />
                            </form:form>
                        </c:if>

                        <div class="btn-group btn-group-xs">
                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ topo.id }" data-backdrop="static" data-keyboard="false">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>

                            <!-- Topo DELETE -->
                            <c:if test="${ sessionScope.user.role == 'user' }">
                                <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ topo.id }').submit();">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </c:if>
                        </div>
                </c:if>

                </div>
             </div>
           <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ topo.id }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Mise a jour</h4>
                            </div>

                            <div class="modal-body">
                                <form:form method="post" action="topo/${topo.id}/update" enctype="multipart/form-data" class="form-horizontal publication-update" modelAttribute="topo">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a topo name" value="${ topo.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="description_update">Description :</label>
                                        <textarea class="form-control" name="description" id="description_update" placeholder="Enter a topo description">${ topo.description }</textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="picture_update">Photo:</label>
                                        <input type="file" name="file" id="picture_update"/>
                                    </div>
                                    <input hidden name="currentPicture" title="picture" value="${ topo.image }" />
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

             <br>
          </c:forEach>


 </div>
 </div>
<%@ include file="include/footer.jsp" %>
</body>
</html>