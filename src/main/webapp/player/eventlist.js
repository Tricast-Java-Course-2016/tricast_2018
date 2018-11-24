window.onload = function() {

    loadBalance();

    const
    destructById = function(array) {
        let
        json = {};
        $.each(array, function(index, elem) {
            json[elem.id] = elem;
        });
        return json;
    };

    let
    eventsById;

    $.when(SB.Utils.getAjax('/sportsbook/api/sports', SB.Token.PLAYER),
            SB.Utils.getAjax('/sportsbook/api/leagues/list', SB.Token.PLAYER),
            SB.Utils.getAjax('/sportsbook/api/competitors/list', SB.Token.PLAYER),
            SB.Utils.getAjax('/sportsbook/api/events/listOpen', SB.Token.PLAYER)).done(
            function(sports, leagues, competitors, events) {

                sportsById = destructById(sports[0]);
                leaguesById = destructById(leagues[0]);
                competitorsById = destructById(competitors[0]);
                eventsById = destructById(events[0]);

                let
                eventList = [];
                $.each(eventsById, function(id, event) {
                    eventList.push({
                        'id' : event.id + '',
                        'title' : event.description,
                        'league' : leaguesById[event.leagueId].description,
                        'startDate' : moment(event.startTime).format('YYYY.MM.DD HH:MM')
                    });
                });

                $('#events-table tbody').html(Handlebars.compile($('#event-row-template').html())({
                    events : eventList
                }));

            });
    
    
    let betslip = [];
    let current;
    for(let i = 0; i < localStorage.length; i++){
    	if(localStorage.key(i)!='betslip'){
    		current=localStorage.getItem(localStorage.key(i));
    		
    		if (current.includes("outcomeOdds"))
    			betslip.push(JSON.parse(current));
    	}
        
    }
	$('#betslip-table tbody').html(Handlebars.compile($('#add-to-betslip').html())(
			betslip
     ));
		
};

function saveStake(element){
	 var x = element.getAttribute('value');
	 localStorage.setItem('stake',x);
}

function eventdetail(element) {
    localStorage.setItem('eventid', element.value);
    window.location.href = "eventdetail.html";
}

function removeFromBetslip(element) {

	let betslip = [];
	let current;
	for(let i = 0; i < localStorage.length; i++){
	    if(localStorage.key(i)!='betslip'){
	    	current=localStorage.getItem(localStorage.key(i));
	    		
	    	if(current.includes(element.getAttribute('data-outcomeId')) && current.includes(element.getAttribute('data-odds'))){
	    		localStorage.removeItem(localStorage.key(i));
	    	}
	    		
	    	if (current.includes("outcomeOdds"))
	    		betslip.push(JSON.parse(current));
	    }	      
	}
		
	localStorage.setItem('betslip',betslip);
	location.reload();
}

function loadBalance() {
    let
    url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/balance";

    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {
        let
        player = {
            username : SB.Utils.getPlayerUsername(),
            balance : data.balance
        };
        SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    });
}