public class Vendor_details {
    private String vendor_Name;  // name of the vendor
    private int vendor_Id; // unique ID of the vendor
    private int total_ticket_by_vendor; // total number of tickets managed by the vendor

    // Constructor to initialize Vendor_details with name and ID
    public Vendor_details(String vendorName, int vendorId){
        this.vendor_Name = vendorName; // set the vendor's name
        this.vendor_Id = vendorId; // set the vendor's ID

    }

    // Default constructor
    public Vendor_details(){

    }

    // Getter method to retrieve the vendor's name
    public String getVendorName() {
        return vendor_Name;
    }
    // Setter method to set the vendor's name
    public void setVendor_Name(String vendorName) {
        this.vendor_Name=vendorName;
    }
    // Getter method to retrieve the vendor's ID
    public int getVendorId() {
        return vendor_Id;
    }
    // Setter method to set the vendor's ID
    public void setVendorId(int vendorId) {
        this.vendor_Id = vendorId;
    }
    // Getter method to retrieve the total tickets managed by the vendor
    public int getTotalTicketByVendor() {
        return total_ticket_by_vendor;
    }
    // Setter method to set the total tickets managed by the vendor
    public void setTotalTicketByVendor(int totalTicketByVendor) {
        this.total_ticket_by_vendor = totalTicketByVendor;
    }

}
