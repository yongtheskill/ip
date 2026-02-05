import components.DialogBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import commands.Command;
import tasks.TaskList;
import storage.Storage;
import exceptions.SixException;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TaskList tasks;
    private Image userImage;
    private Image sixImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userImage = loadImage("/images/user_avatar.png");
        sixImage = loadImage("/images/bot_avatar.png");

        tasks = Storage.load();

        String welcomeMessage = "Hello! I'm Six\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getSixDialog(welcomeMessage, sixImage));
    }

    private Image loadImage(String path) {
        try {
            var stream = this.getClass().getResourceAsStream(path);
            if (stream != null) {
                return new Image(stream);
            }
        } catch (Exception e) {
        }
        return null;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (input.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        try {
            Command command = Parser.parse(input);
            String response = getCommandResponse(command);

            dialogContainer.getChildren().add(
                    DialogBox.getSixDialog(response, sixImage));

            if (command.isBye()) {
                Platform.exit();
            }

        } catch (SixException e) {
            String errorMessage = "OOPS!!! " + e.getMessage();
            dialogContainer.getChildren().add(
                    DialogBox.getSixDialog(errorMessage, sixImage));
        }
    }

    private String getCommandResponse(Command command) throws SixException {
        GuiUi guiUi = new GuiUi();
        command.execute(tasks, guiUi);
        return guiUi.getLastMessage();
    }

    private static class GuiUi extends ui.Ui {
        private String lastMessage = "";

        @Override
        public void showMessage(String message) {
            this.lastMessage = message;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        @Override
        public String readCommand() {
            return "";
        }

        @Override
        public void showWelcome() {
        }

        @Override
        public void showError(String message) {
            this.lastMessage = message;
        }
    }
}
