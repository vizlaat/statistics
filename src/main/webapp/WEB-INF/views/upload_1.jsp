<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>Set filter values</title>
</head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
<body>

	<sptg:header phase="3" />
	
	<div class="container">
		
		<div class="row">
			
			<div class="col-md-12">
				<div class="container">
					<form class="navbar-form navbar-left" role="form" action="/main/upload/cancel" method="post">
					<button type="submit" class="btn btn-warn">Cancel</button>
					</form>
					<form:form class="navbar-form navbar-left" role="form" action="/main/result" method="post" modelAttribute="field">
					<button type="submit" class="btn btn-warn">Choose fields to analyze!</button>
					
					<div class="container">
						<form:select path="field_one">
							<c:forEach items="${sessionScope.header}" var="head">							
								<form:option value="${head }"> <c:out value="${head }"></c:out> </form:option>	
							</c:forEach>						
						</form:select>
					</div>
					<div class="container">
						<form:select path="field_two">
							<c:forEach items="${sessionScope.header}" var="head">							
								<form:option value="${head }"> <c:out value="${head }"></c:out> </form:option>	
							</c:forEach>						
						</form:select>
					</div>
				
					</form:form>
			</div>
		</div>
		</div>
	</div>
	


</body>
</html>