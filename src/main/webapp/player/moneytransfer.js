window.onload = function() {
    // TODO load account data and balance
    let player = {
        username : 'TestPlayer',
        balance : 12300
    };
    SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    bindListeners();
};

function loadAccount() {
    // TODO Orsi
}

function bindListeners() {
    $("#sendMoney").click(function(e) {
        sendMoney();
    });
}

function sendMoney() {
    let data = {
        amount : $("#inputAmount").val()
    }

    let url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/transactions/deposit";
    let type = $("#inputTransactionType").val();
    if (type == 'WITHDRAWAL') {
        url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/transactions/withdraw";
    }

    SB.Utils.postAjax(url, data, true, function(data, status, xhr) {
        alert("success");
    });

}
