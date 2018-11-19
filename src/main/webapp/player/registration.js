window.onload = function() {
    bindListeners();
};

function bindListeners() {
    $("#saveAccount").click(function(e) {
        register();
    });
}

function register() {

    let data = SB.Utils.readFormData($('#registration'));
    data.dob = moment(data.dob).format('YYYYMMDD');
    data.accountType = 'PLAYER';

    SB.Utils.postAjax("/sportsbook/api/accounts", data, null, function(data, status, xhr) {
        alert("success");
        window.location.href = "login.html";
    });

}
