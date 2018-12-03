window.onload = function() {
// TODO load account data and balance
let operator = {
	username : 'TestOperator'
};
SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);

Handlebars.registerHelper('incremented', function (index) {
    index++;
    return index;
});

Handlebars.registerHelper('equals', function(arg1, arg2, options) {
    return (arg1 == arg2) ? options.fn(this) : options.inverse(this);
});

const destructById = function(array) {
	let json = {};
	$.each(array, function(index, elem) {
		json[elem.id] = elem;
	});
	return json;
};

let competitorsByEventId;
let eventsById;
let eventTypeId;
let resultTypesByEventId;
let periodTypesByEventId;

const urlParam = new URLSearchParams(window.location.search);
let id = urlParam.get("eventid");

$.when(SB.Utils.getAjax('/sportsbook/api/competitors/eventid/' + id, SB.Token.OPERATOR),
		SB.Utils.getAjax('/sportsbook/api/events/' + id, SB.Token.OPERATOR),
		SB.Utils.getAjax('/sportsbook/api/results/eventid/' + id, SB.Token.OPERATOR),
		SB.Utils.getAjax('/sportsbook/api/resulttypes/findByEventId/' + id, SB.Token.OPERATOR),
		SB.Utils.getAjax('/sportsbook/api/periodtypes/findByEventId/' + id, SB.Token.OPERATOR)).done(
		function(competitors, events, results, resulttypes, periodtypes) {
			
			eventsById = destructById(events[0]);
			competitorsByEventId = destructById(competitors[0]);
			resultsByEventId = destructById(results[0]);
			resultTypesByEventId = resulttypes[0]; 
			periodTypesByEventId = periodtypes[0]; 

			console.log(resultsByEventId);
			
			let competitorsList = [];
			let resultsList = [];
			let resultTypeList = [];
			let periodTypeList = [];
					
			$.each(competitorsByEventId, function(id, competitor) {
				competitorsList.push({
					'id' : competitor.id + '',
					'description' : competitor.description
				});
			});
			
			$.each(resultsByEventId, function(id, result) {
				resultsList.push({
					'id' : result.id,
					'periodTypeId' : result.periodTypeId,
					'resultTypeId' : result.resultTypeId,
					'result' : result.result,
					'competitorId' : result.comeptitorId
				});
			});
						
			$.each(resultTypesByEventId, function(id, resulttypes) {
				resultTypeList.push({
					'id' : resulttypes.id + '',
					'description' : resulttypes.description
				});
			});
			
			$.each(periodTypesByEventId, function(id, periodtypes) {
				periodTypeList.push({
					'id' : periodtypes.id + '',
					'description' : periodtypes.description
				});
			});
			
			eventTypeId = events[0].eventTypeId;
			
			if(eventTypeId === 1){
				$('#results-tabele table').html(Handlebars.compile($('#football-results-template').html())({					
					competitors : competitorsList,
					results : resultsList,
					resulttypes : resultTypeList,
					periodtypes : periodTypeList
				}));
				
			} else {
				$('#results-tabele table').html(Handlebars.compile($('#horserace-results-template').html())({
					competitors : competitorsList,
				}));
			}

			bindListeners(eventTypeId, resultsList);
			
		});
}

function bindListeners(eventTypeId, resultsList) {
$("#saveResult").click(function(e) {
	ResultSave(eventTypeId, resultsList);
});
}

function ResultSave(eventTypeId, resultsList) {
	
	//const data = SB.Utils.readFormData($('#saveResult'));
	const urlParam = new URLSearchParams(window.location.search);
	
	let eventId = parseInt(urlParam.get("eventid"));
	let competitorId;
	let resultId;
	let periodTypeId;
	let result;
	let resultTypeId;
	
	console.log("This is the EventId: " + eventId + " " + typeof(eventId));
	
	if(eventTypeId === 1){
		$(".resultInput").each(function(){
			competitorId = parseInt($(this).attr("data-competitorId"));
			resultId = parseInt($(this).attr("data-resultId"));
			periodTypeId = parseInt($(this).attr("data-periodId"));
			result = parseInt($(this).val());
			resultTypeId = parseInt($(this).attr("data-resultTypeId"));
			
			console.log("CompetitorID: " + competitorId);
			console.log("ResultID: " + resultId);
			console.log("PeriodTypeID: " + periodTypeId);
			console.log("Result: " + result);
			console.log("ResultTypeId: " + resultTypeId);
			
			if(resultsList.length > 0){
				console.log("!!!!!Módosítás!!!!!")
				SB.Utils.putAjax("/sportsbook/api/results/" + resultId,
					{
					  "eventId": eventId,
					  "resultToSave": {
						"competitorId": competitorId,
						"periodTypeId": periodTypeId,
						"result": result,
						"resultTypeId": resultTypeId
					  }
					},
					SB.Token.OPERATOR, function(data, status, xhr) {
						alert("success");
					});
			} else {
				SB.Utils.postAjax("/sportsbook/api/results",
					{
				  "eventId": eventId,
				  "resultToSave": {
					"competitorId": competitorId,
					"periodTypeId": periodTypeId,
					"result": result,
					"resultTypeId": resultTypeId
				  }
				},
				SB.Token.OPERATOR, function(data, status, xhr) {
					alert("success");
				});
			}
		});
		
	} else {
		$(".resultInput").each(function(){
			competitorId = parseInt($(this).find(":selected").attr("id"));
			periodTypeId = parseInt($(this).find(":selected").attr("data-periodTypeId"));
			result = parseInt($(this).attr("data-index"));
			resultTypeId = parseInt($(this).find(":selected").attr("data-resultTypeId"));

			SB.Utils.postAjax("/sportsbook/api/results",
					{
		    	  "eventId": eventId,
		    	  "resultToSave": {
		    	    "competitorId": competitorId,
		    	    "periodTypeId": periodTypeId,
		    	    "result": result,
		    	    "resultTypeId": resultTypeId
		    	  }
		    	},
				SB.Token.OPERATOR, function(data, status, xhr) {
					alert("success");
				});
		});
	}

}
	
