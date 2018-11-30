window.onload = function() {
	let operator = {
			username : SB.Utils.getOperatorUsername()
		};
	SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
	
	let eventid = localStorage.getItem('eventid');
	

	let url = "/sportsbook/api/events/"+ eventid +"/odds";
	

		    
	SB.Utils.getAjax(url, SB.Token.OPERATOR, function(data) {
		
		let
	    eventDetail = {
	    eventId : data.eventId,
	    eventDescription : data.eventDescription,
	    eventStartTime : moment(data.eventStartTime).format('YYYY.MM.DD HH:MM')
		};
		    $('#event_description').html(Handlebars.compile($('#show_event_description').html())(
	           eventDetail
	       ));

		    $('#event_start').html(Handlebars.compile($('#show_event_start').html())(
	           eventDetail
	       ));
		
		$('#odds_table tbody').html(Handlebars.compile($('#show_odds').html())(
	            data
		));
	});
	
	$('input[data-type="oddsu"]').change(function() {
		$(this).attr('value', $(this).val());
	})
	
};

function updateOdds(){
	eventId=localStorage.getItem('eventid');
	
	var string1='';
	string1+='{';
	string1+='"';
	string1+="eventId";
	string1+='"';
	string1+=": ";
	string1+='"';
	string1+=eventId;
	string1+='"';
	string1+=',';
	string1+='"';
	string1+="outcomeIdOdds";
	string1+='"';
	string1+=":{";
	
	let odds = [];
	let outcomeId = [];
	
	$('input[data-type="oddsu"]').each(function(index, item){
		outcomeId.push($(item).attr('data-outcomeId'));
		odds.push($(item).attr('value'));
	});
	
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
	
	localStorage.setItem('string',string1);
	let url = "/sportsbook/api/events/"+ eventId +"/odds";
	SB.Utils.postAjax(url,JSON.parse(string1),SB.Token.OPERATOR,function(){
    	alert("Odds update successful!");
	});
		
}



    
