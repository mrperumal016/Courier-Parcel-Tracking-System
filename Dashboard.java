import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;



public class Dashboard extends JFrame {



    private ParcelManager manager;



    private int userId;



    private JTable parcelTable;

    private DefaultTableModel tableModel;



    private JLabel totalLabel;

    private JLabel bookedLabel;

    private JLabel transitLabel;

    private JLabel deliveredLabel;





    // =====================================================

    // CONSTRUCTOR

    // =====================================================



    public Dashboard(int userId) {



        this.userId = userId;



        manager = new ParcelManager();



        setTitle(

                "Courier & Parcel Tracking System"

        );



        setSize(

                1150,

                700

        );



        setLocationRelativeTo(null);



        setDefaultCloseOperation(

                JFrame.EXIT_ON_CLOSE

        );



        createGUI();



        refreshTable();

    }





    // =====================================================

    // CREATE GUI

    // =====================================================



    private void createGUI() {



        setLayout(

                new BorderLayout()

        );





        // =================================================

        // SIDEBAR

        // =================================================



        JPanel sidebar =

                new JPanel();



        sidebar.setBackground(

                new Color(

                        25,

                        45,

                        70

                )

        );



        sidebar.setPreferredSize(

                new Dimension(

                        265,

                        700

                )

        );



        sidebar.setLayout(

                new BoxLayout(

                        sidebar,

                        BoxLayout.Y_AXIS

                )

        );





        // =================================================

        // LOGO

        // =================================================



        JLabel logo =

                new JLabel(

                        "LogiTrack"

                );



        logo.setForeground(

                Color.WHITE

        );



        logo.setFont(

                new Font(

                        "Arial",

                        Font.BOLD,

                        30

                )

        );



        logo.setAlignmentX(

                Component.CENTER_ALIGNMENT

        );



        sidebar.add(

                Box.createVerticalStrut(

                        40

                )

        );



        sidebar.add(logo);



        sidebar.add(

                Box.createVerticalStrut(

                        55

                )

        );





        // =================================================

        // BUTTONS

        // =================================================



        JButton dashboardButton =

                new JButton(

                        "Dashboard"

                );



        JButton addButton =

                new JButton(

                        "Add Parcel"

                );



        JButton updateButton =

                new JButton(

                        "Update Status"

                );



        JButton deleteButton =

                new JButton(

                        "Delete Parcel"

                );



        JButton trackButton =

                new JButton(

                        "Track Parcel"

                );



        JButton logoutButton =

                new JButton(

                        "Logout"

                );





        styleButton(

                dashboardButton

        );



        styleButton(

                addButton

        );



        styleButton(

                updateButton

        );



        styleButton(

                deleteButton

        );



        styleButton(

                trackButton

        );



        styleLogoutButton(

                logoutButton

        );





        // =================================================

        // SIDEBAR BUTTONS

        // =================================================



        sidebar.add(

                dashboardButton

        );



        sidebar.add(

                Box.createVerticalStrut(

                        18

                )

        );



        sidebar.add(

                addButton

        );



        sidebar.add(

                Box.createVerticalStrut(

                        18

                )

        );



        sidebar.add(

                updateButton

        );



        sidebar.add(

                Box.createVerticalStrut(

                        18

                )

        );



        sidebar.add(

                deleteButton

        );



        sidebar.add(

                Box.createVerticalStrut(

                        18

                )

        );



        sidebar.add(

                trackButton

        );





        // Push logout to bottom



        sidebar.add(

                Box.createVerticalGlue()

        );



        sidebar.add(

                logoutButton

        );



        sidebar.add(

                Box.createVerticalStrut(

                        30

                )

        );





        add(

                sidebar,

                BorderLayout.WEST

        );





        // =================================================

        // MAIN PANEL

        // =================================================



        JPanel mainPanel =

                new JPanel(

                        new BorderLayout(

                                15,

                                15

                        )

                );



        mainPanel.setBackground(

                new Color(

                        245,

                        247,

                        250

                )

        );



        mainPanel.setBorder(

                BorderFactory.createEmptyBorder(

                        30,

                        30,

                        30,

                        30

                )

        );





        // =================================================

        // TITLE

        // =================================================



        JLabel title =

                new JLabel(

                        "Courier & Parcel Dashboard"

                );



        title.setFont(

                new Font(

                        "Arial",

                        Font.BOLD,

                        30

                )

        );



        title.setForeground(

                new Color(

                        25,

                        50,

                        80

                )

        );



        mainPanel.add(

                title,

                BorderLayout.NORTH

        );





        // =================================================

        // CENTER

        // =================================================



        JPanel centerPanel =

                new JPanel(

                        new BorderLayout(

                                15,

                                15

                        )

                );



        centerPanel.setOpaque(

                false

        );





        // =================================================

        // STATISTICS

        // =================================================



        JPanel statsPanel =

                new JPanel(

                        new GridLayout(

                                1,

                                4,

                                15,

                                15

                        )

                );



        statsPanel.setOpaque(

                false

        );





        totalLabel =

                createValueLabel();



        bookedLabel =

                createValueLabel();



        transitLabel =

                createValueLabel();



        deliveredLabel =

                createValueLabel();





        statsPanel.add(

                createCard(

                        "Total Parcels",

                        totalLabel

                )

        );



        statsPanel.add(

                createCard(

                        "Booked",

                        bookedLabel

                )

        );



        statsPanel.add(

                createCard(

                        "In Transit",

                        transitLabel

                )

        );



        statsPanel.add(

                createCard(

                        "Delivered",

                        deliveredLabel

                )

        );





        centerPanel.add(

                statsPanel,

                BorderLayout.NORTH

        );





        // =================================================

        // TABLE

        // =================================================



        String[] columns = {



                "Parcel ID",

                "Tracking ID",

                "Sender",

                "Receiver",

                "Source",

                "Destination",

                "Weight",

                "Status"

        };





        tableModel =

                new DefaultTableModel(

                        columns,

                        0

                ) {



                    @Override

                    public boolean isCellEditable(

                            int row,

                            int column

                    ) {



                        return false;

                    }

                };





        parcelTable =

                new JTable(

                        tableModel

                );



        // =================================================

        // TRACKING ID - SHOW FULL VALUE ON CLICK

        // =================================================

        parcelTable.getColumnModel()

                .getColumn(1)

                .setPreferredWidth(170);



        parcelTable.addMouseListener(

                new java.awt.event.MouseAdapter() {

                    @Override

                    public void mouseClicked(

                            java.awt.event.MouseEvent e

                    ) {

                        int row =

                                parcelTable.rowAtPoint(

                                        e.getPoint()

                                );

                        int column =

                                parcelTable.columnAtPoint(

                                        e.getPoint()

                                );



                        // Tracking ID is column 1

                        if (row >= 0 && column == 1) {

                            Object value =

                                    parcelTable.getValueAt(

                                            row,

                                            column

                                    );



                            if (value != null) {

                                JOptionPane.showMessageDialog(

                                        Dashboard.this,

                                        value.toString(),

                                        "Tracking ID",

                                        JOptionPane.INFORMATION_MESSAGE

                                );

                            }

                        }

                    }

                }

        );



        parcelTable.setRowHeight(

                32

        );



        parcelTable.setFont(

                new Font(

                        "Arial",

                        Font.PLAIN,

                        14

                )

        );



        parcelTable.getTableHeader()

                .setFont(

                        new Font(

                                "Arial",

                                Font.BOLD,

                                14

                        )

                );



        parcelTable.getTableHeader()

                .setReorderingAllowed(

                        false

                );





        JScrollPane scrollPane =

                new JScrollPane(

                        parcelTable

                );





        centerPanel.add(

                scrollPane,

                BorderLayout.CENTER

        );





        mainPanel.add(

                centerPanel,

                BorderLayout.CENTER

        );





        add(

                mainPanel,

                BorderLayout.CENTER

        );





        // =================================================

        // BUTTON ACTIONS

        // =================================================



        dashboardButton.addActionListener(

                e -> refreshTable()

        );





        addButton.addActionListener(

                e -> showAddParcelDialog()

        );





        updateButton.addActionListener(

                e -> updateParcelStatus()

        );





        deleteButton.addActionListener(

                e -> deleteParcel()

        );





        trackButton.addActionListener(

                e -> {



                    TrackParcelFrame frame =

                            new TrackParcelFrame();



                    frame.setVisible(

                            true

                    );

                }

        );





        logoutButton.addActionListener(

                e -> logout()

        );

    }





    // =====================================================

    // LOGOUT

    // =====================================================



    private void logout() {



        int choice =

                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to logout?",

                        "Confirm Logout",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE

                );





        if (

                choice ==

                        JOptionPane.YES_OPTION

        ) {



            dispose();



            LoginFrame loginFrame =

                    new LoginFrame();



            loginFrame.setVisible(

                    true

            );

        }

    }





    // =====================================================

    // CARD

    // =====================================================



    private JPanel createCard(

            String title,

            JLabel valueLabel

    ) {



        JPanel card =

                new JPanel(

                        new BorderLayout()

                );



        card.setBackground(

                Color.WHITE

        );



        card.setBorder(

                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(

                                new Color(

                                        215,

                                        220,

                                        225

                                )

                        ),

                        BorderFactory.createEmptyBorder(

                                15,

                                10,

                                15,

                                10

                        )

                )

        );





        JLabel titleLabel =

                new JLabel(

                        title,

                        SwingConstants.CENTER

                );



        titleLabel.setFont(

                new Font(

                        "Arial",

                        Font.PLAIN,

                        15

                )

        );





        card.add(

                titleLabel,

                BorderLayout.NORTH

        );



        card.add(

                valueLabel,

                BorderLayout.CENTER

        );





        return card;

    }





    // =====================================================

    // VALUE LABEL

    // =====================================================



    private JLabel createValueLabel() {



        JLabel label =

                new JLabel(

                        "0",

                        SwingConstants.CENTER

                );



        label.setFont(

                new Font(

                        "Arial",

                        Font.BOLD,

                        25

                )

        );



        label.setForeground(

                new Color(

                        40,

                        110,

                        175

                )

        );



        return label;

    }





    // =====================================================

    // BUTTON STYLE

    // =====================================================



    private void styleButton(

            JButton button

    ) {



        button.setMaximumSize(

                new Dimension(

                        220,

                        42

                )

        );



        button.setAlignmentX(

                Component.CENTER_ALIGNMENT

        );



        button.setBackground(

                new Color(

                        45,

                        105,

                        165

                )

        );



        button.setForeground(

                Color.WHITE

        );



        button.setFont(

                new Font(

                        "Arial",

                        Font.BOLD,

                        15

                )

        );



        button.setFocusPainted(

                false

        );



        button.setBorderPainted(

                false

        );



        button.setCursor(

                new Cursor(

                        Cursor.HAND_CURSOR

                )

        );

    }





    // =====================================================

    // LOGOUT BUTTON STYLE

    // =====================================================



    private void styleLogoutButton(

            JButton button

    ) {



        button.setMaximumSize(

                new Dimension(

                        220,

                        42

                )

        );



        button.setAlignmentX(

                Component.CENTER_ALIGNMENT

        );



        button.setBackground(

                new Color(

                        190,

                        65,

                        65

                )

        );



        button.setForeground(

                Color.WHITE

        );



        button.setFont(

                new Font(

                        "Arial",

                        Font.BOLD,

                        15

                )

        );



        button.setFocusPainted(

                false

        );



        button.setBorderPainted(

                false

        );



        button.setCursor(

                new Cursor(

                        Cursor.HAND_CURSOR

                )

        );

    }





    // =====================================================

    // ADD PARCEL

    // =====================================================



    private void showAddParcelDialog() {



        JTextField senderField =

                new JTextField();



        JTextField senderAddressField =

                new JTextField();



        JTextField receiverField =

                new JTextField();



        JTextField receiverAddressField =

                new JTextField();



        JTextField sourceField =

                new JTextField();



        JTextField destinationField =

                new JTextField();



        JTextField weightField =

                new JTextField();





        JComboBox<String> statusBox =

                new JComboBox<>(

                        new String[]{



                                "Booked",

                                "In Transit",

                                "Out for Delivery",

                                "Delivered"

                        }

                );





        // 9 rows:

        // Sender

        // Sender Address

        // Receiver

        // Receiver Address

        // Source

        // Destination

        // Weight

        // Status



        JPanel panel =

                new JPanel(

                        new GridLayout(

                                8,

                                2,

                                10,

                                10

                        )

                );





        panel.add(

                new JLabel(

                        "Sender"

                )

        );



        panel.add(

                senderField

        );





        panel.add(

                new JLabel(

                        "Sender Address"

                )

        );



        panel.add(

                senderAddressField

        );





        panel.add(

                new JLabel(

                        "Receiver"

                )

        );



        panel.add(

                receiverField

        );





        panel.add(

                new JLabel(

                        "Receiver Address"

                )

        );



        panel.add(

                receiverAddressField

        );





        panel.add(

                new JLabel(

                        "Source"

                )

        );



        panel.add(

                sourceField

        );





        panel.add(

                new JLabel(

                        "Destination"

                )

        );



        panel.add(

                destinationField

        );





        panel.add(

                new JLabel(

                        "Weight (kg)"

                )

        );



        panel.add(

                weightField

        );





        panel.add(

                new JLabel(

                        "Status"

                )

        );



        panel.add(

                statusBox

        );





        int result =

                JOptionPane.showConfirmDialog(

                        this,

                        panel,

                        "Add New Parcel",

                        JOptionPane.OK_CANCEL_OPTION,

                        JOptionPane.PLAIN_MESSAGE

                );





        if (

                result !=

                        JOptionPane.OK_OPTION

        ) {



            return;

        }





        try {



            String sender =

                    senderField

                            .getText()

                            .trim();





            String senderAddress =

                    senderAddressField

                            .getText()

                            .trim();





            String receiver =

                    receiverField

                            .getText()

                            .trim();





            String receiverAddress =

                    receiverAddressField

                            .getText()

                            .trim();





            String source =

                    sourceField

                            .getText()

                            .trim();





            String destination =

                    destinationField

                            .getText()

                            .trim();





            String weightText =

                    weightField

                            .getText()

                            .trim();





            // =================================================

            // EMPTY CHECK

            // =================================================



            if (

                    sender.isEmpty()

                            ||

                    senderAddress.isEmpty()

                            ||

                    receiver.isEmpty()

                            ||

                    receiverAddress.isEmpty()

                            ||

                    source.isEmpty()

                            ||

                    destination.isEmpty()

                            ||

                    weightText.isEmpty()

            ) {



                JOptionPane.showMessageDialog(

                        this,

                        "Please fill all fields.",

                        "Missing Information",

                        JOptionPane.WARNING_MESSAGE

                );



                return;

            }





            // =================================================

            // WEIGHT

            // =================================================



            double weight =

                    Double.parseDouble(

                            weightText

                    );





            if (

                    weight <= 0

            ) {



                JOptionPane.showMessageDialog(

                        this,

                        "Weight must be greater than 0.",

                        "Invalid Weight",

                        JOptionPane.WARNING_MESSAGE

                );



                return;

            }





            // =================================================

            // AUTOMATIC PARCEL ID

            // =================================================



            String parcelId =

                    manager.generateParcelId();





            // =================================================

            // AUTOMATIC TRACKING ID

            // =================================================



            String trackingId =

                    manager.generateTrackingId();





            // =================================================

            // STATUS

            // =================================================



            String status =

                    statusBox

                            .getSelectedItem()

                            .toString();





            // =================================================

            // CREATE PARCEL

            // =================================================



            Parcel parcel =

                    new Parcel(

                            parcelId,

                            trackingId,

                            sender,

                            receiver,

                            source,

                            destination,

                            weight,

                            status,

                            senderAddress,

                            receiverAddress

                    );





            // =================================================

            // SAVE

            // =================================================



            manager.addParcel(

                    parcel,

                    userId

            );





            // =================================================

            // REFRESH

            // =================================================



            refreshTable();





            // =================================================

            // SUCCESS

            // =================================================



            JOptionPane.showMessageDialog(

                    this,

                    "Parcel added successfully!\n\n"

                            + "Parcel ID: "

                            + parcelId

                            + "\n\n"

                            + "Tracking Number: "

                            + trackingId,

                    "Parcel Created",

                    JOptionPane.INFORMATION_MESSAGE

            );





        } catch (

                NumberFormatException ex

        ) {



            JOptionPane.showMessageDialog(

                    this,

                    "Please enter a valid weight.",

                    "Invalid Weight",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }





    // =====================================================

    // UPDATE STATUS

    // =====================================================



    private void updateParcelStatus() {



        String id =

                JOptionPane.showInputDialog(

                        this,

                        "Enter Parcel ID:"

                );





        if (

                id == null ||

                id.trim().isEmpty()

        ) {



            return;

        }





        id = id.trim();





        Parcel parcel =

                manager.findParcel(

                        id,

                        userId

                );





        if (

                parcel == null

        ) {



            JOptionPane.showMessageDialog(

                    this,

                    "Parcel not found!",

                    "Error",

                    JOptionPane.ERROR_MESSAGE

            );



            return;

        }





        String[] statuses = {



                "Booked",

                "In Transit",

                "Out for Delivery",

                "Delivered"

        };





        String newStatus =

                (String)

                        JOptionPane.showInputDialog(

                                this,

                                "Select new status:",

                                "Update Parcel Status",

                                JOptionPane.QUESTION_MESSAGE,

                                null,

                                statuses,

                                parcel.getStatus()

                        );





        if (

                newStatus == null

        ) {



            return;

        }





        boolean updated =

                manager.updateStatus(

                        id,

                        newStatus,

                        userId

                );





        if (

                updated

        ) {



            refreshTable();



            JOptionPane.showMessageDialog(

                    this,

                    "Parcel status updated successfully!",

                    "Success",

                    JOptionPane.INFORMATION_MESSAGE

            );

        }

    }





    // =====================================================

    // DELETE PARCEL

    // =====================================================



    private void deleteParcel() {



        String id =

                JOptionPane.showInputDialog(

                        this,

                        "Enter Parcel ID to delete:"

                );





        if (

                id == null ||

                id.trim().isEmpty()

        ) {



            return;

        }





        id = id.trim();





        Parcel parcel =

                manager.findParcel(

                        id,

                        userId

                );





        if (

                parcel == null

        ) {



            JOptionPane.showMessageDialog(

                    this,

                    "Parcel not found!",

                    "Error",

                    JOptionPane.ERROR_MESSAGE

            );



            return;

        }





        int confirm =

                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to delete "

                                + id

                                + "?",

                        "Confirm Delete",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.WARNING_MESSAGE

                );





        if (

                confirm ==

                        JOptionPane.YES_OPTION

        ) {



            boolean deleted =

                    manager.deleteParcel(

                            id,

                            userId

                    );





            if (

                    deleted

            ) {



                refreshTable();



                JOptionPane.showMessageDialog(

                        this,

                        "Parcel deleted successfully!",

                        "Deleted",

                        JOptionPane.INFORMATION_MESSAGE

                );

            }

        }

    }





    // =====================================================

    // REFRESH TABLE

    // =====================================================



    private void refreshTable() {



        tableModel.setRowCount(

                0

        );





        int total = 0;



        int booked = 0;



        int transit = 0;



        int delivered = 0;





        for (

                Parcel parcel :

                manager.getParcels(

                        userId

                )

        ) {



            tableModel.addRow(

                    new Object[]{



                            parcel.getParcelId(),



                            parcel.getTrackingId(),



                            parcel.getSenderName(),



                            parcel.getReceiverName(),



                            parcel.getSource(),



                            parcel.getDestination(),



                            parcel.getWeight()

                                    + " kg",



                            parcel.getStatus()

                    }

            );





            total++;





            if (

                    "Booked".equals(

                            parcel.getStatus()

                    )

            ) {



                booked++;

            }





            if (

                    "In Transit".equals(

                            parcel.getStatus()

                    )

                            ||

                    "Out for Delivery".equals(

                            parcel.getStatus()

                    )

            ) {



                transit++;

            }





            if (

                    "Delivered".equals(

                            parcel.getStatus()

                    )

            ) {



                delivered++;

            }

        }





        totalLabel.setText(

                String.valueOf(

                        total

                )

        );



        bookedLabel.setText(

                String.valueOf(

                        booked

                )

        );



        transitLabel.setText(

                String.valueOf(

                        transit

                )

        );



        deliveredLabel.setText(

                String.valueOf(

                        delivered

                )

        );

    }





    // =====================================================

    // MAIN

    // =====================================================



    public static void main(

            String[] args

    ) {



        SwingUtilities.invokeLater(

                () -> {



                    LoginFrame loginFrame =

                            new LoginFrame();



                    loginFrame.setVisible(

                            true

                    );

                }

        );

    }

}