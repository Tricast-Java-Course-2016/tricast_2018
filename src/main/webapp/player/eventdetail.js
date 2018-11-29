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
	
	if(localStorage.getItem('betType') && 	localStorage.getItem('stake')){
		document.getElementById("stake").value=localStorage.getItem('stake');
		let bettype=localStorage.getItem('betType');
		
		if(bettype=="single"){
			document.getElementById("betType1").checked=true;
		}
		else if(bettype=="double"){
			document.getElementById("betType2").checked=true;
		} 
		else if(bettype=="treble"){
			document.getElementById("betType3").checked=true;
		}
		refresh();
	}
	
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
	    		$('#eventdetail_table thead').html(Handlebars.compile($('#show_odds_message').html())(
	                    thead
	             ));
	    		
	    		$('#eventdetail_table tbody').html(Handlebars.compile($('#show_new_odds').html())(
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
	
	$('#betslip').change(function() {
		  refresh();
		  localStorage.setItem('betType',document.querySelector('input[name="betType"]:checked').value);
		  if( document.getElementById("stake").value!=""){
			localStorage.setItem('stake',document.getElementById("stake").value);  
		  }
	})
		
};

function refresh(){
	let string=createString();
	let json=JSON.parse(string);
	let combinations={
			odds : [ ]
	};
	
	let outcomeIds=[];
	let odds=[];
	let count=0;
	let sumStake=0;
	let sumWin=0;
	let sumOdds=1;
	
	if(json.betStake!=0){
		var myObject = json.outcomeOdds;

		for (var key in myObject) {
		    if (myObject.hasOwnProperty(key)) {
		    	odds[count]=myObject[key];
		    	outcomeIds[count]=key;
		    	count++;
		    }
		}
		
		if(json.bettypeId==1){
			for(let i=0;i<odds.length;i++){
				sumWin+=odds[i]*json.betStake;
			}
			sumStake=json.betStake*odds.length;
		}
		else if(json.bettypeId==2){
			if(odds.length>1){
				for(let i=0;i<outcomeIds.length-1;i++) {
					for(let  j=i+1;j<outcomeIds.length;j++) {
						combinations.odds.push(
							odds[i]*odds[j]		
						);
					}
				}
				sumStake=json.betStake*combinations.odds.length;
				for(let i=0;i<combinations.odds.length;i++){
					sumWin+=json.betStake*combinations.odds[i];
				}
			}
		}
		else if(json.bettypeId==3){
			if(odds.length==3){
				sumStake=json.betStake;
				for(let i=0;i<odds.length;i++){
					sumOdds*=odds[i];
					sumWin=sumOdds*json.betStake;
				}
			}
		}
		if(sumStake==0 || sumWin==0){
			document.getElementById("sumstake").value="N/A";
			document.getElementById("win").value="N/A";
		}else{			
			document.getElementById("sumstake").value=Math.ceil(sumStake);
			document.getElementById("win").value=Math.ceil(sumWin);
		}
	}	
}

function createString(){
	var string1='';
	string1+='{';
	string1+='"';
	string1+="bettypeId";
	string1+='"';
	string1+=": ";

	var stake;
	var bettype = document.querySelector('input[name="betType"]:checked').value;
	
	if(document.getElementById("stake").value!= ""){
		stake = document.getElementById("stake").value;
	}
	else{
		stake=0;
	}
	var accountId=localStorage.getItem('PLAYER_ID');
	
	
	if(bettype=="single"){
		string1+="1,";
	}
	else if(bettype=="double"){
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
	localStorage.removeItem('stake');
	localStorage.removeItem('betType');
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
	refresh();
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
    let url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/balance";
    
    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {
        let player = {
                username : SB.Utils.getPlayerUsername(),
                balance : data.balance
            };
            SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    });
}