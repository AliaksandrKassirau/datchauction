package entities;


public class Lot {
    private double currentPrice;
    private double priceStep;
    private double minPrice;
    private Item[] items;
    private LotStatus status;
    public Lot(double startPrice, double priceStep, double minPrice, Item[] items){
        this.currentPrice = startPrice;
        this.priceStep = priceStep;
        this.minPrice = minPrice;
        this.items = items;
        status = LotStatus.Registered;
    }
}
