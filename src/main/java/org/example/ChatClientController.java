package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.*;

public class ChatClientController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Label usernameLabel;

    @FXML
    private ComboBox<String> senderComboBox;

    @FXML
    private HBox recipientButtonsBox;

    private ChatMediator mediator;

    private final Map<String, ChatClient> clientMap = new HashMap<>();

    private String selectedSender;
    private String selectedRecipient;

    public void setMediator(ChatMediator mediator) {
        this.mediator = mediator;
    }

    public void initializeClients(List<String> usernames) {
        for (String username : usernames) {
            ChatClientImpl client = new ChatClientImpl(username);
            clientMap.put(username, client);
            mediator.registerClient(client);
        }

        Platform.runLater(() -> {
            senderComboBox.getItems().addAll(usernames);
            senderComboBox.getSelectionModel().selectFirst();
            handleSenderChange();
        });
    }

    @FXML
    public void handleSenderChange() {
        selectedSender = senderComboBox.getValue();
        usernameLabel.setText("Logged in as: " + selectedSender);

        recipientButtonsBox.getChildren().clear();
        for (String username : clientMap.keySet()) {
            if (!username.equals(selectedSender)) {
                Button btn = new Button(username);
                btn.setOnAction(e -> selectedRecipient = username);
                recipientButtonsBox.getChildren().add(btn);
            }
        }

        selectedRecipient = null;
    }

    @FXML
    public void handleSendMessage() {
        String message = messageField.getText().trim();

        if (selectedSender == null || selectedRecipient == null || message.isEmpty()) {
            chatArea.appendText("⚠ Please select sender, recipient, and enter a message.\n");
            return;
        }

        mediator.sendMessage(message, selectedSender, selectedRecipient);
        messageField.clear();
    }

    private class ChatClientImpl implements ChatClient {
        private final String username;

        public ChatClientImpl(String username) {
            this.username = username;
        }

        @Override
        public void receiveMessage(String message) {
            Platform.runLater(() -> {
                if (username.equals(selectedSender)) {
                    chatArea.appendText(message + "\n");
                }
            });
        }

        @Override
        public String getUsername() {
            return username;
        }
    }
}
