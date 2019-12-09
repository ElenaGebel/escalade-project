<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}-topos</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
<br><br>

<h1>Nos topos</h1>
 
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
                <label class="control-label col-sm-2" for="topo_image">Photo du topo :</label>
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
                    <img src="${ topo.image }" width="80%" id="img"  />
                    <h1>${ topo.name }</h1>
                         <p>${ topo.description }</p>
                         <a class="btn btn-primary btn-lg" href="#" role="button">En savoir plus</a>
                </div>
             </div> 
             <br>
            </c:forEach>

  </div>
  
 </div>

<%@ include file="include/footer.jsp" %>
</body>
</html>