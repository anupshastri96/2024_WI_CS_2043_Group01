

public class PasswordManagerGUI extends JFrame implements ActionListener {
    private JTextField usernameField, emailField, passwordField;
    private JButton generateButton, saveButton, clearButton;

    public PasswordManagerGUI() {
        setTitle("Password Manager");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel);
        usernameField = new JTextField();
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        add(emailLabel);
        emailField = new JTextField();
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JTextField();
        add(passwordField);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        add(generateButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        add(clearButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
        } else if (e.getSource() == saveButton) {
        } else if (e.getSource() == clearButton) {
            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordManagerGUI());
    }
}
