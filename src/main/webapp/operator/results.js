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

bindListeners();

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

const urlParam = new URLSearchParams(window.location.search);
let id = urlParam.get("eventid");
$.when(SB.Utils.getAjax('/sportsbook/api/competitors/eventid/' + id, SB.Token.OPERATOR),
		SB.Utils.getAjax('/sportsbook/api/events/' + id, SB.Token.OPERATOR)).done(
		function(competitors, events) {
			
			eventsById = destructById(events[0]);
			competitorsByEventId = destructById(competitors[0]);
			
			eventTypeId = events[0].eventTypeId;

			let competitorsList = [];
			
			$.each(competitorsByEventId, function(id, competitor) {
				competitorsList.push({
					'id' : competitor.id + '',
					'description' : competitor.description
				});
			});
			
			if(eventTypeId === 1){
				$('#results-tabele table').html(Handlebars.compile($('#football-results-template').html())({					
					competitors : competitorsList
				}));
			} else {
				$('#results-tabele table').html(Handlebars.compile($('#horserace-results-template').html())({
					competitors : competitorsList,
				}));
			}

		});
}

function bindListeners() {
$("#saveFootballResult").click(function(e) {
	footballResultSave();
});
}

function footballResultSave() {

const data = SB.Utils.readFormData($('#footballResultSave'));



//SB.Utils.postAjax("/sportsbook/api/results",
//				{
//				  "eventId": 1,
//				  "resultToSave": {
//					"competitorId": 3,
//					"periodTypeId": 1,
//					"result": 5,
//					"resultTypeId": 1
//				  }
//				},
//				SB.Token.OPERATOR, function(data, status, xhr) {
//	alert("success");
//});

}
	
