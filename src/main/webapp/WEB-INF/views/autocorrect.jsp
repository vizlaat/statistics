<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>

<html>
<head>
	<title>Autocorrect settings</title>
	<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" >
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	
<script type="text/javascript">

$(document).ready(function(){
	
	$('#overview').hide();
	
	$(".addAnswer").click(function(){
		event.preventDefault();
		$(this).parent().parent().append('<div class="col-md-2"> <div class="row singleCorrect"> <div class="col-md-10"><input class="answer" type="text" placeHolder="answer"/></div> <div class="col-md-2"> <input class="point" type="text" placeHolder="point" size="2"/></div> </div> </div>');
	});
	
	
	$("#submit").click(function(event){
		event.preventDefault();
		var onlyNumbers = true;
		$(".point").each(function(){
			if(isNaN($(this).val())){
				$("#msg").html('Points should be only numbers!');
				onlyNumbers = false;
			}
		});
		if(onlyNumbers){
			var correctAnswerCollection = [];
			console.log("we can collect data");
			$(".singleLine").each(function(){
				if($(this).find(".answer").val().length != 0){
					var correctAnswerList = {};
					correctAnswerList.questionID = $(this).attr('id');
					correctAnswerList.question = $(this).find(".question").html();
					correctAnswerList.answers = [];
					$(this).find(".singleCorrect").each(function(){
						var newAnswer = $(this).find(".answer").val();
						var newPoint = $(this).find(".point").val();
						var correctAnswer = {
								answer: newAnswer,
								point: newPoint
						};
						correctAnswerList.answers.push(correctAnswer);
					});
					correctAnswerCollection.push(correctAnswerList);
				};
				
			});	
		};
		$.ajax({
			url:"<c:url value="evaluation" />",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type: "POST",
			cache: false,
			contentType: "application/json",
			data: JSON.stringify(correctAnswerCollection),
			dataType: "json"
		}).done(function(data){
			console.log(data);
			$("#correctPreview").html("");
			for(let index=0; index<data.length;index++ ){
				$('#correctPreview').append('<div class="row" id="preview'+ data[index].questionID  +'"> <div>');
				var previewRow = $('#preview' + data[index].questionID );
				previewRow.append('<div class="col-md-2">'+ data[index].question +'</div>');
				for(let answerIndex=0, maxAnswer =  data[index].answers.length; answerIndex < maxAnswer; answerIndex++){
					previewRow.append('<div class="col-md-2"> Answer: '+ data[index].answers[answerIndex].answer +' </div> <div class="col-md-2">Point: '+ data[index].answers[answerIndex].point +'  </div> ');
				}
			}
			console.log('ajax is done');
			$("#overview").show();
		});
		return false;
	});
});

</script>
</head>

<body>

<sptg:header phase="1"/>

<div class="container-fluid" id="overview">
	<div class="row">
		<div class="col-md-2"> The submitted answers (you still can modify them below): </div>
		<a href='<c:url value="/autocorrect/evaluation/finalize"/>' ><button class="col-md-2" id="start">Start autocorrecting</button></a>
		<a href='<c:url value="/autocorrect/settings" />' ><button class="col-md-2" id="cancel" value="Refresh">Clear all field</button></a>
	</div>
	<div class="row" id="correctPreview">
	
	</div>
</div>


<div class="container-fluid">
	<div class="col-md-12">	
	<h2 id="msg"></h2>
	<form class="navbar-form navbar-left" role="form" action="autocorrect/evaluation" method="post">		
		<button type="submit" class="btn btn-warn" id="submit">Submit</button>
			<h1>The not specified answers will marked with 0 points. </h1>
			<c:forEach items="${sessionScope.questions }" var="question">
				<div class="row col-md-12 singleLine" id="${question.key }">
					<div class="col-md-2 question"><c:out value="${question.value }"></c:out></div>
					<div class="col-md-1"><button class="addAnswer" > +Answer </button></div> 
					<div class="col-md-2"> 
						<div class="row singleCorrect">
							<div class="col-md-10"><input class="answer" type="text" placeHolder="answer"/> </div> 	
							<div class="col-md-2"><input class="point" type="text" placeHolder="point" size="2"/> </div>
						</div>
					</div>  
					
				</div>
			</c:forEach>
		
	</form>
	</div>

</div>

</body>
</html>
