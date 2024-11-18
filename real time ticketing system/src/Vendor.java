public class Vendor extends Thread {
    private final Ticket_pool_operation Vendor_Ticket_pool;
    private final int release_Rate; //release rate
    private final int total_Tickets;//total ticket


    public Vendor(Ticket_pool_operation Vendor_Ticket_pool, int release_Rate, int total_Tickets) {
        this.Vendor_Ticket_pool = Vendor_Ticket_pool;
        this.release_Rate = release_Rate;
        this.total_Tickets = total_Tickets;
    }


    @Override
    public void run() {
        try {

            for (int i = 0; i < total_Tickets; i++) {
                //add vendors ticket to ticketpool
                Vendor_Ticket_pool.Add_Ticket(i);
                //pause the thread for specific time
                Thread.sleep(1000);
            }
        }catch (Exception e){
            System.out.println("Thread interrupted due to  |"+e.getMessage());//if their any exception show to user
            Thread.currentThread().interrupt();//make thread Interrupt if there is any error
        }
    }


}
