import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Scanner;
import java.sql.*;

public class App extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JTextArea passwordListArea;
    private HashMap<String, String> users; // For simplicity, storing passwords in memory

    public App() {
        super("Password Manager");

        // Initialize GUI components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        passwordListArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(passwordListArea);

        // Initialize user data
        users = new HashMap<>();
        users.put("user1", "password1");
        users.put("user2", "password2");

        // Layout
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(loginPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Set up action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
<<<<<<< HEAD
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "pass");
=======
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "//YOURPASSSORD");
>>>>>>> c489c694d0677e3a5397082538cd985169d432ef
           if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Connection to MySql established.");

                ResultSet tables = meta.getTables(null, null, "versions", null);
                if (tables.next()) {
                    // Database exists
                    try (PreparedStatement stmt = conn.prepareStatement("SELECT MAX(version) as version FROM versions")) {
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) System.out.println("Database found. Version: " + rs.getInt("version"));
                    }
                } else {
                    System.out.println("Database not found");
                }
              user = User.login(conn, username, password);

                if (user == null) throw new RuntimeException("Error while logging in.");
                System.out.println("User " + user.getUsername() + " is now logged in.");
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Menu menu = new Menu(conn, input, user);
                // menu.menu();
            } 
        }
        catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Login not successful! try again");
        }
    }

    private void register() {
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());

      User user;
      
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "pass");
         if (conn != null) {
              DatabaseMetaData meta = conn.getMetaData();
              System.out.println("Connection to MySql established.");

              ResultSet tables = meta.getTables(null, null, "versions", null);
              if (tables.next()) {
                  // Database exists
                  try (PreparedStatement stmt = conn.prepareStatement("SELECT MAX(version) as version FROM versions")) {
                      ResultSet rs = stmt.executeQuery();
                      if (rs.next()) System.out.println("Database found. Version: " + rs.getInt("version"));
                  }
              } else {
                  System.out.println("Database not found");
              }
            user = User.registerNewUser(conn, username, password);

              if (user == null) {
                JOptionPane.showMessageDialog(this, "this username already exists! try again");
                throw new RuntimeException("Error while logging in.");
              }
              else{
                JOptionPane.showMessageDialog(this, "Registeration successful!");
              }
              // Menu menu = new Menu(conn, input, user);
              // menu.menu();
          } 
      }
      catch(Exception e){
          System.out.println(e);
      }
      
    }

    private void displayPasswords(String username) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your saved passwords:\n");

        // Here you can fetch and display passwords associated with the username
        // For simplicity, I'm just displaying dummy data
        stringBuilder.append("Facebook: mypassword123\n");
        stringBuilder.append("Twitter: mypassword456\n");
        stringBuilder.append("Gmail: mypassword789\n");

        passwordListArea.setText(stringBuilder.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}