<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户</title>
</head>
<body>
<c:forEach items="${sessionScope.user}" var="u">
    ${u.name}===${u.password}
    <fmt:formatDate value="${u.create_date}" pattern="yyyy-MMM-dd HH:mm:ss"/><br/>
</c:forEach>



</body>


</html>