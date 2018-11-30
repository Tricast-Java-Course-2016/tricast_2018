window.onload = function() {
	let operator = {
			username : SB.Utils.getOperatorUsername()
		};
	SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
	
	let eventid = localStorage.getItem('eventid');
	

	let url = "/sportsbook/api/events/"+ eventid +"/odds";
		    
	SB.Utils.getAjax(url, SB.Token.OPERATOR, function(e) {		       
		$('#odds_place').html(Handlebars.compile($('#show_event_details').html())({
		            e
		}));
	});	
	
};



    
