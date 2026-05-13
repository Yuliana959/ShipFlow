public class InternationalShipment extends ShipmentOrder {

    private final String destinationCountry;
    private final boolean customsDocumentsRequired;
    private final boolean expressDelivery;

    public InternationalShipment(String orderNumber, String customerName,
                                 double distanceKm, double baseFee, boolean insured,
                                 String destinationCountry,
                                 boolean customsDocumentsRequired,
                                 boolean expressDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry = destinationCountry;
        this.customsDocumentsRequired = customsDocumentsRequired;
        this.expressDelivery = expressDelivery;
    }

    @Override
    protected void validateSpecificRules() {
        if (destinationCountry == null || destinationCountry.isBlank())
            throw new IllegalArgumentException("Destination country must not be empty.");
    }

    @Override
    public String getShipmentType() { return "International"; }

    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 2.10;
    }

    @Override
    protected double calculateAdditionalFee() {
        double fee = 0;
        if (customsDocumentsRequired) fee += 45.0;
        if (expressDelivery) fee += 80.0;
        return fee;
    }

    @Override
    protected double applyBusinessDiscount(double price) {
        if (!expressDelivery && distanceKm > 1000) return price * 0.97;
        return price;
    }
}