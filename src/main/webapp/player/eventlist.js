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
	
	
	const betslipform = document.getElementById("betslip");

	   
	betslipform.addEventListener("submit", function(event) {

		event.preventDefault();
		
		var string1='';
		string1+='{';
		string1+='"';
		string1+="bettypeId";
		string1+='"';
		string1+=": ";

		var bettype = document.querySelector('input[name="betType"]:checked').value;
		var stake = document.getElementById("stake").value;
		var accountId=localStorage.getItem('PLAYER_ID');
		
		
		if(bettype=="single"){
			string1+="1,";
		}
		else if(bettype=='double'){
			string1+="2,";
		} 
		else{
			string1+="3,";
		}
		string1+='"'; 
		string1+="accountId";
		string1+='"';
		string1+=": ";
		string1+='"';
		string1+=accountId;
		string1+='"';
		string1+=',';
		string1+='"';
		string1+="betStake";
		string1+='"';
		string1+=": ";
		string1+=stake;
		string1+=',';
		string1+='"';
		string1+="outcomeOdds";
		string1+='"';
		string1+=": {";
		
		
		let odds = [];
		let outcomeId = [];
		let current;
		for(let i = 0; i < localStorage.length; i++){
			if(localStorage.key(i)!='betslip'){
				current=localStorage.getItem(localStorage.key(i));

				if (current.includes("outcomeOdds")){					
					odds.push(JSON.parse(current).odds);
		    		outcomeId.push(JSON.parse(current).outcomeId);
				}

		    }
		}
		
		for(let i=0;i<odds.length;i++){
			string1+='"';
			string1+=outcomeId[i];
			string1+='"';
			string1+=": ";
			string1+='"';
			string1+=odds[i];
			string1+='"';
			
			if(i<odds.length-1){
				string1+=',';
			}	
		}
		
		
		string1+="}}";
		

		//localStorage.setItem('string',string1);
	    
	    SB.Utils.postAjax("/sportsbook/api/bets",JSON.parse(string1),SB.Token.PLAYER);
    });  
		
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