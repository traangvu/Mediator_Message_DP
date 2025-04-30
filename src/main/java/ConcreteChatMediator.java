import java.util.HashMap;
import java.util.Map;

public class ConcreteChatMediator implements ChatMediator {
    private Map<String, ChatClient> clients = new HashMap<>();

    @Override
    public void sendMessage(String message, String sender, String receiver) {
        ChatClient recipient = clients.get(receiver);
        if (recipient != null) {
            recipient.receiveMessage(sender + ": " + message);
        }
    }

    @Override
    public void registerClient(ChatClient client) {
        clients.put(client.getUsername(), client);
    }
}
