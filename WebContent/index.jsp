<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome on Facebook, !</h1>
<p>This spot is reserved for Facebook timeline, once it's fixed.</p>

<h1>Welcome on Twitter, ${user}!</h1>
	<c:forEach var="post" items="${timeline}"> 
    	${post}<br /> 
    </c:forEach>
</body>
</html>