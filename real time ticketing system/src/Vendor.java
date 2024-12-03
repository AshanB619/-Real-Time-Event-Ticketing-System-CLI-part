public class Vendor extends Vendor_details implements Runnable {
    private final Ticket_pool_operation Vendor_Ticket_pool;
    private final int release_Rate; //release rate
    private final int total_Tickets_in_the_system;//total ticket


    public Vendor(Ticket_pool_operation Vendor_Ticket_pool, int release_Rate, int total_Tickets,String vendor_name,int vendor_id) {
        super(vendor_name,vendor_id);
        this.Vendor_Ticket_pool = Vendor_Ticket_pool;
        this.release_Rate = release_Rate;
        this.total_Tickets_in_the_system = total_Tickets;
    }

    @Override
    public void run() {
        try {

            for (int i = 1; i <= total_Tickets_in_the_system; i++) {
                //add vendors ticket to ticketpool
                Vendor_Ticket_pool.Add_Ticket(i, "Vendor ID: " + getVendorId() + ", Vendor Name: " + getVendorName());
                //pause the thread for specific time
                Thread.sleep(release_Rate* 1000L);
            }
        }catch (Exception e){
            System.out.println("Thread interrupted due to  |"+e.getMessage());//if their any exception show to user
            Thread.currentThread().interrupt();//make thread Interrupt if there is any error
        }
    }


}
