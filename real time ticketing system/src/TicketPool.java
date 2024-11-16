import java.util.List;
import java.util.*;

public class TicketPool  {
    private final List<Integer> Ticket;
    private final int Max_Tickek_capacity_for_pool;


    public TicketPool( int maxTickekCapacityForPool) {
        this.Ticket = new ArrayList<>();
        this.Max_Tickek_capacity_for_pool = maxTickekCapacityForPool;
    }

    public synchronized void Add_Ticket(int Ticket_Number)throws Exception{
        if (Ticket.size() >= Max_Tickek_capacity_for_pool) {
            wait();
        }else{
            Ticket.add(Ticket_Number);
            System.out.println("Ticket_Number "+Ticket_Number+" added to the pool |"+Ticket.size()+" Tickets available");
            notifyAll();

        }

    }
    public synchronized void Release_Ticket()throws Exception{
        if (Ticket.size() <=0) {
            wait();
        }else{
            Ticket.add(Ticket_Number);
            System.out.println("Ticket_Number "+Ticket_Number+" added to the pool |"+Ticket.size()+" Tickets available");
            notifyAll();

        }

    }



}
