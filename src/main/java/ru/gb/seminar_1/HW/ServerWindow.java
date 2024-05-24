package ru.gb.seminar_1.HW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final String LOG_FILE = "log.txt";


    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;

    private final JPanel btnPanel = new JPanel(new GridLayout(1, 2));
    private final JPanel userPanel = new JPanel();

    private DefaultListModel<String> userListModel;
    private JList<String> userList;

    private List<ClientGUI> clients;

    private BufferedWriter logWriter;

    public ServerWindow() {
        isServerWorking = false;
        clients = new ArrayList<>();

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
                    loadChatHistory();
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
                    userListModel.clear();
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

        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userPanel.add(new JScrollPane(userList), BorderLayout.CENTER);

        add(scrollPane, BorderLayout.NORTH);
        add(userPanel, BorderLayout.EAST);
        add(btnPanel, BorderLayout.SOUTH);

        try {
            logWriter = new BufferedWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public boolean getServerStatus() {
        return isServerWorking;
    }

    public void addUser(String user) {
        userListModel.addElement(user);
    }

    public boolean registerClient(ClientGUI clientGUI) {
        if (!clients.contains(clientGUI)) {
            clients.add(clientGUI);
            return true;
        }
        return false;
    }

    private void loadChatHistory() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMsg(String msg) {
        if (isServerWorking) {
            log.append(msg);
            for (ClientGUI client: clients) {
                client.receiveMsg(msg);
            }
            try {
                logWriter.write(msg);
                logWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRegistered(ClientGUI clientGUI) {
        for (ClientGUI client: clients) {
            if (clientGUI.equals(client)) return true;
        }
        return false;
    }
}
