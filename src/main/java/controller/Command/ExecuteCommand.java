package controller.Command;

import controller.Server;

public interface ExecuteCommand {
    void execute(String[] tokens) throws Exception;

    default void done(String content) {
        Server.getServer().send(content);
    }
}
