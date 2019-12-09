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

 <div class="card-columns">
           <c:forEach var="spot" items="${spots}" >   
           <div class="card">        
                <div class="card-body" >
                    <img src="${ spot.image }" width="80%" id="img"  />
                    <h1>${ spot.name }</h1>
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