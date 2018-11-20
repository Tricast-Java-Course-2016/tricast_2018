window.onload = function() {
    loadBalance();
    bindListeners();
};

function loadBalance() {
    let url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/balance";
    
    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {
        let player = {
                username : SB.Utils.getPlayerUsername(),
                balance : data.balance
            };
            SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    });
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

    SB.Utils.postAjax(url, data, SB.Token.PLAYER, function(data, status, xhr) {
        loadBalance();
    });

}
