<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>Preview</title>
<meta charset="utf-8">

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
</head>
<body>

<sptg:header phase="0" />

<div class="container">	
	<div class="row">
		<div class="col-md-12">
			<h1>The preview of the fields: </h1>				
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">		
			<table class="table table-bordered" >
				<thead>
					<tr>
					<c:forEach items="${preview[0].questionMap}" var="question" >
						<td><c:out value="${question.value}"></c:out></td>
					</c:forEach>				
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${preview }" var="preview">
					
						<tr>
						<c:forEach items="${preview.answerMap }" var="answer" >
							<td><c:out value="${answer.value}"></c:out></td>
						</c:forEach>				
						</tr>
					
					</c:forEach>						
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
