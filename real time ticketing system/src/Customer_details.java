public class Customer_details {
    private String customer_Name;
    private int customer_Id;
    private int total_ticket_by_customer;

    public Customer_details(String customerName, int customerId) {
        this.customer_Name = customerName;
        this.customer_Id = customerId;
    }

    public Customer_details() {
    }

    public String getCustomerName() {
        return customer_Name;
    }

    public void setCustomer_Name(String customerName) {
        this.customer_Name = customerName;
    }

    public int getCustomerId() {
        return customer_Id;
    }

    public void setCustomerId(int customerId) {
        this.customer_Id = customerId;
    }

    public int getTotalTicketToBuy() {
        return total_ticket_by_customer;
    }

    public void setTotalTicketToBuy(int totalTicketToBuy) {
        this.total_ticket_by_customer = totalTicketToBuy;
    }
}
