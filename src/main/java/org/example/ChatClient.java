package org.example;

public interface ChatClient {
    void receiveMessage(String message);
    String getUsername();
}
