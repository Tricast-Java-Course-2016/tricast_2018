window.onload = function() {
    // TODO load account data and balance
    let player = {
        username : 'TestPlayer',
        balance : 12300
    };
    SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);

    const destructById = function(array) {
        let json = {};
        $.each(array, function(index, elem) {
            json[elem.id] = elem;
        });
        return json;
    };

    let eventsById;

    $.when(SB.Utils.getAjax('/sportsbook/api/sports', SB.Token.PLAYER),
            SB.Utils.getAjax('/sportsbook/api/leagues/list', SB.Token.PLAYER),
            SB.Utils.getAjax('/sportsbook/api/competitors/list', SB.Token.PLAYER),
            SB.Utils.getAjax('/sportsbook/api/events/listOpen', SB.Token.PLAYER)
        ).done(
            function(sports, leagues, competitors, events) {

                sportsById = destructById(sports[0]);
                leaguesById = destructById(leagues[0]);
                competitorsById = destructById(competitors[0]);
                eventsById = destructById(events[0]);

                let eventList = [];
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
};