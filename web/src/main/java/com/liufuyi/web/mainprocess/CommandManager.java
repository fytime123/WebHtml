package com.liufuyi.web.mainprocess;

import android.os.RemoteException;

import com.liufuyi.base.GsonUtils;
import com.liufuyi.web.Command;
import com.liufuyi.web.CommandWebToMain;
import com.liufuyi.web.JsParam;
import com.liufuyi.web.MainToJsCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class CommandManager extends CommandWebToMain.Stub {

    private volatile static CommandManager instance;

    private Map<String, Command> mapper = new HashMap<>();


    public static CommandManager getInstance() {
        if (instance == null) {
            synchronized (CommandManager.class) {
                if (instance == null)
                    instance = new CommandManager();
            }
        }

        return instance;
    }

    public CommandManager() {
        ServiceLoader<Command> commands = ServiceLoader.load(Command.class);
        for (Command command : commands) {
            mapper.put(command.getName(), command);
        }
    }


    @Override
    public void handleWebCommand(String jsonParams, MainToJsCallback callback) {

        JsParam request = GsonUtils.fromJson(jsonParams, JsParam.class);
        if (request != null) {
            Command command = mapper.get(request.getName());
            if (command != null) command.execute(jsonParams, callback);
        }
    }
}
