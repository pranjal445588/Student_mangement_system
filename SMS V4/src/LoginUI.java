import dao.UserDao;

import javax.swing.*;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JLabel statusLabel = new JLabel();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(statusLabel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();
            String role = UserDao.authenticate(username, password);
            if (role == null) {
                statusLabel.setText("Login failed!");
            } else if ("admin".equals(role)) {
                new AdminDashboard().setVisible(true);
                this.dispose();
            } else {
                new StudentDashboard(username).setVisible(true);
                this.dispose();
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}