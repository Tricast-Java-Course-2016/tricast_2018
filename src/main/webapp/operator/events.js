window.onload = function() {

	Handlebars.registerHelper('ifEqual', function(v1, v2, options) {
		if (v1 == v2) {
			return options.fn(this);
		}
		return options.inverse(this);
	});
	Handlebars.registerHelper('ifContain', function(what, inside, options) {
		if (inside.indexOf(what) >= 0) {
			return options.fn(this);
		}
		return options.inverse(this);
	});
	
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

                $.each(sportsById, function(index, sport){
                	sport.periodTypesById = destructById(sport.periodTypes);
                	sport.marketTypesById = destructById(sport.marketTypes);
                });
                
                $.each(eventsById, function(index, event){
                	event.date = moment(event.startTime).format('YYYY-MM-DD');
                	event.time = moment(event.startTime).format('HH:MM');
                });
                
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
                            marketTypes : [ 
                            	{ id : 1, description : 'WDW', 		sportId : 1 }, 
                            	{ id : 9, description : 'Outright', sportId : 2 }, 
                        	],
                            periods : periodsById
                        }));
                
                $.each($('#period-market-type-table tr[data-sport-id]'), function(index, tr){
                	tr = $(tr);
                	const sportId = tr.attr('data-sport-id');
                	$.each(tr.find('td'), function(index, td){
                		td = $(td);
                		const periodTypeId = td.children('input[type="checkbox"]').attr('data-period-type-id');
                		const hasPeriod = sportsById[sportId].periodTypesById.hasOwnProperty(periodTypeId);
                		if (!hasPeriod)
                			td.empty();
                	});
                });

                $('#events-table tbody').html(Handlebars.compile($('#event-row-template').html())({
                    events : eventList
                }));

                const editModalTemplate = Handlebars.compile($('#edit-modal-body-template').html());
                $(document).on('click', '[data-edit-modal]', function(){
                	const eventId = $(this).attr('data-edit-modal');
                	$('#edit-event-modal .modal-body').html(editModalTemplate({
                		event: eventsById[eventId],
                		leagues : leaguesById,
                        competitors : competitorsById
                	}));
                	$('#edit-event-modal').modal('show');
                });
                
                $('#new-event-form select[name="leagueId"]').on('change', function() {
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
                            option.css('display', 'block');
                        } else {
                        	const disable = (competitorsById[option.attr('value')].leagueIds.indexOf(leagueId) === -1);
                            option.prop('disabled', disable);
                            option.css('display', disable ? 'none' : 'block');
                        }
                    });
                    $('#new-event-form [data-name="competitorIds"]').val([]);
                    $.each($('#period-market-type-table tr'), function(index, tr) {
                        tr = $(tr);
                        if (sportId === -1) {
                            tr.find('input[type="checkbox"]').prop('disabled', false);
                        } else {
                        	const disable = tr.attr('data-sport-id') != sportId;
                            tr.find('input[type="checkbox"]').prop('disabled', disable);
                            tr.find('input[type="checkbox"][disabled]').prop('checked', false);
                        }
                    });
                });

                const refreshEventDescription = function (){
                	const form = $(this).parents('form');
                	const descriptionInput = form.find('input[name="description"]');
                	const selectedCompetitors = form.find('[data-name="competitorIds"]').val();
                	if (form.find('select[name="eventTypeId"]').val() == 1) {
                		if(selectedCompetitors.length == 2)
                			descriptionInput.val(
                					competitorsById[ selectedCompetitors[0] ].description + 
                					'-' + 
                					competitorsById[ selectedCompetitors[1] ].description);
                		else 
                			descriptionInput.val('');
                	}
                }
                
                $('select[data-name="competitorIds"]').on('change', refreshEventDescription);
                
                $('#new-event-form select[name="eventTypeId"]').on('change', function() {
                	const select = $(this);
                	const descriptionInput = select.parents('form').find('input[name="description"]');
                	if (select.val() == 1) {
                		descriptionInput.val('').prop('readonly', true);
                		refreshEventDescription();
                	}
                	else {
                		descriptionInput.val('').prop('readonly', false);
                	} 
                });
                
                $('#new-event-form').on('submit', function(e) {
                    e.preventDefault();

                    const data = Object.assign({}, SB.Utils.readFormData($('#new-event-form')),
                            readCustomInputsFrom($('#new-event-form')));
                    SB.Utils.postAjax('/sportsbook/api/events', data, SB.Token.OPERATOR, function() {
                        location.reload(true);
                    });
                });
                
                $(document).on('submit', '#edit-event-form', function(e) {
                    e.preventDefault();

                    const data = SB.Utils.readFormData($('#edit-event-form'));
                    SB.Utils.putAjax('/sportsbook/api/events/' + $(this).attr('data-event-id'), data, SB.Token.OPERATOR, function() {
                        location.reload(true);
                    });
                });
                
                // Sumák Frontend Technic (SFC) - NEM AJÁNLOTT! NE HASZNÁLD!
                $('#search').on('change keyup', function(){
                	const search = $(this).val().toLowerCase().trim();
                	
                	if(search.length === 0){
                		$('#events-table tbody tr').css('display', 'table-row');
                	}
                	else {
        	        	const regex = new RegExp(search, 'i');
        	        	$.each($('#events-table tbody tr'), function(index, tr){
        	        		tr = $(tr);
        	        		tr.css('display', ( 	regex.test(tr.attr('data-name')) || 
        	        								regex.test(tr.attr('data-league')) ||
        	        								regex.test(tr.attr('data-start-date')) ||
        	        								regex.test(tr.attr('data-status'))
        	        		) ? 'table-row' : 'none' );
        	        	});
            		}
                });
                
            });
};

function oddsload(element) {
    localStorage.setItem('eventid', element.getAttribute('data-eventid'));
    window.location.href = "oddsupdate.html";
};

function resultsload(element) { 
	localStorage.setItem('eventid', element.getAttribute('data-eventid')); 
	window.location.href = "results.html?eventid=" + element.getAttribute('data-eventid');
}
