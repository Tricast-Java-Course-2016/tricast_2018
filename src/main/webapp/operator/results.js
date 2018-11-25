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
		SB.Utils.getAjax('/sportsbook/api/resulttypes/findByEventId/' + id, SB.Token.OPERATOR),
		SB.Utils.getAjax('/sportsbook/api/periodtypes/findByEventId/' + id, SB.Token.OPERATOR)).done(
		function(competitors, events, resulttypes, periodtypes) {
			
			eventsById = destructById(events[0]);
			competitorsByEventId = destructById(competitors[0]);
			resultTypesByEventId = resulttypes[0];
			periodTypesByEventId = periodtypes[0];
			
			let competitorsList = [];
			let resultTypeList = [];
			let periodTypeList = [];
					
			$.each(competitorsByEventId, function(id, competitor) {
				competitorsList.push({
					'id' : competitor.id + '',
					'description' : competitor.description
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
					resulttypes : resultTypeList,
					periodtypes : periodTypeList
				}));
				
			} else {
				$('#results-tabele table').html(Handlebars.compile($('#horserace-results-template').html())({
					competitors : competitorsList,
				}));
			}

			bindListeners(eventTypeId);
			
		});
}

function bindListeners(eventTypeId) {
$("#saveResult").click(function(e) {
	ResultSave(eventTypeId);
});
}

function ResultSave(eventTypeId) {
	
	//const data = SB.Utils.readFormData($('#saveResult'));
	const urlParam = new URLSearchParams(window.location.search);
	
	let eventId = parseInt(urlParam.get("eventid"));
	let competitorId;
	let periodTypeId;
	let result;
	let resultTypeId;
	
	console.log("This is the EventId: " + eventId + " " + typeof(eventId));
	
	if(eventTypeId === 1){
		console.log("Foci");
		$(".resultInput").each(function(){
			competitorId = parseInt($(this).attr("data-competitorId"));
			periodTypeId = parseInt($(this).attr("data-periodId"));
			result = parseInt($(this).val());
			resultTypeId = parseInt($(this).attr("data-resultTypeId"));
	
		
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
					//alert("success");
				});
		});
		
	} else {
		console.log("Ló");
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
					//alert("success");
				});
		});
	}

}
	
