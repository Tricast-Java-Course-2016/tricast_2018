window.onload = function() {
    // TODO load account data and balance
    let operator = {
        username : 'TestOperator'
    };
    SB.Utils.loadTemplate('#navbar-content', 'operator_navbar.html', operator, null);
};
