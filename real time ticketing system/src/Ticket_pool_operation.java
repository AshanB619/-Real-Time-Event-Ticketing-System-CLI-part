public interface Ticket_pool_operation {
    void Add_Ticket(int ticket_number,String Vendor_Details)throws InterruptedException;
    int Release_Ticket(String s)throws InterruptedException;

}
