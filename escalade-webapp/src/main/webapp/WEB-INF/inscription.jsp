<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>

    <form method="post" action="inscription" >
        <div >
            <label for="nickname">Nickname :</label>
            <div>
                <input type="text" name="nickname" id="nickname" placeholder="Enter nickname"/>
            </div>
        </div>

        <div >
            <label  for="email">Email :</label>
            <div>
                <input type="email" name="email" id="email" placeholder="Enter email"/>
            </div>
        </div>

        <div >
            <label for="password">Mot de passe :</label>
            <div >
                <input type="password"  name="password" id="password" placeholder="Enter password"/>
            </div>
        </div>

        <div >
            <div >
                <button type="submit" > Inscription</button>
            </div>
        </div>
    </form>
        <ul>
        <c:forEach var="user" items="${users}"> 
           <li><c:out value="${user.getNickname()} "></c:out><c:out value="${user.getEmail()} "></c:out><c:out value="${user.getRole()}"></c:out></li>
        </c:forEach>
    </ul>
    </body>
</html>
