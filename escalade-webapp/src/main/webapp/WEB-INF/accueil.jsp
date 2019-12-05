<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}</title>
</head>
<body>
<%@ include file="include/menu.jsp" %>
<div class="col-md-10 col-md-offset-1">

      <div  class="card-group">
           <c:forEach var="spot" items="${spots}" >
               <div class="card text-white "  id="bloc">   
               <div class="card-header "></p></div>            
                <div class="card-body" style="display: flex;">
                    <div id="divImage" class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                        <img src="${ spot.image }" width="100%" height="100%" id="img"  />
                    </div>
                    <div id="divInfos" class="col-lg-8 col-md-8 col-sm-8">
                         <h1>${ spot.name }</h1>
                         <p>${ spot.description }</p>
                         <a class="btn btn-primary btn-lg" href="#" role="button">En savoir plus</a>
                    </div>
                   
                </div>
            </div>

            </c:forEach>

  </div>  
      
      </div>

<%@ include file="include/footer.jsp" %>
</body>
</html>