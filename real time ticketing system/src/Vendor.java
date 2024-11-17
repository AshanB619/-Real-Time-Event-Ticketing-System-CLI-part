public class Vendor implements Runnable {
    private final TicketPool ticketPool_for_vendor;
    private final int release_Rate; //release rate
    private final int total_Tickets;//total ticket vendor can release at atime


    public Vendor(TicketPool ticketPool, int release_Rate, int total_Tickets) {
        this.ticketPool_for_vendor = ticketPool;
        this.release_Rate = release_Rate;
        this.total_Tickets = total_Tickets;
    }
    @Override
    public void run() {
        try {

            for (int i = 0; i < total_Tickets; i++) {
                //add vendors ticket to ticketpool
                ticketPool_for_vendor.Add_Ticket(i);
                //pause the thread for specific time
                Thread.sleep(release_Rate);
            }
        }catch (Exception e){
            System.out.println("Thread interrupted due to |"+e.getMessage());//if their any exception show to user
            Thread.currentThread().interrupt();//make thread Interrupt if there is any error
        }
    }

}
