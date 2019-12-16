<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name} - sites</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
<br><br>

<h1>Nos sites</h1>
 

    <c:if test="${ !empty sessionScope.user }">
    
        <p>
           <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
              <span class="glyphicon glyphicon-plus"></span> Ajouter un site
          </button>
        </p>
        <div class="collapse" id="collapseExample">
        <form method="post" action="spot" enctype="multipart/form-data" class="form-horizontal" >
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nom :</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" placeholder="Spot name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="description">Description :</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="description" placeholder="Spot description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="spot_image">Spot image</label>
                <div class="col-sm-10">
                    <input type="file" name="file" id="spot_image"/>
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
          <c:forEach var="spot" items="${spots}" >   
           <div class="card">        
                <div class="card-body" >
                    <img src="${pageContext.request.contextPath}/${ spot.image }" width="80%" id="img"  />
                     <a href="${pageContext.request.contextPath}/spot/${ spot.id }"><h1><c:out value="${ spot.name }" /></h1></a>
                         <p>${ spot.description }</p>
                         
                  <c:if test="${ !empty sessionScope.user }">
                        <c:if test="${ sessionScope.user.role == 'admin' or sessionScope.user.id == spot.userId}">
                            <form:form method="post" action="spot/${spot.id}/delete" class="publication-delete${ spot.id }" modelAttribute="spot">
                                <input hidden name="picture" title="picture" value="${ spot.image }" />
                            </form:form>
	                        <div class="btn-group btn-group-xs">
	                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ spot.id }" data-backdrop="static" data-keyboard="false">
	                                <span class="glyphicon glyphicon-pencil"></span>
	                            </a>
	                            <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ spot.id }').submit();">
	                                <span class="glyphicon glyphicon-remove"></span>
	                            </a>
	                        </div>
                        </c:if>
                 </c:if>

                </div>
             </div>
              <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ spot.id }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h4 class="modal-title">Mettre jour</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <div class="modal-body">
                                <form method="post" action="spot/${spot.id}/update" enctype="multipart/form-data" class="form-horizontal" >
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <input type="text" class="form-control" name="name" id="name_update" placeholder="Enter a spot name" value="${ spot.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="description_update">Description :</label>
                                        <textarea class="form-control" name="description" id="description_update" placeholder="Enter a spot description">${ spot.description }</textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="picture_update">Photo:</label>
                                        <input type="file" name="file" id="picture_update"/>
                                    </div>
                                    <input type="hidden" name="currentPicture" title="picture" value="${ spot.image }" />
                                    <input  type="hidden" class="form-control" name="topoId" id="topoId" value="${ spot.topoId }" />
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