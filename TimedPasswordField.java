import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TimedPasswordField extends JPasswordField {

    private static final char MASK_CHAR = '*';

    private char lastTypedCharacter = 0;

    private long lastTypedTime = 0;

    private boolean showAllPassword = false;

    private final Timer timer;

    public TimedPasswordField() {

        super();

        // Default: password hidden
        setEchoChar(MASK_CHAR);

        /*
         * Repaint every 100 milliseconds.
         * This checks whether 1 second is completed.
         */
        timer = new Timer(100, e -> {

            if (!showAllPassword) {

                if (lastTypedCharacter != 0) {

                    long elapsed =
                            System.currentTimeMillis()
                            - lastTypedTime;

                    if (elapsed >= 1000) {

                        lastTypedCharacter = 0;

                        repaint();
                    }
                }
            }
        });

        timer.start();

        /*
         * Detect when user types a new character.
         */
        getDocument().addDocumentListener(
                new DocumentListener() {

                    @Override
                    public void insertUpdate(
                            DocumentEvent e) {

                        showLastCharacter();
                    }

                    @Override
                    public void removeUpdate(
                            DocumentEvent e) {

                        lastTypedCharacter = 0;

                        repaint();
                    }

                    @Override
                    public void changedUpdate(
                            DocumentEvent e) {
                    }
                }
        );
    }

    // =====================================================
    // SHOW LAST TYPED CHARACTER FOR 1 SECOND
    // =====================================================

    private void showLastCharacter() {

        if (showAllPassword) {
            return;
        }

        char[] password = getPassword();

        if (password.length == 0) {

            lastTypedCharacter = 0;

            repaint();

            return;
        }

        /*
         * Only the latest character is stored
         * for temporary display.
         */
        lastTypedCharacter =
                password[password.length - 1];

        lastTypedTime =
                System.currentTimeMillis();

        repaint();
    }

    // =====================================================
    // SHOW ALL PASSWORD
    // =====================================================

    public void showAllPassword(
            boolean show) {

        showAllPassword = show;

        if (show) {

            // Show complete password
            setEchoChar((char) 0);

            lastTypedCharacter = 0;

        } else {

            // Hide complete password
            setEchoChar(MASK_CHAR);

            lastTypedCharacter = 0;

            lastTypedTime =
                    System.currentTimeMillis();
        }

        repaint();
    }

    // =====================================================
    // CHECK PASSWORD VISIBILITY
    // =====================================================

    public boolean isPasswordVisible() {

        return showAllPassword;
    }

    // =====================================================
    // PAINT
    // =====================================================

    @Override
    protected void paintComponent(
            Graphics g) {

        /*
         * Normal JPasswordField paints
         * all characters as '*'.
         */
        super.paintComponent(g);

        if (showAllPassword) {
            return;
        }

        char[] password = getPassword();

        if (password.length == 0) {
            return;
        }

        if (lastTypedCharacter == 0) {
            return;
        }

        long elapsed =
                System.currentTimeMillis()
                - lastTypedTime;

        /*
         * After 1 second, don't show
         * the last character.
         */
        if (elapsed >= 1000) {
            return;
        }

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        g2.setFont(getFont());

        g2.setColor(getForeground());

        FontMetrics fm =
                g2.getFontMetrics();

        /*
         * Calculate the position of the
         * last password character.
         */
        int x =
                getInsets().left;

        for (int i = 0;
             i < password.length - 1;
             i++) {

            x += fm.charWidth(MASK_CHAR);
        }

        int y =
                (getHeight()
                        - fm.getHeight())
                        / 2
                        + fm.getAscent();

        /*
         * Draw ONLY the latest character.
         */
        g2.drawString(
                String.valueOf(
                        lastTypedCharacter
                ),
                x,
                y
        );

        g2.dispose();
    }
}