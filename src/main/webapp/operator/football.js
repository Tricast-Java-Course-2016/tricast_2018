window.onload = function() {

    let operator = {
        username : SB.Utils.getOperatorUsername()
    };
    SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
    bindListeners();
    
    
    const destructById = function(array) {
        let json = {};
        $.each(array, function(index, elem) {
            json[elem.id] = elem;
        });
        return json;
    };

    let competitorsByEventId;

    $.when(SB.Utils.getAjax('/sportsbook/api/competitors/eventid/{id}', SB.Token.OPERATOR)).done(
            function(competitors) {

	        	competitorsByEventId = destructById(competitors[0]);
	            
	            $('#test').html(Handlebars.compile($('#test').html())({
	                competitors : competitorsByEventId,
	            }));
            
            });
}

function bindListeners() {
    $("#saveFootballResult").click(function(e) {
        footballResultSave();
    });
}

function footballResultSave() {

    const data = SB.Utils.readFormData($('#footballResultSave'));
    
    SB.Utils.postAjax("/sportsbook/api/results",
    				{
			    	  "eventId": 1,
			    	  "resultToSave": {
			    	    "competitorId": 3,
			    	    "periodTypeId": 1,
			    	    "result": 5,
			    	    "resultTypeId": 1
			    	  }
			    	},
		    		SB.Token.OPERATOR, function(data, status, xhr) {
        alert("success");
    });

}
        
