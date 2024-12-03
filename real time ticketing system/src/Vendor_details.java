public class Vendor_details {
    private String vendor_Name;
    private int vendor_Id;
    private int total_ticket_by_vendor;


    public String getVendorName() {
        return vendor_Name;
    }
    public void setVendor_Name(String vendorName) {
        this.vendor_Name=vendorName;
    }
    public int getVendorId() {
        return vendor_Id;
    }
    public void setVendorId(int vendorId) {
        this.vendor_Id = vendorId;
    }
    public int getTotalTicketByVendor() {
        return total_ticket_by_vendor;
    }

    public void setTotalTicketByVendor(int totalTicketByVendor) {
        this.total_ticket_by_vendor = totalTicketByVendor;
    }

}
