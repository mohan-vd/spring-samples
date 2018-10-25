<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<body>
<h4>Links for this controller</h4>
<c:forEach var="link" items="${links}">
    <a href="${link.value}">${link.key}</a> <br>
</c:forEach>
</body>
</html>
