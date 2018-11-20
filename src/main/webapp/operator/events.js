window.onload = function() {

    let operator = {
        username : SB.Utils.getOperatorUsername()
    };
    SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);

    const readCustomInputsFrom = function(form) {
        let data = {};
        $.each($(form).find('[data-custom-input][data-name][data-input-type]'), function(index, elem) {
            elem = $(elem);
            switch (elem.attr('data-input-type').toLowerCase()) {

            case 'multiple-select':
                data[elem.attr('data-name')] = elem.val();
                break;

            case 'date-time':
                data[elem.attr('data-name')] = moment(
                        elem.find('[type="date"]').val() + ' ' + elem.find('[type="time"]').val()).toISOString();
                break;

            case 'period-market-type-matrix':
                const checkedOnes = elem.find(':checked');
                let markets = new Array(checkedOnes.length);
                $.each(checkedOnes, function(index, checkbox) {
                    checkbox = $(checkbox);
                    const tr = checkbox.parents('tr');
                    markets[index] = {
                        marketTypeId : tr.attr('data-market-type-id'),
                        periodTypeId : checkbox.attr('data-period-type-id')
                    };
                });
                data[elem.attr('data-name')] = markets;
                break;

            default:
                console.error('Nem támogatott egyedi input mező: "' + elem.attr('data-input-type') + '"');
                break;
            }
        });
        return data;
    }

    const destructById = function(array) {
        let json = {};
        $.each(array, function(index, elem) {
            json[elem.id] = elem;
        });
        return json;
    };

    let eventsById;
    let leaguesById;
    let competitorsById;
    let sportsById;
    let periodsbyId;

    $.when(SB.Utils.getAjax('/sportsbook/api/sports', SB.Token.OPERATOR),
            SB.Utils.getAjax('/sportsbook/api/leagues/list', SB.Token.OPERATOR),
            SB.Utils.getAjax('/sportsbook/api/competitors/list', SB.Token.OPERATOR),
            SB.Utils.getAjax('/sportsbook/api/events/list', SB.Token.OPERATOR),
            SB.Utils.getAjax('/sportsbook/api/periodtypes', SB.Token.OPERATOR)).done(
            function(sports, leagues, competitors, events, periods) {

                sportsById = destructById(sports[0]);
                leaguesById = destructById(leagues[0]);
                competitorsById = destructById(competitors[0]);
                eventsById = destructById(events[0]);
                periodsById = destructById(periods[0]);

                let eventList = [];
                $.each(eventsById, function(id, event) {
                    eventList.push({
                        'id' : event.id + '',
                        'title' : event.description,
                        'league' : leaguesById[event.leagueId].description,
                        'startDate' : moment(event.startTime).format('YYYY.MM.DD HH:MM'),
                        'status' : event.status.toUpperCase() == 'OPEN' ? 'Aktív' : 'Inaktív'
                    });
                });

                $('#new-event-modal').html(Handlebars.compile($('#new-event-modal').html())({
                    leagues : leaguesById,
                    competitors : competitorsById
                }));

                $('#period-market-type-table').html(
                        Handlebars.compile($('#period-market-type-table-template').html())({
                            marketTypes : [ {
                                id : 1,
                                description : 'WDW',
                                sportId : 1
                            }, {
                                id : 9,
                                description : 'Outright',
                                sportId : 2
                            }, ],
                            periods : periodsById
                        }));

                $('#events-table tbody').html(Handlebars.compile($('#event-row-template').html())({
                    events : eventList
                }));

                $('#new-event-form select[name="leagueId"]').on(
                        'change',
                        function() {
                            let sportId = -1;
                            const select = $(this);
                            const leagueId = (select.val().trim().length === 0) ? -1 : parseInt(select.val());
                            if (leagueId > 0) {
                                sportId = select.find('option[value="' + leagueId + '"]').attr('data-sport-id');
                            }
                            $.each($('#new-event-form [data-name="competitorIds"] option'), function(index, option) {
                                option = $(option);
                                if (leagueId === -1) {
                                    option.prop('disabled', false);
                                } else {
                                    option.prop('disabled', competitorsById[option.attr('value')].leagueIds
                                            .indexOf(leagueId) === -1);
                                }
                            });
                            $.each($('#period-market-type-table tr'), function(index, tr) {
                                tr = $(tr);
                                if (sportId === -1) {
                                    tr.find('input[type="checkbox"]').prop('disabled', false);
                                } else {
                                    tr.find('input[type="checkbox"]').prop('disabled',
                                            tr.attr('data-sport-id') != sportId);
                                }
                            });
                        });

                $('#new-event-form').on(
                        'submit',
                        function(e) {
                            e.preventDefault();

                            const data = Object.assign({}, SB.Utils.readFormData($('#new-event-form')),
                                    readCustomInputsFrom($('#new-event-form')));
                            SB.Utils.postAjax('/sportsbook/api/events', data, SB.Token.OPERATOR, function() {
                                location.reload(true);
                            });
                        });
            });
};
