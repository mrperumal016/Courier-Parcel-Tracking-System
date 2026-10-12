import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParcelManager {

    // =====================================================
    // GENERATE PARCEL ID
    // =====================================================

    public String generateParcelId() {

        String sql =
                "SELECT parcel_id FROM parcels " +
                "WHERE parcel_id LIKE 'P%' " +
                "ORDER BY parcel_id DESC LIMIT 1";

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection == null) {
            return "P000001";
        }

        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            if (resultSet.next()) {

                String lastId =
                        resultSet.getString(
                                "parcel_id"
                        );

                try {

                    int number =
                            Integer.parseInt(
                                    lastId.substring(1)
                            );

                    return String.format(
                            "P%06d",
                            number + 1
                    );

                } catch (
                        NumberFormatException e
                ) {

                    return "P000001";
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error generating Parcel ID."
            );

            e.printStackTrace();
        }

        return "P000001";
    }


    // =====================================================
    // GENERATE TRACKING ID
    // =====================================================

    public String generateTrackingId() {

        String date =
                LocalDate.now().format(
                        DateTimeFormatter.ofPattern(
                                "yyyyMMdd"
                        )
                );

        String prefix =
                "LT" + date;


        String sql =
                "SELECT tracking_id FROM parcels " +
                "WHERE tracking_id LIKE ? " +
                "ORDER BY tracking_id DESC LIMIT 1";


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return prefix + "0001";
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    prefix + "%"
            );


            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    String lastId =
                            resultSet.getString(
                                    "tracking_id"
                            );


                    try {

                        String numberPart =
                                lastId.substring(
                                        prefix.length()
                                );


                        int number =
                                Integer.parseInt(
                                        numberPart
                                );


                        return String.format(
                                "%s%04d",
                                prefix,
                                number + 1
                        );


                    } catch (Exception e) {

                        return prefix + "0001";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error generating Tracking ID."
            );

            e.printStackTrace();
        }

        return prefix + "0001";
    }


    // =====================================================
    // ADD PARCEL
    // =====================================================

    public void addParcel(
            Parcel parcel,
            int userId
    ) {

        String sql =
                "INSERT INTO parcels " +
                "(parcel_id, tracking_id, " +
                "sender_name, sender_address, " +
                "receiver_name, receiver_address, " +
                "source, destination, weight, " +
                "status, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            System.out.println(
                    "Cannot add parcel."
            );

            return;
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            // 1 Parcel ID

            statement.setString(
                    1,
                    parcel.getParcelId()
            );


            // 2 Tracking ID

            statement.setString(
                    2,
                    parcel.getTrackingId()
            );


            // 3 Sender Name

            statement.setString(
                    3,
                    parcel.getSenderName()
            );


            // 4 Sender Address

            statement.setString(
                    4,
                    parcel.getSenderAddress()
            );


            // 5 Receiver Name

            statement.setString(
                    5,
                    parcel.getReceiverName()
            );


            // 6 Receiver Address

            statement.setString(
                    6,
                    parcel.getReceiverAddress()
            );


            // 7 Source

            statement.setString(
                    7,
                    parcel.getSource()
            );


            // 8 Destination

            statement.setString(
                    8,
                    parcel.getDestination()
            );


            // 9 Weight

            statement.setDouble(
                    9,
                    parcel.getWeight()
            );


            // 10 Status

            statement.setString(
                    10,
                    parcel.getStatus()
            );


            // 11 User ID

            statement.setInt(
                    11,
                    userId
            );


            int rows =
                    statement.executeUpdate();


            if (rows > 0) {

                System.out.println(
                        "Parcel added successfully!"
                );

                System.out.println(
                        "Parcel ID: "
                                + parcel.getParcelId()
                );

                System.out.println(
                        "Tracking ID: "
                                + parcel.getTrackingId()
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Error adding parcel."
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // GET PARCELS FOR LOGGED-IN USER
    // =====================================================

    public ArrayList<Parcel> getParcels(
            int userId
    ) {

        ArrayList<Parcel> parcels =
                new ArrayList<>();


        String sql =
                "SELECT parcel_id, tracking_id, " +
                "sender_name, sender_address, " +
                "receiver_name, receiver_address, " +
                "source, destination, weight, status " +
                "FROM parcels " +
                "WHERE user_id = ? " +
                "ORDER BY parcel_id";


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return parcels;
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    userId
            );


            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                while (
                        resultSet.next()
                ) {

                    Parcel parcel =
                            createParcel(
                                    resultSet
                            );

                    parcels.add(
                            parcel
                    );
                }
            }


        } catch (SQLException e) {

            System.out.println(
                    "Error loading parcels."
            );

            e.printStackTrace();
        }


        return parcels;
    }


    // =====================================================
    // FIND PARCEL BY PARCEL ID
    // =====================================================

    public Parcel findParcel(
            String parcelId,
            int userId
    ) {

        String sql =
                "SELECT parcel_id, tracking_id, " +
                "sender_name, sender_address, " +
                "receiver_name, receiver_address, " +
                "source, destination, weight, status " +
                "FROM parcels " +
                "WHERE parcel_id = ? " +
                "AND user_id = ?";


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return null;
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    parcelId
            );

            statement.setInt(
                    2,
                    userId
            );


            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (
                        resultSet.next()
                ) {

                    return createParcel(
                            resultSet
                    );
                }
            }


        } catch (SQLException e) {

            System.out.println(
                    "Error finding parcel."
            );

            e.printStackTrace();
        }


        return null;
    }


    // =====================================================
    // FIND PARCEL BY TRACKING ID
    // =====================================================

    public Parcel findByTrackingId(
            String trackingId
    ) {

        String sql =
                "SELECT parcel_id, tracking_id, " +
                "sender_name, sender_address, " +
                "receiver_name, receiver_address, " +
                "source, destination, weight, status " +
                "FROM parcels " +
                "WHERE tracking_id = ?";


        System.out.println(
                "Searching tracking ID: "
                        + trackingId
        );


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            System.out.println(
                    "Database connection is null."
            );

            return null;
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    trackingId
            );


            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (
                        resultSet.next()
                ) {

                    System.out.println(
                            "Tracking ID found!"
                    );

                    return createParcel(
                            resultSet
                    );
                }


                System.out.println(
                        "Tracking ID not found."
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Error finding tracking number."
            );

            e.printStackTrace();
        }


        return null;
    }


    // =====================================================
    // CREATE PARCEL FROM DATABASE
    // =====================================================

    private Parcel createParcel(
            ResultSet resultSet
    ) throws SQLException {

        return new Parcel(

                resultSet.getString(
                        "parcel_id"
                ),

                resultSet.getString(
                        "tracking_id"
                ),

                resultSet.getString(
                        "sender_name"
                ),

                resultSet.getString(
                        "receiver_name"
                ),

                resultSet.getString(
                        "source"
                ),

                resultSet.getString(
                        "destination"
                ),

                resultSet.getDouble(
                        "weight"
                ),

                resultSet.getString(
                        "status"
                ),

                resultSet.getString(
                        "sender_address"
                ),

                resultSet.getString(
                        "receiver_address"
                )
        );
    }


    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public boolean updateStatus(
            String parcelId,
            String newStatus,
            int userId
    ) {

        String sql =
                "UPDATE parcels " +
                "SET status = ? " +
                "WHERE parcel_id = ? " +
                "AND user_id = ?";


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return false;
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    newStatus
            );

            statement.setString(
                    2,
                    parcelId
            );

            statement.setInt(
                    3,
                    userId
            );


            int rows =
                    statement.executeUpdate();


            if (
                    rows > 0
            ) {

                System.out.println(
                        "Parcel status updated successfully!"
                );

                return true;
            }


        } catch (SQLException e) {

            System.out.println(
                    "Error updating parcel."
            );

            e.printStackTrace();
        }


        return false;
    }


    // =====================================================
    // DELETE PARCEL
    // =====================================================

    public boolean deleteParcel(
            String parcelId,
            int userId
    ) {

        String sql =
                "DELETE FROM parcels " +
                "WHERE parcel_id = ? " +
                "AND user_id = ?";


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return false;
        }


        try (
                connection;
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    parcelId
            );

            statement.setInt(
                    2,
                    userId
            );


            int rows =
                    statement.executeUpdate();


            if (
                    rows > 0
            ) {

                System.out.println(
                        "Parcel deleted successfully!"
                );

                return true;
            }


        } catch (SQLException e) {

            System.out.println(
                    "Error deleting parcel."
            );

            e.printStackTrace();
        }


        return false;
    }
}