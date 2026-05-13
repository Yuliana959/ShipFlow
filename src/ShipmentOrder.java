public abstract class ShipmentOrder implements SummaryPrintable {

    private final String orderNumber;
    private final String customerName;
    protected final double distanceKm;
    protected final double baseFee;
    private final boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName,
                         double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public String getOrderNumber()         { return orderNumber; }
    public String getCustomerName()        { return customerName; }
    public double getLastCalculatedPrice() { return lastCalculatedPrice; }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();
}