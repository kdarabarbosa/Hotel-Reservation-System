
package hotel_reservation_system;

/**
 *
 * @author Kathleen Barbosa
 */
public class Product {
    
    private int total_amount;
    private String fullname, email, address, phone, room_Type, room_Num, room_price, check_in, check_out, num_Day, num_Person, payment_method, Amount_Status, Guest_status;
    private byte [] image;
    
    
    public Product(String G_fullname, String G_email, String G_address, String G_phone, String G_room_Type, String G_room_Num, String G_room_price, String G_check_in, String G_check_out, String G_num_Day, String G_num_Person, String G_payment_method, String G_Amount_Status, String G_Guest_status, byte [] G_img)
    {
        this.fullname = G_fullname;
        this.email = G_email;
        this.address = G_address;
        this.phone = G_phone;
        this.room_Type = G_room_Type;
        this.room_Num = G_room_Num;
        this.room_price = G_room_price;
        this.check_in = G_check_in;
        this.check_out = G_check_out;
        this.num_Day = G_num_Day;
        this.num_Person = G_num_Person;
        this.payment_method = G_payment_method;
        this.Amount_Status = G_Amount_Status;
        this.Guest_status = G_Guest_status;
        this.image = G_img;
    }       
        
    
    
    public String getFullname()
    {
        return fullname;
    }
    public String getEmail()
    {
        return email;
    }
    public String getAddress()
    {
        return address;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getRoom_Type()
    {
        return room_Type;
    }
    public String getRoom_Num()
    {
        return room_Num;
    }
    public String getRoom_price()
    {
        return room_price;
    }
    public String getCheck_in()
    {
        return check_in;
    }
    public String getCheck_out()
    {
        return check_out;
    }
    public String getNum_Day()
    {
        return num_Day;
    }
    public String getNum_Person()
    {
        return num_Person;
    }
    public int getTotal_amount()
    {
        return total_amount;
    }
    
    public String getPayment_method()
    {
        return payment_method;
    }
    public String getAmount_Status()
    {
        return Amount_Status;
    }
    public String getGuest_status()
    {
        return Guest_status;
    }
    public byte [] getImage()
    {
        return image;
    }
    
}
