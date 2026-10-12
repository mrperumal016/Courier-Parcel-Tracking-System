import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class TrackingServer {

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) throws Exception {

        int port = 8080;

        String portString = System.getenv("PORT");

        if (portString != null && !portString.isEmpty()) {

            try {
                port = Integer.parseInt(portString);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid PORT. Using 8080."
                );

                port = 8080;
            }
        }

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(
                                "0.0.0.0",
                                port
                        ),
                        0
                );

        server.createContext(
                "/",
                TrackingServer::handleHome
        );

        server.createContext(
                "/track",
                TrackingServer::handleTrack
        );

        server.setExecutor(null);

        server.start();

        System.out.println(
                "======================================"
        );

        System.out.println(
                "LogiTrack Tracking Server Started"
        );

        System.out.println(
                "Port: " + port
        );

        System.out.println(
                "======================================"
        );
    }

    // =====================================================
    // HOME PAGE
    // =====================================================

    private static void handleHome(
            HttpExchange exchange
    ) {

        try {

            String html = """
                    <!DOCTYPE html>
                    <html>

                    <head>

                        <meta charset="UTF-8">

                        <meta name="viewport"
                              content="width=device-width,
                              initial-scale=1.0">

                        <title>
                            LogiTrack - Track Parcel
                        </title>

                        <style>

                            * {
                                box-sizing: border-box;
                            }

                            body {
                                margin: 0;
                                font-family: Arial, sans-serif;
                                background: #f5f7fa;
                            }

                            .header {
                                background: #193d63;
                                color: white;
                                padding: 25px 50px;
                                display: flex;
                                justify-content: space-between;
                                align-items: center;
                            }

                            .logo {
                                font-size: 30px;
                                font-weight: bold;
                            }

                            .title {
                                font-size: 24px;
                                font-weight: bold;
                            }

                            .container {
                                width: 650px;
                                max-width: 90%;
                                margin: 80px auto;
                                background: white;
                                padding: 40px;
                                border-radius: 12px;
                                box-shadow:
                                    0 5px 20px
                                    rgba(0,0,0,0.10);
                            }

                            h1 {
                                color: #193d63;
                                text-align: center;
                                margin-bottom: 10px;
                            }

                            p {
                                text-align: center;
                                color: #777;
                                font-size: 15px;
                            }

                            input {
                                width: 100%;
                                padding: 15px;
                                font-size: 16px;
                                border: 1px solid #ccd5df;
                                border-radius: 6px;
                                margin-top: 20px;
                                outline: none;
                            }

                            input:focus {
                                border-color: #1289eb;
                            }

                            button {
                                width: 100%;
                                padding: 15px;
                                margin-top: 20px;
                                background: #1289eb;
                                color: white;
                                border: none;
                                border-radius: 6px;
                                font-size: 16px;
                                font-weight: bold;
                                cursor: pointer;
                            }

                            button:hover {
                                background: #0876cf;
                            }

                            @media (max-width: 600px) {

                                .header {
                                    padding: 20px;
                                }

                                .logo {
                                    font-size: 24px;
                                }

                                .title {
                                    font-size: 18px;
                                }

                                .container {
                                    margin-top: 40px;
                                    padding: 25px;
                                }
                            }

                        </style>

                    </head>

                    <body>

                        <div class="header">

                            <div class="logo">
                                LogiTrack
                            </div>

                            <div class="title">
                                Track Your Parcel
                            </div>

                        </div>

                        <div class="container">

                            <h1>
                                Track Your Parcel
                            </h1>

                            <p>
                                Enter your Tracking Number
                            </p>

                            <form
                                action="/track"
                                method="get"
                            >

                                <input
                                    type="text"
                                    name="tracking"
                                    placeholder="Example: LT202609200001"
                                    autocomplete="off"
                                    required
                                >

                                <button
                                    type="submit"
                                >
                                    TRACK PARCEL
                                </button>

                            </form>

                        </div>

                    </body>

                    </html>
                    """;

            sendResponse(
                    exchange,
                    html,
                    200
            );

        } catch (Exception e) {

            e.printStackTrace();

            sendError(
                    exchange,
                    e
            );
        }
    }

    // =====================================================
    // TRACK PARCEL
    // =====================================================

    private static void handleTrack(
            HttpExchange exchange
    ) {

        try {

            String query =
                    exchange
                            .getRequestURI()
                            .getQuery();

            System.out.println(
                    "Received request: " + query
            );

            String trackingId = "";

            if (query != null) {

                String[] parameters =
                        query.split("&");

                for (
                        String parameter :
                        parameters
                ) {

                    if (
                            parameter.startsWith(
                                    "tracking="
                            )
                    ) {

                        trackingId =
                                URLDecoder.decode(
                                        parameter.substring(
                                                "tracking=".length()
                                        ),
                                        StandardCharsets.UTF_8
                                ).trim();

                        break;
                    }
                }
            }

            System.out.println(
                    "Tracking ID: " + trackingId
            );

            if (trackingId.isEmpty()) {

                sendResponse(
                        exchange,
                        errorPage(
                                "Please enter a tracking number."
                        ),
                        400
                );

                return;
            }

            // =================================================
            // DATABASE
            // =================================================

            ParcelManager manager =
                    new ParcelManager();

            Parcel parcel =
                    manager.findByTrackingId(
                            trackingId
                    );

            // =================================================
            // NOT FOUND
            // =================================================

            if (parcel == null) {

                System.out.println(
                        "Tracking number not found: "
                                + trackingId
                );

                sendResponse(
                        exchange,
                        errorPage(
                                "Tracking number not found."
                        ),
                        404
                );

                return;
            }

            // =================================================
            // FOUND
            // =================================================

            System.out.println(
                    "Parcel found: "
                            + parcel.getParcelId()
            );

            String html =
                    createParcelResultPage(
                            parcel
                    );

            sendResponse(
                    exchange,
                    html,
                    200
            );

        } catch (Exception e) {

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "ERROR IN TRACKING SERVER"
            );

            System.out.println(
                    "======================================"
            );

            e.printStackTrace();

            sendError(
                    exchange,
                    e
            );
        }
    }

    // =====================================================
    // RESULT PAGE
    // =====================================================

    private static String createParcelResultPage(
            Parcel parcel
    ) {

        String parcelId =
                escapeHtml(
                        parcel.getParcelId()
                );

        String trackingId =
                escapeHtml(
                        parcel.getTrackingId()
                );

        String sender =
                escapeHtml(
                        parcel.getSenderName()
                );

        String receiver =
                escapeHtml(
                        parcel.getReceiverName()
                );

        String source =
                escapeHtml(
                        parcel.getSource()
                );

        String destination =
                escapeHtml(
                        parcel.getDestination()
                );

        String weight =
                escapeHtml(
                        String.valueOf(
                                parcel.getWeight()
                        )
                );

        String status =
                escapeHtml(
                        parcel.getStatus()
                );

        String html = """
                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width,
                          initial-scale=1.0">

                    <title>
                        LogiTrack - Parcel Tracking
                    </title>

                    <style>

                        * {
                            box-sizing: border-box;
                        }

                        body {
                            margin: 0;
                            font-family: Arial, sans-serif;
                            background: #f5f7fa;
                        }

                        .header {
                            background: #193d63;
                            color: white;
                            padding: 25px 50px;
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                        }

                        .logo {
                            font-size: 30px;
                            font-weight: bold;
                        }

                        .title {
                            font-size: 24px;
                            font-weight: bold;
                        }

                        .container {
                            width: 800px;
                            max-width: 90%%;
                            margin: 50px auto;
                            background: white;
                            padding: 35px;
                            border-radius: 12px;
                            box-shadow:
                                0 5px 20px
                                rgba(0,0,0,0.10);
                        }

                        h1 {
                            color: #193d63;
                            text-align: center;
                            margin-bottom: 25px;
                        }

                        table {
                            width: 100%%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }

                        td {
                            padding: 15px;
                            border-bottom:
                                1px solid #eeeeee;
                        }

                        td:first-child {
                            font-weight: bold;
                            color: #777777;
                            width: 35%%;
                        }

                        td:last-child {
                            color: #193d63;
                            font-weight: 500;
                        }

                        .status {
                            color: #1289eb !important;
                            font-weight: bold !important;
                        }

                        .back {
                            display: block;
                            width: 220px;
                            text-align: center;
                            margin: 30px auto 0;
                            padding: 12px;
                            background: #1289eb;
                            color: white;
                            text-decoration: none;
                            border-radius: 6px;
                            font-weight: bold;
                        }

                        .back:hover {
                            background: #0876cf;
                        }

                        @media (max-width: 600px) {

                            .header {
                                padding: 20px;
                            }

                            .logo {
                                font-size: 24px;
                            }

                            .title {
                                font-size: 18px;
                            }

                            .container {
                                margin-top: 30px;
                                padding: 20px;
                            }

                            td {
                                padding: 10px;
                            }
                        }

                    </style>

                </head>

                <body>

                    <div class="header">

                        <div class="logo">
                            LogiTrack
                        </div>

                        <div class="title">
                            Parcel Tracking
                        </div>

                    </div>

                    <div class="container">

                        <h1>
                            Parcel Details
                        </h1>

                        <table>

                            <tr>
                                <td>
                                    Parcel ID
                                </td>

                                <td>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Tracking Number
                                </td>

                                <td>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Sender
                                </td>

                                <td>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Receiver
                                </td>

                                <td>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Source
                                </td>

                                <td>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Destination
                                </td>

                                <td>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Weight
                                </td>

                                <td>
                                    %s kg
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    Status
                                </td>

                                <td class="status">
                                    %s
                                </td>
                            </tr>

                        </table>

                        <a
                            class="back"
                            href="/"
                        >
                            Track Another Parcel
                        </a>

                    </div>

                </body>

                </html>
                """;

        /*
         * IMPORTANT:
         * The % signs in CSS are written as %% above
         * because String.formatted() is used.
         */

        return String.format(
                html,
                parcelId,
                trackingId,
                sender,
                receiver,
                source,
                destination,
                weight,
                status
        );
    }

    // =====================================================
    // HTML ESCAPE
    // =====================================================

    private static String escapeHtml(
            String value
    ) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    // =====================================================
    // ERROR PAGE
    // =====================================================

    private static String errorPage(
            String message
    ) {

        String safeMessage =
                escapeHtml(message);

        return """
                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width,
                          initial-scale=1.0">

                    <title>
                        LogiTrack - Error
                    </title>

                    <style>

                        body {
                            margin: 0;
                            font-family: Arial, sans-serif;
                            background: #f5f7fa;
                            text-align: center;
                        }

                        .box {
                            background: white;
                            width: 500px;
                            max-width: 90%%;
                            margin: 100px auto;
                            padding: 40px;
                            border-radius: 12px;
                            box-shadow:
                                0 5px 20px
                                rgba(0,0,0,0.10);
                        }

                        h2 {
                            color: #c0392b;
                        }

                        p {
                            color: #777;
                        }

                        a {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 12px 25px;
                            background: #1289eb;
                            color: white;
                            text-decoration: none;
                            border-radius: 6px;
                            font-weight: bold;
                        }

                        a:hover {
                            background: #0876cf;
                        }

                    </style>

                </head>

                <body>

                    <div class="box">

                        <h2>
                            %s
                        </h2>

                        <p>
                            Please check your
                            tracking number and
                            try again.
                        </p>

                        <a href="/">
                            Track Another Parcel
                        </a>

                    </div>

                </body>

                </html>
                """.formatted(
                safeMessage
        );
    }

    // =====================================================
    // SEND RESPONSE
    // =====================================================

    private static void sendResponse(
            HttpExchange exchange,
            String html,
            int statusCode
    ) {

        try {

            byte[] response =
                    html.getBytes(
                            StandardCharsets.UTF_8
                    );

            exchange.getResponseHeaders()
                    .set(
                            "Content-Type",
                            "text/html; charset=UTF-8"
                    );

            exchange.sendResponseHeaders(
                    statusCode,
                    response.length
            );

            try (
                    OutputStream output =
                            exchange.getResponseBody()
            ) {

                output.write(response);

                output.flush();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // SERVER ERROR
    // =====================================================

    private static void sendError(
            HttpExchange exchange,
            Exception e
    ) {

        try {

            String html =
                    errorPage(
                            "Server Error: "
                                    + e.getClass()
                                    .getSimpleName()
                    );

            sendResponse(
                    exchange,
                    html,
                    500
            );

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}