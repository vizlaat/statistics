<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ attribute name="phase" required="true" rtexprvalue="false"%>



<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<ul class="nav navbar-nav">
				<li class="navbar-brand">Analyze!</li>	
				<li class="navbar-brand">Online tool to analyze your data.</li>
				<li class="navbar-brand"> <c:out value="${sessionScope.fileName }"></c:out> </li>
				
			</ul>	
				<form class="navbar-form navbar-left" role="form"
					action="<c:url value='/upload' />" method="post" enctype="multipart/form-data">
				
					<div class="form-group">
						<div class="col-sm-10">
							<input class="form-control" type="file" name="file" id="file" />
						</div>
					</div>
					 <button type="submit" class="btn btn-default" value="Submit" name="upload" id="upload">Submit</button>
				</form>
		</div>
		
		
		
		
		<div class="navbar-header navbar-left">
		<ul  class="nav nav-tabs">
			<li  role="presentation" class=<c:out value="${phase == 1 ? 'active' : 'non-active'}"/>> <a href='<c:url value="/autocorrect/settings" />'>Auto-correct</a> </li>
			<li  role="presentation" class=<c:out value="${phase == 2 ? 'active' : 'non-active'}"/>> <a href="<c:url value="/analyze/settings" />" >Analyze</a></li>
			<li  role="presentation" class=<c:out value="${phase == 3 ? 'active' : 'non-active'}"/>> <a href="<c:url value="/plot/settings" />"  >Plot </a></li>
		 </ul>
		</div>
	
	</div>
</nav>