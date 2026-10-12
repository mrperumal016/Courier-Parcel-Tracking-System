import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;

public class SignupFrame extends JFrame {

    private JTextField usernameField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    private TimedPasswordField passwordField;
    private TimedPasswordField confirmPasswordField;

    private JCheckBox showPassword;

    private final Color NAVY = new Color(8, 48, 88);
    private final Color BLUE = new Color(18, 137, 235);
    private final Color LIGHT = new Color(246, 249, 253);
    private final Color TEXT = new Color(12, 42, 76);
    private final Color MUTED = new Color(125, 140, 160);

    public SignupFrame() {

        setTitle("Courier & Parcel Tracking System - Create Account");
        setSize(1100, 730);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel main = new JPanel(new GridLayout(1, 2));

        main.add(new LeftPanel());
        main.add(new SignupPanel());

        setContentPane(main);
    }

    // =====================================================
    // LEFT PANEL
    // =====================================================

    class LeftPanel extends JPanel {

        public LeftPanel() {
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(NAVY);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new Color(8, 73, 130));

            g2.fillOval(-180, -180, 450, 350);
            g2.fillOval(360, -170, 300, 300);

            // Decorative dots
            g2.setColor(new Color(25, 105, 175));

            for (int x = 70; x < 420; x += 12) {
                for (int y = 100; y < 250; y += 12) {

                    if ((x + y) % 36 == 0) {
                        g2.fillOval(x, y, 3, 3);
                    }
                }
            }

            // Bottom waves
            g2.setColor(new Color(7, 61, 110));
            g2.fillOval(-100, 580, 600, 230);

            g2.setColor(new Color(10, 72, 125));
            g2.fillOval(180, 610, 600, 190);

            g2.dispose();
        }

        @Override
        public void addNotify() {

            super.addNotify();

            JLabel box =
                    new JLabel("▣", SwingConstants.CENTER);

            box.setFont(
                    new Font(
                            "Segoe UI Symbol",
                            Font.BOLD,
                            75
                    )
            );

            box.setForeground(
                    new Color(245, 190, 110)
            );

            box.setBounds(150, 95, 250, 80);

            add(box);

            JLabel logo =
                    new JLabel(
                            "<html><center>" +
                            "<span style='color:white'>Logi</span>" +
                            "<span style='color:#168BEF'>Track</span>" +
                            "</center></html>",
                            SwingConstants.CENTER
                    );

            logo.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            50
                    )
            );

            logo.setBounds(45, 230, 460, 70);

            add(logo);

            JPanel line = new JPanel();

            line.setBackground(BLUE);
            line.setBounds(205, 315, 90, 3);

            add(line);

            JLabel description =
                    new JLabel(
                            "<html><center>" +
                            "Smart Courier & Parcel<br>" +
                            "Management System" +
                            "</center></html>",
                            SwingConstants.CENTER
                    );

            description.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            21
                    )
            );

            description.setForeground(
                    new Color(225, 238, 250)
            );

            description.setBounds(
                    50, 335, 450, 65
            );

            add(description);

            JLabel bottom =
                    new JLabel(
                            "C O N N E C T I N G   P E O P L E   &   P L A C E S",
                            SwingConstants.CENTER
                    );

            bottom.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            11
                    )
            );

            bottom.setForeground(
                    new Color(155, 195, 230)
            );

            bottom.setBounds(
                    35, 665, 470, 25
            );

            add(bottom);
        }
    }

    // =====================================================
    // SIGNUP PANEL
    // =====================================================

    class SignupPanel extends JPanel {

        public SignupPanel() {

            setBackground(LIGHT);
            setLayout(null);

            JLabel title =
                    new JLabel("Create Account");

            title.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            38
                    )
            );

            title.setForeground(TEXT);

            title.setBounds(
                    70, 45, 400, 55
            );

            add(title);

            JLabel subtitle =
                    new JLabel(
                            "Create your LogiTrack account"
                    );

            subtitle.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            18
                    )
            );

            subtitle.setForeground(MUTED);

            subtitle.setBounds(
                    72, 95, 400, 30
            );

            add(subtitle);

            // Username

            addLabel("USERNAME", 70, 135);

            usernameField =
                    createTextField();

            usernameField.setBounds(
                    70, 162, 430, 45
            );

            add(usernameField);

            // Full Name

            addLabel("FULL NAME", 70, 220);

            nameField =
                    createTextField();

            nameField.setBounds(
                    70, 247, 430, 45
            );

            add(nameField);

            // Email

            addLabel("EMAIL", 70, 305);

            emailField =
                    createTextField();

            emailField.setBounds(
                    70, 332, 430, 45
            );

            add(emailField);

            // Phone

            addLabel("PHONE", 70, 390);

            phoneField =
                    createTextField();

            phoneField.setBounds(
                    70, 417, 205, 45
            );

            // Phone number: digits only, maximum 10 digits
            ((AbstractDocument) phoneField.getDocument())
                    .setDocumentFilter(new DocumentFilter() {

                        @Override
                        public void replace(
                                FilterBypass fb,
                                int offset,
                                int length,
                                String text,
                                AttributeSet attrs)
                                throws BadLocationException {

                            String current =
                                    fb.getDocument().getText(
                                            0,
                                            fb.getDocument().getLength()
                                    );

                            String updated =
                                    current.substring(0, offset)
                                    + (text == null ? "" : text)
                                    + current.substring(
                                            offset + length
                                    );

                            if (updated.matches("\\d{0,10}")) {

                                fb.replace(
                                        offset,
                                        length,
                                        text,
                                        attrs
                                );
                            }
                        }

                        @Override
                        public void insertString(
                                FilterBypass fb,
                                int offset,
                                String text,
                                AttributeSet attrs)
                                throws BadLocationException {

                            replace(
                                    fb,
                                    offset,
                                    0,
                                    text,
                                    attrs
                            );
                        }
                    });

            add(phoneField);

            // Password

            addLabel("PASSWORD", 290, 390);

            passwordField =
                    createPasswordField();

            passwordField.setBounds(
                    290, 417, 210, 45
            );

            add(passwordField);

            // Confirm password

            addLabel(
                    "CONFIRM PASSWORD",
                    70,
                    475
            );

            confirmPasswordField =
                    createPasswordField();

            confirmPasswordField.setBounds(
                    70, 502, 430, 45
            );

            add(confirmPasswordField);

            // Show password

            showPassword =
                    new JCheckBox(
                            "Show Password"
                    );

            showPassword.setBackground(LIGHT);

            showPassword.setForeground(
                    new Color(75, 90, 110)
            );

            showPassword.setFocusPainted(false);

            showPassword.setBounds(
                    70, 550, 150, 30
            );

            showPassword.addActionListener(
                    e -> {

                        if (showPassword.isSelected()) {

                            passwordField.showAllPassword(true);

                            confirmPasswordField
                                    .showAllPassword(true);

                        } else {

                            passwordField.showAllPassword(false);

                            confirmPasswordField
                                    .showAllPassword(false);
                        }
                    }
            );

            add(showPassword);

            // Create account

            JButton create =
                    new JButton(
                            "CREATE ACCOUNT   →"
                    );

            create.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            create.setForeground(Color.WHITE);
            create.setBackground(BLUE);

            create.setFocusPainted(false);
            create.setBorderPainted(false);

            create.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            create.setBounds(
                    70, 585, 430, 52
            );

            create.addActionListener(
                    e -> createAccount()
            );

            add(create);

            // Login link

            JLabel already =
                    new JLabel(
                            "Already have an account?"
                    );

            already.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14
                    )
            );

            already.setForeground(MUTED);

            already.setBounds(
                    120, 650, 180, 25
            );

            add(already);

            JButton login =
                    new JButton("Login");

            login.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            login.setForeground(BLUE);
            login.setBackground(LIGHT);

            login.setBorderPainted(false);
            login.setFocusPainted(false);

            login.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            login.setBounds(
                    300, 645, 100, 35
            );

            login.addActionListener(
                    e -> {

                        dispose();

                        LoginFrame frame =
                                new LoginFrame();

                        frame.setVisible(true);
                    }
            );

            add(login);
        }

        private void addLabel(
                String text,
                int x,
                int y
        ) {

            JLabel label =
                    new JLabel(text);

            label.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            13
                    )
            );

            label.setForeground(TEXT);

            label.setBounds(
                    x, y, 200, 25
            );

            add(label);
        }

        private JTextField createTextField() {

            JTextField field =
                    new JTextField();

            field.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            15
                    )
            );

            field.setBorder(
                    new CompoundBorder(
                            new LineBorder(
                                    new Color(
                                            195,
                                            210,
                                            225
                                    ),
                                    1,
                                    true
                            ),
                            new EmptyBorder(
                                    5,
                                    12,
                                    5,
                                    12
                            )
                    )
            );

            return field;
        }

        private TimedPasswordField createPasswordField() {

            TimedPasswordField field =
                    new TimedPasswordField();

            field.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            15
                    )
            );

            field.setEchoChar('•');

            field.setBorder(
                    new CompoundBorder(
                            new LineBorder(
                                    new Color(
                                            195,
                                            210,
                                            225
                                    ),
                                    1,
                                    true
                            ),
                            new EmptyBorder(
                                    5,
                                    12,
                                    5,
                                    12
                            )
                    )
            );

            return field;
        }
    }

    // =====================================================
    // CREATE ACCOUNT
    // =====================================================

    private void createAccount() {

        String username =
                usernameField.getText().trim();

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String confirm =
                new String(
                        confirmPasswordField.getPassword()
                );

        // Required fields

        if (username.isEmpty()
                || name.isEmpty()
                || email.isEmpty()
                || phone.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields.",
                    "Required Fields",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Phone validation

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "Invalid Phone Number",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Password and confirm password

        if (!password.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Password Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =================================================
        // PASSWORD MINIMUM 8 CHARACTERS
        // =================================================

        if (password.length() < 8) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 8 characters.",
                    "Weak Password",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Username and email checking

        String checkSql =
                "SELECT COUNT(*) FROM users " +
                "WHERE username = ? OR email = ?";

        try (
                Connection checkCon =
                        DatabaseConnection.getConnection();

                PreparedStatement checkPs =
                        checkCon.prepareStatement(checkSql)
        ) {

            checkPs.setString(
                    1,
                    username
            );

            checkPs.setString(
                    2,
                    email
            );

            ResultSet checkRs =
                    checkPs.executeQuery();

            if (checkRs.next()
                    && checkRs.getInt(1) > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username or email already exists. Please use a different one.",
                        "Account Already Exists",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to verify account details.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Insert account

        String sql =
                "INSERT INTO users " +
                "(username, full_name, email, phone, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    name
            );

            ps.setString(
                    3,
                    email
            );

            ps.setString(
                    4,
                    phone
            );

            ps.setString(
                    5,
                    hashPassword(password)
            );

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            LoginFrame login =
                    new LoginFrame();

            login.setVisible(true);

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username or email already exists.",
                        "Account Exists",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to create account.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // =====================================================
    // PASSWORD HASH
    // =====================================================

    private String hashPassword(
            String password
    ) {

        try {

            java.security.MessageDigest md =
                    java.security.MessageDigest
                            .getInstance("SHA-256");

            byte[] hash =
                    md.digest(
                            password.getBytes()
                    );

            StringBuilder sb =
                    new StringBuilder();

            for (byte b : hash) {

                sb.append(
                        String.format(
                                "%02x",
                                b
                        )
                );
            }

            return sb.toString();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            SignupFrame frame =
                    new SignupFrame();

            frame.setVisible(true);
        });
    }
}