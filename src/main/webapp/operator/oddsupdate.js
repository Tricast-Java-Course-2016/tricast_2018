window.onload = function() {
	let operator = {
			username : SB.Utils.getOperatorUsername()
		};
	SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
	
	let eventid = localStorage.getItem('eventid');
	

	let url = "/sportsbook/api/events/"+ eventid +"/odds";
	
	localStorage.setItem('url',url);
		    
	SB.Utils.getAjax(url, SB.Token.OPERATOR, function(data) {		      
		localStorage.setItem('url',url);
		$('#eventdetail_table tbody').html(Handlebars.compile($('#show_event_details').html())(
		            data
		));
		$('#odds_table tbody').html(Handlebars.compile($('#show_odds').html())(
	            data
	));
	});	
	
	let
    eventDetail = {
    eventId : data.eventId,
    eventDescription : data.eventDescription,
    eventStartTime : moment(data.eventStartTime).format('YYYY.MM.DD HH:MM')
	 };
	    $('#event_description thead').html(Handlebars.compile($('#show_event_description').html())(
           eventDetail
       ));

	    $('#event_start thead').html(Handlebars.compile($('#show_event_start').html())(
           eventDetail
       ));
	
};



    
