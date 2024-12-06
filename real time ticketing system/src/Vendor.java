public class Vendor extends Vendor_details implements Runnable {
    private final Ticket_pool_operation Vendor_Ticket_pool;
    private final int release_Rate;
    private final int total_Tickets_in_the_system;

    public Vendor(Ticket_pool_operation Vendor_Ticket_pool, int release_Rate, int total_Tickets, String vendor_name, int vendor_id) {
        super(vendor_name, vendor_id);
        this.Vendor_Ticket_pool = Vendor_Ticket_pool;
        this.release_Rate = release_Rate;
        this.total_Tickets_in_the_system = total_Tickets;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= total_Tickets_in_the_system; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Vendor thread exiting...");
                    break;
                }
                Vendor_Ticket_pool.Add_Ticket(i, "Vendor ID: " + getVendorId() + ", Vendor Name: " + getVendorName());
                Thread.sleep(release_Rate * 1000L);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting From Vendor thread...");
            Thread.currentThread().interrupt();
        }
    }

}
