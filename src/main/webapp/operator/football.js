window.onload = function() {
    // TODO load account data and balance
    let operator = {
        username : 'TestOperator'
    };
    SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);	
    bindListeners();
};

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
