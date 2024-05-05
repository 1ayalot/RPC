import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface for the auction server
public interface AuctionServer extends Remote {
    void displayProduct() throws RemoteException;
    void askForBid(double price) throws RemoteException;
    void comparePrices(double price) throws RemoteException;
    boolean acceptBid() throws RemoteException;
    void confirmSale() throws RemoteException;
}

