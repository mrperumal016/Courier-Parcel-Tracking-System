import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;

public class ResetPasswordFrame extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;

    private TimedPasswordField passwordField;
    private TimedPasswordField confirmPasswordField;

    private JCheckBox showPassword;

    private final Color NAVY = new Color(8, 48, 88);
    private final Color BLUE = new Color(18, 137, 235);
    private final Color LIGHT = new Color(246, 249, 253);
    private final Color TEXT = new Color(12, 42, 76);
    private final Color MUTED = new Color(125, 140, 160);

    public ResetPasswordFrame() {

        setTitle(
                "Courier & Parcel Tracking System - Reset Password"
        );

        setSize(1100, 730);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(false);

        JPanel main =
                new JPanel(
                        new GridLayout(1, 2)
                );

        main.add(new LeftPanel());
        main.add(new ResetPanel());

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

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(NAVY);

            g2.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );

            g2.setColor(
                    new Color(8, 73, 130)
            );

            g2.fillOval(
                    -180,
                    -180,
                    450,
                    350
            );

            g2.fillOval(
                    360,
                    -170,
                    300,
                    300
            );

            g2.setColor(
                    new Color(7, 61, 110)
            );

            g2.fillOval(
                    -100,
                    580,
                    600,
                    230
            );

            g2.setColor(
                    new Color(10, 72, 125)
            );

            g2.fillOval(
                    180,
                    610,
                    600,
                    190
            );

            g2.dispose();
        }

        @Override
        public void addNotify() {

            super.addNotify();

            JLabel box =
                    new JLabel(
                            "▣",
                            SwingConstants.CENTER
                    );

            box.setFont(
                    new Font(
                            "Segoe UI Symbol",
                            Font.BOLD,
                            75
                    )
            );

            box.setForeground(
                    new Color(
                            245,
                            190,
                            110
                    )
            );

            box.setBounds(
                    150,
                    95,
                    250,
                    80
            );

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

            logo.setBounds(
                    45,
                    230,
                    460,
                    70
            );

            add(logo);

            JPanel line =
                    new JPanel();

            line.setBackground(BLUE);

            line.setBounds(
                    205,
                    315,
                    90,
                    3
            );

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
                    new Color(
                            225,
                            238,
                            250
                    )
            );

            description.setBounds(
                    50,
                    335,
                    450,
                    65
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
                    new Color(
                            155,
                            195,
                            230
                    )
            );

            bottom.setBounds(
                    35,
                    665,
                    470,
                    25
            );

            add(bottom);
        }
    }

    // =====================================================
    // RESET PANEL
    // =====================================================

    class ResetPanel extends JPanel {

        public ResetPanel() {

            setBackground(LIGHT);
            setLayout(null);

            JLabel title =
                    new JLabel(
                            "Reset Password"
                    );

            title.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            38
                    )
            );

            title.setForeground(TEXT);

            title.setBounds(
                    75,
                    80,
                    400,
                    55
            );

            add(title);

            JLabel subtitle =
                    new JLabel(
                            "Create a new password for your account"
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
                    77,
                    130,
                    430,
                    30
            );

            add(subtitle);

            // Username

            addLabel(
                    "USERNAME",
                    75,
                    190
            );

            usernameField =
                    createTextField();

            usernameField.setBounds(
                    75,
                    217,
                    430,
                    50
            );

            add(usernameField);

            // Email

            addLabel(
                    "REGISTERED EMAIL",
                    75,
                    285
            );

            emailField =
                    createTextField();

            emailField.setBounds(
                    75,
                    312,
                    430,
                    50
            );

            add(emailField);

            // New Password

            addLabel(
                    "NEW PASSWORD",
                    75,
                    380
            );

            passwordField =
                    createPasswordField();

            passwordField.setBounds(
                    75,
                    407,
                    430,
                    50
            );

            add(passwordField);

            // Confirm Password

            addLabel(
                    "CONFIRM PASSWORD",
                    75,
                    475
            );

            confirmPasswordField =
                    createPasswordField();

            confirmPasswordField.setBounds(
                    75,
                    502,
                    430,
                    50
            );

            add(confirmPasswordField);

            // Show password

            showPassword =
                    new JCheckBox(
                            "Show Password"
                    );

            showPassword.setBackground(LIGHT);

            showPassword.setForeground(
                    new Color(
                            75,
                            90,
                            110
                    )
            );

            showPassword.setFocusPainted(false);

            showPassword.setBounds(
                    75,
                    560,
                    150,
                    30
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

            // Reset button

            JButton reset =
                    new JButton(
                            "RESET PASSWORD   →"
                    );

            reset.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            reset.setForeground(Color.WHITE);
            reset.setBackground(BLUE);

            reset.setFocusPainted(false);
            reset.setBorderPainted(false);

            reset.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            reset.setBounds(
                    75,
                    600,
                    430,
                    52
            );

            reset.addActionListener(
                    e -> resetPassword()
            );

            add(reset);

            // Back to login

            JButton back =
                    new JButton(
                            "← Back to Login"
                    );

            back.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            back.setForeground(BLUE);
            back.setBackground(LIGHT);

            back.setBorderPainted(false);
            back.setFocusPainted(false);

            back.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            back.setBounds(
                    205,
                    660,
                    170,
                    30
            );

            back.addActionListener(
                    e -> {

                        dispose();

                        LoginFrame login =
                                new LoginFrame();

                        login.setVisible(true);
                    }
            );

            add(back);
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
                    x,
                    y,
                    220,
                    25
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
    // RESET PASSWORD
    // =====================================================

    private void resetPassword() {

        String username =
                usernameField.getText().trim();

        String email =
                emailField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String confirm =
                new String(
                        confirmPasswordField
                                .getPassword()
                );

        // Required fields

        if (username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Required Fields",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Confirm password

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

        // Update password

        String sql =
                "UPDATE users SET password = ? " +
                "WHERE username = ? AND email = ?";

        try (
                Connection con =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    hashPassword(password)
            );

            ps.setString(
                    2,
                    username
            );

            ps.setString(
                    3,
                    email
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password reset successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                LoginFrame login =
                        new LoginFrame();

                login.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Username and email do not match.",
                        "Verification Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Database error.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
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

            ResetPasswordFrame frame =
                    new ResetPasswordFrame();

            frame.setVisible(true);
        });
    }
}