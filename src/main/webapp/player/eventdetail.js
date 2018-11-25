window.onload = function() {
	loadBalance();
    let eventid = localStorage.getItem('eventid');


    let eventsById;
    let url="/sportsbook/api/events/"+eventid+"/detail";
    
    SB.Utils.getAjax(url, SB.Token.PLAYER,function(events) {
    	
    	 let
         eventDetail = {
         eventId : events.eventId,
         eventDescription : events.eventDescription,
         eventStartTime : moment(events.eventStartTime).format('YYYY.MM.DD HH:MM')
    	 };
   	    $('#event_description').html(Handlebars.compile($('#show_event_description').html())(
                eventDetail
            ));

   	    $('#event_start').html(Handlebars.compile($('#show_event_start').html())(
                eventDetail
            ));
    	
   	    $('#eventdetail_table tbody').html(Handlebars.compile($('#show_event_details').html())(
                   events
               ));

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

		var json={};
		
		event.preventDefault();
      
		var bettype = document.querySelector('input[name="betType"]:checked').value;
		var stake = document.getElementById("stake").value;
		var accountId=localStorage.getItem('PLAYER_ID');
		
		json.accountId=accountId;
		json.betStake=stake;
		
		localStorage.setItem('check',bettype);
		
		if(bettype=="single"){
			json.betTypeId=1;
		}
		else if(bettype=='double'){
			json.betTypeId=2;
		} 
		else{
			json.betTypeId=3;
		}
		
		json.outcomeOdds={};
		
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
			let outcome=outcomeId[i];
			let odd=odds[i];
			json.outcomeOdds[i] ={outcome:odd};
		}
		   


	    localStorage.setItem('json',JSON.stringify(json));
	    localStorage.setItem('odds',JSON.stringify(odds));
	    localStorage.setItem('outcomes',JSON.stringify(outcomeId));
	    
	    //SB.Utils.postAjax("/sportsbook/api/bets",json,SB.Token.PLAYER);
    });  
	
};


function addToBetslip(element) {
		
    let detail={
    		outcomeOdds:"true",
    		odds:element.getAttribute('data-odds'),
    		outcomeId:element.getAttribute('data-outcomeId'),
    		marketDescription:element.getAttribute('data-marketDescription'),
    		outcomeDescription:element.getAttribute('data-outcomeDescription')
    };
    
    localStorage.setItem(element.getAttribute('data-outcomeId'),JSON.stringify(detail));
    
    let betslip = [];
    let current;
    for(let i = 0; i < localStorage.length; i++){
    	if(localStorage.key(i)!='betslip'){
    		current=localStorage.getItem(localStorage.key(i));
    		
    		if (current.includes("outcomeOdds"))
    			betslip.push(JSON.parse(current));
    	}
        
    }
	
    localStorage.setItem('betslip',betslip);
    
    
	$('#betslip-table tbody').html(Handlebars.compile($('#add-to-betslip').html())(
			betslip
     ));
	
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
    let url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/balance";
    
    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {
        let player = {
                username : SB.Utils.getPlayerUsername(),
                balance : data.balance
            };
            SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    });
}