// CommandWebToMain.aidl
package com.liufuyi.web;

import com.liufuyi.web.MainToJsCallback;
// Declare any non-default types here with import statements

interface CommandWebToMain {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String jsonParams, in MainToJsCallback callback);
}