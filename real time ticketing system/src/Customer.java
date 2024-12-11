public class Customer extends Customer_details implements Runnable {
    private final Ticket_pool_operation Customer_ticket_pool; // reference to the ticket pool operation interface
    private final int retrieval_Rate; // rate at which tickets are retrieved
    private final int total_Tickets_to_buy; // total number of tickets the customer wants to buy

    // Constructor to initialize the Customer object with ticket pool, retrieval rate, total tickets, name, and ID
    public Customer(Ticket_pool_operation Customer_ticket_pool, int retrieval_Rate, int total_Tickets, String customer_Name, int Customer_id) {
        super(customer_Name, Customer_id); // call the superclass constructor to initialize customer details
        this.Customer_ticket_pool = Customer_ticket_pool; // set the ticket pool operation reference
        this.retrieval_Rate = retrieval_Rate; // set the retrieval rate
        this.total_Tickets_to_buy = total_Tickets; // set the total tickets to buy
    }

    // Method to define the behavior of the customer thread
    @Override
    public void run() {
        try {
            // Loop through the total tickets the customer wants to buy
            for (int i = 1; i <= total_Tickets_to_buy; i++) {
                if (Thread.currentThread().isInterrupted()) { // check if the current thread is interrupted
                    System.out.println("Customer thread exiting..."); // message indicating thread exit
                    break;
                }
                // Attempt to release a ticket from the pool and log the transaction details
                int ticket_number = Customer_ticket_pool.Release_Ticket("Customer ID: " + getCustomerId() + ", Customer Name: " + getCustomerName());
                Thread.sleep(retrieval_Rate * 1000L);
            }
        } catch (InterruptedException e) { // handle interruption exception
            System.out.println("Exiting From Customer thread...");
            Thread.currentThread().interrupt();  // set the thread's interrupted status
        }
    }
}
