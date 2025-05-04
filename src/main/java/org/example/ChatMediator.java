package org.example;

public interface ChatMediator {
    void sendMessage(String message, String sender, String receiver);
    void registerClient(ChatClient client);
}
