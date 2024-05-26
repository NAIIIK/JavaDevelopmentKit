package ru.gb.seminar_1.HW.server;

import ru.gb.seminar_1.HW.client.ClientController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private ServerView serverView;
    private static final String LOG_FILE = "src/main/java/ru/gb/seminar_1/HW/log.txt";

    private List<ClientController> clients;

    private BufferedWriter logWriter;

    private boolean isOnline;

    public ServerController() {
        this.clients = new ArrayList<>();
    }

    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }

    public boolean connectUser(ClientController clientController) {
        if (!clients.contains(clientController)) {
            clients.add(clientController);
            serverView.connectUser(clientController.getName());
            return true;
        }
        return false;
    }

    public void disconnectUser(ClientController clientController) {
        serverView.disconnectUser(clientController.getName());
        clients.remove(clientController);
    }

    public void message(String msg) {
        if (isOnline) {
            if (!msg.isEmpty()) {
                broadcastMessage(msg);
            }
        } else {
            serverView.showOnLogs("Server is offline...");
        }
    }

    public void broadcastMessage(String msg) {
        serverView.showOnLogs(msg);
        for (ClientController client: clients) {
            client.message(msg);
        }
        try {
            logWriter.write(msg);
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if(!isOnline) {
            isOnline = true;
            serverView.showOnLogs("Server started...\n");
            getHistory();
        } else {
            serverView.showOnLogs("Server is already running...\n");
        }
    }

    public void stop() {
        if (isOnline) {
            isOnline = false;
            serverView.showOnLogs("Server stopped...\n");
        } else {
            serverView.showOnLogs("Server is not running...\n");
        }
    }

    public boolean saveHistory() {
        try {
            logWriter = new BufferedWriter(new FileWriter(LOG_FILE, true));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line)
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
