package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class ChatApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/chat_client.fxml"));
            Parent root = loader.load();

            ChatClientController controller = loader.getController();
            controller.setMediator(new ConcreteChatMediator());
            controller.initializeClients(Arrays.asList("Alice", "Bob", "Charlie"));

            primaryStage.setTitle("Mediator Chat");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
