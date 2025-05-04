package org.example;

import java.util.HashMap;
import java.util.Map;

public class ConcreteChatMediator implements ChatMediator {
    private final Map<String, ChatClient> clients = new HashMap<>();

    @Override
    public void sendMessage(String message, String sender, String receiver) {
        ChatClient receiverClient = clients.get(receiver);
        ChatClient senderClient = clients.get(sender);

        if (receiverClient != null) {
            receiverClient.receiveMessage(sender + " to " + receiver + ": " + message);
        }
        if (senderClient != null) {
            senderClient.receiveMessage(sender + " to " + receiver + ": " + message);
        }
    }

    @Override
    public void registerClient(ChatClient client) {
        clients.put(client.getUsername(), client);
    }
}
