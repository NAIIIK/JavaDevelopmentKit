package ru.gb.seminar_1.HW.client;

import ru.gb.seminar_1.HW.server.ServerController;

public class ClientController {
    private ClientView clientView;
    private ServerController server;
    private boolean connected;
    private String name;


    public ClientController(ServerController server) {
        this.server = server;
        connected = false;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)) {
            showOnWindow("Connected successfully...\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Failed to connect...\n");
            return false;
        }
    }

    public void disconnectFromServer() {
        server.disconnectUser(this);
        connected = false;
        clientView.showMessage("You were disconnected from the server...\n");
    }

    public void answerFromServer(String message) {
        showOnWindow(message);
    }

    public void message(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.message(name + ": " + message);
            }
        } else {
            showOnWindow("No connection...\n");
        }
    }

    private void showOnWindow(String text) {
        clientView.showMessage(text);
    }

    public String getName() {
        return name;
    }

}
