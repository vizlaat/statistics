<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>Autocorrect settings</title>
	<meta charset="utf-8">
	<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>

<body>

<sptg:header phase="1"/>

<div class="container-fluid">
<button>Save to file</button>
<table class="table table-bordered">
	<thead>
		<tr>
			<c:forEach items="${sessionScope.corrected[0].questionMap }" var="question" >
				<td><c:out value="${question.value}"></c:out></td>
			</c:forEach>				
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${sessionScope.corrected }" var="surveyResult">
		<tr>
			<c:forEach items="${surveyResult.answerMap }" var="answer" >
				<td> <c:out value="${answer.value }"></c:out>  </td>
			</c:forEach>
		</tr>
	</c:forEach>
	</tbody>
</table>

</div>

</body>
</html>