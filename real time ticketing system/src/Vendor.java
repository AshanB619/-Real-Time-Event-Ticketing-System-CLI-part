public class Vendor implements Runnable {
    private TicketPool ticketPool_for_vendor;
    private int release_Rate;
    private int total_Tickets;


    public Vendor(TicketPool ticketPool, int release_Rate, int total_Tickets) {
        this.ticketPool_for_vendor = ticketPool;
        this.release_Rate = release_Rate;
        this.total_Tickets = total_Tickets;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < total_Tickets; i++) {
                ticketPool_for_vendor.Add_Ticket(i);
                Thread.sleep(release_Rate);
            }
        }catch (Exception e){
            System.out.println("Thread interrupted due to |"+e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
