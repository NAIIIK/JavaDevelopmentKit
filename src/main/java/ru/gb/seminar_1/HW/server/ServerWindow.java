package ru.gb.seminar_1.HW.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final String TITLE = "Chat server";

    private ServerController server;

    private JButton btnStart, btnStop;

    private JTextArea log;
    private JPanel btnPanel, userPanel;

    private JList<String> userList;
    private DefaultListModel<String> userListModel;

    public ServerWindow(ServerController server) {

        this.server = server;

        setting();
        createPanel();

        setVisible(true);
    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle(TITLE);
        setResizable(false);
        setAlwaysOnTop(true);
    }

    private void createPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(createLogArea());
        topPanel.add(createUserPanel());
        add(createBtnPanel(), BorderLayout.SOUTH);
        add(topPanel);
    }

    private Component createLogArea() {
        JPanel logPanel = new JPanel();
        JLabel logLabel = new JLabel("Logs");
        logLabel.setHorizontalAlignment(JLabel.CENTER);
        log = new JTextArea();
        log.setEditable(false);
        logPanel.setLayout(new BorderLayout());
        logPanel.add(logLabel, BorderLayout.NORTH);
        logPanel.add(new JScrollPane(log));
        return logPanel;
    }

    private Component createUserPanel() {
        userPanel = new JPanel();
        JLabel userListLabel = new JLabel("Users");
        userListLabel.setHorizontalAlignment(JLabel.CENTER);
        userPanel.setLayout(new BorderLayout());

        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);

        userPanel.add(userListLabel, BorderLayout.NORTH);
        userPanel.add(new JScrollPane(userList), BorderLayout.CENTER);
        return userPanel;
    }

    private Component createBtnPanel() {
        btnPanel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                server.start();
            }
        });
        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                server.stop();
                userListModel.clear();
            }
        });
        btnPanel.setLayout(new GridLayout(1, 2));
        btnPanel.add(btnStart);
        btnPanel.add(btnStop);
        return btnPanel;
    }

    @Override
    public void showOnLogs(String msg) {
        log.append(msg);
    }

    @Override
    public void connectUser(String name) {
        userListModel.addElement(name);
    }

    @Override
    public void disconnectUser(String name) {
        userListModel.removeElement(name);
    }
}
