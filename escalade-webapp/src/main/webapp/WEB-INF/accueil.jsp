<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name}</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
<br><br>
<div class="jumbotron text-center">
  <h1>Nos meilleur sites</h1>
  <p>Petit tour d'horizon des meilleurs sites d'escalade: une selection pour les aventuriers !</p>
</div>
 <div class="card-columns">
           <c:forEach var="spot" items="${spots}" >   
           <div class="card">        
                <div class="card-body" >
                    <img src="${ spot.image }" width="80%" id="img"  />
                     <a href="${pageContext.request.contextPath}/spot/${ spot.id }"><h1><c:out value="${ spot.name }" /></h1></a>
                         <p>${ spot.description }</p>
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