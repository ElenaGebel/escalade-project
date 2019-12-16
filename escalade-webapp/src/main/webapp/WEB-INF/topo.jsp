<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-topos</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Nos topos</h1>
 
    <c:if test="${ !empty sessionScope.user }">
    
        <p>
           <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
              <span class="glyphicon glyphicon-plus"></span> Ajouter un topo
          </button>
        </p>
        <div class="collapse" id="collapseExample">
        <form:form method="post" action="topo" enctype="multipart/form-data" modelAttribute="topo" class="form-horizontal" >
            <div class="form-group">
                <form:label class="control-label col-sm-2"  path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Topo name"/>
                </div>
            </div>
            <div class="form-group">
                <form:label class="control-label col-sm-2" path="description">Description :</form:label>
                <div class="col-sm-10">
                    <form:textarea class="form-control" path="description" placeholder="Topo description"></form:textarea>
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
        </form:form>
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
                        <c:if test="${ sessionScope.user.role == 'admin' or sessionScope.user.id == topo.userId}">
                            <form:form method="post" action="topo/${topo.id}/delete" class="publication-delete${ topo.id }" modelAttribute="topo">
                                <input hidden name="picture" title="picture" value="${ topo.image }" />
                            </form:form>
                           <div class="btn-group btn-group-xs">
	                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ topo.id }" data-backdrop="static" data-keyboard="false">
	                                <span class="glyphicon glyphicon-pencil"></span>
	                            </a>
	                            <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ topo.id }').submit();">
	                                <span class="glyphicon glyphicon-remove"></span>
	                            </a>
                            </div>
                        </c:if>
                </c:if>

                </div>
             </div>
           <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ topo.id }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">                               
                                <h4 class="modal-title">Mettre a jour</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <div class="modal-body">
                                <form method="post" action="topo/${topo.id}/update" enctype="multipart/form-data" class="form-horizontal" >
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <input type="text" class="form-control" name="name" id="name_update" placeholder="Enter a topo name" value="${ topo.name }" />
                                    </div>
                                    <div class="form-group">
                                        <label for="description_update">Description :</label>
                                        <textarea class="form-control" name="description" id="description_update" placeholder="Enter a topo description">${ topo.description }</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture_update">Photo:</label>
                                        <input type="file" name="file" id="picture_update"/>
                                    </div>
                                    <input type="hidden"  name="currentPicture" title="picture" value="${ topo.image }" />
                                    
                                     <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Save</button>
                                    </div>
                                </form>
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