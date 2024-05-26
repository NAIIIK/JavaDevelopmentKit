package ru.gb.seminar_1.HW;

import ru.gb.seminar_1.HW.client.ClientController;
import ru.gb.seminar_1.HW.client.ClientGUI;
import ru.gb.seminar_1.HW.server.ServerController;
import ru.gb.seminar_1.HW.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerController server = new ServerController();
        ServerWindow serverWindow = new ServerWindow(server);
        server.setServerView(serverWindow);

        ClientController clientController1 = new ClientController(server);
        ClientGUI clientGUI1 = new ClientGUI(clientController1);
        clientController1.setClientView(clientGUI1);

        ClientController clientController2 = new ClientController(server);
        ClientGUI clientGUI2 = new ClientGUI(clientController2);
        clientController2.setClientView(clientGUI2);
    }
}
