package ru.gb.seminar_1.HW.server;

import ru.gb.seminar_1.HW.client.ClientController;
import ru.gb.seminar_1.HW.repository.DataHandler;
import ru.gb.seminar_1.HW.repository.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerController {
    private ServerView serverView;

    private List<ClientController> clients;

    private DataHandler logHandler;

    private boolean isOnline;

    public ServerController() {
        this.clients = new ArrayList<>();
        logHandler = new FileHandler();
        isOnline = false;
    }

    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }

    public boolean connectUser(ClientController clientController) {
        if (isOnline) {
            if (!clients.contains(clientController)) {
                clients.add(clientController);
                serverView.connectUser(clientController.getName());
                serverView.showOnLogs(clientController.getName() + " connected...\n");
                return true;
            }
        }
        return false;
    }

    public void disconnectUser(ClientController clientController) {
        clients.remove(clientController);
        serverView.disconnectUser(clientController.getName());
        serverView.showOnLogs(clientController.getName() + " disconnected...\n");
    }

    public void message(String msg) {
        if (isOnline) {
            serverView.showOnLogs(msg + "\n");
            for (ClientController client: clients) {
                client.answerFromServer(msg + "\n");
            }
            saveHistory(msg);
        }
    }

    public void start() {
        if(!isOnline) {
            isOnline = true;
            serverView.showOnLogs("Server started...\n");
            serverView.showOnLogs(getHistory());
        } else {
            serverView.showOnLogs("Server is already running...\n");
        }
    }

    public void stop() {
        if (isOnline) {
            isOnline = false;
            for (ClientController client : clients) {
                client.disconnectFromServer();
            }
            serverView.showOnLogs("Server stopped...\n");
        } else {
            serverView.showOnLogs("Server is not running...\n");
        }
    }

    public void saveHistory(String msg) {
        logHandler.saveHistory(msg);
    }

    public String getHistory() {
        return logHandler.getHistory();
    }
}
