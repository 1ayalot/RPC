import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuctionServerImpl extends UnicastRemoteObject implements AuctionServer {
    private double currentPrice;
    private boolean sold;

    protected AuctionServerImpl() throws RemoteException {
        currentPrice = 0.0;
        sold = false;
    }

    @Override
    public void displayProduct() throws RemoteException {
        if (!sold) {
            System.out.println("Product: XYZ, Current Price: " + currentPrice);
        } else {
            System.out.println("Product: XYZ, Sold for: " + currentPrice);
        }
    }

    @Override
    public void askForBid(double price) throws RemoteException {
        System.out.println("Asking for bid...");
        comparePrices(price);
    }

    @Override
    public void comparePrices(double price) throws RemoteException {
        if (price > currentPrice) {
            currentPrice = price;
            System.out.println("New bid accepted: " + currentPrice);
        } else {
            System.out.println("Bid too low. Current Price: " + currentPrice);
        }
    }

    @Override
    public boolean acceptBid() throws RemoteException {
        return true; // Accept any bid without registration
    }

    @Override
    public void confirmSale() throws RemoteException {
        sold = true;
        System.out.println("Product sold for: " + currentPrice);
    }

    public static void main(String[] args) {
        try {
            AuctionServer server = new AuctionServerImpl();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("AuctionServer", server);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

