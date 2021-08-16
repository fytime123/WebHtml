var webxjs = {};
webxjs.os = {};
webxjs.os.isIOS = /iOS|iPhone|iPod/i.test(navigator.userAgent);
webxjs.os.isAndroid = !webxjs.os.isIOS;
webxjs.callbacks = {};

webxjs.callback = function(callbackName,response){
    var callbackObject = webxjs.callbacks[callbackName];
    if(callbackObject !== undefined){
        if(callbackObject.callback != undefined){
            var ret = callbackObject.callback(response);
            if(ret !== false){
                return
            }
            delete webxjs.callbacks[callbackName];
        }
    }
};

webxjs.takeNativeAction = function(commandName,parameters){

	console.log("webxjs takeNativeAction");
	var request = {};
	request.name = commandName;
	request.param = parameters;
	if(window.webxjs.os.isAndroid){
	 	console.log("android take native action "+JSON.stringify(request));
		window.webxPlugin.doNativeAction(JSON.stringify(request));

	}else {
		window.webkit.messageHandlers.webxPlugin.postMessage(JSON.stringify(request));
	}

};

webxjs.takeNativeActionWithCallback = function(commandName,parameters,callback){

    var callbackName = "nativetojs_callback_"+((new Date()).getTime()) + "_" + Math.floor(Math.random()*10000);
    webxjs.callbacks[callbackName] = {callback:callback};

	console.log("webxjs takeNativeAction");
	var request = {};
	request.name = commandName;
	request.param = parameters;
	request.callbackName = callbackName;
	if(window.webxjs.os.isAndroid){
	 	console.log("android take native action "+JSON.stringify(request));
		window.webxPlugin.doNativeAction(JSON.stringify(request));

	}else {
		window.webkit.messageHandlers.webxPlugin.postMessage(JSON.stringify(request));
	}

};

window.webxjs = webxjs;

