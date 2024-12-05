public class SystemConfig {
    private int Total_Number_of_Tickets;
    private int Tickets_Release_rate;
    private int Customer_Retrieval_Rate;
    private int Maximum_Ticket_Capacity;
    private int Ticket_Availability_for_customer;
    private int Total_ticket_that_vendors_sell=0;



    public int getTotal_Number_of_Tickets() {
        return Total_Number_of_Tickets;
    }

    public void setTotal_Number_of_Tickets(int total_Number_of_Tickets) {
        this.Total_Number_of_Tickets = total_Number_of_Tickets;
        this.Ticket_Availability_for_customer = total_Number_of_Tickets;

    }
    public int getTicket_Availability_for_customer(){
        return Total_ticket_that_vendors_sell;
    }

    public int getTickets_Release_rate() {
        return Tickets_Release_rate;
    }

    public void setTickets_Release_rate(int tickets_Release_rate) {
        this.Tickets_Release_rate = tickets_Release_rate;
    }
    public int getCustomer_Retrieval_Rate() {
        return Customer_Retrieval_Rate;
    }
    public void setCustomer_Retrieval_Rate(int customer_Retrieval_Rate) {
        this.Customer_Retrieval_Rate = customer_Retrieval_Rate;
    }
    public int getMaximum_Ticket_Capacity() {
        return Maximum_Ticket_Capacity;
    }
    public void setMaximum_Ticket_Capacity(int maximum_Ticket_Capacity) {
        this.Maximum_Ticket_Capacity = maximum_Ticket_Capacity;
    }

    public void find_Remaining_Total_Tickets_for_vendor(int ticket_Amount) {
        this.Total_Number_of_Tickets-=ticket_Amount;
        this.Total_ticket_that_vendors_sell+=ticket_Amount;
    }
    public void find_Remaining_Total_Tickets_for_customer(int ticket_Amount) {
        this.Total_ticket_that_vendors_sell-=ticket_Amount;
    }

}
