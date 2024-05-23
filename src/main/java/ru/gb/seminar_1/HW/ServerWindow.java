package ru.gb.seminar_1.HW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;

    private final JPanel btnPanel = new JPanel(new GridLayout(1, 2));
    private final JPanel userPanel = new JPanel();

    private JList userList;

    public ServerWindow() {
        isServerWorking = false;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat server");
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(new GridLayout(2, 1));

        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!isServerWorking) {
                    isServerWorking = true;
                    log.append("Server started...\n");
                } else {
                    log.append("Server is already running...\n");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isServerWorking) {
                    isServerWorking = false;
                    log.append("Server stopped...\n");
                } else {
                    log.append("Server is not running...\n");
                }
            }
        });

        btnPanel.add(btnStart);
        btnPanel.add(btnStop);

        userPanel.setLayout(new BorderLayout());

        JLabel userListLabel = new JLabel("Users");
        userListLabel.setHorizontalAlignment(JLabel.CENTER);
        userPanel.add(userListLabel, BorderLayout.NORTH);

        userList = new JList<>();
        userPanel.add(new JScrollPane(userList), BorderLayout.CENTER);

        String[] users = {"nikita", "sanya"};
        userList.setListData(users);

        add(scrollPane, BorderLayout.NORTH);
        add(userPanel, BorderLayout.EAST);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
