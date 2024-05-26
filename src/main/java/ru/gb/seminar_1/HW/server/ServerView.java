package ru.gb.seminar_1.HW.server;

public interface ServerView {
    void showOnLogs(String msg);
    void connectUser(String name);
    void disconnectUser(String name);
}
