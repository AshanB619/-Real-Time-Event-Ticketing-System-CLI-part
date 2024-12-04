public class Customer extends Customer_details implements Runnable {
    private final Ticket_pool_operation Customer_ticket_pool;
    private final int retrieval_Rate; //retrieval rate of the customer
    private final int total_Tickets_to_buy;


    public Customer(Ticket_pool_operation Customer_ticket_pool, int retrieval_Rate,int total_Tickets,String customer_Name,int Customer_id) {
        super(customer_Name,Customer_id);
        this.Customer_ticket_pool = Customer_ticket_pool;
        this.retrieval_Rate = retrieval_Rate;
        this.total_Tickets_to_buy = total_Tickets;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= total_Tickets_to_buy; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                int ticket_number = Customer_ticket_pool.Release_Ticket( "Customer ID: " + getCustomerId() + ", Customer Name: " + getCustomerName());
                Thread.sleep(retrieval_Rate * 1000L);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted due to |" + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
