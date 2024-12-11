import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;


public class TicketPool implements Ticket_pool_operation  {
    private final List<Integer> ticket_list; //list for store tickets
    private final int max_Ticket_Capacity_For_Pool; //variable for maximum tickets that pool can hold
    private final BufferedWriter Transaction_writer; //for logging transactions
    private static int count_for_ticket_number=1;//for ticket count

    // Constructor to initialize the TicketPool with a maximum capacity and transaction writer
    public TicketPool( int maxTickekCapacityForPool,BufferedWriter Transaction_writer) {
        this.ticket_list = new ArrayList<>(maxTickekCapacityForPool); // initialize the ticket list
        this.max_Ticket_Capacity_For_Pool = maxTickekCapacityForPool; // set the maximum ticket capacity
        this.Transaction_writer = Transaction_writer;
    }



    // use synchronized for thread safety
    public synchronized void Add_Ticket(int Ticket_Number,String Vendor_Details)throws InterruptedException{
        while(ticket_list.size() >= max_Ticket_Capacity_For_Pool) { //check current size of ticket list
            wait(); // if current size is grater than max capacity put the current thread into waiting list
        }
        Ticket_Number = count_for_ticket_number++;
        ticket_list.add(Ticket_Number); //add ticket into list
        String date_time_for_add=get_Time_Date(); // get current timestamp for logging
        System.out.println("Ticket_Number " + Ticket_Number + " added to the pool by " + Vendor_Details + " | " + ticket_list.size() + " Tickets available |"+date_time_for_add+"|");
        LOG_writer("Ticket_Number " + Ticket_Number + " added to the pool by " + Vendor_Details + " | " + ticket_list.size() + " Tickets available |"+date_time_for_add+"|");
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
        LOG_writer("Ticket_Number " + Ticket_Number + " bought from the pool by " + Customer_details + " | " + ticket_list.size() + " Tickets available|"+date_time_for_remove+"|");
        notifyAll();//notify waiting threads.
        return Ticket_Number; // return ticket number that sold
    }

    // Helper method to get the current date and time in the format "yyyy-MM-dd HH:mm:ss"
    private String get_Time_Date(){
        DateTimeFormatter Date_time=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(Date_time);
    }

    // Helper method to log messages to the transaction writer
    private void LOG_writer(String message){
        try {
            Transaction_writer.write(message+"\n");
        }catch (IOException e){
            System.out.println("Error while writing file");
        }
    }



}
