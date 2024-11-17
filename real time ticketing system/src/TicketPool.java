import java.util.List;
import java.util.*;

public class TicketPool  {
    private final List<Integer> ticket_list; //list for store tickets
    private final int max_Ticket_Capacity_For_Pool; //variable for maximum tickets that pool can hold


    public TicketPool( int maxTickekCapacityForPool) {
        this.ticket_list = new ArrayList<>();
        this.max_Ticket_Capacity_For_Pool = maxTickekCapacityForPool;
    }

    // use synchronized for thread safety
    public synchronized void Add_Ticket(int Ticket_Number)throws Exception{
        while(ticket_list.size() >= max_Ticket_Capacity_For_Pool) { //check current size of ticket list
            wait(); // if current size is grater than max capacity put the current thread into waiting list
        }
        ticket_list.add(Ticket_Number); //add ticket into list
        System.out.println("Ticket_Number "+Ticket_Number+" added to the pool |"+ ticket_list.size()+" Tickets available");
        notifyAll(); //notify waiting threads.

    }
    // use synchronized for thread safety
    public synchronized int Release_Ticket()throws Exception{
        while (ticket_list.size() <=0){ //check is list is empty
            wait(); // make thread wait until new ticket is added
        }
        int Ticket_Number = ticket_list.remove(0); //remove first ticket in list
        System.out.println("Ticket_Number "+Ticket_Number+" sold |"+ ticket_list.size()+" Tickets available");
        notifyAll();//notify waiting threads.
        return Ticket_Number; // return ticket number that sold
    }



}
