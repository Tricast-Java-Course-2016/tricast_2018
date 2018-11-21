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
    
    let leaguesById;
    let competitorsById;

    $.when( SB.Utils.getAjax('/sportsbook/api/leagues/list', SB.Token.OPERATOR),
            SB.Utils.getAjax('/sportsbook/api/competitors/list', SB.Token.OPERATOR) )
    .done( function(leagues, competitors) {
        leaguesById = destructById(leagues[0]);
        competitorsById = destructById(competitors[0]);
        
        $.each(competitorsById, function(index, competitor){
        	let leagueNames = new Array(competitor.leagueIds.length);
        	$.each(competitor.leagueIds, function(index, leagueId){
        		leagueNames[index] = leaguesById[leagueId].description;
        	});
        	competitor.leaguesNameConcatenated = leagueNames.join(', ');
        });
        
        $('#competitors-table tbody').html(Handlebars.compile($('#competitor-row-template').html())({
            competitors : competitorsById
        }));

        $('#new-competitor-modal').html(Handlebars.compile($('#new-competitor-modal').html())({
            leagues : leaguesById
        }));
        
        $('#new-competitor-form').on( 'submit', function(e) {
                    e.preventDefault();

                    const data = Object.assign({}, 
                    		SB.Utils.readFormData($('#new-competitor-form')), 
                    		readCustomInputsFrom($('#new-competitor-form'))
                		);
                    
                    SB.Utils.postAjax('/sportsbook/api/competitors', data, SB.Token.OPERATOR, function() {
                        location.reload(true);
                    });
                });
        
    });
			
			
};