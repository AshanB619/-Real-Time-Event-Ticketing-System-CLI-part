public class Vendor extends Vendor_details implements Runnable {
    private final Ticket_pool_operation Vendor_Ticket_pool; // reference to the ticket pool operation interface
    private final int release_Rate; // rate at which tickets are released by the vendor
    private final int total_Tickets_in_the_system; // total number of tickets the vendor will add to the system

    // Constructor to initialize the Vendor object with ticket pool, release rate, total tickets, vendor name, and ID
    public Vendor(Ticket_pool_operation Vendor_Ticket_pool, int release_Rate, int total_Tickets, String vendor_name, int vendor_id) {
        super(vendor_name, vendor_id); // call the superclass constructor to initialize vendor details
        this.Vendor_Ticket_pool = Vendor_Ticket_pool; // set the ticket pool operation reference
        this.release_Rate = release_Rate; // set the release rate
        this.total_Tickets_in_the_system = total_Tickets; // set the total tickets the vendor will add
    }

    // Method to define the behavior of the vendor thread
    @Override
    public void run() {
        try {
            // Loop to add tickets to the pool up to the total number of tickets
            for (int i = 1; i <= total_Tickets_in_the_system; i++) {
                if (Thread.currentThread().isInterrupted()) { // check if the current thread is interrupted
                    System.out.println("Vendor thread exiting...");
                    break;
                }
                // Add a ticket to the pool and log the transaction details
                Vendor_Ticket_pool.Add_Ticket(i, "Vendor ID: " + getVendorId() + ", Vendor Name: " + getVendorName());
                Thread.sleep(release_Rate * 1000L); // pause the thread for the specified release rate
            }
        } catch (InterruptedException e) { // handle interruption exception
            System.out.println("Exiting From Vendor thread...");
            Thread.currentThread().interrupt(); // set the thread's interrupted status
        }
    }

}
