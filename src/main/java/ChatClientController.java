import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatClientController implements ChatClient {

    private String username;
    private ChatMediator mediator;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private TextField recipientField;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMediator(ChatMediator mediator) {
        this.mediator = mediator;
        mediator.registerClient(this);
    }

    @Override
    public void receiveMessage(String message) {
        Platform.runLater(() -> chatArea.appendText(message + "\n"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @FXML
    public void handleSendMessage(ActionEvent actionEvent) {
        String message = messageField.getText().trim();
        String recipient = recipientField.getText().trim();

        if (!message.isEmpty() && !recipient.isEmpty()) {
            mediator.sendMessage(message, username, recipient);
            chatArea.appendText("Me to " + recipient + ": " + message + "\n");
            messageField.clear();
        } else {
            chatArea.appendText("⚠ Please enter both message and recipient.\n");
        }
    }
}
