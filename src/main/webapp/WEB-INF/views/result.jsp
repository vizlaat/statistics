<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>
<html>
<head>
	<title>Result</title>
</head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
<body>

<sptg:header phase="4"/>


<div class="container-fluid">
	<div><h1>The result is:</h1>
	<h2>The correlation between <c:out value="${ field.field_one }"></c:out> and <c:out value="${ field.field_two }"></c:out> : </h2>
	<h2 id="result"> <c:out value="${correlation }"></c:out>	</h2>
	</div>
	<div>
<%-- 	<c:if test="sessionScope.filters != null"> --%>
	<h2>The answers are filtered for:</h2>
		<c:forEach items="${sessionScope.filters }" var="filter">
			<h2><c:out value="${filter.headerValue }"></c:out></h2>
			<h2><c:out value="${filter.filterValue }"></c:out></h2>
			<br>
		</c:forEach>  
<%-- 	</c:if> --%>
	</div>
	
	


</div>



</body>
</html>