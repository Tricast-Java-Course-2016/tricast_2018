window.onload = function() {
    bindListeners();
};

function bindListeners() {
    $("#loginSubmit").click(function(e) {
        login();
    });
}

function login() {
    let data = {
        userName : $("#lgusername").val(),
        password : $("#lgpassword").val()
    }

    SB.Utils.postAjax("/sportsbook/api/accounts/login", data, false, function(data, status, xhr) {
        alert("success");
        // save data to the local storage
        let token = xhr.getResponseHeader('Authorization');
        SB.Utils.saveOperatorData(token, data.id);
        window.location.href = "operatorhome.html";
    });
}