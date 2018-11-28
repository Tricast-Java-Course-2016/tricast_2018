window.onload = function() {

    loadBalance();
    loadTransactionHistory();
};

function loadTransactionHistory() {
    let
    url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/transactions/filter";

    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {

        console.log(data);
        let
        transactionList = [];
        data.forEach(function(entry) {
            transactionList.push({
                'transactionId' : entry.transactionId,
                'betId' : entry.betId,
                'type' : entry.type,
                'description' : entry.description,
                'amount' : entry.amount,
                'createdDate' : entry.createdDate
            });
        });

        console.log(transactionList);

        $('#transactions-table tbody').html(Handlebars.compile($('#transactions-row-template').html())({
            transactions : transactionList
        }));
    });

}

function loadBalance() {
    let
    url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/balance";

    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {
        let
        player = {
            username : SB.Utils.getPlayerUsername(),
            balance : data.balance
        };
        SB.Utils.loadTemplate('#navbar-content', 'player_navbar.html', player, null);
    });
}