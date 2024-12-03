public class Customer extends Thread {
    private final Ticket_pool_operation Customer_ticket_pool;
    private final int retrieval_Rate; //retrieval rate of the customer


    public Customer(Ticket_pool_operation Customer_ticket_pool, int retrieval_Rate) {
        this.Customer_ticket_pool = Customer_ticket_pool;
        this.retrieval_Rate = retrieval_Rate;
    }

    public void run() {
        try {
            while (true){
                if (Thread.currentThread().isInterrupted()) { //check whether thread is interrupted
                    break; //if thread id interrupted use break
                }
                //retrieve ticket from list
                int ticket_number=Customer_ticket_pool.Release_Ticket(); //get the ticket number of released ticket
                System.out.println("Ticket number: "+ticket_number+" Sold");//show its to user
                //pause the thread for specific time
                Thread.sleep(retrieval_Rate * 1000L);

            }

        }catch (InterruptedException e){
            System.out.println("Thread interrupted due to |"+e.getMessage());//if their any exception show to user
            Thread.currentThread().interrupt();//make thread Interrupt if there is any error
        }

    }

}
