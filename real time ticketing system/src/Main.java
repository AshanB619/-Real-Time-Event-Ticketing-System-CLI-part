import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner_main = new Scanner(System.in);
        SystemConfig config1 = get_ticket_inputs(scanner_main);
        Ticket_pool_operation ticket_pool_operation = new TicketPool(config1.getMaximum_Ticket_Capacity());
        List<Vendor_details> vendor_details_List = get_Vendor_Details(scanner_main);
        for (Vendor_details vendor_details_for : vendor_details_List) {
            Vendor vendor1 = new Vendor(
                    ticket_pool_operation,
                    config1.getTickets_Release_rate(),
                    vendor_details_for.getTotalTicketByVendor(),
                    vendor_details_for.getVendorName(),
                    vendor_details_for.getVendorId()
            );
            Thread vendor_Thread = new Thread(vendor1);
            vendor_Thread.start();
        }
        List<Customer_details> customer_details_List = get_Customer_Details(scanner_main);
        for (Customer_details customer_details_for : customer_details_List) {
            Customer customer = new Customer(
                    ticket_pool_operation,
                    config1.getCustomer_Retrieval_Rate(),
                    customer_details_for.getTotalTicketToBuy(),
                    customer_details_for.getCustomerName(),
                    customer_details_for.getCustomerId()

            );
            Thread customer_Thread = new Thread(customer);
            customer_Thread.start();
        }

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

    private static List<Vendor_details> get_Vendor_Details(Scanner scanner_main) {
        List<Vendor_details> vendor_details_List = new ArrayList<>();
        boolean for_more_vendors = true;

        while (for_more_vendors) {
            Vendor_details vendor_details = new Vendor_details();

            while (true) {
                System.out.println("Enter Vendor Name:");
                String vendor_name = scanner_main.nextLine();
                if (!vendor_name.matches("[a-zA-Z]+")) {
                    System.out.println("!Invalid input!--|You can enter only letters for input|");
                    continue;
                }
                vendor_details.setVendor_Name(vendor_name);
                break;
            }

            while (true) {
                try {
                    System.out.println("Enter Vendor ID:");
                    int vendor_id = Integer.parseInt(scanner_main.nextLine());
                    vendor_details.setVendorId(vendor_id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }

            while (true) {
                try {
                    System.out.println("Enter Total Tickets number you want to sell:");
                    int vendor_ticket_number_for_vendor = Integer.parseInt(scanner_main.nextLine());
                    if (vendor_ticket_number_for_vendor <= 0) {
                        System.out.println("Enter a positive number.");
                        continue;
                    }
                    vendor_details.setTotalTicketByVendor(vendor_ticket_number_for_vendor);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }

            vendor_details_List.add(vendor_details);

            while (true) {
                System.out.println("Do you want to add another vendor|yes/no|");
                String add_more_vendors = scanner_main.nextLine().toLowerCase();
                if (add_more_vendors.equals("no")) {
                    for_more_vendors = false;
                    break;
                } else if (add_more_vendors.equals("yes")) {
                    break;
                } else {
                    System.out.println("!!!you can enter yes or no only!!!");
                }
            }
        }

        return vendor_details_List;
    }

    private static List<Customer_details>get_Customer_Details(Scanner scanner_main) {
        List<Customer_details> customer_details_List = new ArrayList<>();
        boolean for_more_customers = true;

        while (for_more_customers) {
            Customer_details customer_details = new Customer_details();
            while (true) {
                System.out.println("Enter Customer Name:");
                String customer_name = scanner_main.nextLine();
                if (!customer_name.matches("[a-zA-Z]+")) {
                    System.out.println("!Invalid input!--|You can enter only letters for input|");
                    continue;
                }
                customer_details.setCustomer_Name(customer_name);
                break;
            }
            while (true) {
                try {
                    System.out.println("Enter Customer ID:");
                    int customer_id = Integer.parseInt(scanner_main.nextLine());
                    customer_details.setCustomerId(customer_id);
                    break;
                }
                catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }
            while (true) {
                try {
                    System.out.println("Enter Total Tickets number you want to buy:");
                    int total_ticket_number_for_customer = Integer.parseInt(scanner_main.nextLine());
                    if (total_ticket_number_for_customer <= 0) {
                        System.out.println("Enter a positive number.");
                        continue;
                    }
                    customer_details.setTotalTicketToBuy(total_ticket_number_for_customer);
                    break;
                }catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }
            customer_details_List.add(customer_details);
            while (true) {
                System.out.println("Do you want to add another Customer|yes/no|");
                String add_more_customers = scanner_main.nextLine().toLowerCase();
                if (add_more_customers.equals("no")) {
                    for_more_customers = false;
                    break;
                } else if (add_more_customers.equals("yes")) {
                    break;

                }else{
                    System.out.println("!!!you can enter yes or no only!!!");
                }
            }
        }
        return customer_details_List;

    }


}
