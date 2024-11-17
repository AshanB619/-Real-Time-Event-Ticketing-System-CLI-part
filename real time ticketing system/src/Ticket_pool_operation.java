public interface Ticket_pool_operation {
    void Add_Ticket(int ticket_number)throws InterruptedException;
    int Release_Ticket()throws InterruptedException;
}
