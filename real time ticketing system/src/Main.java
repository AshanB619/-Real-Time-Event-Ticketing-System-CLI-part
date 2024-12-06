import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner_main = new Scanner(System.in);
        SystemConfig config1 = get_ticket_inputs(scanner_main);
        Ticket_pool_operation ticket_pool_operation = new TicketPool(config1.getMaximum_Ticket_Capacity());
        List<Vendor_details> vendor_details_List = get_Vendor_Details(scanner_main, config1);
        List<Customer_details> customer_details_List = get_Customer_Details(scanner_main, config1);

        List<Thread> vendorThreads = for_Vendor_Threads(vendor_details_List, ticket_pool_operation, config1);
        List<Thread> customerThreads = for_Customer_Threads(customer_details_List, ticket_pool_operation, config1);

        System.out.println("Type 'stop' to shut down the system:");
        while (true) {
            String input = scanner_main.nextLine();
            if (input.equalsIgnoreCase("stop")) {
                // Interrupt all vendor threads
                for (Thread thread : vendorThreads) {
                    thread.interrupt();
                }
                // Interrupt all customer threads
                for (Thread thread : customerThreads) {
                    thread.interrupt();
                }
                break;
            }
        }

        // Wait for all threads to complete
        for (Thread thread : vendorThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error while stopping vendor threads: " + e.getMessage());
            }
        }
        for (Thread thread : customerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error while stopping customer threads: " + e.getMessage());
            }
        }

        System.out.println("System shut down successfully.");
    }

    private static SystemConfig get_ticket_inputs(Scanner scanner_main) {
        SystemConfig config1 = new SystemConfig();
        while (true) {
            try {
                System.out.println("Enter total number of tickets to sell:");
                int totalTicket = Integer.parseInt(scanner_main.nextLine());
                if (totalTicket <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config1.setTotal_Number_of_Tickets(totalTicket);
                break;
            } catch (NumberFormatException e) {
                System.out.println("!Invalid input!--|You need to Enter Integer value|");
            }
        }
        while (true) {
            try {
                System.out.println("Enter Release rate (per second):");
                int release_rate = Integer.parseInt(scanner_main.nextLine());
                if (release_rate <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config1.setTickets_Release_rate(release_rate);
                break;
            } catch (NumberFormatException e) {
                System.out.println("!Invalid input!--|You need to Enter Integer value|");
            }
        }
        while (true) {
            try {
                System.out.println("Enter Retrieve rate (per second):");
                int retrieve_rate = Integer.parseInt(scanner_main.nextLine());
                if (retrieve_rate <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config1.setCustomer_Retrieval_Rate(retrieve_rate);
                break;
            } catch (NumberFormatException e) {
                System.out.println("!Invalid input!--|You need to Enter Integer value|");
            }
        }
        while (true) {
            try {
                System.out.println("Enter maximum ticket capacity that the system can handle:");
                int maximum_ticket_capacity = Integer.parseInt(scanner_main.nextLine());
                if (maximum_ticket_capacity <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config1.setMaximum_Ticket_Capacity(maximum_ticket_capacity);
                break;
            } catch (NumberFormatException e) {
                System.out.println("!Invalid input!--|You need to Enter Integer value|");
            }
        }
        return config1;
    }

    private static List<Vendor_details> get_Vendor_Details(Scanner scanner_main, SystemConfig config2) {
        List<Vendor_details> vendor_details_List = new ArrayList<>();
        boolean for_more_vendors = true;

        while (for_more_vendors) {
            Vendor_details vendor_details = new Vendor_details();

            System.out.println("Enter Vendor Name:");
            vendor_details.setVendor_Name(scanner_main.nextLine());

            System.out.println("Enter Vendor ID:");
            vendor_details.setVendorId(Integer.parseInt(scanner_main.nextLine()));

            System.out.println("Enter Total Tickets number you want to sell:");
            int totalTicket = Integer.parseInt(scanner_main.nextLine());
            vendor_details.setTotalTicketByVendor(totalTicket);

            vendor_details_List.add(vendor_details);

            System.out.println("Do you want to add another vendor|yes/no|");
            String add_more_vendors = scanner_main.nextLine().toLowerCase();
            if (add_more_vendors.equals("no")) {
                for_more_vendors = false;
            }
        }
        return vendor_details_List;
    }

    private static List<Customer_details> get_Customer_Details(Scanner scanner_main, SystemConfig config) {
        List<Customer_details> customer_details_List = new ArrayList<>();
        boolean for_more_customers = true;

        while (for_more_customers) {
            Customer_details customer_details = new Customer_details();

            System.out.println("Enter Customer Name:");
            customer_details.setCustomer_Name(scanner_main.nextLine());

            System.out.println("Enter Customer ID:");
            customer_details.setCustomerId(Integer.parseInt(scanner_main.nextLine()));

            System.out.println("Enter Total Tickets number you want to buy:");
            int totalTicket = Integer.parseInt(scanner_main.nextLine());
            customer_details.setTotalTicketToBuy(totalTicket);

            customer_details_List.add(customer_details);

            System.out.println("Do you want to add another Customer|yes/no|");
            String add_more_customers = scanner_main.nextLine().toLowerCase();
            if (add_more_customers.equals("no")) {
                for_more_customers = false;
            }
        }
        return customer_details_List;
    }

    private static List<Thread> for_Vendor_Threads(List<Vendor_details> vendor_details_List, Ticket_pool_operation ticket_pool_operation, SystemConfig config1) {
        List<Thread> vendorThreads = new ArrayList<>();
        for (Vendor_details vendor_details : vendor_details_List) {
            Vendor vendor = new Vendor(
                    ticket_pool_operation,
                    config1.getTickets_Release_rate(),
                    vendor_details.getTotalTicketByVendor(),
                    vendor_details.getVendorName(),
                    vendor_details.getVendorId()
            );
            Thread thread = new Thread(vendor);
            thread.start();
            vendorThreads.add(thread);
        }
        return vendorThreads;
    }

    private static List<Thread> for_Customer_Threads(List<Customer_details> customer_details_List, Ticket_pool_operation ticket_pool_operation, SystemConfig config1) {
        List<Thread> customerThreads = new ArrayList<>();
        for (Customer_details customer_details : customer_details_List) {
            Customer customer = new Customer(
                    ticket_pool_operation,
                    config1.getCustomer_Retrieval_Rate(),
                    customer_details.getTotalTicketToBuy(),
                    customer_details.getCustomerName(),
                    customer_details.getCustomerId()
            );
            Thread thread = new Thread(customer);
            thread.start();
            customerThreads.add(thread);
        }
        return customerThreads;
    }
}
