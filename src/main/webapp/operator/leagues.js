window.onload = function() {

    let operator = {
        username : SB.Utils.getOperatorUsername()
    };
    SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
        
    const destructById = function(array) {
        let json = {};
        $.each(array, function(index, elem) {
            json[elem.id] = elem;
        });
        return json;
    };
    
    $.when( SB.Utils.getAjax('/sportsbook/api/leagues/list', SB.Token.OPERATOR),
            SB.Utils.getAjax('/sportsbook/api/sports', SB.Token.OPERATOR) )
    .done( function(leagues, sports) {
        leagues = destructById(leagues[0]);
        sports  = destructById(sports[0]);
        
        $.each(leagues, function(index, league){
        	league.sportName = sports[league.sportId].description;
        });
        
        $('#league-table tbody').html(Handlebars.compile($('#league-row-template').html())({
            leagues : leagues
        }));

        $('#new-league-modal').html(Handlebars.compile($('#new-league-modal').html())({
            sports : sports
        }));
        
        $('#new-league-form').on( 'submit', function(e) {
                    e.preventDefault();

                    const data = SB.Utils.readFormData($('#new-league-form'));
                    
                    SB.Utils.postAjax('/sportsbook/api/leagues', data, SB.Token.OPERATOR, function() {
                        location.reload(true);
                    });
                });
        
        // Sumák Frontend Technic (SFC) - NEM AJÁNLOTT! NE HASZNÁLD!
        $('#search').on('change keyup', function(){
        	const search = $(this).val().toLowerCase().trim();
        	if(search.length === 0){
        		$('#league-table tbody tr').css('display', 'table-row');
        	}
        	else {
	        	const regex = new RegExp(search, 'i');
	        	$.each($('#league-table tbody tr'), function(index, tr){
	        		tr = $(tr);
	        		tr.css('display', ( regex.test(tr.attr('data-name')) || regex.test(tr.attr('data-sport-name')) ) ? 'table-row' : 'none' )
	        	});
    		}
        });
        
    });
			
			
};