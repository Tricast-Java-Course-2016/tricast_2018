window.SB = window.SB || {};

const playerToken = "PLAYER_TOKEN";
const playerId = "PLAYER_ID";
const playerUsername = "PLAYER_USERNAME";
const operatorToken = "OPERATOR_TOKEN";
const operatorId = "OPERATOR_ID";
const operatorUsername = "OPERATOR_USERNAME";

window.SB.Token = {
        PLAYER : 'PLAYER',
        OPERATOR : 'OPERATOR'
};

window.SB.Utils = {
    loadTemplate : function loadTemplate(containerId, tplName, data, afterLoadCallback) {
        SB.Utils.loadHTMLSnippet('../tpl/' + tplName, function(tplSource) {
            let template = Handlebars.compile(tplSource);
            let html = template(data);
            let container = $(containerId);

            container.html(html);
            if(afterLoadCallback) {
                afterLoadCallback(html);
            }
        });
    },

    loadHTMLSnippet : function loadHTMLSnippet(tplPath, afterLoadCallback) {
        $.ajax({
            type : 'GET',
            url : tplPath,
            dataType : 'text',
            beforeSend : function(xhr, settings) {
                xhr.url = settings.url;
            },
            success : function(tpl) {
                afterLoadCallback(tpl);
            }
        });
    },
    
    readFormData : function readFormData($form) {
        let data = {};
        for(let val of (new FormData($form.get(0))).entries()) {
            data[val[0]] = val[1];
        }
        return data;
    },
    
    defaultErrorHandling : function defaultErrorHandling(xhr) {
        let errorMsg;
        if (xhr.status == 0) {
            errorMsg = "The server is not responding or is not reachable.";
        } else {
            errorMsg = (xhr.statusText != "")? xhr.responseText : xhr.response;
        }
        console.log(errorMsg);
        alert(errorMsg);
     },
     
    postAjax : function postAjax(url, data, tokenToUse, successCallback, errorCallback) {
        
        if(!errorCallback) {
            errorCallback = SB.Utils.defaultErrorHandling;
        }
        
        return $.ajax({
            type : 'POST',
            dataType : 'json',
            contentType : "application/json;charset=utf-8",
            url : url,
            data : JSON.stringify(data),
            cache : false,
            timeout : 30000,
            ifModified : false,
            success : successCallback,
            error : errorCallback,
            beforeSend: function(xhr){
                if (tokenToUse != null ){
                    xhr.setRequestHeader("Authorization", SB.Utils.getToken(tokenToUse));
               }
            }
        });
    },
    
    getAjax : function getAjax(url, tokenToUse, successCallback, errorCallback) {
        
        if(!errorCallback) {
            errorCallback = SB.Utils.defaultErrorHandling;
        }
        
        return $.ajax({
            type : 'GET',
            dataType : 'json',
            contentType : "application/json;charset=utf-8",
            url : url,
            data : null,
            cache : false,
            timeout : 30000,
            ifModified : false,
            success : successCallback,
            error : errorCallback,
            beforeSend: function(xhr){
                if (tokenToUse != null ){
                    xhr.setRequestHeader("Authorization", SB.Utils.getToken(tokenToUse));
                }
            }
        });
    }, 
    
    getToken : function getToken(tokenToUse) {
        let token = null;
        if(tokenToUse == SB.Token.PLAYER) {
            token = localStorage.getItem(playerToken);
        } else if (tokenToUse == SB.Token.OPERATOR) {
            token = localStorage.getItem(operatorToken);
        }
        return token;
    },
    
    savePlayerData : function savePlayerData(token, id, username) {
        localStorage.setItem(playerToken, token);
        localStorage.setItem(playerId, id);
        localStorage.setItem(playerUsername, username);
    }, 
    
    getPlayerId : function getPlayerId() {
        return localStorage.getItem(playerId);
    },
    
    getPlayerUsername : function getPlayerUsername() {
        return localStorage.getItem(playerUsername);
    },
    
    saveOperatorData : function saveOperatorData(token, id, username) {
        localStorage.setItem(operatorToken, token);
        localStorage.setItem(operatorId, id);
        localStorage.setItem(operatorUsername, username);
    }, 
    
    getOperatorId : function getOperatorId() {
        return localStorage.getItem(operatorId);
    },
    
    getOperatorUsername : function getOperatorUsername() {
        return localStorage.getItem(operatorUsername);
    }
};