import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrackParcelFrame extends JFrame {

    private JTextField trackingField;

    private JLabel parcelIdValue;
    private JLabel trackingIdValue;

    private JLabel senderValue;
    private JLabel senderAddressValue;

    private JLabel receiverValue;
    private JLabel receiverAddressValue;

    private JLabel sourceValue;
    private JLabel destinationValue;

    private JLabel weightValue;
    private JLabel statusValue;


    // =====================================================
    // COLORS
    // =====================================================

    private final Color NAVY =
            new Color(8, 48, 88);

    private final Color BLUE =
            new Color(18, 137, 235);

    private final Color LIGHT =
            new Color(246, 249, 253);

    private final Color TEXT =
            new Color(12, 42, 76);

    private final Color MUTED =
            new Color(125, 140, 160);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public TrackParcelFrame() {

        setTitle(
                "Courier & Parcel Tracking System - Track Parcel"
        );

        setSize(
                900,
                700
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(false);

        createGUI();
    }


    // =====================================================
    // CREATE GUI
    // =====================================================

    private void createGUI() {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(
                LIGHT
        );


        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                NAVY
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );


        JLabel logo =
                new JLabel(
                        "LogiTrack"
                );

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        logo.setForeground(
                Color.WHITE
        );

        header.add(
                logo,
                BorderLayout.WEST
        );


        JLabel title =
                new JLabel(
                        "Track Your Parcel",
                        SwingConstants.RIGHT
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(
                Color.WHITE
        );

        header.add(
                title,
                BorderLayout.EAST
        );


        main.add(
                header,
                BorderLayout.NORTH
        );


        // =================================================
        // CENTER
        // =================================================

        JPanel center =
                new JPanel();

        center.setBackground(
                LIGHT
        );

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        center.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        70,
                        25,
                        70
                )
        );


        // =================================================
        // INSTRUCTION
        // =================================================

        JLabel instruction =
                new JLabel(
                        "Enter your tracking number"
                );

        instruction.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        instruction.setForeground(
                TEXT
        );

        instruction.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        center.add(
                instruction
        );


        center.add(
                Box.createVerticalStrut(
                        12
                )
        );


        // =================================================
        // SEARCH PANEL
        // =================================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchPanel.setOpaque(
                false
        );


        trackingField =
                new JTextField();


        trackingField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );


        trackingField.setBorder(
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
                                8,
                                12,
                                8,
                                12
                        )
                )
        );


        JButton trackButton =
                new JButton(
                        "TRACK PARCEL"
                );


        trackButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        trackButton.setForeground(
                Color.WHITE
        );


        trackButton.setBackground(
                BLUE
        );


        trackButton.setFocusPainted(
                false
        );


        trackButton.setBorderPainted(
                false
        );


        trackButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        searchPanel.add(
                trackingField,
                BorderLayout.CENTER
        );


        searchPanel.add(
                trackButton,
                BorderLayout.EAST
        );


        searchPanel.setMaximumSize(
                new Dimension(
                        700,
                        50
                )
        );


        center.add(
                searchPanel
        );


        center.add(
                Box.createVerticalStrut(
                        25
                )
        );


        // =================================================
        // RESULT PANEL
        // =================================================

        JPanel resultPanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                12,
                                12
                        )
                );


        resultPanel.setBackground(
                Color.WHITE
        );


        resultPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                ),
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );


        // =================================================
        // VALUE LABELS
        // =================================================

        parcelIdValue =
                createValueLabel();


        trackingIdValue =
                createValueLabel();


        senderValue =
                createValueLabel();


        senderAddressValue =
                createAddressLabel();


        receiverValue =
                createValueLabel();


        receiverAddressValue =
                createAddressLabel();


        sourceValue =
                createValueLabel();


        destinationValue =
                createValueLabel();


        weightValue =
                createValueLabel();


        statusValue =
                createValueLabel();


        // =================================================
        // ADDRESS CLICK ACTIONS
        // =================================================

        senderAddressValue.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        showFullAddress(
                                "Sender Address",
                                senderAddressValue.getText()
                        );
                    }
                }
        );


        receiverAddressValue.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        showFullAddress(
                                "Receiver Address",
                                receiverAddressValue.getText()
                        );
                    }
                }
        );


        // =================================================
        // RESULT ROW 1
        // =================================================

        resultPanel.add(
                createInfoPanel(
                        "Parcel ID",
                        parcelIdValue
                )
        );


        resultPanel.add(
                createInfoPanel(
                        "Tracking Number",
                        trackingIdValue
                )
        );


        // =================================================
        // RESULT ROW 2
        // =================================================

        resultPanel.add(
                createInfoPanel(
                        "Sender",
                        senderValue
                )
        );


        resultPanel.add(
                createInfoPanel(
                        "Sender Address",
                        senderAddressValue
                )
        );


        // =================================================
        // RESULT ROW 3
        // =================================================

        resultPanel.add(
                createInfoPanel(
                        "Receiver",
                        receiverValue
                )
        );


        resultPanel.add(
                createInfoPanel(
                        "Receiver Address",
                        receiverAddressValue
                )
        );


        // =================================================
        // RESULT ROW 4
        // =================================================

        resultPanel.add(
                createInfoPanel(
                        "Source",
                        sourceValue
                )
        );


        resultPanel.add(
                createInfoPanel(
                        "Destination",
                        destinationValue
                )
        );


        // =================================================
        // RESULT ROW 5
        // =================================================

        resultPanel.add(
                createInfoPanel(
                        "Weight",
                        weightValue
                )
        );


        resultPanel.add(
                createInfoPanel(
                        "Status",
                        statusValue
                )
        );


        center.add(
                resultPanel
        );


        center.add(
                Box.createVerticalStrut(
                        15
                )
        );


        // =================================================
        // NOTE
        // =================================================

        JLabel note =
                new JLabel(
                        "Click Sender Address or Receiver Address to view full address.",
                        SwingConstants.CENTER
                );


        note.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        note.setForeground(
                MUTED
        );


        note.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        center.add(
                note
        );


        main.add(
                center,
                BorderLayout.CENTER
        );


        // =================================================
        // TRACK BUTTON
        // =================================================

        trackButton.addActionListener(
                e -> trackParcel()
        );


        // =================================================
        // ENTER KEY
        // =================================================

        trackingField.addActionListener(
                e -> trackParcel()
        );


        setContentPane(
                main
        );
    }


    // =====================================================
    // TRACK PARCEL
    // =====================================================

    private void trackParcel() {

        String trackingId =
                trackingField
                        .getText()
                        .trim();


        if (
                trackingId.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a tracking number.",
                    "Tracking Number Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        ParcelManager manager =
                new ParcelManager();


        Parcel parcel =
                manager.findByTrackingId(
                        trackingId
                );


        if (
                parcel == null
        ) {

            clearDetails();


            JOptionPane.showMessageDialog(
                    this,
                    "Tracking number not found.",
                    "Parcel Not Found",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =================================================
        // DISPLAY DETAILS
        // =================================================

        parcelIdValue.setText(
                safeText(
                        parcel.getParcelId()
                )
        );


        trackingIdValue.setText(
                safeText(
                        parcel.getTrackingId()
                )
        );


        senderValue.setText(
                safeText(
                        parcel.getSenderName()
                )
        );


        senderAddressValue.setText(
                safeText(
                        parcel.getSenderAddress()
                )
        );


        receiverValue.setText(
                safeText(
                        parcel.getReceiverName()
                )
        );


        receiverAddressValue.setText(
                safeText(
                        parcel.getReceiverAddress()
                )
        );


        sourceValue.setText(
                safeText(
                        parcel.getSource()
                )
        );


        destinationValue.setText(
                safeText(
                        parcel.getDestination()
                )
        );


        weightValue.setText(
                parcel.getWeight()
                        + " kg"
        );


        statusValue.setText(
                safeText(
                        parcel.getStatus()
                )
        );


        // =================================================
        // STATUS COLOR
        // =================================================

        if (
                "Delivered".equals(
                        parcel.getStatus()
                )
        ) {

            statusValue.setForeground(
                    new Color(
                            30,
                            140,
                            80
                    )
            );

        } else if (
                "In Transit".equals(
                        parcel.getStatus()
                )
        ) {

            statusValue.setForeground(
                    BLUE
            );

        } else {

            statusValue.setForeground(
                    TEXT
            );
        }
    }


    // =====================================================
    // SAFE TEXT
    // =====================================================

    private String safeText(
            String value
    ) {

        if (
                value == null ||
                value.trim().isEmpty()
        ) {

            return "-";
        }

        return value;
    }


    // =====================================================
    // SHOW FULL ADDRESS
    // =====================================================

    private void showFullAddress(
            String title,
            String address
    ) {

        if (
                address == null ||
                address.equals("-") ||
                address.trim().isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Address not available.",
                    title,
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        JTextArea textArea =
                new JTextArea(
                        address
                );


        textArea.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );


        textArea.setLineWrap(
                true
        );


        textArea.setWrapStyleWord(
                true
        );


        textArea.setEditable(
                false
        );


        textArea.setFocusable(
                false
        );


        textArea.setBackground(
                Color.WHITE
        );


        textArea.setForeground(
                TEXT
        );


        textArea.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        textArea
                );


        scrollPane.setPreferredSize(
                new Dimension(
                        550,
                        180
                )
        );


        scrollPane.setBorder(
                new LineBorder(
                        new Color(
                                215,
                                225,
                                235
                        ),
                        1,
                        true
                )
        );


        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =====================================================
    // CLEAR DETAILS
    // =====================================================

    private void clearDetails() {

        parcelIdValue.setText(
                "-"
        );


        trackingIdValue.setText(
                "-"
        );


        senderValue.setText(
                "-"
        );


        senderAddressValue.setText(
                "-"
        );


        receiverValue.setText(
                "-"
        );


        receiverAddressValue.setText(
                "-"
        );


        sourceValue.setText(
                "-"
        );


        destinationValue.setText(
                "-"
        );


        weightValue.setText(
                "-"
        );


        statusValue.setText(
                "-"
        );


        statusValue.setForeground(
                TEXT
        );
    }


    // =====================================================
    // NORMAL VALUE LABEL
    // =====================================================

    private JLabel createValueLabel() {

        JLabel label =
                new JLabel(
                        "-"
                );


        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        label.setForeground(
                TEXT
        );


        return label;
    }


    // =====================================================
    // ADDRESS LABEL
    // =====================================================

    private JLabel createAddressLabel() {

        JLabel label =
                new JLabel(
                        "-"
                );


        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        label.setForeground(
                TEXT
        );


        label.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        label.setToolTipText(
                "Click to view full address"
        );


        return label;
    }


    // =====================================================
    // INFO PANEL
    // =====================================================

    private JPanel createInfoPanel(
            String title,
            JLabel value
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );


        panel.setBackground(
                Color.WHITE
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        titleLabel.setForeground(
                MUTED
        );


        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        panel.add(
                value,
                BorderLayout.CENTER
        );


        return panel;
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    TrackParcelFrame frame =
                            new TrackParcelFrame();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}