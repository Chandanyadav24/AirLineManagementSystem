
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;



public class CancelTicket extends JFrame implements ActionListener {
    
    JTextField tfpnr;
    JLabel tfname,cancellationno,lblfcode ,lbldate;
    JButton fetchbutton ,flight;
    
    
    public CancelTicket(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        Random random = new Random();
        
        
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180,20,250,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        add(heading);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470,120,250,250);
        add(image);
        
        
        JLabel lblaadhar  = new JLabel("PNR Number");
        lblaadhar.setBounds(60,80,150,25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN,16));
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220,80,150,25);
        add(tfpnr);
        
        
         fetchbutton = new JButton("Show Details");
        fetchbutton.setBackground(Color.BLACK);
        fetchbutton.setForeground(Color.WHITE);
        fetchbutton.setBounds(380,80,120,25);
        fetchbutton.addActionListener(this);
        add(fetchbutton);
        
 
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60,130,150,25);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220,130,150,25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Cancellation No");
        lblnationality.setBounds(60,180,150,25);
        lblnationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblnationality);
        
        
        cancellationno = new JLabel( "" + random.nextInt(1000000));
        cancellationno.setBounds(220,180,150,25);
        add(cancellationno);
        
     
        
        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN,16));
        lbladdress.setBounds(60,230,150,25);
        add(lbladdress);
        
        
        lblfcode = new JLabel();
        lblfcode.setBounds(220,230,150,25);
        add(lblfcode);
        
        JLabel lblgender= new JLabel("Date");
        lblgender.setBounds(60,280,150,25);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender);
        
        lbldate= new JLabel();
        lbldate.setBounds(220,280,150,25);
        add(lbldate);
   
    
        
        flight = new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220,330,120,25);
        flight.addActionListener(this);
        add(flight);
        
        
         
       
        
        setSize(800,450);
        setLocation(350,150);
        setVisible(true);
    }
    
   public void actionPerformed(ActionEvent ae){
       if(ae.getSource() == fetchbutton){
       String pnr = tfpnr.getText();
          
  
       
       try{
           Conn conn = new Conn();
           
           String query = "select * from reservation where PNR = '"+pnr+"'";
           
           ResultSet rs = conn.s.executeQuery(query);
           
           if(rs.next()){
               tfname.setText(rs.getString("name"));
                 lblfcode.setText(rs.getString("flightcode"));
                   lbldate.setText(rs.getString("ddate"));
                   
                      
           }else{
               JOptionPane.showMessageDialog(null, "Please enter correct PNR");
           }
           
           
           
//           setVisible(false);
           
       }catch(Exception e){
           e.printStackTrace();
       }
       
}   else if(ae.getSource() == flight){
       String name = tfname.getText();
       String pnr = tfpnr.getText();
       String cancelno = cancellationno.getText();
       String fcode = lblfcode.getText();
       String date = lbldate.getText();
          
  
       
       try{
           Conn conn = new Conn();
           
           String query = "insert into cancel values ('"+pnr+"' , '"+name+"' ,'"+cancelno+"' ,'"+fcode+"' ,'"+date+"')";
           
            conn.s.executeUpdate(query);
            conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
           
           
               JOptionPane.showMessageDialog(null, "Ticket Cancelled");
           
           
           setVisible(false);
           
       }catch(Exception e){
           e.printStackTrace();
       }
       } 
     

   } 
      
    public static void main(String[] args){
        new CancelTicket();
    }
    
}
