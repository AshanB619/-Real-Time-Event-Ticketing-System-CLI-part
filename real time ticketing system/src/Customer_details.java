public class Customer_details {
    private String customer_Name; // name of the customer
    private int customer_Id; // unique ID of the customer
    private int total_ticket_by_customer; // total tickets the customer wants to buy

    // Constructor to initialize Customer_details with name and ID
    public Customer_details(String customerName, int customerId) {
        this.customer_Name = customerName; // set the customer's name
        this.customer_Id = customerId;  // set the customer's ID
    }

    // Default constructor
    public Customer_details() {
    }

    // Getter method to retrieve the customer's name
    public String getCustomerName() {
        return customer_Name;
    }

    // Setter method to set the customer's name
    public void setCustomer_Name(String customerName) {
        this.customer_Name = customerName;
    }

    // Getter method to retrieve the customer's ID
    public int getCustomerId() {
        return customer_Id;
    }

    // Setter method to set the customer's ID
    public void setCustomerId(int customerId) {
        this.customer_Id = customerId;
    }

    // Getter method to retrieve the total tickets the customer wants to buy
    public int getTotalTicketToBuy() {
        return total_ticket_by_customer;
    }

    // Setter method to set the total tickets the customer wants to buy
    public void setTotalTicketToBuy(int totalTicketToBuy) {
        this.total_ticket_by_customer = totalTicketToBuy;
    }
}
