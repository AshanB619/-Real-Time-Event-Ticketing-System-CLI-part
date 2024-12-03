import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemConfig config = new SystemConfig();

        // Input for total tickets
        while (true) {
            try {
                System.out.println("Enter total number of tickets to sell:");
                int totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config.setTotal_Number_of_Tickets(totalTickets);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }

        // Input for release rate
        while (true) {
            try {
                System.out.println("Enter release rate (per second):");
                int releaseRate = Integer.parseInt(scanner.nextLine());
                if (releaseRate <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config.setTickets_Release_rate(releaseRate);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }

        // Input for retrieve rate
        while (true) {
            try {
                System.out.println("Enter retrieve rate (per second):");
                int retrieveRate = Integer.parseInt(scanner.nextLine());
                if (retrieveRate <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config.setCustomer_Retrieval_Rate(retrieveRate);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }

        // Input for maximum ticket capacity
        while (true) {
            try {
                System.out.println("Enter maximum ticket capacity that the system can handle:");
                int maxCapacity = Integer.parseInt(scanner.nextLine());
                if (maxCapacity <= 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }
                config.setMaximum_Ticket_Capacity(maxCapacity);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }

        // Ticket pool setup
        Ticket_pool_operation ticketPool = new TicketPool(config.getMaximum_Ticket_Capacity());

        // Manage multiple vendors
        List<Vendor> vendors = new ArrayList<>();
        while (true) {
            try {
                System.out.println("Enter vendor name:");
                String vendorName = scanner.nextLine();
                System.out.println("Enter vendor ID:");
                int vendorId = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter vendor email:");
                String vendorEmail = scanner.nextLine();
                System.out.println("Enter tickets vendor wants to sell:");
                int ticketsToSell = Integer.parseInt(scanner.nextLine());

                if (ticketsToSell > config.getTotal_Number_of_Tickets()) {
                    System.out.println("Tickets exceed total available tickets. Try again.");
                    continue;
                }

                // Deduct tickets from total
                config.setTotal_Number_of_Tickets(config.getTotal_Number_of_Tickets() - ticketsToSell);

                // Create and start vendor thread
                Vendor vendor = new Vendor(ticketPool, config.getTickets_Release_rate(), ticketsToSell,
                        vendorName, vendorId, vendorEmail);
                vendors.add(vendor);
                vendor.start();

                // Option to add more vendors
                System.out.println("Do you want to add another vendor? (yes/no):");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            }
        }

        // Start customer thread
        Customer customer = new Customer(ticketPool, config.getCustomer_Retrieval_Rate());
        customer.start();
    }
}
