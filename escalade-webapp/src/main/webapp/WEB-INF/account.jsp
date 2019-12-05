<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="include/html_head_libs.jsp" %>
    <title>${application.name} - Mon compte</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>

<div class="container">
    <h1>Mon compte</h1>

    <c:if test="${ !empty sessionScope.user }">
        <form method="post" action="/account/${ sessionScope.user.id }" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" for="pseudo">Pseudo :</label>
                <div class="col-sm-10">
                    <input required type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Veuillez saisir votre pseudo" value="${ sessionScope.user.pseudo }"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email :</label>
                <div class="col-sm-10">
                    <input required type="email" class="form-control" name="email" id="email" placeholder="Veuillez saisir votre email" value="${ sessionScope.user.email }"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="last_password">Ancien mot de passe :</label>
                <div class="col-sm-10">
                    <input required type="password" class="form-control" name="last_password" id="last_password" placeholder="Veuillez saisir votre mot de passe"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="new_password">Nouveau mot de passe :</label>
                <div class="col-sm-10">
                    <input required type="password" class="form-control" name="new_password" id="new_password" placeholder="veuillez saisir un nouveau mot de passe"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Modifier</button>
                </div>
            </div>
        </form>

        <form method="post" action="/account/delete">
            <button type="submit" class="btn btn-danger btn-xs">
                <span class="glyphicon glyphicon-remove"></span> Supprimer le compte
            </button>
        </form>
    </c:if>
</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>
