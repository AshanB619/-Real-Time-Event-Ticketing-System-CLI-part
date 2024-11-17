public class Customer extends Thread {
    private final Ticket_pool_operation Customer_ticket_pool;
    private final int retrieval_Rate;

    public Customer(Ticket_pool_operation Customer_ticket_pool, int retrieval_Rate) {
        this.Customer_ticket_pool = Customer_ticket_pool;
        this.retrieval_Rate = retrieval_Rate;
    }

    public void run() {
        try {
            while (true){
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                int ticket_number=Customer_ticket_pool.Release_Ticket();
                System.out.println("Ticket number: "+ticket_number+" Sold");
                Thread.sleep(2000/retrieval_Rate);

            }

        }catch (InterruptedException e){
            System.out.println("Thread interrupted due to |"+e.getMessage());//if their any exception show to user
            Thread.currentThread().interrupt();//make thread Interrupt if there is any error
        }

    }

}
