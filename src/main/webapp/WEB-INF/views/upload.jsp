<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>Set filters</title>
</head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
<body>

	<sptg:header phase="2" />
	
	

	<div class="container">
		<h1>Here you can specify some factor to use as a filter: </h1>
		<div class="row">
			
			<div class="col-md-4">
				<div class="container">
					<form class="navbar-form navbar-left" role="form" action="/main/upload/cancel" method="post">
					<button type="submit" class="btn btn-warn">Cancel</button>
					</form>
					<form:form class="navbar-form navbar-left" role="form" action="/main/upload/filter" method="post" modelAttribute="wrapper" >
					<button type="submit" class="btn btn-warn">Set filter</button>
					<table class="table table-bordered" >
						<thead>
							<c:forEach items="${sessionScope.header}" var="head" varStatus="q">
								<tr>
										<td>
										 <label><c:out value="${head}"></c:out></label>
										</td>
										<td>
										 <form:input type="text" path="filters[${q.index }].filterValue"  />
										 <form:hidden path="filters[${q.index }].headerValue" value="${head}" />
										</td>
										
										
								</tr>
							</c:forEach>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				
					</form:form>
			</div>
		</div>
		</div>
	</div>


</body>
</html>