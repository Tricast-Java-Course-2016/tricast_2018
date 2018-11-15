window.onload = function() {
	
	
    // TODO load account data and balance
    let operator = {
        username : 'TestOperator'
    };
    SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
    
    const destructById = function(array){
    	let json = {};
    	$.each(array, function(index, elem){
    		json[elem.id] = elem;
    	});
    	return json;
    }; 
    
    let eventsById;
    let leaguesById;
    let competitorsById;
    let sportsById;
    
    $.when( 
	    SB.Utils.getAjax('/sportsbook/api/sports', false),
	    SB.Utils.getAjax('/sportsbook/api/leagues/list', false),
	    SB.Utils.getAjax('/sportsbook/api/competitors/list', false),
	    SB.Utils.getAjax('/sportsbook/api/events/list', false)		
	).done(function(sports, leagues, competitors, events){
		
		sportsById = destructById(sports[0]);
	    leaguesById = destructById(leagues[0]);
	    competitorsById = destructById(competitors[0]);
		eventsById = destructById(events[0]);
	    
		let eventList = [];
		$.each(eventsById, function(id, event){
			eventList.push({
				'id': event.id + '',
				'title': event.description,
				'league': leaguesById[event.leagueId].description,
				'startDate': moment(event.startTime).format('YYYY.MM.DD HH:MM'),
				'status': event.status.toUpperCase() == 'OPEN' ? 'Aktív': 'Inaktív'
			});
		});
		
		$('#events-table tbody').html( Handlebars.compile($('#event-row-template').html())({ events: eventList }) );
		
	});    
};
