package ru.gb.seminar_1.HW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    private final JTextArea chatArea = new JTextArea();

    private final JPanel topPanel = new JPanel(new GridLayout(2, 3));
    private final JButton btnLogin = new JButton("Login");
    private final JTextField ipField = new JTextField("IP");
    private final JTextField portField = new JTextField("Port");
    private final JTextField loginField = new JTextField("Guest");
    private final JPasswordField passwdField = new JPasswordField();

    private final JPanel bottomPanel = new JPanel(new BorderLayout());
    private final JTextField msgField = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public ClientGUI(ServerWindow serverWindow) {
        setLocationRelativeTo(serverWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat client");

        topPanel.add(ipField);
        topPanel.add(portField);
        topPanel.add(loginField);
        topPanel.add(passwdField);
        topPanel.add(btnLogin);
        add(topPanel, BorderLayout.NORTH);

        bottomPanel.add(msgField, BorderLayout.CENTER);
        bottomPanel.add(btnSend, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        chatArea.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(chatArea);
        add(scrollLog);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String login = loginField.getText();
                String password = new String(passwdField.getPassword());
                String ip = ipField.getText();
                String port = portField.getText();

                chatArea.append("Attempting to connect to " + ip + ":" + port + " as "
                + login + "\n");

                if (!serverWindow.getServerStatus()) {
                    chatArea.append("Attempt failed...\n");
                } else {
                    if (serverWindow.registerClient(getClient())) {
                        serverWindow.addUser(login);
                        chatArea.append("Connected successfully...\n");
                    } else {
                        chatArea.append("Already connected...\n");
                    }
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String msg = msgField.getText();
                if(!msg.isEmpty()) {
                    String chatMsg = loginField.getText() + ": " + msg + "\n";
                    if (serverWindow.isRegistered(getClient())) {
                        serverWindow.broadcastMsg(chatMsg);
                    } else {
                        chatArea.append(chatMsg);
                    }
                    msgField.setText("");
                }
            }
        });

        setVisible(true);
    }

    public void receiveMsg(String msg) {
        chatArea.append(msg);
    }

    private ClientGUI getClient() {
        return this;
    }
}
