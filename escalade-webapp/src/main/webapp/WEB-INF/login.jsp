<!DOCTYPE html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name} - Connexion</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Connexion</h1>
    <c:if test="${ !empty sessionScope.reponse }">
        <div class="alert alert-warning alert-dismissable">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Attention! </strong>${ sessionScope.reponse.message }
        </div>
    </c:if>
    <form method="post" action="login" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="email" id="email" placeholder="Veuillez saisir votre email" value="" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password :</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="Veuillez saisir votre password" value="" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label><input type="checkbox" name="remember" checked="checked"/> Remember me</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span> Connexion</button>
            </div>
        </div>
    </form>

</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>
