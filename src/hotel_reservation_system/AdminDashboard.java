/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_reservation_system;

import com.sun.istack.internal.logging.Logger;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Kathleen Barbosa
 */
public class AdminDashboard extends javax.swing.JFrame {
    
    String dbURL = "jdbc:oracle:thin:@localhost:1521:cdb";
    String userN= "system";
    String passW = "Lucena123!";
    
    
    
    Connection conn = null;
    Statement stmt = null;
    
   
    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
        tableRoomRefresh ();
        clearManageRoom ();
        refreshGuest();
        UpdateGuestAccount();
        DataPanelTable ();
        
        
//        Show_guest();
        requestTable ();
    }

    public Connection getConnection()
    {
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            return conn;
        }catch(SQLException ex){
          java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
           return null;
        } 
    }
    
    private int total_Amount;
    private String fullname, email, address, phone, room_Type, room_Num, room_price, check_in, check_out, num_Day, num_Person, payment_method, Amount_Status, Guest_status;
    private byte [] image;
    
    
    public AdminDashboard(String G_fullname, String G_email, String G_address, String G_phone, String G_room_Type, String G_room_Num, String G_room_price, String G_check_in, String G_check_out, String G_num_Day, String G_num_Person, int G_total_amount, String G_payment_method, String G_Amount_Status, String G_Guest_status, byte[] G_img)
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
        this.total_Amount = G_total_amount;
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
        return total_Amount;
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
    
    
     String ImgPath =null;
    
     public void clearManageRoom ()
    {
        txt_roomNum.setText("");
//        txt_roomType.removeAllItems();
        txt_price.setText("");
        jCheckBox1.setSelected(false);
        
        txt_roomNum2.setText("");
//        txt_roomType2.removeAllItems();
        txt_price2.setText("");
        jCheckBox2.setSelected(false);
        RoomNumber2.setText("");
    }
    
    
    public void tableRoomRefresh ()
    {
        
        
         try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room ORDER BY room_number");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) roomTable_list.getModel();
            tm.setRowCount(0);
            while(rs.next()){
            Object row[]= {
                
                rs.getString("room_number"),
                rs.getString("room_type"),
                rs.getString("room_price"),
                rs.getString("activate"),
                rs.getString("room_status")};
            tm.addRow(row);
            }
            conn.close();
            
        }catch(SQLException se){
            System.out.println(se);
        } //end try
 
    }
    public void requestTable ()
    {
        
        
         try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room Where room_status='Request to Booked' ");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) request_list.getModel();
            tm.setRowCount(0);
            while(rs.next()){
            Object row[]= {
                
                rs.getString("room_number"),
                
//                rs.getString("activate"),
                rs.getString("room_status"),
                rs.getString("room_type"),
                rs.getString("room_price"),};
            tm.addRow(row);
            }
            conn.close();
            
        }catch(SQLException se){
            System.out.println(se);
        } //end try
 
    }
   
    public void DataPanelTable ()
    {
        
        
         try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Manage_Guest");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) Table_listData.getModel();
            tm.setRowCount(0);
            while(rs.next()){
            Object row[]= {
                
                rs.getString("Guest_status"),
                        rs.getString("fullname"),
                        rs.getString("check_in"),
                        rs.getString("check_out"),
                        rs.getString("Amount_Status"),};
            tm.addRow(row);
            }
            conn.close();
            
        }catch(SQLException se){
            System.out.println(se);
        } //end try
 
    }
    
    
    
    public ArrayList<AdminDashboard> getProductList()
    {
            ArrayList<AdminDashboard> productList = new ArrayList<AdminDashboard>();
            Connection conn = getConnection();
            String query = "SELECT * FROM Manage_Guest";
            
            Statement st;
            ResultSet rs;
        try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            st = conn.createStatement();
            rs = st.executeQuery(query);
            AdminDashboard product;
            
            
          
            while(rs.next())
            {
                product = new AdminDashboard(rs.getString("fullname"),rs.getString("email"),rs.getString("address"),rs.getString("phone"),rs.getString("room_Type"),rs.getString("room_Num"),rs.getString("room_price"),rs.getString("check_in"),rs.getString("check_out"),rs.getString("num_Day"),rs.getString("num_Person"),rs.getInt("total_Amount"),rs.getString("payment_method"),rs.getString("Amount_Status"),rs.getString("Guest_status"),rs.getBytes("image"));
                productList.add(product);
            }
            
        } catch (SQLException ex) {
         java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
    }
    
    
   public ImageIcon ResizeImage(String imagePath, byte[] pic)
     {
         ImageIcon myImage = null;
         
         if(imagePath != null)
         {
             myImage = new ImageIcon(imagePath);
         }else{
             
             myImage = new ImageIcon (pic);
         }
         
         Image img = myImage.getImage();
         Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
         ImageIcon image = new ImageIcon(img2);
         return image;
     }
    
  public void ShowItemGuest(int index)
  {
        
              txt_fullname1.setText(getProductList().get(index).getFullname());
              txt_fullname1.setEditable(false);
              txt_email.setText(getProductList().get(index).getEmail());
              txt_email.setEditable(false);
              txt_address.setText(getProductList().get(index).getAddress());
              txt_address.setEditable(false);
              txt_contact.setText(getProductList().get(index).getPhone());
              txt_contact.setEditable(false);
              txt_roomtype.setText(getProductList().get(index).getRoom_Type());
              txt_roomtype.setEditable(false);
              txt_roomnumber.setText(getProductList().get(index).getRoom_Num());
              txt_roomnumber.setEditable(false);
              txt_roomprice.setText(getProductList().get(index).getRoom_price());
              txt_roomprice.setEditable(false);
              
 
              ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).setText(getProductList().get(index).getCheck_in());
              jDateChooser2.setEnabled(false);
              
              ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(getProductList().get(index).getCheck_out());
              jDateChooser1.setEnabled(false);
       
              txt_day.setText(getProductList().get(index).getNum_Day());
              txt_day.setEditable(false);
              txt_person.setText(getProductList().get(index).getNum_Person());
              txt_person.setEditable(false);
              txt_amount.setText(Integer.toString(getProductList().get(index).getTotal_amount()));
              txt_amount.setEditable(false);
              lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
              
              
               
              
              
              
  }
          
    
   public void ShowItemGuest1(int index)
  {
        
             
              lbl_image2.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
              
          
  }
  
  
   public void TableDetailsGuest(int index)
  {
        
              txt_fullname2.setText(getProductList().get(index).getFullname());
              txt_fullname2.setEditable(false);
              txt_email2.setText(getProductList().get(index).getEmail());
              txt_email2.setEditable(false);
              txt_address2.setText(getProductList().get(index).getAddress());
              txt_address2.setEditable(false);
              txt_contact2.setText(getProductList().get(index).getPhone());
              txt_contact2.setEditable(false);
              txt_roomtype2.setText(getProductList().get(index).getRoom_Type());
              txt_roomtype2.setEditable(false);
              txt_roomnumber2.setText(getProductList().get(index).getRoom_Num());
              txt_roomnumber2.setEditable(false);
              txt_roomprice2.setText(getProductList().get(index).getRoom_price());
              txt_roomprice2.setEditable(false);
              
              txt_method.setText(getProductList().get(index).getPayment_method());
              txt_method.setEditable(false);
              
              
 
              ((JTextField)jDateChooser22.getDateEditor().getUiComponent()).setText(getProductList().get(index).getCheck_in());
              
              
              ((JTextField)jDateChooser23.getDateEditor().getUiComponent()).setText(getProductList().get(index).getCheck_out());
              
       
              txt_day2.setText(getProductList().get(index).getNum_Day());
              txt_day2.setEditable(false);
              txt_person2.setText(getProductList().get(index).getNum_Person());
              txt_person2.setEditable(false);
              txt_amount2.setText(Integer.toString(getProductList().get(index).getTotal_amount()));
              txt_amount2.setEditable(false);
              txt_guest2.setText(getProductList().get(index).getGuest_status());
              txt_guest2.setEditable(false);
              txt_amountStatus.setText(getProductList().get(index).getAmount_Status());
               txt_amountStatus.setEditable(false);
              txt_image2.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
              
              
               
              
              
              
  }
  
  
  public void clearfieldsData()
  {
        txt_fullname2.setText("");
         txt_email2.setText("");
         txt_address2.setText("");
         txt_contact2.setText("");
         txt_roomtype2.setText("");
         txt_roomnumber2.setText("");
         txt_roomprice2.setText("");
         txt_method.setText("");
         txt_day2.setText("");
         txt_person2.setText("");
         txt_amount2.setText("");
         txt_guest2.setText("");
         txt_amountStatus.setText("");
       
         ((JTextField)jDateChooser22.getDateEditor().getUiComponent()).setText("");
         ((JTextField)jDateChooser23.getDateEditor().getUiComponent()).setText("");
         
         txt_image2.setText("");
  }
  
  
  
  
  
  
    
      public void refreshGuest()
    {
        
        
         try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Manage_Guest");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) guest_table.getModel();
            tm.setRowCount(0);
            while(rs.next()){
            Object row[]= {
                
                rs.getString("fullname"),
                rs.getString("room_Num"),
                rs.getString("check_in"),
                rs.getString("check_out"),
                rs.getString("Guest_status")
                        };
            tm.addRow(row);
            }
            conn.close();
            
        }catch(SQLException se){
            System.out.println(se);
        } //end try
 
    }
    public void clearCheckin()
    {
         
         txt_fullname1.setText("");
         txt_email.setText("");
         txt_address.setText("");
         txt_contact.setText("");
         txt_price.setText("");
         txt_roomnumber.setText("");
         txt_amount.setText("");
         ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).setText("");
         ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
          txt_roomtype.setText("");
         txt_roomprice.setText("");
         lbl_image.setText("");
         keyword.setText("");

         
     
    }
    
    
    public void clearUpdate()
     {
         fullname_txt.setText("");
         email_txt.setText("");
         address_txt.setText("");
         phone_txt.setText("");
        guestStatus_txt.setText("");
        roomnum_txt.setText("");
         roomType_txt.setText("");
         price_txt.setText("");
         amount_txt.setText("");
         ((JTextField)check_in_txt.getDateEditor().getUiComponent()).setText("");
         ((JTextField)check_out_txt.getDateEditor().getUiComponent()).setText("");
         method_txt.setText("");
         amountStatus_txt.setText("");
         days_txt.setText("");
         person_txt.setText("");
         search_txt.setText("");
         lbl_image2.setText("");
         
         
     }
    
    
    
    ////panel 4
          public void UpdateGuestAccount()
    {
        
        
         try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from Manage_Guest Where Guest_status='Updating Account' ");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) request_account_table.getModel();
            tm.setRowCount(0);
            while(rs.next()){
            Object row[]= {
                
                rs.getString("fullname"),
               
                rs.getString("total_Amount"),
                 rs.getString("Amount_Status"),
                rs.getString("Guest_status")
                        };
            tm.addRow(row);
            }
            conn.close();
            
        }catch(SQLException se){
            System.out.println(se);
        } //end try
 
    }
      
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        adminName = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_roomNum = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable_list = new javax.swing.JTable()

        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 4){
                    if(value.equals("Booked"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                    if(value.equals("Not Booked")){
                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }
                    if(value.equals("Request to Booked")){
                        // if date  equal current date
                        componenet.setBackground(Color.yellow);
                        componenet.setForeground(Color.black);
                    }

                }

                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }

                return componenet;
            }
        }

        ;
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        txt_price2 = new javax.swing.JTextField();
        txt_roomNum2 = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        txt_roomType2 = new javax.swing.JComboBox<>();
        txt_roomType = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        RoomNumber2 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        request_list = new javax.swing.JTable();
        keyword = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        guest_table = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 4){

                    if(value.equals("Booked In"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }
                    if(value.equals("Request To Booked In")){
                        // if date  equal current date
                        componenet.setBackground(Color.BLUE);
                        componenet.setForeground(Color.WHITE);
                    }

                    if(value.equals("Updating Account")){
                        // if date  equal current date
                        componenet.setBackground(Color.YELLOW);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(value.equals("Request to Booked In")){
                        // if date  equal current date
                        componenet.setBackground(Color.ORANGE);
                        componenet.setForeground(Color.BLACK);
                    }

                    if(value.equals("Booked Out")){
                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }

                }

                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }

                return componenet;
            }

        }

        ;
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        confirmBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txt_roomprice = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        txt_numberperson = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txt_fullname1 = new javax.swing.JTextField();
        txt_roomtype = new javax.swing.JTextField();
        txt_day = new javax.swing.JTextField();
        txt_roomnumber = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txt_person = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        sendMessageBtn = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton11 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        request_account_table = new javax.swing.JTable();
        search_txt = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        address_txt = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        fullname_txt = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        person_txt = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        amountStatus_txt = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        phone_txt = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        days_txt = new javax.swing.JTextField();
        check_in_txt = new com.toedter.calendar.JDateChooser();
        jLabel43 = new javax.swing.JLabel();
        check_out_txt = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        email_txt = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        guestStatus_txt = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        price_txt = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        roomnum_txt = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        roomType_txt = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        amount_txt = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        method_txt = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        lbl_image2 = new javax.swing.JLabel();
        search_fullname = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table_listData = new javax.swing.JTable()
        {
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object value = getModel().getValueAt(rowIndex,columnIndex);

                if(columnIndex == 4){

                    if(value.equals("Paid"))
                    {

                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);

                    }

                    if(value.equals("Not Paid")){
                        // if date  equal current date
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.WHITE);
                    }

                }

                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }

                return componenet;
            }

        }
        ;
        jLabel22 = new javax.swing.JLabel();
        searchh = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txt_fullname2 = new javax.swing.JTextField();
        txt_image2 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txt_email2 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txt_address2 = new javax.swing.JTextField();
        txt_contact2 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txt_roomtype2 = new javax.swing.JTextField();
        txt_guest2 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txt_roomnumber2 = new javax.swing.JTextField();
        txt_amountStatus = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jDateChooser23 = new com.toedter.calendar.JDateChooser();
        jDateChooser22 = new com.toedter.calendar.JDateChooser();
        jLabel61 = new javax.swing.JLabel();
        txt_roomprice2 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txt_person2 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txt_method = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txt_amount2 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txt_day2 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/2548901-200-removebg-preview.png"))); // NOI18N
        jButton1.setText("Update Booking");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 200, 60));

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/manage-removebg-preview.png"))); // NOI18N
        jButton2.setText("Manage Room");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 60));

        jButton3.setBackground(new java.awt.Color(255, 255, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/data-removebg-preview.png"))); // NOI18N
        jButton3.setText("Data");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 200, 60));

        jButton4.setBackground(new java.awt.Color(255, 255, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/customer-removebg-preview (1).png"))); // NOI18N
        jButton4.setText("Guest Checking In");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 200, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/smallLogo-removebg-preview.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Admin Form");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("------------------------------------------------------------");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        adminName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adminName.setForeground(new java.awt.Color(255, 255, 0));
        jPanel1.add(adminName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 80, 20));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/logout-removebg-preview (1).png"))); // NOI18N
        jButton5.setText("Logout      ");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 200, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 640));

        jPanel3.setBackground(new java.awt.Color(153, 51, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UPDATE & DELETE ROOM");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Room Number:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        jPanel3.add(txt_roomNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 160, 30));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Activate or Deactivate");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, 20));

        jCheckBox1.setBackground(new java.awt.Color(153, 51, 255));
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("YES");
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 50, 40));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Room Type:");
        jLabel11.setToolTipText("");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 30));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Room Price:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 90, 20));
        jPanel3.add(txt_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 160, 30));

        saveBtn.setBackground(new java.awt.Color(255, 204, 204));
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/save-removebg-preview.png"))); // NOI18N
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel3.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 140, 40));

        jPanel6.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 740, 10));

        jLabel13.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ADD NEW ROOM");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Activate or Deactivate");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        roomTable_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Room Type", "Room Price", "Activate", "Room Status"
            }
        ));
        roomTable_list.setGridColor(new java.awt.Color(153, 51, 255));
        roomTable_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTable_listMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(roomTable_list);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 740, 250));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Room Type:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, 20));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Room Price:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, -1));

        jCheckBox2.setBackground(new java.awt.Color(153, 51, 255));
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("YES");
        jPanel3.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, -1, 30));
        jPanel3.add(txt_price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 160, 30));

        txt_roomNum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_roomNum2ActionPerformed(evt);
            }
        });
        txt_roomNum2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_roomNum2KeyReleased(evt);
            }
        });
        jPanel3.add(txt_roomNum2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 150, 30));

        updateBtn.setBackground(new java.awt.Color(255, 204, 204));
        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/save-removebg-preview.png"))); // NOI18N
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel3.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 140, 60));

        deleteBtn.setBackground(new java.awt.Color(255, 204, 204));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/DELETE-removebg-preview.png"))); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel3.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 140, 60));

        txt_roomType2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Room ", "Double Room ", "Triple Room ", "Quad Room ", "Twin Room ", "Double-double Room", "Queen Room", "King Room ", "Master Room ", "Deluxe Room ", "Adjacent Room", "Adjoining Room", "Studio Room ", "Suite Room ", "Villa Room ", " " }));
        jPanel3.add(txt_roomType2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 160, 30));

        txt_roomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Room ", "Double Room ", "Triple Room ", "Quad Room ", "Twin Room ", "Double-double Room", "Queen Room", "King Room ", "Master Room ", "Deluxe Room ", "Adjacent Room", "Adjoining Room", "Studio Room ", "Suite Room ", "Villa Room ", " " }));
        jPanel3.add(txt_roomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 160, 30));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        jLabel25.setText("Search...");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Room Number:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));
        jPanel3.add(RoomNumber2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 150, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/UPDATE-removebg-preview.png"))); // NOI18N
        jButton7.setText("Refresh Table");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 170, 40));

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel2.setBackground(new java.awt.Color(153, 51, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Guest Request Checkin In Data");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 420, -1));

        request_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room Number", "Room Status", "Room Type", "Room Price"
            }
        ));
        jScrollPane3.setViewportView(request_list);
        if (request_list.getColumnModel().getColumnCount() > 0) {
            request_list.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 490, 110));

        keyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keywordActionPerformed(evt);
            }
        });
        keyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keywordKeyReleased(evt);
            }
        });
        jPanel2.add(keyword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 180, 30));

        guest_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fullname", "Room Number", "Check In", "Check Out", "Guest Status"
            }
        ));
        guest_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guest_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(guest_table);
        if (guest_table.getColumnModel().getColumnCount() > 0) {
            guest_table.getColumnModel().getColumn(1).setHeaderValue("Room Number");
            guest_table.getColumnModel().getColumn(2).setHeaderValue("Check In");
            guest_table.getColumnModel().getColumn(3).setHeaderValue("Check Out");
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 720, 110));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Requesting Checking in List:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Search Room Number:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 51));
        jLabel21.setText("Click the data from the table to view more details.");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        lbl_image.setBackground(new java.awt.Color(204, 204, 255));
        lbl_image.setOpaque(true);
        jPanel2.add(lbl_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 210, 140));

        confirmBtn.setBackground(new java.awt.Color(255, 204, 204));
        confirmBtn.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        confirmBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/check-removebg-preview.png"))); // NOI18N
        confirmBtn.setText("Confirm Booking");
        confirmBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });
        jPanel2.add(confirmBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 150, 40));

        CancelBtn.setBackground(new java.awt.Color(255, 204, 204));
        CancelBtn.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        CancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/x-removebg-preview.png"))); // NOI18N
        CancelBtn.setText("Cancel Booking");
        CancelBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });
        jPanel2.add(CancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 150, 40));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Fullname:");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        txt_roomprice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txt_roomprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 130, 30));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("email:");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));
        jPanel2.add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 130, 30));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Address:");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });
        jPanel2.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, 130, 30));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Contact No.");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        jPanel2.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 130, 30));

        txt_numberperson.setForeground(new java.awt.Color(255, 255, 255));
        txt_numberperson.setText("Number of Person Checking in:");
        jPanel2.add(txt_numberperson, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, -1, -1));

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Room Type:");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, -1, -1));

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Room Number:");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, -1, -1));

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Room Price:");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, -1, -1));
        jPanel2.add(txt_fullname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 130, 30));

        txt_roomtype.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txt_roomtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 130, 30));

        txt_day.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txt_day, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 110, 30));

        txt_roomnumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txt_roomnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, 130, 30));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Check in:");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, -1, -1));

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Check out:");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, -1, -1));

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Number Days to Stay:");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, -1, -1));

        txt_person.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txt_person, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 40, 30));

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Total Amount To Pay:");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 530, 160, -1));

        txt_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        jPanel2.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 110, 30));

        sendMessageBtn.setBackground(new java.awt.Color(255, 204, 204));
        sendMessageBtn.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        sendMessageBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/send-removebg-preview.png"))); // NOI18N
        sendMessageBtn.setText("Generate Message");
        sendMessageBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        sendMessageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageBtnActionPerformed(evt);
            }
        });
        jPanel2.add(sendMessageBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 560, 170, 40));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 170, 30));
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 170, 30));

        jButton11.setBackground(new java.awt.Color(255, 204, 204));
        jButton11.setText("Refresh Table");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 110, -1));

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel4.setBackground(new java.awt.Color(153, 51, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        request_account_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fullname", "Amount to pay", "Amount Status", "Guest Status"
            }
        ));
        request_account_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                request_account_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(request_account_table);
        if (request_account_table.getColumnModel().getColumnCount() > 0) {
            request_account_table.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 510, 170));
        jPanel4.add(search_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 180, 30));

        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Fullname:");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        address_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(address_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 140, 30));

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Contact Number:");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, -1));

        fullname_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(fullname_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 130, 30));

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Check in:");
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, -1, -1));

        person_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(person_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 120, 30));

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Room Type:");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        amountStatus_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(amountStatus_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 130, 30));

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Number of Person");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, -1, -1));

        phone_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(phone_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 140, 30));

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Email:");
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, -1, -1));

        days_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(days_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 120, 30));
        jPanel4.add(check_in_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 140, 30));

        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Check out:");
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));
        jPanel4.add(check_out_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, 130, 30));

        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Guest Status:");
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, -1, -1));

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Address:");
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, -1, -1));

        email_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(email_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 130, 30));

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("How many Days Stay");
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 410, -1, -1));

        guestStatus_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(guestStatus_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 120, 30));

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Room Number:");
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        price_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 130, 30));

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Room Price:");
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        roomnum_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(roomnum_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 130, 30));

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Amount Status");
        jPanel4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, -1, -1));

        roomType_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(roomType_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 140, 30));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Amount To Pay");
        jPanel4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, -1, -1));

        amount_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(amount_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 140, 30));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Payment Method");
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, -1, -1));

        method_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(method_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 140, 30));

        jButton8.setBackground(new java.awt.Color(255, 204, 204));
        jButton8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/x-removebg-preview.png"))); // NOI18N
        jButton8.setText("Cancel Request");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 190, 70));

        jButton9.setBackground(new java.awt.Color(255, 204, 204));
        jButton9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/save-removebg-preview.png"))); // NOI18N
        jButton9.setText("Update Booking");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, 190, 70));

        jLabel51.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Request Update Account");
        jPanel4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        lbl_image2.setBackground(new java.awt.Color(204, 204, 255));
        lbl_image2.setOpaque(true);
        jPanel4.add(lbl_image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, 170));

        search_fullname.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        search_fullname.setText("Search Fullname ");
        search_fullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fullnameActionPerformed(evt);
            }
        });
        jPanel4.add(search_fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 170, 30));

        jButton6.setBackground(new java.awt.Color(255, 204, 204));
        jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/send-removebg-preview.png"))); // NOI18N
        jButton6.setText("Generate Message");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 190, 70));

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel5.setBackground(new java.awt.Color(153, 51, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Guest Data");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        Table_listData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Guest Status", "Fullname", "Check in", "Check out", "Amount Status"
            }
        ));
        Table_listData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_listDataMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Table_listData);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 740, 170));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        jLabel22.setText("Search Guest Fullname");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        searchh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchhKeyReleased(evt);
            }
        });
        jPanel5.add(searchh, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 200, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Fullname:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 740, 10));
        jPanel5.add(txt_fullname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 140, 30));

        txt_image2.setOpaque(true);
        jPanel5.add(txt_image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 210, 160));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Email:");
        jPanel5.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));
        jPanel5.add(txt_email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 140, 30));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Address:");
        jPanel5.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));
        jPanel5.add(txt_address2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 140, 30));
        jPanel5.add(txt_contact2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 140, 30));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Contact No.");
        jPanel5.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Guest Status");
        jPanel5.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, -1, -1));
        jPanel5.add(txt_roomtype2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 160, 30));
        jPanel5.add(txt_guest2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 160, 30));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Room Type:");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Room Price:");
        jPanel5.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, -1, -1));

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Room Number:");
        jPanel5.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, -1, -1));
        jPanel5.add(txt_roomnumber2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 160, 30));
        jPanel5.add(txt_amountStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 530, 210, 30));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Check In:");
        jPanel5.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Check Out:");
        jPanel5.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, -1));
        jPanel5.add(jDateChooser23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, 140, 30));
        jPanel5.add(jDateChooser22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 140, 30));

        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Amount to Pay:");
        jPanel5.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 580, -1, -1));
        jPanel5.add(txt_roomprice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 160, 30));

        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("No. of Person:");
        jPanel5.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, -1));
        jPanel5.add(txt_person2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, 160, 30));

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Payment Method:");
        jPanel5.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, -1));
        jPanel5.add(txt_method, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 140, 30));

        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Amount Status:");
        jPanel5.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 510, -1, -1));
        jPanel5.add(txt_amount2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 160, 30));

        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("No. Days Stay:");
        jPanel5.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, -1, -1));
        jPanel5.add(txt_day2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, 160, 30));

        jButton10.setBackground(new java.awt.Color(255, 204, 204));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/UPDATE-removebg-preview.png"))); // NOI18N
        jButton10.setText("Refresh");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 570, 140, 40));

        jTabbedPane1.addTab("tab4", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, -31, 790, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to Logout","Select", JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            setVisible(false);
            new MainFrame().setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
       //manage account SAVE BUTTON CODE
       
       String RoomNumber = txt_roomNum.getText();
       String RoomType = (String)txt_roomType.getSelectedItem();
       String RoomPrice = txt_price.getText();
       String activate;
       String room_status="Not Booked";
       if(jCheckBox1.isSelected())
       {
           activate="Yes";
       }
       else
           activate="No";
       try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO room (room_number, room_type, room_price, activate, room_status) VALUES ('"+RoomNumber+"', '"+RoomType+"', '"+RoomPrice+"', '"+activate+"', '"+room_status+"')";
            stmt.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(null,"Inserted records into the table!");
            tableRoomRefresh ();
            clearManageRoom ();
            
            
            conn.close();
        }catch(SQLException se) {
            System.out.println(se);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void txt_roomNum2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_roomNum2KeyReleased
        
        System.out.print ("You typed "+ txt_roomNum2.getText() + "\n ");
        
        String search = txt_roomNum2.getText();
        int i=0;
       
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room where room_number like '%"+search+"%'");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) roomTable_list.getModel();
                tm.setRowCount(0);
                while(rs.next()){
                    
                    Object row[]={
                        
                       rs.getString("room_number"),
                       rs.getString("room_type"),
                       rs.getString("room_price"),
                       rs.getString("activate"),
                       rs.getString("room_status")};
                    tm.addRow(row);
                    }
                conn.close();
                }catch(SQLException se){
                    System.out.println(se);
                }
        
        
    }//GEN-LAST:event_txt_roomNum2KeyReleased

    private void roomTable_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTable_listMouseClicked
        
        DefaultTableModel tm = (DefaultTableModel) roomTable_list.getModel();
        int selectedRowIndex = roomTable_list.getSelectedRow();
        RoomNumber2.setText(tm.getValueAt(selectedRowIndex, 0) .toString());
        RoomNumber2.setEditable(false);
        txt_price2.setText(tm.getValueAt(selectedRowIndex, 2) .toString());
        String activate = (tm.getValueAt(selectedRowIndex, 3) .toString());
        if(activate.equals("Yes")){
            
            jCheckBox2.setSelected(true);
        }
        else{
            
             jCheckBox2.setSelected(false);
            
           
        }
        String comboType = (tm.getValueAt(selectedRowIndex, 1) .toString());
        for (int i=0; 1<txt_roomType2.getItemCount(); i++){
            if (txt_roomType2.getItemAt(i).toString().equalsIgnoreCase(comboType)){
                txt_roomType2.setSelectedIndex(i);
            }
        }
       
         
      
       
    }//GEN-LAST:event_roomTable_listMouseClicked

    private void txt_roomNum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_roomNum2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_roomNum2ActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        
       String RoomNumber= RoomNumber2.getText();
       String RoomType = (String)txt_roomType2.getSelectedItem();
       String RoomPrice = txt_price2.getText();
       String activate;
       String room_status="Not Booked";
       if(jCheckBox2.isSelected())
       {
           activate="Yes";
       }
       else
           activate="No";
        try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            stmt = conn.createStatement();
            
            String sql = "UPDATE room set room_type='"+RoomType+"', room_price='"+RoomPrice+"', activate='"+activate+"', room_status='"+room_status+"' WHERE room_number= "+ RoomNumber;
            
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Updated Records successfully!");
            tableRoomRefresh ();
            clearManageRoom ();


            conn.close();

        }catch(SQLException se){
            System.out.println(se);
        }
       
      
        
        
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        
       String RoomNumber1 = RoomNumber2.getText();
       
       try{
           conn = DriverManager.getConnection(dbURL, userN, passW);
            stmt = conn.createStatement();
            String sql = ("DELETE from room where room_number = '"+RoomNumber1+"'");
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to delete this record?");
            if(dialogResult == JOptionPane.YES_OPTION){
                stmt.execute(sql);

                JOptionPane.showMessageDialog(null, "Deleted successfully....");
                 tableRoomRefresh ();
                 clearManageRoom ();
        
            }
            conn.close();
        }catch(SQLException se){
            System.out.println(se);
        }
        
       
      
        
        
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void guest_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guest_tableMouseClicked
        int index = guest_table.getSelectedRow();
        ShowItemGuest(index);
        
        
        
    }//GEN-LAST:event_guest_tableMouseClicked

    private void keywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keywordKeyReleased
               
        System.out.print ("You typed "+ keyword.getText() + "\n ");
        
        String search = keyword.getText();
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from Manage_Guest where room_Num like '%"+search+"%'");
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel) guest_table.getModel();
                tm.setRowCount(0);
                while(rs.next()){
                    Object row[]={
               
                        rs.getString("fullname"),
                        rs.getString("room_Num"),
                        rs.getString("check_in"),
                        rs.getString("check_out"),
                        rs.getString("Guest_status")};
                    tm.addRow(row);
                    }
                conn.close();
                }catch(SQLException se){
                    System.out.println(se);
                }
        
    }//GEN-LAST:event_keywordKeyReleased

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
   
        if(!txt_fullname1.getText().equals("") && !txt_roomnumber.getText().equals(""))
     {
         
         
          try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("UPDATE Manage_Guest set Guest_status='Booked In' WHERE fullname = ?");
                PreparedStatement ps1 = conn.prepareStatement("UPDATE room set room_status='Booked' WHERE room_number = ?");
                String fullname1 = txt_fullname1.getText();
                String roomNum2 = txt_roomnumber.getText();
                ps.setString(1, fullname1);
                ps1.setString(1,roomNum2);
                ps.executeUpdate();
                ps1.executeUpdate();

                
                JOptionPane.showMessageDialog(null,"Account Successfully Updated!");
                
                requestTable ();
                refreshGuest();
                DataPanelTable ();
                clearCheckin();
            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
   
     
 


    }//GEN-LAST:event_confirmBtnActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_amountActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        
        
       
        if(!txt_fullname1.getText().equals("") && !txt_roomnumber.getText().equals(""))
        {
            
            try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Manage_Guest WHERE fullname = ?");
                PreparedStatement ps1 = conn.prepareStatement("UPDATE room set room_status='Not Booked' WHERE room_number = ?");
                String fullname = txt_fullname1.getText();
                String roomnum = txt_roomnumber.getText();
                ps.setString(1, fullname);
                ps1.setString(1,roomnum);
                ps.executeUpdate();
                ps1.executeUpdate();

                
                JOptionPane.showConfirmDialog(null, "Are you sure you what to cancel the booking?!", "Message",
               JOptionPane.YES_NO_OPTION);
                refreshGuest();
                clearCheckin();
            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
       
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        tableRoomRefresh ()  ;    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void search_fullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fullnameActionPerformed
             String fullnameb =search_txt.getText();
//        String dateValue=((JTextField)checkinB.getDateEditor().getUiComponent()).getText();
        
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from Manage_Guest where fullname='"+fullnameb+"' and Guest_status='Updating Account'");
            ResultSet rs = ps.executeQuery();
             if(rs.next()) 
            {
                fullname_txt.setText(rs.getString(1));
                email_txt.setText(rs.getString(2));
                address_txt.setText(rs.getString(3));
                phone_txt.setText(rs.getString(4));
                roomType_txt.setText(rs.getString(5));
               
                roomnum_txt.setText(rs.getString(6));
//                room_numberB.setEditable(false);
                price_txt.setText(rs.getString(7));
               
                
                String dateValue = rs.getString(8); // What ever column
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
                check_in_txt.setDate(date);
//                check_in_txt.setEnabled(false);
                
                String dateValue1 = rs.getString(9); // What ever column
                java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue1);
                check_out_txt.setDate(date1);
                
                days_txt.setText(rs.getString(10));
                person_txt.setText(rs.getString(11));
            
                amount_txt.setText(rs.getString(12));
                
                method_txt.setText(rs.getString(13));
                
                amountStatus_txt.setText(rs.getString(14));
                guestStatus_txt.setText(rs.getString(15));
            
                
                
            }
            
            else
            {
              JOptionPane.showMessageDialog(null,"Can't find guest fullname!");
            }
        }
        
        
        catch(SQLException se){
                    System.out.println(se);
                    
                } catch (ParseException ex) {
          
        }
    }//GEN-LAST:event_search_fullnameActionPerformed

    private void request_account_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_request_account_tableMouseClicked
      
        int index = request_account_table.getSelectedRow();
        ShowItemGuest1(index);
    }//GEN-LAST:event_request_account_tableMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
         if(!fullname_txt.getText().equals("") && !roomnum_txt.getText().equals(""))
     {
         
         
          try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("UPDATE Manage_Guest set Guest_status='Booked In' WHERE fullname = ?");
                PreparedStatement ps1 = conn.prepareStatement("UPDATE room set room_status='Booked' WHERE room_number = ?");
                String fullname2 = fullname_txt.getText();
                String roomNum3 = roomnum_txt.getText();
                ps.setString(1, fullname2);
                ps1.setString(1,roomNum3);
                ps.executeUpdate();
                ps1.executeUpdate();

                
                JOptionPane.showMessageDialog(null,"Account Successfully Updated!");
                UpdateGuestAccount();
                tableRoomRefresh ();
                requestTable ();
                DataPanelTable ();
                
               
                clearUpdate();
            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
   
   


        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
           
       
        if(!fullname_txt.getText().equals("") && !roomnum_txt.getText().equals(""))
        {
            
            try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Manage_Guest WHERE fullname = ?");
                PreparedStatement ps1 = conn.prepareStatement("UPDATE room set room_status='Not Booked' WHERE room_number = ?");
                String fullname4 = fullname_txt.getText();
                String roomnum4 = roomnum_txt.getText();
                ps.setString(1, fullname4);
                ps1.setString(1,roomnum4);
                ps.executeUpdate();
                ps1.executeUpdate();

                
                JOptionPane.showConfirmDialog(null, "Are you sure you what to cancel the update booking?!", "Message",
               JOptionPane.YES_NO_OPTION);
                UpdateGuestAccount();
                clearUpdate();
            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void Table_listDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_listDataMouseClicked
       int index = Table_listData.getSelectedRow();
        TableDetailsGuest(index);
        
        
        
    }//GEN-LAST:event_Table_listDataMouseClicked

    private void searchhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchhKeyReleased

             System.out.print ("You typed "+ searchh.getText() + "\n ");
        
        String search = searchh.getText();
        int i=0;
       
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from Manage_Guest where fullname like '%"+search+"%'");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) Table_listData.getModel();
                tm.setRowCount(0);
                while(rs.next()){
                    
                    Object row[]={
                        
                        rs.getString("Guest_status"),
                        rs.getString("fullname"),
                        rs.getString("check_in"),
                        rs.getString("check_out"),
                        rs.getString("Amount_Status"),};
                    tm.addRow(row);
                    }
                conn.close();
                }catch(SQLException se){
                    System.out.println(se);
                }
        
  
    }//GEN-LAST:event_searchhKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        clearfieldsData();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void sendMessageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageBtnActionPerformed
      
            Desktop browser = Desktop.getDesktop();
        
        try{
            try {
                browser.browse(new URI ("https://mail.google.com/mail/u/0/#inbox?compose=new"));
            } catch (URISyntaxException ex) {
                
            }
        }
        catch(IOException err){
            
        }
        
        
    }//GEN-LAST:event_sendMessageBtnActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      
                 Desktop browser = Desktop.getDesktop();
        
        try{
            try {
                browser.browse(new URI ("https://mail.google.com/mail/u/0/#inbox?compose=new"));
            } catch (URISyntaxException ex) {
                
            }
        }
        catch(IOException err){
            
        }
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void keywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keywordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keywordActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       refreshGuest();
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JTextField RoomNumber2;
    private javax.swing.JTable Table_listData;
    private javax.swing.JTextField address_txt;
    public static javax.swing.JLabel adminName;
    private javax.swing.JTextField amountStatus_txt;
    private javax.swing.JTextField amount_txt;
    private com.toedter.calendar.JDateChooser check_in_txt;
    private com.toedter.calendar.JDateChooser check_out_txt;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JTextField days_txt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField email_txt;
    private javax.swing.JTextField fullname_txt;
    private javax.swing.JTextField guestStatus_txt;
    private javax.swing.JTable guest_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser22;
    private com.toedter.calendar.JDateChooser jDateChooser23;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField keyword;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_image2;
    private javax.swing.JTextField method_txt;
    private javax.swing.JTextField person_txt;
    private javax.swing.JTextField phone_txt;
    private javax.swing.JTextField price_txt;
    private javax.swing.JTable request_account_table;
    private javax.swing.JTable request_list;
    private javax.swing.JTable roomTable_list;
    private javax.swing.JTextField roomType_txt;
    private javax.swing.JTextField roomnum_txt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton search_fullname;
    private javax.swing.JTextField search_txt;
    private javax.swing.JTextField searchh;
    private javax.swing.JButton sendMessageBtn;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_address2;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_amount2;
    private javax.swing.JTextField txt_amountStatus;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_contact2;
    private javax.swing.JTextField txt_day;
    private javax.swing.JTextField txt_day2;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_email2;
    private javax.swing.JTextField txt_fullname1;
    private javax.swing.JTextField txt_fullname2;
    private javax.swing.JTextField txt_guest2;
    private javax.swing.JLabel txt_image2;
    private javax.swing.JTextField txt_method;
    private javax.swing.JLabel txt_numberperson;
    private javax.swing.JTextField txt_person;
    private javax.swing.JTextField txt_person2;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_price2;
    private javax.swing.JTextField txt_roomNum;
    private javax.swing.JTextField txt_roomNum2;
    private javax.swing.JComboBox<String> txt_roomType;
    private javax.swing.JComboBox<String> txt_roomType2;
    private javax.swing.JTextField txt_roomnumber;
    private javax.swing.JTextField txt_roomnumber2;
    private javax.swing.JTextField txt_roomprice;
    private javax.swing.JTextField txt_roomprice2;
    private javax.swing.JTextField txt_roomtype;
    private javax.swing.JTextField txt_roomtype2;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
