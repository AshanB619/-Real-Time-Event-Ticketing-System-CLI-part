import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (BufferedWriter log_file_writer = new BufferedWriter(new FileWriter("log_history.txt"))) {
            // Initialize scanner for user input
            Scanner scanner_main = new Scanner(System.in);
            // Get system configuration inputs from the user
            SystemConfig config1 = get_ticket_inputs(scanner_main);
            // Create a ticket pool operation with maximum ticket capacity and log writer
            Ticket_pool_operation ticket_pool_operation = new TicketPool(config1.getMaximum_Ticket_Capacity(), log_file_writer);
            // Get vendor details from the user
            List<Vendor_details> vendor_details_List = get_Vendor_Details(scanner_main, config1);
            // Get customer details from the user
            List<Customer_details> customer_details_List = get_Customer_Details(scanner_main, config1);

            // Create and start threads for vendors
            List<Thread> vendor_Threads_list = for_Vendor_Threads(vendor_details_List, ticket_pool_operation, config1);
            // Create and start threads for customers
            List<Thread> customer_Threads_list = for_Customer_Threads(customer_details_List, ticket_pool_operation, config1);

            // Allow user to stop the system by typing 'STOP'
            System.out.println("Enter 'STOP' to Exit the system");
            while (true) {
                String input_for_stop = scanner_main.nextLine().toLowerCase();
                if (input_for_stop.equals("stop")) {
                    // Interrupt all vendor threads
                    for (Thread thread_ven : vendor_Threads_list) {
                        thread_ven.interrupt();
                    }
                    // Interrupt all customer threads
                    for (Thread thread_cus : customer_Threads_list) {
                        thread_cus.interrupt();
                    }
                    break;
                }
            }


            // Wait for all vendor threads to finish
            for (Thread thread_ven : vendor_Threads_list) {
                try {
                    thread_ven.join();
                } catch (InterruptedException e) {
                    System.out.println("Error happened when Stopping the Thread");
                }
            }
            // Wait for all customer threads to finish
            for (Thread thread_cus : customer_Threads_list) {
                try {
                    thread_cus.join();
                } catch (InterruptedException e) {
                    System.out.println("Error happened when Stopping the Thread");
                }
            }
            System.out.println("Exit the system successfully");
        } catch (IOException e) {
            System.out.println("Error when writing in the file : " + e.getMessage());
        }
    }

    // Method to get system configuration inputs from the user
    private static SystemConfig get_ticket_inputs(Scanner scanner_main) {
        SystemConfig config1 = new SystemConfig();

        // Get total number of tickets to sell
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

        // Get ticket release rate
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

        // Get customer retrieval rate
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

        // Get maximum ticket capacity
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

    // Method to get vendor details from the user
    private static List<Vendor_details> get_Vendor_Details(Scanner scanner_main,SystemConfig config2) {
        List<Vendor_details> vendor_details_List = new ArrayList<>();
        boolean for_more_vendors = true;

        // Loop to add multiple vendors
        while (for_more_vendors) {
            Vendor_details vendor_details = new Vendor_details();

            // Get vendor name
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

            // Get vendor ID
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

            // Get total tickets vendor wants to sell
            while (true) {
                try {
                    System.out.println("Total Ticket Remaining "+config2.getTotal_Number_of_Tickets());
                    System.out.println("Enter Total Tickets number you want to sell:");
                    int vendor_ticket_number_for_vendor = Integer.parseInt(scanner_main.nextLine());
                    if (vendor_ticket_number_for_vendor <= 0) {
                        System.out.println("Enter a positive number.");
                        continue;
                    }
                    if (vendor_ticket_number_for_vendor>config2.getTotal_Number_of_Tickets()){
                        System.out.println("!!!You can sell only "+config2.getTotal_Number_of_Tickets()+" tickets!!!");
                        continue;
                    }
                    config2.find_Remaining_Total_Tickets_for_vendor(vendor_ticket_number_for_vendor);
                    vendor_details.setTotalTicketByVendor(vendor_ticket_number_for_vendor);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }

            vendor_details_List.add(vendor_details);

            // Check if user wants to add another vendor
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

    // Method to get customer details from the user
    private static List<Customer_details> get_Customer_Details(Scanner scanner_main, SystemConfig config) {
        List<Customer_details> customer_details_List = new ArrayList<>();
        boolean for_more_customers = true;

        // Loop to add multiple customers
        while (for_more_customers) {
            Customer_details customer_details = new Customer_details();

            // Get customer name
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

            // Get customer ID
            while (true) {
                try {
                    System.out.println("Enter Customer ID:");
                    int customer_id = Integer.parseInt(scanner_main.nextLine());
                    customer_details.setCustomerId(customer_id);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }

            // Get total tickets customer wants to buy
            while (true) {
                try {
                    int Max_ticket_for_customer= config.getTicket_Availability_for_customer();
                    System.out.println("Total Tickets Remaining: " + Max_ticket_for_customer);
                    System.out.println("Enter Total Tickets number you want to buy:");
                    int total_ticket_number_for_customer = Integer.parseInt(scanner_main.nextLine());

                    if (total_ticket_number_for_customer <= 0) {
                        System.out.println("Enter a positive number.");
                        continue;
                    }
                    if (total_ticket_number_for_customer > Max_ticket_for_customer) {
                        System.out.println("!!!You can only buy up to " + Max_ticket_for_customer + " tickets!!!");
                        continue;
                    }

                    config.find_Remaining_Total_Tickets_for_customer(total_ticket_number_for_customer);
                    customer_details.setTotalTicketToBuy(total_ticket_number_for_customer);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("!Invalid input!--|You need to Enter Integer value|");
                }
            }

            customer_details_List.add(customer_details);

            // Check if user wants to add another customer
            while (true) {
                System.out.println("Do you want to add another Customer|yes/no|");
                String add_more_customers = scanner_main.nextLine().toLowerCase();
                if (add_more_customers.equals("no")) {
                    for_more_customers = false;
                    break;
                } else if (add_more_customers.equals("yes")) {
                    break;
                } else {
                    System.out.println("!!!You can enter yes or no only!!!");
                }
            }
        }
        return customer_details_List;
    }

    // Method to create and start vendor threads
    private static List<Thread> for_Vendor_Threads(List<Vendor_details> vendor_details_List,
                                                   Ticket_pool_operation ticket_pool_operation,
                                                   SystemConfig system_config) {
        List<Thread>vendor_Threads_list=new ArrayList<>();
        for (Vendor_details vendor_details_for : vendor_details_List) {
            Vendor vendor1 = new Vendor(
                    ticket_pool_operation,
                    system_config.getTickets_Release_rate(),
                    vendor_details_for.getTotalTicketByVendor(),
                    vendor_details_for.getVendorName(),
                    vendor_details_for.getVendorId()
            );
            Thread vendor_Thread = new Thread(vendor1);
            vendor_Thread.start();
            vendor_Threads_list.add(vendor_Thread);
        }
        return vendor_Threads_list;
    }

    // Method to create and start customer threads
    private static List<Thread> for_Customer_Threads(List<Customer_details> customer_details_List,
                                                     Ticket_pool_operation ticket_pool_operation,
                                                     SystemConfig system_config) {
        List<Thread>customerThreads_list=new ArrayList<>();
        for (Customer_details customer_details_for : customer_details_List) {
            Customer customer1 = new Customer(
                    ticket_pool_operation,
                    system_config.getCustomer_Retrieval_Rate(),
                    customer_details_for.getTotalTicketToBuy(),
                    customer_details_for.getCustomerName(),
                    customer_details_for.getCustomerId()

            );
            Thread customer_Thread = new Thread(customer1);
            customer_Thread.start();
            customerThreads_list.add(customer_Thread);
        }
        return customerThreads_list;
    }




}
