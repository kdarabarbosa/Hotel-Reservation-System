/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_reservation_system;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kathleen Barbosa
 */
public class CustomerDashboard extends javax.swing.JFrame {

    String dbURL = "jdbc:oracle:thin:@localhost:1521:cdb";
    String userN= "system";
    String passW = "Lucena123!";
    
  
    
    
    
    Connection conn = null;
    Statement stmt = null;
    Statement stmt1 = null;
    
    
    public CustomerDashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
//        tableRoomRefresh ();
        tableRoomRefresh2 ();
       
       
        
        
       
    }
    
    String Room_type;
    String Room_number;
    String Room_price;
    String ImgPath =null;
    
    public void roomDetails()
    {
        RoomNoCombo.removeAllItems();
        txt_price.setText("");
        Room_type=(String)combo_roomtype.getSelectedItem();
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room where room_type='"+Room_type+"' and room_status='Not Booked'");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                 RoomNoCombo.addItem(rs.getString("room_number"));
            }
        }
        catch(SQLException se){
                    System.out.println(se);
                }
    }
    
    
//     public void tableRoomRefresh ()
//    {
//        
//        
//         try {
//            conn = DriverManager.getConnection(dbURL, userN, passW);
//            PreparedStatement ps = conn.prepareStatement("SELECT * from room");
//            ResultSet rs = ps.executeQuery();
//            DefaultTableModel tm = (DefaultTableModel) roomTable_list.getModel();
//            tm.setRowCount(0);
//            while(rs.next()){
//            Object row[]= {
//                
//                rs.getString("room_number"),
//                rs.getString("room_type"),
//                rs.getString("room_price"),
//                rs.getString("room_status")
//               };
//            tm.addRow(row);
//            }
//            conn.close();
//            
//        }catch(SQLException se){
//            System.out.println(se);
//        } //end try
// 
//    }
     
     public void clearCheckIn()
     {
         txt_fullname.setText("");
         txt_email.setText("");
         txt_address.setText("");
         txt_phone.setText("");
         txt_price.setText("");
         
         
         ((JTextField)combo_checkin.getDateEditor().getUiComponent()).setText("");
         ((JTextField)combo_checkout.getDateEditor().getUiComponent()).setText("");
         txt_days.setText("");
         txt_numperson.setText("");
         lbl_imagee.setText("");
//         txt_filename.setText("");
         txt_payment.setText("");
         roomNumber();
         
     }
     
     public void refreshpaid()
     {
         amountStatus_txt.setText("Paid");
         amount_txt.setText("0");
     }
      public void clearCheckout()
      {
          checkout_search_txt.setText("");
          fullname_txt.setText("");
          email_txt.setText("");
          address_txt.setText("");
          phone_txt.setText("");
          roomnum_txt.setText("");
          roomType_txt.setText("");
          price_txt.setText("");
          person_txt.setText("");
          guestStatus_txt.setText("");
          ((JTextField)check_in_txt.getDateEditor().getUiComponent()).setText("");
         ((JTextField)check_out_txt.getDateEditor().getUiComponent()).setText("");
          
          days_txt.setText("");
          amount_txt.setText("");
          method_txt.setText("");
          amountStatus_txt.setText("");
          
          
      }
     
     
     public void clearManageBooking()
     {
         txt_fullnameB.setText("");
         emailB.setText("");
         AddressB.setText("");
         phoneB.setText("");
         guest_status.setText("");
         room_numberB.setText("");
         room_typeB.setText("");
         room_priceB.setText("");
         PersonB.setText("");
         ((JTextField)checkinB.getDateEditor().getUiComponent()).setText("");
         ((JTextField)checkoutB.getDateEditor().getUiComponent()).setText("");
         DayB.setText("");
         payB.setText("");
         advancedPaymentB.setText("");
         BalanceB.setText("");
         cardNo.setText("");
         
         credit.setSelected(false);
         online.setSelected(false);
         cash.setSelected(false);
         gcash.setSelected(false);
         roomNumber();
         
     }
     
     
     public void roomNumber(){
         
         
         int i=0;
         try
         {
           conn = DriverManager.getConnection(dbURL, userN, passW);
           PreparedStatement ps = conn.prepareStatement("SELECT * from room where activate='Yes' and room_status='Not Booked'");
           ResultSet rs = ps.executeQuery();
           while(rs.next()) 
           {
               i=1;
               RoomNoCombo.addItem(rs.getString(1));
           }
           if(i==0)
           {
               save1.setVisible(false);
               JOptionPane.showMessageDialog(null,"All Rooms are already Booked");
           }
         }catch(SQLException se){
              System.out.println(se);
         }
     }
    
   
     
     
     public void tableRoomRefresh2 ()
    {
        
        
         try {
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) roomTable_list2.getModel();
            tm.setRowCount(0);
            while(rs.next()){
            Object row[]= {
                
                rs.getString("room_number"),
                rs.getString("room_type"),
                rs.getString("room_price"),
                rs.getString("room_status")
               };
            tm.addRow(row);
            }
            conn.close();
            
        }catch(SQLException se){
            System.out.println(se);
        } //end try
 
    }
   
     
     
     
     
     
     
     
     
     
     
     

      /// ///Resize Image
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
         Image img2 = img.getScaledInstance(lbl_imagee.getWidth(), lbl_imagee.getHeight(), Image.SCALE_SMOOTH);
         ImageIcon image = new ImageIcon(img2);
         return image;
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
        checkInBtn = new javax.swing.JButton();
        CheckoutBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        RoomsBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomTable_list2 = new javax.swing.JTable();
        keyword = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_fullname = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        RoomNoCombo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        save1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        combo_roomtype = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        combo_checkin = new com.toedter.calendar.JDateChooser();
        combo_checkout = new com.toedter.calendar.JDateChooser();
        DayBtn = new javax.swing.JButton();
        txt_days = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        lbl_imagee = new javax.swing.JLabel();
        txt_numperson = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txt_payment = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        combo_method = new javax.swing.JComboBox<>();
        Btn_Choose_Image = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txt_fullnameB = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        room_numberB = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        room_typeB = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        checkoutB = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        room_priceB = new javax.swing.JTextField();
        payB = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        guest_status = new javax.swing.JTextField();
        checkinB = new com.toedter.calendar.JDateChooser();
        jLabel63 = new javax.swing.JLabel();
        PersonB = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        DayB = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        phoneB = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        emailB = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        AddressB = new javax.swing.JTextField();
        PaynowBtn = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        advancedPaymentB = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        BalanceB = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        manage_searchBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        credit = new javax.swing.JCheckBox();
        cash = new javax.swing.JCheckBox();
        online = new javax.swing.JCheckBox();
        gcash = new javax.swing.JCheckBox();
        jLabel69 = new javax.swing.JLabel();
        cardNo = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        AmountBtn = new javax.swing.JButton();
        countdayBtn = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        searchCheckoutBtn = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        person_txt = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        checkout_search_txt = new javax.swing.JTextField();
        fullname_txt = new javax.swing.JTextField();
        email_txt = new javax.swing.JTextField();
        address_txt = new javax.swing.JTextField();
        phone_txt = new javax.swing.JTextField();
        roomnum_txt = new javax.swing.JTextField();
        roomType_txt = new javax.swing.JTextField();
        price_txt = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        days_txt = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        guestStatus_txt = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        check_out_txt = new com.toedter.calendar.JDateChooser();
        check_in_txt = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        amountStatus_txt = new javax.swing.JTextField();
        amount_txt = new javax.swing.JTextField();
        PayBtn = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        method_txt = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        CheckOutBtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 153));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setMinimumSize(new java.awt.Dimension(250, 610));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 640));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkInBtn.setBackground(new java.awt.Color(255, 255, 0));
        checkInBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/customer-removebg-preview (1).png"))); // NOI18N
        checkInBtn.setText("Check In");
        checkInBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        checkInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInBtnActionPerformed(evt);
            }
        });
        jPanel1.add(checkInBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 200, 60));

        CheckoutBtn.setBackground(new java.awt.Color(255, 255, 0));
        CheckoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/checkout-removebg-preview.png"))); // NOI18N
        CheckoutBtn.setText("Check Out");
        CheckoutBtn.setToolTipText("");
        CheckoutBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        CheckoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckoutBtnActionPerformed(evt);
            }
        });
        jPanel1.add(CheckoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 200, 60));

        CancelBtn.setBackground(new java.awt.Color(255, 255, 0));
        CancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/e-removebg-preview (1).png"))); // NOI18N
        CancelBtn.setText("Manage Booking");
        CancelBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(CancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 200, 60));

        RoomsBtn.setBackground(new java.awt.Color(255, 255, 0));
        RoomsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/manage-removebg-preview.png"))); // NOI18N
        RoomsBtn.setText("Room");
        RoomsBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        RoomsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomsBtnActionPerformed(evt);
            }
        });
        jPanel1.add(RoomsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 60));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/smallLogo-removebg-preview.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 130, 90));

        jLabel14.setFont(new java.awt.Font("Eras Bold ITC", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Welcome To");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/25694-removebg-preview.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 200, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, -1));

        jPanel7.setBackground(new java.awt.Color(153, 51, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roomTable_list2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Room Number", "Room Type", "Room Price"
            }
        ));
        roomTable_list2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTable_list2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(roomTable_list2);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 730, 160));

        keyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                keywordKeyReleased(evt);
            }
        });
        jPanel7.add(keyword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 170, 30));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 60, 50));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Search for Room Type");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));
        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/singleroom.png"))); // NOI18N
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/double room.jpg"))); // NOI18N
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/King.PNG"))); // NOI18N
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Double-double.PNG"))); // NOI18N
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, 70));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Twin.PNG"))); // NOI18N
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 120));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Queen.PNG"))); // NOI18N
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Quad.PNG"))); // NOI18N
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/triple.PNG"))); // NOI18N
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, 100));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Master Bedroom.jpg"))); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Single Room  ");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Double Room ");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Triple Room ");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Quad Room  ");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Twin Room ");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Double-double Room");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Queen Room");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("King Room  ");
        jPanel4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Master Room ");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Murphy room.PNG"))); // NOI18N
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Murphy Room ");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Adjacent.PNG"))); // NOI18N
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Adjacent Room");
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, -1, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/adjoining.PNG"))); // NOI18N
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Adjoining Room");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Studio.PNG"))); // NOI18N
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 530, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Studio Room ");
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, -1, -1));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Suite.PNG"))); // NOI18N
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Suite Room ");
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 640, -1, -1));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/Villa.PNG"))); // NOI18N
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, -1));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/large_deluxe-double-double.jpg"))); // NOI18N
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 530, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Villa Room ");
        jPanel4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Delux Room");
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, -1, -1));

        jScrollPane4.setViewportView(jPanel4);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 730, 350));

        jTabbedPane2.addTab("tab2", jPanel7);

        jPanel6.setBackground(new java.awt.Color(153, 51, 255));
        jPanel6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel6ComponentShown(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fullname:");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email:");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 40, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Address:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Phone Number:");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel8.setFont(new java.awt.Font("Eras Bold ITC", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Check In");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 280, 10));

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));
        jPanel3.setForeground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 250, 10));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 0));
        jLabel10.setText("Guest Information");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));
        jPanel6.add(txt_fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 180, 30));

        txt_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phoneActionPerformed(evt);
            }
        });
        jPanel6.add(txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 180, 30));
        jPanel6.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 180, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("How many Person to check in:");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));
        jPanel6.add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 180, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Room Booking");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Choose Room No:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        RoomNoCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomNoComboActionPerformed(evt);
            }
        });
        jPanel6.add(RoomNoCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 180, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Check out:");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Check in:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 30));

        save1.setBackground(new java.awt.Color(255, 204, 204));
        save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/save-removebg-preview.png"))); // NOI18N
        save1.setText("Check in");
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });
        jPanel6.add(save1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 130, 50));

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/UPDATE-removebg-preview.png"))); // NOI18N
        jButton2.setText("Clear Form");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 130, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Choose Room Type:");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        combo_roomtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Room ", "Double Room ", "Triple Room ", "Quad Room ", "Twin Room ", "Double-double Room", "Queen Room", "King Room ", "Master Room ", "Deluxe Room ", "Adjacent Room", "Adjoining Room", "Studio Room ", "Suite Room ", "Villa Room ", " ", " " }));
        combo_roomtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_roomtypeActionPerformed(evt);
            }
        });
        jPanel6.add(combo_roomtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 180, 30));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Price:");
        jPanel6.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 60, -1));

        txt_price.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txt_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 180, 30));

        combo_checkin.setDateFormatString("yyyy-MM-dd");
        jPanel6.add(combo_checkin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 180, 30));

        combo_checkout.setDateFormatString("yyyy-MM-dd");
        jPanel6.add(combo_checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 180, 30));

        DayBtn.setBackground(new java.awt.Color(255, 204, 204));
        DayBtn.setForeground(new java.awt.Color(255, 0, 0));
        DayBtn.setText("Click to Display Number of Day:");
        DayBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        DayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DayBtnActionPerformed(evt);
            }
        });
        jPanel6.add(DayBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 210, 30));

        txt_days.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_days.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txt_days, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 60, 30));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Upload ID for Verification");
        jPanel6.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, 30));

        lbl_imagee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_imagee.setOpaque(true);
        jPanel6.add(lbl_imagee, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 300, 160));

        txt_numperson.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txt_numperson, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 120, 30));

        jButton3.setBackground(new java.awt.Color(255, 204, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Calculate Amount to Pay");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 300, 40));

        txt_payment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_payment.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(txt_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 300, 40));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/c-removebg-preview.png"))); // NOI18N
        jPanel6.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Payment Method:");
        jPanel6.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, -1, -1));

        combo_method.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit Card", "Cash", "Online Bank", "GCash" }));
        jPanel6.add(combo_method, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 200, 30));

        Btn_Choose_Image.setText("Attach");
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });
        jPanel6.add(Btn_Choose_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, -1, -1));

        jButton4.setBackground(new java.awt.Color(255, 204, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/print-removebg-preview.png"))); // NOI18N
        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, 120, 50));

        jTabbedPane2.addTab("tab1", jPanel6);

        jPanel8.setBackground(new java.awt.Color(153, 51, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Manage your Booking");
        jPanel8.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/b.png"))); // NOI18N
        jPanel8.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, -20, -1, -1));
        jPanel8.add(txt_fullnameB, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 200, 30));

        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Room Number:");
        jPanel8.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, 20));
        jPanel8.add(room_numberB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 120, 30));

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Room Type:");
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 20));
        jPanel8.add(room_typeB, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 110, 30));

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Check in Date:");
        jPanel8.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));

        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Check out Date:");
        jPanel8.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, -1, -1));

        checkoutB.setDateFormatString("yyyy-MM-dd");
        jPanel8.add(checkoutB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 270, 30));

        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Room Price:");
        jPanel8.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));
        jPanel8.add(room_priceB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 140, 30));

        payB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(payB, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 130, 50));

        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Guest Status:");
        jPanel8.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, 30));
        jPanel8.add(guest_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 120, 30));

        checkinB.setDateFormatString("yyyy-MM-dd");
        jPanel8.add(checkinB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 270, 30));

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("No Person Checking In:");
        jPanel8.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, -1, -1));

        PersonB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(PersonB, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 130, 30));

        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Number of Days Stay:");
        jPanel8.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        DayB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(DayB, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 110, 30));

        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Email:");
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));
        jPanel8.add(phoneB, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 110, 30));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Phone Number:");
        jPanel8.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));
        jPanel8.add(emailB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, 30));

        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Address:");
        jPanel8.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));
        jPanel8.add(AddressB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 120, 30));

        PaynowBtn.setBackground(new java.awt.Color(255, 204, 204));
        PaynowBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PaynowBtn.setText("Pay Now");
        PaynowBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        PaynowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaynowBtnActionPerformed(evt);
            }
        });
        jPanel8.add(PaynowBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 140, 40));

        jLabel73.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Balance:");
        jPanel8.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, -1, -1));

        advancedPaymentB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(advancedPaymentB, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, 130, 30));

        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Input Advanced Payment Amount:");
        jPanel8.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 364, -1, 20));

        BalanceB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel8.add(BalanceB, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 130, 50));

        jButton5.setBackground(new java.awt.Color(255, 204, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/save-removebg-preview.png"))); // NOI18N
        jButton5.setText("Update");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 160, 50));

        jButton6.setBackground(new java.awt.Color(255, 204, 204));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/cancel-removebg-preview.png"))); // NOI18N
        jButton6.setText("Cancel Booking");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 560, 160, 50));

        jButton7.setBackground(new java.awt.Color(255, 204, 204));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/UPDATE-removebg-preview.png"))); // NOI18N
        jButton7.setText("Clear ");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 120, 50));

        manage_searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        manage_searchBtn.setText("Search Fullname:");
        manage_searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manage_searchBtnActionPerformed(evt);
            }
        });
        jPanel8.add(manage_searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 200, 30));

        jPanel5.setBackground(new java.awt.Color(153, 0, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Payment Method");
        jPanel5.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Choose Method:");
        jPanel5.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        credit.setBackground(new java.awt.Color(153, 0, 51));
        credit.setForeground(new java.awt.Color(255, 255, 255));
        credit.setText("Credit Card");
        jPanel5.add(credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        cash.setBackground(new java.awt.Color(153, 0, 51));
        cash.setForeground(new java.awt.Color(255, 255, 255));
        cash.setText("Cash");
        jPanel5.add(cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        online.setBackground(new java.awt.Color(153, 0, 51));
        online.setForeground(new java.awt.Color(255, 255, 255));
        online.setText("Online Bank");
        jPanel5.add(online, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        gcash.setBackground(new java.awt.Color(153, 0, 51));
        gcash.setForeground(new java.awt.Color(255, 255, 255));
        gcash.setText("GCash");
        jPanel5.add(gcash, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Card No. ");
        jPanel5.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));
        jPanel5.add(cardNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 240, 30));

        jPanel8.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 380, 200));

        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("--------------------------------------------------------------------------------");
        jPanel8.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, -1, -1));

        AmountBtn.setBackground(new java.awt.Color(255, 204, 204));
        AmountBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AmountBtn.setText("Amount To Pay");
        AmountBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        AmountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmountBtnActionPerformed(evt);
            }
        });
        jPanel8.add(AmountBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 140, 50));

        countdayBtn.setBackground(new java.awt.Color(255, 204, 204));
        countdayBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        countdayBtn.setText("Count Days");
        countdayBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        countdayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countdayBtnActionPerformed(evt);
            }
        });
        jPanel8.add(countdayBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 110, 40));

        jButton8.setBackground(new java.awt.Color(255, 204, 204));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/print-removebg-preview.png"))); // NOI18N
        jButton8.setText("Print");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 560, 120, 50));

        jTabbedPane2.addTab("tab3", jPanel8);

        jPanel9.setBackground(new java.awt.Color(153, 51, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchCheckoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/images-removebg-preview.png"))); // NOI18N
        searchCheckoutBtn.setText("Search Fullname:");
        searchCheckoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCheckoutBtnActionPerformed(evt);
            }
        });
        jPanel9.add(searchCheckoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 40));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/checkout-removebg-preview (1).png"))); // NOI18N
        jPanel9.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 120, 140));

        jLabel61.setFont(new java.awt.Font("Eras Bold ITC", 1, 36)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Check Out");
        jPanel9.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 140, 10));

        jPanel11.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 280, 10));

        person_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(person_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 550, 70, 30));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Number of Person Checking in:");
        jPanel9.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Fullname:");
        jPanel9.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 20));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Email:");
        jPanel9.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Address:");
        jPanel9.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Contact No.");
        jPanel9.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Room Number:");
        jPanel9.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Room Type:");
        jPanel9.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Room Price:");
        jPanel9.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));
        jPanel9.add(checkout_search_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 190, 40));

        fullname_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(fullname_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 180, 30));

        email_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(email_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 180, 30));

        address_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        address_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                address_txtActionPerformed(evt);
            }
        });
        jPanel9.add(address_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 180, 30));

        phone_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(phone_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 180, 30));

        roomnum_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(roomnum_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 180, 30));

        roomType_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        roomType_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomType_txtActionPerformed(evt);
            }
        });
        jPanel9.add(roomType_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 180, 30));

        price_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 180, 30));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Personal Information:");
        jPanel9.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 240, 10));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Room Booking:");
        jPanel9.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Check In:");
        jPanel9.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, 20));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Check Out:");
        jPanel9.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Number of Days Stay:");
        jPanel9.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, -1, -1));

        days_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(days_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 80, 30));

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Gues Status:");
        jPanel9.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));

        guestStatus_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(guestStatus_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 180, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 270, 10));

        jPanel14.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 270, 10));
        jPanel9.add(check_out_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 180, 30));
        jPanel9.add(check_in_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 180, 30));

        jPanel15.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 280, 10));

        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Balance:");
        jPanel9.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, -1, -1));

        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Payment Method:");
        jPanel9.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, -1));

        amountStatus_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(amountStatus_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, 180, 30));

        amount_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(amount_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 180, 30));

        PayBtn.setText("Pay Balance");
        PayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayBtnActionPerformed(evt);
            }
        });
        jPanel9.add(PayBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 100, 30));

        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Amount Status:");
        jPanel9.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, -1, -1));

        method_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel9.add(method_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, 180, 30));

        jPanel16.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 550, 280, 10));

        CheckOutBtn.setBackground(new java.awt.Color(255, 204, 204));
        CheckOutBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CheckOutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/checkout-removebg-preview.png"))); // NOI18N
        CheckOutBtn.setText("Check Out");
        CheckOutBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        CheckOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutBtnActionPerformed(evt);
            }
        });
        jPanel9.add(CheckOutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 570, 150, 50));

        jButton9.setBackground(new java.awt.Color(255, 204, 204));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hotel_reservation_system/img/print-removebg-preview.png"))); // NOI18N
        jButton9.setText("Print");
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 570, 120, 50));

        jTabbedPane2.addTab("tab4", jPanel9);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, 790, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
         jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void CheckoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckoutBtnActionPerformed
         jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_CheckoutBtnActionPerformed

    private void checkInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInBtnActionPerformed
         jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_checkInBtnActionPerformed

    private void RoomsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomsBtnActionPerformed
         jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_RoomsBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
            setVisible(false);
            new MainFrame().setVisible(true);
        
             
    }//GEN-LAST:event_jButton1ActionPerformed

    private void roomTable_list2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTable_list2MouseClicked
      
       
    }//GEN-LAST:event_roomTable_list2MouseClicked

    private void keywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keywordKeyReleased
        System.out.print ("You typed "+ keyword.getText() + "\n ");
        
        String search = keyword.getText();
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room where room_type like '%"+search+"%'");
            ResultSet rs = ps.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel) roomTable_list2.getModel();
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
        
    }//GEN-LAST:event_keywordKeyReleased

    private void jPanel6ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel6ComponentShown
//        roomDetails();
        roomNumber();
    }//GEN-LAST:event_jPanel6ComponentShown

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearCheckIn();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed

          if( ImgPath != null)
             {
                try
                {
            
                        conn = DriverManager.getConnection(dbURL, userN, passW);

                        
                        PreparedStatement pst = conn.prepareStatement(" INSERT INTO Manage_Guest (fullname, email, address, phone, room_Type, room_Num, room_Price, check_in, check_out, num_day, num_Person, total_Amount, payment_method, Amount_Status, Guest_status, image)"
                                +"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        pst.setString(1,txt_fullname.getText());
                        pst.setString(2,txt_email.getText());
                        pst.setString(3,txt_address.getText());
                        pst.setString(4,txt_phone.getText());
                        pst.setString(5,(String)combo_roomtype.getSelectedItem());
                        pst.setString(6,(String)RoomNoCombo.getSelectedItem());
                        pst.setString(7,txt_price.getText());
                        pst.setString(8,((JTextField)combo_checkin.getDateEditor().getUiComponent()).getText());
                        pst.setString(9,((JTextField)combo_checkout.getDateEditor().getUiComponent()).getText());
                        pst.setString(10,txt_days.getText());
                        pst.setString(11,txt_numperson.getText());
                        pst.setInt(12, Integer.parseInt(txt_payment.getText()));
                        pst.setString(13,(String)combo_method.getSelectedItem());
                        pst.setString(14,"Not Paid");
                        pst.setString(15,"Request To Booked In");
                        

                        InputStream img = new FileInputStream(new File (ImgPath));
                        pst.setBlob(16, img);
                        pst.executeUpdate();
                        
                         JOptionPane.showMessageDialog(null,"Checking in successfully!, Please wait for the email confirmation!");
                         clearCheckIn();
                        
                        
                        
            
                        PreparedStatement ps=conn.prepareStatement("UPDATE room set room_status='Request to Booked' where room_number=?");
                        ps.setString(1, (String)RoomNoCombo.getSelectedItem());
                        ps.executeUpdate();

                        
                         
                         
                        conn.close();
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
             }else{
                    JOptionPane.showMessageDialog(null, "Please attached File!");
          }
          System.out.println("Fullname =>" +txt_fullname.getText());
          System.out.println("Email =>" +txt_email.getText());
          System.out.println("Address =>" +txt_address.getText());
          System.out.println("Phone No. =>" +txt_phone.getText());
          System.out.println("Room Type =>" +(String)combo_roomtype.getSelectedItem());
          System.out.println("Room Number =>" +(String)RoomNoCombo.getSelectedItem());
          System.out.println("Room Price =>" +txt_price.getText());
          System.out.println("Check in =>" +((JTextField)combo_checkin.getDateEditor().getUiComponent()).getText());
          System.out.println("Check out =>" +((JTextField)combo_checkout.getDateEditor().getUiComponent()).getText());
          System.out.println("Number of Days Stay =>" +txt_days.getText());
          System.out.println("Number of Person checking in =>" +txt_numperson.getText());
          System.out.println("Total Amount =>" +Integer.parseInt(txt_payment.getText()));
          System.out.println("Payment Method =>" +(String)combo_method.getSelectedItem());
          System.out.println("Amount Status =>" + "Not Paid");
          System.out.println("Guest Status=>" + "Booked In");
          System.out.println("Image =>" +ImgPath);
              
        
        

    }//GEN-LAST:event_save1ActionPerformed

    private void txt_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phoneActionPerformed

    private void DayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DayBtnActionPerformed
       
         String fromdate=((JTextField)combo_checkin.getDateEditor().getUiComponent()).getText();
        String todate=((JTextField)combo_checkout.getDateEditor().getUiComponent()).getText();
        
        LocalDate fday=LocalDate.parse(fromdate);
        LocalDate tday=LocalDate.parse(todate);
        
        
        Long day_gap =ChronoUnit.DAYS.between(fday, tday);
        txt_days.setText(String.valueOf(day_gap));
    }//GEN-LAST:event_DayBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
            
        String p = txt_price.getText().toString();
        String nd=txt_days.getText().toString();
        String np=txt_numperson.getText().toString();
        
        int price = Integer.parseInt(p);
        int numberday = Integer.parseInt(nd);
        int numberperson = Integer.parseInt(np);
        int s = price*numberday*numberperson;
        
         txt_payment.setText(""+s);
        

        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void RoomNoComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomNoComboActionPerformed
       
        Room_number=(String)RoomNoCombo.getSelectedItem();
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from room where room_number='"+Room_number+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                txt_price.setText(rs.getString("room_price"));
            }
            
            
        }
        catch(SQLException se){
                    System.out.println(se);
                }
        
        
    }//GEN-LAST:event_RoomNoComboActionPerformed

    private void combo_roomtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_roomtypeActionPerformed
     roomDetails();        
    }//GEN-LAST:event_combo_roomtypeActionPerformed

    private void PaynowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaynowBtnActionPerformed
        
        int total_amount1=Integer.parseInt(payB.getText());
        int advancePayment=Integer.parseInt(advancedPaymentB.getText());
        int balance_amount=total_amount1-advancePayment;
        BalanceB.setText(" "+balance_amount);
        
        
    }//GEN-LAST:event_PaynowBtnActionPerformed

    private void manage_searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manage_searchBtnActionPerformed
        String fullnameb =txt_fullnameB.getText();
//        String dateValue=((JTextField)checkinB.getDateEditor().getUiComponent()).getText();
        
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from Manage_Guest where fullname='"+fullnameb+"' and Guest_status='Booked In'");
            ResultSet rs = ps.executeQuery();
             if(rs.next()) 
            {
//                txt_fullnameB.setEditable(false);
                emailB.setText(rs.getString(2));
                AddressB.setText(rs.getString(3));
                phoneB.setText(rs.getString(4));
                room_typeB.setText(rs.getString(5));
                room_typeB.setEditable(false);
                room_numberB.setText(rs.getString(6));
                room_numberB.setEditable(false);
                room_priceB.setText(rs.getString(7));
                room_priceB.setEditable(false);
                
                String dateValue = rs.getString(8); // What ever column
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
                checkinB.setDate(date);
                checkinB.setEnabled(false);
                
                String dateValue1 = rs.getString(9); // What ever column
                java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue1);
                checkoutB.setDate(date1);
                
                DayB.setText(rs.getString(10));
                PersonB.setText(rs.getString(11));
                PersonB.setEditable(false);
                
//                 payB.setInteger(12, Integer.parseInt(payB.getText()));
                payB.setText(rs.getString(12));

                
             
                
                if(rs.getString(13).equals("Credit Card"))
                {
                    credit.setSelected(true);
                }
                else if (rs.getString(13).equals("Cash"))
                {
                     cash.setSelected(true);
                }
                else if (rs.getString(13).equals("Online Bank"))
                {
                     online.setSelected(true);
                }else if (rs.getString(13).equals("Gcash"))
                {
                    
                    gcash.setSelected(true);
                }
                

               guest_status.setText(rs.getString(15));
               guest_status.setEditable(false);
            }
            
            else
            {
              JOptionPane.showMessageDialog(null,"Guest does not exist OR the Admin still processing the request Booked In");
            }
        }
        
        
        catch(SQLException se){
                    System.out.println(se);
                    
                } catch (ParseException ex) {
            Logger.getLogger(CustomerDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_manage_searchBtnActionPerformed

    private void AmountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmountBtnActionPerformed
        
        String p2 = room_priceB.getText().toString();
        String nd2=DayB.getText().toString();
        String np2=PersonB.getText().toString();
        
        int price2 = Integer.parseInt(p2);
        int numberday2 = Integer.parseInt(nd2);
        int numberperson2 = Integer.parseInt(np2);
        int s2 = price2*numberday2*numberperson2;
        
         payB.setText(""+s2);
        
    }//GEN-LAST:event_AmountBtnActionPerformed

    private void countdayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countdayBtnActionPerformed
       
        String fromdate=((JTextField)checkinB.getDateEditor().getUiComponent()).getText();
        String todate=((JTextField)checkoutB.getDateEditor().getUiComponent()).getText();
        
        LocalDate fday=LocalDate.parse(fromdate);
        LocalDate tday=LocalDate.parse(todate);
        
        
        Long day_gap =ChronoUnit.DAYS.between(fday, tday);
        DayB.setText(String.valueOf(day_gap));
        
    }//GEN-LAST:event_countdayBtnActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
        
      String fullname2 = txt_fullnameB.getText();
      String email2 = emailB.getText();
      String address2 = AddressB.getText();
      String phone2 = phoneB.getText();
      String roomtype2 = room_typeB.getText();
      String roomNum2 = room_numberB.getText();
      String roomprice2 = room_priceB.getText();
      String checked_in2 =((JTextField)checkinB.getDateEditor().getUiComponent()).getText();
      String checked_out2=((JTextField)checkoutB.getDateEditor().getUiComponent()).getText();
      String numDay2 = DayB.getText();
      String person2 = PersonB.getText();
      int total_amount2 = Integer.parseInt(payB.getText());
      String payment_method2;
                if(credit.isSelected())
                {
                    payment_method2="Credit Card";
                }
                else if (cash.isSelected());
                {
                     payment_method2="Cash";
                }
                if (online.isSelected())
                {
                     payment_method2="Online Bank";
                     
                }
                else if (gcash.isSelected());
                {
                    payment_method2="Gcash";
                    
                }

      String Amount_Status2 = "Not Paid";
      String Guest_status2 = "Updating Account";
//      String image = txt_filename.getText();
//      image=image.replace("\\","\\\\");
     

                try
                {
            
                        conn = DriverManager.getConnection(dbURL, userN, passW);
                        stmt = conn.createStatement();
            
                        

                         
                         
                
                        String sql = "UPDATE Manage_Guest set  email='"+email2+"', address='"+address2+"', phone='"+phone2+"', room_Type='"+roomtype2+"', room_Num='"+roomNum2+"', room_Price='"+roomprice2+"', check_in='"+checked_in2+"', check_out='"+checked_out2+"', num_Day='"+numDay2+"', num_Person='"+person2+"', total_Amount='"+total_amount2+"', payment_method='"+payment_method2+"', Amount_Status='"+Amount_Status2+"', Guest_status='"+Guest_status2+"' WHERE fullname='"+fullname2+"'";
                        stmt.executeUpdate(sql);
                       
                        
                        JOptionPane.showMessageDialog(null,"Account Successfully Updated!, Please wait for the email confirmation!");
                        clearManageBooking();
                        
                         
                         
                        conn.close();
                    }catch(SQLException se) {
                        System.out.println(se);
                    }
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if(!txt_fullnameB.getText().equals("") && !room_numberB.getText().equals(""))
        {
            
            try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Manage_Guest WHERE fullname = ?");
                PreparedStatement ps1 = conn.prepareStatement("UPDATE room set room_status='Not Booked' WHERE room_number = ?");
                String fullnamed = txt_fullnameB.getText();
                String roomnum2 = room_numberB.getText();
                ps.setString(1, fullnamed);
                ps1.setString(1,roomnum2);
                ps.executeUpdate();
                ps1.executeUpdate();

                
                JOptionPane.showConfirmDialog(null, "Are you sure you what to cancel the booking?!", "Message",
                JOptionPane.YES_NO_OPTION);
              
                clearManageBooking(); 
            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
       
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        clearManageBooking();   
    }//GEN-LAST:event_jButton7ActionPerformed

    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_imagee.setIcon(ResizeImage(path, null));
            ImgPath = path;
        }
        
        else{
            System.out.println("No File Selected");
            
        }
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

    private void roomType_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomType_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomType_txtActionPerformed

    private void searchCheckoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCheckoutBtnActionPerformed
         
        
        String fullnameb =checkout_search_txt.getText();

        
        try{
            conn = DriverManager.getConnection(dbURL, userN, passW);
            PreparedStatement ps = conn.prepareStatement("SELECT * from Manage_Guest where fullname='"+fullnameb+"' and Guest_status='Booked In'");
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
        
        
        
        
      
    }//GEN-LAST:event_searchCheckoutBtnActionPerformed

    private void PayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayBtnActionPerformed
     
    
          if(!fullname_txt.getText().equals(""))
     {
         
         
          try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("UPDATE Manage_Guest set Amount_Status='Paid', total_Amount='0' WHERE fullname = ?");

                String fullname2 = fullname_txt.getText();
              
                ps.setString(1, fullname2);
                
               
                ps.executeUpdate();
                
                
                
                


                
                JOptionPane.showMessageDialog(null,"Successfully paid the Balance");
                refreshpaid();
               
               
            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
   
   
        
        
        
        
    }//GEN-LAST:event_PayBtnActionPerformed

    private void CheckOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutBtnActionPerformed
       
          if(!fullname_txt.getText().equals("") && !roomnum_txt.getText().equals(""))
     {
         
         
          try{
                conn = DriverManager.getConnection(dbURL, userN, passW);
                PreparedStatement ps = conn.prepareStatement("UPDATE Manage_Guest set Guest_status='Booked Out' WHERE fullname = ?");
                PreparedStatement ps1 = conn.prepareStatement("UPDATE room set room_status='Not Booked' WHERE room_number = ?");
                String sample_fullname = fullname_txt.getText();
                String sample_roomNum = roomnum_txt.getText();
                ps.setString(1, sample_fullname);
                ps1.setString(1,sample_roomNum);
                ps.executeUpdate();
                ps1.executeUpdate();

                
                JOptionPane.showConfirmDialog(null, "Are you sure you what to Booked out?!", "Message",
                JOptionPane.YES_NO_OPTION);
                clearCheckout();

            }
            
            catch(Exception e)
             {
                    JOptionPane.showMessageDialog(null, e);
            }
    
        }
   
        
        
        
    }//GEN-LAST:event_CheckOutBtnActionPerformed

    private void address_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_address_txtActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.PORTRAIT);
        PageFormat postformat = pjob.pageDialog(preformat);
        //If user does not hit cancel then print.
        if (preformat != postformat) {
            //Set print component
            pjob.setPrintable(new PrintResult(jPanel6), postformat);
            if (pjob.printDialog()) {
                try{
                pjob.print();
                }catch(Exception e){
                    System.out.print("Error: " + e);
                }
            }
        }      

        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       
          PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        PageFormat postformat = pjob.pageDialog(preformat);
        //If user does not hit cancel then print.
        if (preformat != postformat) {
            //Set print component
            pjob.setPrintable(new PrintResult(jPanel8), postformat);
            if (pjob.printDialog()) {
                try{
                pjob.print();
                }catch(Exception e){
                    System.out.print("Error: " + e);
                }
            }
        }      

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
          PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        PageFormat postformat = pjob.pageDialog(preformat);
        //If user does not hit cancel then print.
        if (preformat != postformat) {
            //Set print component
            pjob.setPrintable(new PrintResult(jPanel9), postformat);
            if (pjob.printDialog()) {
                try{
                pjob.print();
                }catch(Exception e){
                    System.out.print("Error: " + e);
                }
            }
        }      

    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddressB;
    private javax.swing.JButton AmountBtn;
    private javax.swing.JTextField BalanceB;
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton CheckOutBtn;
    private javax.swing.JButton CheckoutBtn;
    private javax.swing.JTextField DayB;
    private javax.swing.JButton DayBtn;
    private javax.swing.JButton PayBtn;
    private javax.swing.JButton PaynowBtn;
    private javax.swing.JTextField PersonB;
    private javax.swing.JComboBox<String> RoomNoCombo;
    private javax.swing.JButton RoomsBtn;
    private javax.swing.JTextField address_txt;
    private javax.swing.JTextField advancedPaymentB;
    private javax.swing.JTextField amountStatus_txt;
    private javax.swing.JTextField amount_txt;
    private javax.swing.JTextField cardNo;
    private javax.swing.JCheckBox cash;
    private javax.swing.JButton checkInBtn;
    private com.toedter.calendar.JDateChooser check_in_txt;
    private com.toedter.calendar.JDateChooser check_out_txt;
    private com.toedter.calendar.JDateChooser checkinB;
    private com.toedter.calendar.JDateChooser checkoutB;
    private javax.swing.JTextField checkout_search_txt;
    private com.toedter.calendar.JDateChooser combo_checkin;
    private com.toedter.calendar.JDateChooser combo_checkout;
    private javax.swing.JComboBox<String> combo_method;
    private javax.swing.JComboBox<String> combo_roomtype;
    private javax.swing.JButton countdayBtn;
    private javax.swing.JCheckBox credit;
    private javax.swing.JTextField days_txt;
    private javax.swing.JTextField emailB;
    private javax.swing.JTextField email_txt;
    private javax.swing.JTextField fullname_txt;
    private javax.swing.JCheckBox gcash;
    private javax.swing.JTextField guestStatus_txt;
    private javax.swing.JTextField guest_status;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField keyword;
    private javax.swing.JLabel lbl_imagee;
    private javax.swing.JButton manage_searchBtn;
    private javax.swing.JTextField method_txt;
    private javax.swing.JCheckBox online;
    private javax.swing.JTextField payB;
    private javax.swing.JTextField person_txt;
    private javax.swing.JTextField phoneB;
    private javax.swing.JTextField phone_txt;
    private javax.swing.JTextField price_txt;
    private javax.swing.JTable roomTable_list2;
    private javax.swing.JTextField roomType_txt;
    private javax.swing.JTextField room_numberB;
    private javax.swing.JTextField room_priceB;
    private javax.swing.JTextField room_typeB;
    private javax.swing.JTextField roomnum_txt;
    private javax.swing.JButton save1;
    private javax.swing.JButton searchCheckoutBtn;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_days;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fullname;
    private javax.swing.JTextField txt_fullnameB;
    private javax.swing.JTextField txt_numperson;
    private javax.swing.JTextField txt_payment;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables

public class PrintResult implements Printable {
    final Component comp;

    public PrintResult(Component comp){
        this.comp = comp;
    }

    public int print(Graphics g, PageFormat format, int page_index) 
            throws PrinterException {
        if (page_index > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = comp.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = format.getImageableHeight();
        double pWidth = format.getImageableWidth();

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;


        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);

        return Printable.PAGE_EXISTS;
    }
    }



}
