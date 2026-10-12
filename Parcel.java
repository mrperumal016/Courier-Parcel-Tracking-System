public class Parcel {

    private String parcelId;
    private String trackingId;

    private String senderName;
    private String senderAddress;

    private String receiverName;
    private String receiverAddress;

    private String source;
    private String destination;

    private double weight;
    private String status;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Parcel(
            String parcelId,
            String trackingId,
            String senderName,
            String receiverName,
            String source,
            String destination,
            double weight,
            String status,
            String senderAddress,
            String receiverAddress) {

        this.parcelId =
                parcelId;

        this.trackingId =
                trackingId;

        this.senderName =
                senderName;

        this.senderAddress =
                senderAddress;

        this.receiverName =
                receiverName;

        this.receiverAddress =
                receiverAddress;

        this.source =
                source;

        this.destination =
                destination;

        this.weight =
                weight;

        this.status =
                status;
    }


    // =====================================================
    // GET PARCEL ID
    // =====================================================

    public String getParcelId() {

        return parcelId;
    }


    // =====================================================
    // GET TRACKING ID
    // =====================================================

    public String getTrackingId() {

        return trackingId;
    }


    // =====================================================
    // GET SENDER NAME
    // =====================================================

    public String getSenderName() {

        return senderName;
    }


    // =====================================================
    // GET SENDER ADDRESS
    // =====================================================

    public String getSenderAddress() {

        return senderAddress;
    }


    // =====================================================
    // GET RECEIVER NAME
    // =====================================================

    public String getReceiverName() {

        return receiverName;
    }


    // =====================================================
    // GET RECEIVER ADDRESS
    // =====================================================

    public String getReceiverAddress() {

        return receiverAddress;
    }


    // =====================================================
    // GET SOURCE
    // =====================================================

    public String getSource() {

        return source;
    }


    // =====================================================
    // GET DESTINATION
    // =====================================================

    public String getDestination() {

        return destination;
    }


    // =====================================================
    // GET WEIGHT
    // =====================================================

    public double getWeight() {

        return weight;
    }


    // =====================================================
    // GET STATUS
    // =====================================================

    public String getStatus() {

        return status;
    }


    // =====================================================
    // SET STATUS
    // =====================================================

    public void setStatus(
            String status
    ) {

        this.status =
                status;
    }
}