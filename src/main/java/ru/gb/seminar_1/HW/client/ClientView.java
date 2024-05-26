package ru.gb.seminar_1.HW.client;

public interface ClientView {
    void showMessage(String msg);
    void disconnectedFromServer();
    void connectToServer();
}
