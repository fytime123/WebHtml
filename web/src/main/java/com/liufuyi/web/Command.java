package com.liufuyi.web;

public interface Command {

    String getName();
    void execute(String params,MainToJsCallback callback);

}
