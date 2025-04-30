import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApp extends Application {

    private final ConcreteChatMediator mediator = new ConcreteChatMediator();

    @Override
    public void start(Stage primaryStage) {
        launchClient("Alice");
        launchClient("Bob");
        launchClient("Charlie");
    }

    private void launchClient(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat_client.fxml"));
            Parent root = loader.load();

            ChatClientController controller = loader.getController();
            controller.setUsername(username);
            controller.setMediator(mediator);

            Stage stage = new Stage();
            stage.setTitle("Chat - " + username);
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
