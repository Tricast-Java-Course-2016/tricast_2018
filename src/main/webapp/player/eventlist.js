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
		let string1=createString();

	
		localStorage.setItem('string',string1);
		

	    
	    SB.Utils.postAjax("/sportsbook/api/bets",JSON.parse(string1),SB.Token.PLAYER,function(data){
	    	
	    	if(data.sumStake<0){
	    		alert("Bet placement unsuccessful!");
	    		
	    		localStorage.setItem('status',"BAD_STATUS");
	    		
	    		var input = { combinations: [ ] };
	    		for(var i = 0; i < data.listOfBetResponses[0].marketDescription.length; ++i)
	    		    input.combinations.push({
	    		        marketDescription:   data.listOfBetResponses[0].marketDescription[i],
	    		        outcome: data.listOfBetResponses[0].outcome[i],
	    		        odds: data.listOfBetResponses[0].odds[i]
	    		    });
	    		
	    		let thead={
	    				message: 'Some of the odds have changed.'
	    		}
	    		$('#events-table thead').html(Handlebars.compile($('#show_odds_message').html())(
	                    thead
	             ));
	    		
	    		$('#events-table tbody').html(Handlebars.compile($('#show_new_odds').html())(
	                    input
	             ));
	    		
	    		$('#buttons').html(Handlebars.compile($('#generate_buttons').html())(
	                    
	             ));	
	    		
	    	}
	    	else{
		    	removeAll();
		    	alert("Bet placement successful!");
		    	alert.show();
	    	}
    	
	    });
    });
			
};

function createString(){
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

			if (current.includes("outcomeOdds") && localStorage.key(i)!='string'){					
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
	return string1;
}

function removeAll(){
	localStorage.removeItem('status');
	for(let i = 0; i < localStorage.length; i++){
		current=localStorage.getItem(localStorage.key(i));

		if (current.includes("outcomeOdds")){					
			localStorage.removeItem(localStorage.key(i));
			i--;
		}
	}
	localStorage.removeItem('string');
	location.reload();
}

function placeBetAnyways(){
	let json=localStorage.getItem('string');
	
	
	SB.Utils.postAjax("/sportsbook/api/bets/withoutOddsCheck",JSON.parse(json),SB.Token.PLAYER,function(data){
    	
			removeAll();
	    	alert("Bet placement successful!");
	    	alert.show();
    	
    });
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
	    		
	    	if(current.includes(element.getAttribute('data-outcomeId')) && current.includes(element.getAttribute('data-odds')) 
	    			&& current.includes(element.getAttribute('data-marketdescription')) 
	    			&& current.includes(element.getAttribute('data-outcomedescription'))){
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