<!DOCTYPE html>
<html>
<head>
    <%@ include file="include/html_head_libs.jsp" %>
       <title>${application.name} - Mes topos</title>
</head>
<body>

<%@ include file="include/menu.jsp" %>
<div class="container">
    <h1>Mes topos</h1>

<c:if test="${ !empty sessionScope.user }">
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Topo</th>
            <th>Status de reservation</th>
            <th>Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="topo" items="${ topoList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/topo/${topo.id}"><c:out value="${ topo.name }" /></a></td>
                <td><c:out value="${ topo.statusReservationMessage(false)}" /></td>
                <td class="text-center">
		            <c:if test="${ topo.statusReservation == 2 or topo.statusReservation == 1}">		                
	                    <form:form method="post" action="reservation/cancel" class="reservation-cancel${ topo.id }" modelAttribute="topo">
	                        <input hidden name="userReservedId" title="userReservedId" value="${ topo.userReservedId }" />
                            <input hidden name="topoId" title="topoId" value="${ topo.id }" />
                        </form:form>  
	                    <c:if test="${ topo.statusReservation == 2 }">
	                         <form:form method="post" action="reservation/accepte" class="reservation-accepte${ topo.id }" modelAttribute="topo">
		                          <input hidden name="userReservedId" title="userReservedId" value="${ topo.userReservedId }" />
	                              <input hidden name="topoId" title="topoId" value="${ topo.id }" />
                              </form:form>   	                                     
	                         <div class="btn-group btn-group-xs">
	                            <a class="option-cursor" href="#" onclick="$('.reservation-accepte${ topo.id }').submit();" data-backdrop="static" data-keyboard="false">Accepter</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                            <a class="option-cursor" href="#" onclick="$('.reservation-cancel${ topo.id }').submit();" data-backdrop="static" data-keyboard="false">Refuser</a>           
	                        </div>
	                    </c:if>
	                    <c:if test="${ topo.statusReservation == 1 }">               
	                         <div class="btn-group btn-group-xs">
	                            <a class="option-cursor" href="#" onclick="$('.reservation-cancel${ topo.id }').submit();" data-backdrop="static" data-keyboard="false">Annuler</a>
	                         </div>
	                    </c:if>
		            </c:if>  
	            </td>            
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<div class="container">
    <h1>Mes reservations</h1>

<c:if test="${ !empty sessionScope.user }">
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Topo</th>
            <th>Status de reservation</th>
            <th>Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="reservation" items="${ reservationsList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/topo/${reservation.id}"><c:out value="${ reservation.name }" /></a></td>
                <td><c:out value="${ reservation.statusReservationMessage(true)}" /></td>      
                 <td class="text-center">          
	                <c:if test="${ reservation.statusReservation == 2 or reservation.statusReservation == 1}">	               
	                    <form:form method="post" action="reservation/cancel" class="reservation-cancel${ reservation.id }" modelAttribute="topo"> 
	                        <input hidden name="userReservedId" title="userReservedId" value="0" />
                            <input hidden name="topoId" title="topoId" value="${ reservation.id }" />
                        </form:form>                 
                         <div class="btn-group btn-group-xs">
                            <a class="option-cursor" href="#" onclick="$('.reservation-cancel${ reservation.id }').submit();" data-backdrop="static" data-keyboard="false">Annuler</a>
                         </div>		                   		  
	                </c:if>
                </td>
	        </tr>             
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>