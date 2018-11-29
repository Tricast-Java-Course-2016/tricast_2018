window.onload = function() {

    loadBalance();
    loadTransactionHistory();
    loadTransactionTypesForFilter();

    bindListeners();
};

function bindListeners() {
    $('#filter-btn').on('click', function(e) {
        loadTransactionHistory();
    });
}

function loadTransactionHistory() {

    let
    url = "/sportsbook/api/accounts/" + SB.Utils.getPlayerId() + "/transactions/filter";

    let
    typeFromFilter = $('#transaction-type-filter').val();

    console.log(typeFromFilter);

    let
    fromDateIdFromFilter = $('#tr-from-date-filter').val();
    let
    toDateIdFromFilter = $('#tr-to-date-filter').val();

    let
    typeFilterSet = false;
    if (typeFromFilter != 'NONE') {
        url += "?transactionType=" + typeFromFilter;
        typeFilterSet = true;
    }

    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {

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

function loadTransactionTypesForFilter() {
    let
    url = "/sportsbook/api/transactions/types";

    SB.Utils.getAjax(url, SB.Token.PLAYER, function(data, status, xhr) {
        let
        transactionTypes = [];
        data.forEach(function(entry) {
            transactionTypes.push({
                'id' : entry.id,
                'type' : entry.type,
                'description' : entry.description
            });
        });

        $('#transaction-type-filter').html(Handlebars.compile($('#transaction-type-filter').html())({
            transactiontypes : transactionTypes
        }));
    });
}