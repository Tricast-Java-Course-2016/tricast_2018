window.SB = window.SB || {};

let playerToken = "PLAYER_TOKEN";
let playerId = "PLAYER_ID";
let operatorToken = "OPERATOR_TOKEN";
let operatorId = "OPERATOR_ID";

window.SB.Utils = {
    loadTemplate : function loadTemplate(containerId, tplName, data, afterLoadCallback) {
        SB.Utils.loadHTMLSnippet('../tpl/' + tplName, function(tplSource) {
            var template = Handlebars.compile(tplSource);
            var html = template(data);
            var container = $(containerId);

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
        var errorMsg;
        if (xhr.status == 0) {
            errorMsg = "The server is not responding or is not reachable.";
        } else {
            errorMsg = (xhr.statusText != "")? xhr.responseText : xhr.response;
        }
        console.log(errorMsg);
        alert(errorMsg);
     },
     
    postAjax : function postAjax(url, data, player, successCallback, errorCallback) {
        
        if(!errorCallback) {
            errorCallback = SB.Utils.defaultErrorHandling;
        }
        
        $.ajax({
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
                if (player != null ){
                    xhr.setRequestHeader("Authorization", SB.Utils.getToken(player));
               }
            }
        });
    },
    
    getAjax : function getAjax(url, player, successCallback, errorCallback) {
        
        if(!errorCallback) {
            errorCallback = defaultErrorHandling(xhr);
        }
        
        $.ajax({
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
                if (player != null ){
                    xhr.setRequestHeader("Authorization", SB.Utils.getToken(player));
                }
            }
        });
    }, 
    
    getToken : function getToken(player) {
        let token = null;
        if(player == true) {
            token = localStorage.getItem(playerToken);
        } else if (player == false) {
            token = localStorage.getItem(operatorToken);
        }
        return token;
    },
    
    savePlayerData : function savePlayerData(token, id) {
        localStorage.setItem(playerToken, token);
        localStorage.setItem(playerId, id);
    }, 
    
    getPlayerId : function getPlayerId() {
        return localStorage.getItem(playerId);
    },
    
    saveOperatorData : function saveOperatorData(token, id) {
        localStorage.setItem(operatorToken, token);
        localStorage.setItem(operatorId, id);
    }, 
    
    getOperatorId : function getOperatorId() {
        return localStorage.getItem(operatorId);
    },
};