package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class AuctionsManager {
    private Statement st;
    private int aCount;
    public AuctionsManager(Statement st) throws SQLException {
        this.st = st;
    }
    public Auction getAuctionById(int ID) throws SQLException, AuctionException {
        ResultSet rs = st.executeQuery("SELECT * FROM auctions WHERE ID="+ID);
        if(rs.next())
            return new Auction(ID,rs.getString("description"),rs.getInt("tick"),rs.getInt("naxLots"),rs.getTime("beginTime").toLocalTime(),rs.getInt("maxDuration"));
        else throw new AuctionException("Auction not found",31);
    }
    public void addAuction(String description, int tick, int maxLots, LocalTime beginTime,int maxDuration) throws SQLException {
        st.execute("INSERT INTO auctions VALUE ("+(aCount+1)+", \'"+ description + "\', "+tick+", "+maxLots+", \'"+beginTime+"\', "+maxDuration + ")");
    }
    public Map<Integer,Auction> getAuctions() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM auctions");
        Map<Integer,Auction> auctionMap = new HashMap<Integer, Auction>();
        while (rs.next()){
            auctionMap.put(rs.getInt("ID"),new Auction(rs.getInt("ID"),rs.getString("description"),rs.getInt("tick"),rs.getInt("naxLots"),rs.getTime("beginTime").toLocalTime(),rs.getInt("maxDuration")));
        }
        return auctionMap;
    }
}
