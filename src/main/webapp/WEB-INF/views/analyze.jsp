<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>Analyze</title>
<meta charset="utf-8">

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	var questions = {
			<c:forEach items="${sessionScope.questions }" var="question">
				<c:out value="${question.key}" /> : "<c:out value="${question.value}" />", 
			</c:forEach>
	};
	
	$('#addFilter').click(function(){
		$('.filterRow').first().clone().appendTo('#filterBody');
	});
	
	$('#removeFilters').click(function(){
		$.ajax({
			url:"<c:url value='/analyze/filter/remove' />",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    type: "POST",
			cache: false,
			contentType: "application/json",
			dataType: "json"
		}).done(function(data){
			console.log(data);
			var newText = createRemoveFilterText(data);
			$('#resultDiv').prepend(newText);
		});
		
	});
	
	function createRemoveFilterText(data){
		var text = '<div class="row"> <div class="col-md-12"><h1>';
		text += data;
		text += '</h1></div></div>';
		return text;
	};
	
	$('#submitFilters').click(function(){
		var filters = [];
		$('.filterRow').each(function(){
			var newQuestionID = $(this).find('.filterSelector').val();
			var newFilterValue = $(this).find('.filterValue').val();
			var filter = {
					questionID : newQuestionID,
					filterValue: newFilterValue
			};
			filters.push(filter);
		});
		$.ajax({
			url:"<c:url value='/analyze/filter/submit' />",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    type: "POST",
			cache: false,
			contentType: "application/json",
			data: JSON.stringify(filters),
			dataType: "json"
		}).done(function(data){
			console.log(data);
			var newText = createFilterText(data);
			$('#resultDiv').prepend(newText);
		});
		
	});
	
	function createFilterText(data){
		var text = "";
		text += '<div class="row"> <div class="col-md-12">';
		text += '<h1> The filters are set to: </h1>';
		text += '<table><thead><tr><td> Question</td> <td> Filter value </td></tr></thead><tbody> '
		for(var index = 0; index<data.length; index++){
			var questionID = data[index].questionID;
			var question = questions[questionID];
			text += '<tr> <td>' + question + '</td><td>' + data[index].filterValue + '</td></tr>';
		}
		
		text += '</tbody></table></div> </div>';
		return text;
	};
	
	$('#calculateAverage').click(function(){
		$.ajax({
			url:"<c:url value='/analyze/average'/>",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    type: "POST",
			cache: false,
			contentType: "application/json",
			dataType: "json"
		}).done(function(data){
			console.log(data);
			var newText = createAverageText(data);
			$('#resultDiv').prepend(newText);
		});
	})
	
	function createAverageText(data){
		var text = '<div class="row"> <div class="col-md-12">';
		text += '<h1> The averages for the number fields: </h1>';
		text += '<table><thead><tr><td> Question</td> <td> Average </td></tr></thead><tbody class="averages"> ';
		for(var index=0; index<data.length; index++){
			var questionID = data[index].questionID;
			var question = questions[questionID];
			text += '<tr> <td>' + question + '</td><td class="average-'+questionID+'">' + data[index].average + '</td></tr>';
		};
		text += '</tbody></table></div> </div>';
		return text;
	};
	
	$('#correlation').click(function(){
		var correlationSettings = {
				fieldOne : $('#correlationFieldOne').val(),
				fieldTwo : $('#correlationFieldTwo').val(),
		};
		
		$.ajax({
			url:"<c:url value='/analyze/correlation'/>",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    type: "POST",
			cache: false,
			contentType: "application/json",
			data: JSON.stringify(correlationSettings),
			dataType: "json"
		}).done(function(data){
			console.log(data);
			var newText = createCorrelationText(data);
			$('#resultDiv').prepend(newText);
		});
	});
	
	function createCorrelationText(data){
		var text = '<div class="row"> <div class="col-md-12">';
		text += '<h1> The correlation between '+ questions[$('#correlationFieldOne').val()] +' and '+ questions[$('#correlationFieldTwo').val()]+':</h1>';
		text += '<h2 class="correlation"> '+ data +' </h2>'	
		text += '</div> </div>';
		return text;
	};
	
	
});

function deleteFilter(delBTN){
	delBTN.parent().parent().remove();
}

</script>

</head>
<body>

<sptg:header phase="2" />

<div class="row">
	<div class="col-md-6">
		<div class="row">
			<div class="col-md-12"><h1>You can specify filters (if you wish): </h1></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-bordered" >
					<thead>
						<tr><td class="col-md-6">Field</td><td class="col-md-6">Filter value</td></tr>
					</thead>
					<tbody id="filterBody">
						<tr class="filterRow">
							<td class="col-md-6">
								<select name="filter1" class="filterSelector "style="width: 80%">
								<c:forEach items="${sessionScope.questions }" var="question">
									<option value="${question.key }"> <c:out value="${question.value }"/></option>
								</c:forEach>
								</select>
							</td>
							<td class="col-md-6"> 
								<textarea class="filterValue" rows="1"></textarea>
								<button class="pull-right deleteFilter" onclick="deleteFilter($(this));">Delete row</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-12">
				<button class="pull-right" id="addFilter">Add extra filter</button>
				<button class="pull-right" id="removeFilters">Remove all filter</button>
				<button class="pull-right" id="submitFilters">Submit filter(s)</button>
			</div>		
		</div>
		<div class="row">
			<div class="col-md-12"><h1>You can specify fields for correlation analysis: </h1></div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<table>
				<thead><tr><td>Field One</td><td>Field Two</td></tr></thead>
				<tbody>
					<tr>
						<td>
							<select name="fieldOne" id="correlationFieldOne" style="width: 80%">
								<c:forEach items="${sessionScope.questions }" var="question">
									<option value="${question.key }"> <c:out value="${question.value }"/></option>
								</c:forEach>
							</select>	
						</td>
						<td>
							<select name="fieldTwo" id="correlationFieldTwo" style="width: 80%">
								<c:forEach items="${sessionScope.questions }" var="question">
									<option value="${question.key }"> <c:out value="${question.value }"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div class="col-md-12">
				<button class="pull-right" id="correlation">Calculate</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h1>You can calculate the averages of the answers:</h1>
				<button class="pull-right" id="calculateAverage">Average</button>
			</div>
		</div>
	
	</div>
	<div class="col-md-6" id="resultDiv">
		<div class="row resultRow" >
			<div class="col-md-12">
			
			</div>
		</div>
	</div>
</div>

</body>
</html>
