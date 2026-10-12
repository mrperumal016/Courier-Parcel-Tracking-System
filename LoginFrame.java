import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private TimedPasswordField passwordField;
    private JCheckBox showPassword;

    private final Color NAVY = new Color(8, 48, 88);
    private final Color BLUE = new Color(18, 137, 235);
    private final Color LIGHT = new Color(246, 249, 253);
    private final Color TEXT = new Color(12, 42, 76);
    private final Color MUTED = new Color(125, 140, 160);

    public LoginFrame() {

        setTitle(
                "Courier & Parcel Tracking System - Login"
        );

        setSize(1100, 730);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        JPanel main =
                new JPanel(
                        new GridLayout(1, 2)
                );

        main.add(new LeftPanel());
        main.add(new LoginPanel());

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

            // Package icon

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
                    new Color(245, 190, 110)
            );

            box.setBounds(
                    150,
                    95,
                    250,
                    80
            );

            add(box);


            // Logo

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


            // Line

            JPanel line =
                    new JPanel();

            line.setBackground(BLUE);

            line.setBounds(
                    255,
                    315,
                    110,
                    4
            );

            add(line);


            // Description

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
                            25
                    )
            );

            description.setForeground(Color.WHITE);

            description.setBounds(
                    50,
                    350,
                    450,
                    80
            );

            add(description);


            // Bottom feature icons

            addFeature(
                    "🚚",
                    "Track",
                    "Real-Time",
                    125
            );

            addFeature(
                    "▣",
                    "Manage",
                    "Efficiently",
                    350
            );

            addFeature(
                    "◆",
                    "Deliver",
                    "Safely",
                    575
            );


            JLabel footer =
                    new JLabel(
                            "C O N N E C T I N G   P E O P L E   &   P L A C E S",
                            SwingConstants.CENTER
                    );

            footer.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            11
                    )
            );

            footer.setForeground(
                    new Color(180, 210, 235)
            );

            footer.setBounds(
                    50,
                    675,
                    450,
                    25
            );

            add(footer);
        }


        private void addFeature(
                String icon,
                String title,
                String subtitle,
                int x
        ) {

            JLabel iconLabel =
                    new JLabel(
                            icon,
                            SwingConstants.CENTER
                    );

            iconLabel.setFont(
                    new Font(
                            "Segoe UI Symbol",
                            Font.BOLD,
                            25
                    )
            );

            iconLabel.setForeground(
                    Color.WHITE
            );

            iconLabel.setBorder(
                    new LineBorder(
                            BLUE,
                            2,
                            true
                    )
            );

            iconLabel.setBounds(
                    x,
                    500,
                    75,
                    75
            );

            add(iconLabel);


            JLabel titleLabel =
                    new JLabel(
                            title,
                            SwingConstants.CENTER
                    );

            titleLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            titleLabel.setForeground(
                    Color.WHITE
            );

            titleLabel.setBounds(
                    x - 10,
                    580,
                    95,
                    25
            );

            add(titleLabel);


            JLabel subtitleLabel =
                    new JLabel(
                            subtitle,
                            SwingConstants.CENTER
                    );

            subtitleLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14
                    )
            );

            subtitleLabel.setForeground(
                    new Color(180, 210, 235)
            );

            subtitleLabel.setBounds(
                    x - 15,
                    605,
                    105,
                    25
            );

            add(subtitleLabel);
        }
    }


    // =====================================================
    // LOGIN PANEL
    // =====================================================

    class LoginPanel extends JPanel {

        public LoginPanel() {

            setBackground(LIGHT);

            setLayout(null);


            JLabel title =
                    new JLabel(
                            "Welcome Back!"
                    );

            title.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            40
                    )
            );

            title.setForeground(TEXT);

            title.setBounds(
                    75,
                    75,
                    450,
                    55
            );

            add(title);


            JLabel subtitle =
                    new JLabel(
                            "Sign in to your LogiTrack account"
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
                    78,
                    125,
                    450,
                    30
            );

            add(subtitle);


            // USERNAME

            JLabel usernameLabel =
                    new JLabel(
                            "USERNAME"
                    );

            usernameLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            13
                    )
            );

            usernameLabel.setForeground(TEXT);

            usernameLabel.setBounds(
                    75,
                    180,
                    150,
                    25
            );

            add(usernameLabel);


            usernameField =
                    createTextField();

            usernameField.setBounds(
                    75,
                    207,
                    430,
                    50
            );

            add(usernameField);


            // PASSWORD

            JLabel passwordLabel =
                    new JLabel(
                            "PASSWORD"
                    );

            passwordLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            13
                    )
            );

            passwordLabel.setForeground(TEXT);

            passwordLabel.setBounds(
                    75,
                    275,
                    150,
                    25
            );

            add(passwordLabel);


            passwordField =
                    new TimedPasswordField();

            passwordField.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            16
                    )
            );

            passwordField.setEchoChar('•');

            passwordField.setBorder(
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

            passwordField.setBounds(
                    75,
                    302,
                    430,
                    50
            );

            add(passwordField);


            // SHOW PASSWORD

            showPassword =
                    new JCheckBox(
                            "Show Password"
                    );

            showPassword.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14
                    )
            );

            showPassword.setBackground(LIGHT);

            showPassword.setForeground(
                    new Color(75, 90, 110)
            );

            showPassword.setFocusPainted(false);

            showPassword.setBounds(
                    75,
                    365,
                    160,
                    30
            );

            showPassword.addActionListener(
                    e ->
                            passwordField.showAllPassword(
                                    showPassword.isSelected()
                            )
            );

            add(showPassword);


            // FORGOT PASSWORD

            JButton forgot =
                    new JButton(
                            "Forgot Password?"
                    );

            forgot.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            forgot.setForeground(BLUE);

            forgot.setBackground(LIGHT);

            forgot.setBorderPainted(false);

            forgot.setFocusPainted(false);

            forgot.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            forgot.setBounds(
                    330,
                    365,
                    175,
                    30
            );

            forgot.addActionListener(
                    e -> {

                        dispose();

                        ResetPasswordFrame reset =
                                new ResetPasswordFrame();

                        reset.setVisible(true);
                    }
            );

            add(forgot);


            // LOGIN BUTTON

            JButton login =
                    new JButton(
                            "LOGIN   →"
                    );

            login.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            login.setForeground(Color.WHITE);

            login.setBackground(BLUE);

            login.setFocusPainted(false);

            login.setBorderPainted(false);

            login.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            login.setBounds(
                    75,
                    410,
                    430,
                    55
            );

            login.addActionListener(
                    e -> loginUser()
            );

            add(login);


            // OR

            JLabel or =
                    new JLabel(
                            "────────────  OR  ────────────",
                            SwingConstants.CENTER
                    );

            or.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            13
                    )
            );

            or.setForeground(
                    new Color(145, 155, 170)
            );

            or.setBounds(
                    75,
                    480,
                    430,
                    30
            );

            add(or);


            // CREATE ACCOUNT

            JPanel signupBox =
                    new JPanel(null);

            signupBox.setBackground(
                    new Color(238, 245, 252)
            );

            signupBox.setBorder(
                    new LineBorder(
                            new Color(225, 235, 245),
                            1,
                            true
                    )
            );

            signupBox.setBounds(
                    65,
                    525,
                    450,
                    75
            );

            add(signupBox);


            JLabel accountText =
                    new JLabel(
                            "Don't have an account?"
                    );

            accountText.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14
                    )
            );

            accountText.setForeground(
                    new Color(75, 90, 110)
            );

            accountText.setBounds(
                    20,
                    23,
                    180,
                    30
            );

            signupBox.add(accountText);


            JButton signup =
                    new JButton(
                            "Create New Account"
                    );

            signup.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            signup.setForeground(BLUE);

            signup.setBackground(Color.WHITE);

            signup.setBorder(
                    new LineBorder(
                            BLUE,
                            1,
                            true
                    )
            );

            signup.setFocusPainted(false);

            signup.setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            signup.setBounds(
                    245,
                    13,
                    190,
                    50
            );

            signup.addActionListener(
                    e -> {

                        dispose();

                        SignupFrame signupFrame =
                                new SignupFrame();

                        signupFrame.setVisible(true);
                    }
            );

            signupBox.add(signup);


            // FOOTER

            JLabel footer =
                    new JLabel(
                            "© 2026 LogiTrack. All rights reserved.",
                            SwingConstants.CENTER
                    );

            footer.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            12
                    )
            );

            footer.setForeground(
                    new Color(120, 140, 160)
            );

            footer.setBounds(
                    60,
                    650,
                    460,
                    25
            );

            add(footer);
        }


        private JTextField createTextField() {

            JTextField field =
                    new JTextField();

            field.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            16
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
    }


    // =====================================================
    // LOGIN DATABASE CHECK - FIXED
    // =====================================================

    private void loginUser() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (
                username.isEmpty()
                        ||
                password.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String sql =
                "SELECT user_id, password " +
                "FROM users " +
                "WHERE username = ?";


        Connection con = null;

        try {

            con =
                    DatabaseConnection
                            .getConnection();


            // IMPORTANT:
            // DatabaseConnection can return null
            // when MySQL connection fails.

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cannot connect to MySQL database.\n" +
                        "Please check MySQL Server and DatabaseConnection.java.",
                        "Database Connection Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setString(
                    1,
                    username
            );


            ResultSet rs =
                    ps.executeQuery();


            if (rs.next()) {

                int userId =
                        rs.getInt(
                                "user_id"
                        );


                String storedPassword =
                        rs.getString(
                                "password"
                        );


                String enteredPassword =
                        hashPassword(
                                password
                        );


                if (
                        storedPassword != null
                                &&
                        storedPassword.equals(
                                enteredPassword
                        )
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Login successful!",
                            "Welcome",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    rs.close();

                    ps.close();

                    con.close();


                    dispose();


                    Dashboard dashboard =
                            new Dashboard(
                                    userId
                            );

                    dashboard.setVisible(
                            true
                    );

                } else {

                    rs.close();

                    ps.close();

                    con.close();


                    JOptionPane.showMessageDialog(
                            this,
                            "Incorrect password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } else {

                rs.close();

                ps.close();

                con.close();


                JOptionPane.showMessageDialog(
                        this,
                        "Username not found.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (SQLException e) {

            e.printStackTrace();


            if (con != null) {

                try {
                    con.close();
                } catch (SQLException ignored) {
                }
            }


            JOptionPane.showMessageDialog(
                    this,
                    "Database connection error.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // SHA-256 PASSWORD HASH
    // =====================================================

    private String hashPassword(
            String password
    ) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance(
                            "SHA-256"
                    );


            byte[] hash =
                    md.digest(
                            password.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );


            StringBuilder sb =
                    new StringBuilder();


            for (
                    byte b :
                    hash
            ) {

                sb.append(
                        String.format(
                                "%02x",
                                b
                        )
                );
            }


            return sb.toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    e
            );
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    LoginFrame frame =
                            new LoginFrame();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}