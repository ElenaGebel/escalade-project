<!DOCTYPE html>
<head>
     <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name} - Inscription</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Inscription</h1>
    <c:if test="${ !empty sessionScope.reponse }">
    <div class="alert alert-warning alert-dismissable col-lg-4">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Attention!</strong>${ sessionScope.reponse.message }
    </div>
    </c:if>
    <form method="post" action="inscription" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="pseudo">Pseudo :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Veuillez saisir un pseudo"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email :</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" name="email" id="email" placeholder="Veuillez saisir un email"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Mot de passe :</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="Veuillez saisir un password"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Inscription</button>
            </div>
        </div>
    </form>
</div>


<%@ include file="include/footer.jsp" %>

</body>
</html>
