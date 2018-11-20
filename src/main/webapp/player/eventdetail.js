window.onload = function() {
    // TODO load account data and balance
    let player = {
        username : 'TestPlayer',
        balance : 12300
    };
    SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    
    let eventid = localStorage.getItem('eventid');
    $('#place').html(Handlebars.compile($('#show_event_id').html())({
        eventid
    }));
     

    const destructById = function(array) {
        let json = {};
        $.each(array, function(index, elem) {
            json[elem.id] = elem;
        });
        return json;
    };

    let eventsById;
    let url="/sportsbook/api/events/"+eventid+"/detail";
    
    $.when(SB.Utils.getAjax(url, SB.Token.PLAYER)
        ).done(
            function(events) {

                eventsById = destructById(events[0]);

                let eventdetail = [];
                
                
                $.each(eventsById, function(id, event) {
                    eventdetail.push({
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
};