package com.liufuyi.web;


public class JsParam {

    private String name;
    private Object param;
    private String callbackName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }
}
