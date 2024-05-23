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
    private final JTextField ipField = new JTextField("127.0.0.1");
    private final JTextField portField = new JTextField("8080");
    private final JTextField loginField = new JTextField();
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
                if (login.isEmpty()) login = "Guest";
                String password = new String(passwdField.getPassword());
                String ip = ipField.getText();
                String port = portField.getText();

                chatArea.append("Attempting to connect to " + ip + ":" + port + " as "
                + login + "\n");
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String msg = msgField.getText();
                if(!msg.isEmpty()) {
                    chatArea.append("Me: " + msg + "\n");
                    msgField.setText("");
                }
            }
        });

        setVisible(true);
    }
}
