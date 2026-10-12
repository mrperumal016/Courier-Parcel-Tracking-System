import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    /*
     * ============================================================
     * LOCAL + ONLINE DATABASE CONNECTION
     * ============================================================
     *
     * LOCAL:
     * jdbc:mysql://localhost:3306/courier_db
     *
     * ONLINE:
     * Uses environment variables:
     *
     * MYSQLHOST
     * MYSQLPORT
     * MYSQLDATABASE
     * MYSQLUSER
     * MYSQLPASSWORD
     *
     * If online variables are not available,
     * the program automatically uses local MySQL.
     */

    private static final String LOCAL_URL =
            "jdbc:mysql://localhost:3306/courier_db"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC"
                    + "&connectTimeout=5000"
                    + "&socketTimeout=5000";

    private static final String LOCAL_USER =
            "root";

    /*
     * IMPORTANT:
     *
     * For local testing, put your existing MySQL password
     * here.
     *
     * For online deployment, MYSQLPASSWORD environment
     * variable will be used instead.
     */
    private static final String LOCAL_PASSWORD =
            "MySql@123";


    public static Connection getConnection() {

        /*
         * ========================================================
         * CHECK ONLINE DATABASE VARIABLES
         * ========================================================
         */

        String host =
                getEnvironmentVariable(
                        "MYSQLHOST"
                );

        String port =
                getEnvironmentVariable(
                        "MYSQLPORT"
                );

        String database =
                getEnvironmentVariable(
                        "MYSQLDATABASE"
                );

        String user =
                getEnvironmentVariable(
                        "MYSQLUSER"
                );

        String password =
                getEnvironmentVariable(
                        "MYSQLPASSWORD"
                );


        /*
         * ========================================================
         * ONLINE DATABASE
         * ========================================================
         */

        if (isValid(host)
                && isValid(port)
                && isValid(database)
                && isValid(user)
                && isValid(password)) {

            String onlineUrl =
                    "jdbc:mysql://"
                            + host
                            + ":"
                            + port
                            + "/"
                            + database
                            + "?useSSL=false"
                            + "&allowPublicKeyRetrieval=true"
                            + "&serverTimezone=UTC"
                            + "&connectTimeout=10000"
                            + "&socketTimeout=10000";

            try {

                System.out.println(
                        "Connecting to ONLINE MySQL..."
                );

                Connection connection =
                        DriverManager.getConnection(
                                onlineUrl,
                                user,
                                password
                        );

                System.out.println(
                        "Online MySQL connected successfully!"
                );

                return connection;

            } catch (SQLException e) {

                System.out.println(
                        "===================================="
                );

                System.out.println(
                        "ONLINE MYSQL CONNECTION FAILED"
                );

                System.out.println(
                        "===================================="
                );

                System.out.println(
                        "Error: "
                                + e.getMessage()
                );

                return null;
            }
        }


        /*
         * ========================================================
         * LOCAL DATABASE
         * ========================================================
         */

        try {

            System.out.println(
                    "Connecting to LOCAL MySQL..."
            );

            Connection connection =
                    DriverManager.getConnection(
                            LOCAL_URL,
                            LOCAL_USER,
                            LOCAL_PASSWORD
                    );

            System.out.println(
                    "Local MySQL connected successfully!"
            );

            return connection;

        } catch (SQLException e) {

            System.out.println(
                    "===================================="
            );

            System.out.println(
                    "MYSQL CONNECTION FAILED"
            );

            System.out.println(
                    "===================================="
            );

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );

            return null;
        }
    }


    /*
     * ============================================================
     * GET ENVIRONMENT VARIABLE
     * ============================================================
     */

    private static String getEnvironmentVariable(
            String name
    ) {

        String value =
                System.getenv(name);

        if (value == null) {
            return "";
        }

        return value.trim();
    }


    /*
     * ============================================================
     * CHECK VALUE
     * ============================================================
     */

    private static boolean isValid(
            String value
    ) {

        return value != null
                && !value.isEmpty();
    }


    /*
     * ============================================================
     * DATABASE TEST
     * ============================================================
     */

    public static void main(
            String[] args
    ) {

        Connection connection =
                getConnection();

        if (connection != null) {

            System.out.println(
                    "Database test successful!"
            );

            try {

                connection.close();

                System.out.println(
                        "Connection closed."
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }

        } else {

            System.out.println(
                    "Database test failed!"
            );
        }
    }
}