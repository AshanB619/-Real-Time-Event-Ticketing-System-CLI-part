public class SystemConfig {
    private int Total_Number_of_Tickets; // total number of tickets available in the system
    private int Tickets_Release_rate;  // rate at which tickets are released by vendors (per second)
    private int Customer_Retrieval_Rate; // rate at which tickets are retrieved by customers
    private int Maximum_Ticket_Capacity; // maximum capacity of tickets the system can hold
    private int Ticket_Availability_for_customer; // number of tickets available for customers
    private int Total_ticket_that_vendors_sell=0;  // total tickets that vendors have sold


    // Getter method to retrieve the total number of tickets in the system
    public int getTotal_Number_of_Tickets() {
        return Total_Number_of_Tickets;
    }

    // Setter method to set the total number of tickets in the system and initialize customer availability
    public void setTotal_Number_of_Tickets(int total_Number_of_Tickets) {
        this.Total_Number_of_Tickets = total_Number_of_Tickets;
        this.Ticket_Availability_for_customer = total_Number_of_Tickets;

    }
    // Getter method to retrieve ticket availability for customers
    public int getTicket_Availability_for_customer(){
        return Total_ticket_that_vendors_sell;
    }
    // Getter method to retrieve the ticket release rate
    public int getTickets_Release_rate() {
        return Tickets_Release_rate;
    }
    // Setter method to set the ticket release rate
    public void setTickets_Release_rate(int tickets_Release_rate) {
        this.Tickets_Release_rate = tickets_Release_rate;
    }
    // Getter method to retrieve the customer retrieval rate
    public int getCustomer_Retrieval_Rate() {
        return Customer_Retrieval_Rate;
    }
    // Setter method to set the customer retrieval rate
    public void setCustomer_Retrieval_Rate(int customer_Retrieval_Rate) {
        this.Customer_Retrieval_Rate = customer_Retrieval_Rate;
    }
    // Getter method to retrieve the maximum ticket capacity
    public int getMaximum_Ticket_Capacity() {
        return Maximum_Ticket_Capacity;
    }
    // Setter method to set the maximum ticket capacity
    public void setMaximum_Ticket_Capacity(int maximum_Ticket_Capacity) {
        this.Maximum_Ticket_Capacity = maximum_Ticket_Capacity;
    }

    // Method to update the total and available tickets after a vendor sells tickets
    public void find_Remaining_Total_Tickets_for_vendor(int ticket_Amount) {
        this.Total_Number_of_Tickets-=ticket_Amount; // reduce total tickets in the system
        this.Total_ticket_that_vendors_sell+=ticket_Amount; // update tickets sold by vendors
    }
    // Method to update the tickets available for customers after a customer buys tickets
    public void find_Remaining_Total_Tickets_for_customer(int ticket_Amount) {
        this.Total_ticket_that_vendors_sell-=ticket_Amount; // reduce the tickets sold by vendors
    }

}
