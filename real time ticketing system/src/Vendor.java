public class Vendor extends Thread {
    private final Ticket_pool_operation Vendor_Ticket_pool;
    private final int release_Rate; // Release rate (per second)
    private final int total_Tickets; // Total tickets allocated to this vendor
    private final String vendorName;
    private final int vendorId;
    private final String vendorEmail;

    // Constructor for initializing vendor details and operations
    public Vendor(Ticket_pool_operation Vendor_Ticket_pool, int release_Rate, int total_Tickets,
                  String vendorName, int vendorId, String vendorEmail) {
        this.Vendor_Ticket_pool = Vendor_Ticket_pool;
        this.release_Rate = release_Rate;
        this.total_Tickets = total_Tickets;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.vendorEmail = vendorEmail;
    }

    // Getter methods for vendor details
    public String getVendorName() {
        return vendorName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    // Thread execution logic for releasing tickets
    @Override
    public void run() {
        try {
            for (int i = 0; i < total_Tickets; i++) {
                Vendor_Ticket_pool.Add_Ticket(i);
                System.out.println("Vendor " + vendorName + " added Ticket #" + i);
                Thread.sleep(release_Rate * 1000L); // Pause for release rate
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
