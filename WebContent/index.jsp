<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${email != null}">
       	<%@ include file="dashboard.jsp" %>
    </c:when>    
    <c:otherwise>
        <%@ include file="login.jsp" %>
    </c:otherwise>
</c:choose>