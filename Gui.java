import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PasswordManagementApp extends Application {

    private Stage primaryStage;
    private TextField usernameField;
    private PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Password Management");

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 0);

        usernameField = new TextField();
        grid.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 1);

        passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> login());
        grid.add(loginButton, 0, 2);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> logout());
        logoutButton.setDisable(true);
        grid.add(logoutButton, 1, 2);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void login() {
        // Implement your login logic here
        // For simplicity, let's just check if the fields are not empty
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            System.out.println("Login successful");
            primaryStage.setTitle("Password Management - Logged In");
            // Enable logout button
            ((Button) primaryStage.getScene().getRoot().getChildrenUnmodifiable().get(2)).setDisable(false);
        } else {
            System.out.println("Login failed. Please enter username and password.");
        }
    }

    private void logout() {
        System.out.println("Logout successful");
        primaryStage.setTitle("Password Management");
        // Disable logout button
        ((Button) primaryStage.getScene().getRoot().getChildrenUnmodifiable().get(2)).setDisable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}