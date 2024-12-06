import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;


public class TicketPool implements Ticket_pool_operation  {
    private final List<Integer> ticket_list; //list for store tickets
    private final int max_Ticket_Capacity_For_Pool; //variable for maximum tickets that pool can hold


    public TicketPool( int maxTickekCapacityForPool) {
        this.ticket_list = new ArrayList<>(maxTickekCapacityForPool);
        this.max_Ticket_Capacity_For_Pool = maxTickekCapacityForPool;
    }



    // use synchronized for thread safety
    public synchronized void Add_Ticket(int Ticket_Number,String Vendor_Details)throws InterruptedException{
        while(ticket_list.size() >= max_Ticket_Capacity_For_Pool) { //check current size of ticket list
            wait(); // if current size is grater than max capacity put the current thread into waiting list
        }
        ticket_list.add(Ticket_Number); //add ticket into list
        String date_time_for_add=get_Time_Date();
        System.out.println("Ticket_Number " + Ticket_Number + " added to the pool by " + Vendor_Details + " | " + ticket_list.size() + " Tickets available |"+date_time_for_add+"|");
        notifyAll(); //notify waiting threads.

    }
    // use synchronized for thread safety
    public synchronized int Release_Ticket(String Customer_details)throws InterruptedException{
        while (ticket_list.size() <=0){ //check is list is empty
            wait(); // make thread wait until new ticket is added

        }
        int Ticket_Number = ticket_list.remove(0); //remove first ticket in list
        String date_time_for_remove=get_Time_Date();
        System.out.println("Ticket_Number " + Ticket_Number + " bought from the pool by " + Customer_details + " | " + ticket_list.size() + " Tickets available|"+date_time_for_remove+"|");
        notifyAll();//notify waiting threads.
        return Ticket_Number; // return ticket number that sold
    }

    private String get_Time_Date(){
        DateTimeFormatter Date_time=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(Date_time);
    }



}
