import java.rmi.Naming;

public class AuctionClient {
    public static void main(String[] args) {
        try {
            AuctionServer server = (AuctionServer) Naming.lookup("//localhost/AuctionServer");
            double initialPrice = 100.0;
            
            System.out.println("Auction started for product XYZ!");
            System.out.println("Initial Price: " + initialPrice);
            
            while (true) {
                server.displayProduct();
                
                // Ask for bid
                System.out.print("Enter your bid (0 to exit): ");
                double bid = Double.parseDouble(System.console().readLine());
                
                if (bid == 0) {
                    System.out.println("Exiting auction...");
                    break;
                }
                
                // Compare bid
                server.askForBid(bid);
                
                // Accept bid
                if (server.acceptBid()) {
                    System.out.println("Bid accepted!");
                } else {
                    System.out.println("Bid rejected!");
                    continue;
                }
                
                // Confirm sale
                server.confirmSale();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

