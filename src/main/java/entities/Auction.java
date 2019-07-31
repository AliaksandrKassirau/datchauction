package entities;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Auction {
    private int ID;
    private String description;
    private AuctionStatus status;
    private List<Lot> lots;
    private List<User> followers;
    private int maxLots;
    private int tick;   // is seconds
    private LocalTime beginTime;
    private int maxDuration;    //in minutes
    public Auction(int ID, String description, int tick, int maxLots, LocalTime beginTime, int maxDuration){
        this.ID = ID;
        this.description = description;
        status=AuctionStatus.Disabled;
        lots = new ArrayList<Lot>();
        followers = new ArrayList<User>();
        this.maxLots = maxLots;
        this.tick = tick;
        this.beginTime = beginTime;
        this.maxDuration=maxDuration;
    }
    public boolean makePlaned(){
        if(status==AuctionStatus.Disabled) {
            status = AuctionStatus.Planed;
            return true;
        }
        return false;
    }
    public void createLot(double startPrice, double priceStep, double minPrice, Item[] items) throws Exception {
        if(maxLots==lots.size())throw new Exception("Max lots are reached");
        if(status!=AuctionStatus.Planed)throw new Exception("Auction is not planed yet");
        lots.add(new Lot(startPrice, priceStep, minPrice, items));
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

}
