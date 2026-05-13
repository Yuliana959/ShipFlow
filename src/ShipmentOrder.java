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

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.isBlank())
            throw new IllegalArgumentException("Order number must not be empty.");
        if (distanceKm <= 0)
            throw new IllegalArgumentException("Distance must be positive.");
    }

    private double applyInsurance(double price) {
        return insured ? price * 1.07 : price;
    }

    private void printProcessingResult() {
        System.out.printf("Processed [%s] for %s — total: %.2f PLN%n",
                orderNumber, customerName, lastCalculatedPrice);
    }

    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();

}