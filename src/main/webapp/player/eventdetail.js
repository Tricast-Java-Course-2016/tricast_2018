window.onload = function() {
    // TODO load account data and balance
    let player = {
        username : 'TestPlayer',
        balance : 12300
    };
    SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    
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
   	    $('#eventdetail_table thead').html(Handlebars.compile($('#show_event_description').html())(
                eventDetail
            ));


    	
   	    $('#eventdetail_table tbody').html(Handlebars.compile($('#show_event_details').html())(
                   events
               ));

            });
};