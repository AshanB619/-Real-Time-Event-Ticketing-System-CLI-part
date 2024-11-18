import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner_main = new Scanner(System.in);
        SystemConfig config1 = new SystemConfig();
        System.out.println("Enter total number of ticket to sell");
        int totalTicket = scanner_main.nextInt();
        System.out.println("Enter Release rate !per second!");
        int release_rate = scanner_main.nextInt();
        System.out.println("Enter Retrieve rate !per second!");
        int retrieve_rate = scanner_main.nextInt();
        System.out.println("Enter maximum ticket capacity that system can handle ");
        int maximum_ticket_capacity = scanner_main.nextInt();
        config1.setTotal_Number_of_Tickets(totalTicket);
        config1.setTickets_Release_rate(release_rate);
        config1.setCustomer_Retrieval_Rate(retrieve_rate);
        config1.setMaximum_Ticket_Capacity(maximum_ticket_capacity);

        Ticket_pool_operation ticket_pool_operation= new TicketPool(config1.getMaximum_Ticket_Capacity());

        Vendor vendor1=new Vendor(ticket_pool_operation,config1.getTickets_Release_rate(),config1.getTotal_Number_of_Tickets());
        Customer customer1=new Customer(ticket_pool_operation,config1.getCustomer_Retrieval_Rate());

        Thread vendorThread = new Thread(vendor1);
        Thread customerThread = new Thread(customer1);

        vendorThread.start();
        customerThread.start();










    }
}