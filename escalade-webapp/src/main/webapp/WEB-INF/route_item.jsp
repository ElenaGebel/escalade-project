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


    <h4>Description</h4>
    <p>${ route.name } -> ${ route.height } metres, ${ route.pointsNum } points</p>


</div>

</body>
</html>