package University.Management.Management;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;

public class applyLeavesFaculty extends JFrame implements ActionListener {
    JTextField empfield;
    JButton cancel,submit;
    JDateChooser leavedate,returndate;
    HashSet<String>empid1;
    applyLeavesFaculty(){
        empid1=new HashSet<>();
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel leaveapply=new JLabel("Faculty leaves window");
        leaveapply.setBounds(150,5,300,30);
        leaveapply.setFont(new Font("Thoma",Font.BOLD,19));
        add(leaveapply);

        JLabel empid=new JLabel("employeeID");
        empid.setBounds(10,60,120,20);
        empid.setFont(new Font("Thoma",Font.BOLD,14));
        add(empid);

        empfield = new JTextField();
        empfield.setBounds(140, 65, 150, 20);
        add(empfield);


        // Date of leave
        JLabel leaveapplydate = new JLabel("Date of leave");
        leaveapplydate.setBounds(10, 90, 150, 20);
        leaveapplydate.setFont(new Font("Thoma", Font.BOLD, 14));
        add(leaveapplydate);

        leavedate = new JDateChooser();
        leavedate.setBounds(140, 98, 110, 20);
        add(leavedate);

        //date of returning
        JLabel returningdate = new JLabel("Date of returning");
        returningdate.setBounds(10, 120, 150, 20);
        returningdate.setFont(new Font("Thoma", Font.BOLD, 14));
        add(returningdate);

        returndate=new JDateChooser();
        returndate.setBounds(140,125,150,20);
        add(returndate);

        submit = new JButton("submit");
        submit.setBounds(100, 190, 100, 20);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Thoma", Font.BOLD, 13));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("cancel");
        cancel.setBounds(220, 190, 100, 20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Thoma", Font.BOLD, 13));
        cancel.addActionListener(this);
        add(cancel);



        setSize(450,300);
        setLocation(600,250);
        setVisible(true);

    }
    public static void main(String[] args) {
        new applyLeavesFaculty();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource()==cancel){
             setVisible(false);
         }
         else if (e.getSource()==submit){
            String empid2=empfield.getText();
             Date dateofleave=leavedate.getDate();
             java.sql.Date sqlDate=new java.sql.Date(dateofleave.getTime());
             Date dateofjoin=returndate.getDate();
             java.sql.Date sqlDate2=new java.sql.Date(dateofjoin.getTime());

             if (sqlDate2.before(sqlDate)){
                 JOptionPane.showMessageDialog(this,"invalid dates");
                 return;
             }

           boolean isthere=realempid(empid2);
           if (isthere){
               String query="insert into leaveapplication(EmployeeID,dateofleave,dateofreturn) values(?,?,?)";
               try {
                   Conn c=new Conn();
                   PreparedStatement s=c.c.prepareStatement(query);
                   s.setString(1,empid2);
                   s.setDate(2,sqlDate);
                   s.setDate(3,sqlDate2);
                   int update=s.executeUpdate();
                   if (update==1){
                       JOptionPane.showMessageDialog(null,"leave applied successfully");

                   }
                   else {
                       JOptionPane.showMessageDialog(null,"leave application failed");
                   }


               }catch (Exception e5){
                   e5.printStackTrace();
               }
           }
           else {
               JOptionPane.showMessageDialog(null,"enter a valid employee ID");
           }

         }
    }
    private boolean realempid(String empid){
        String query="select employeeID from register4 where employeeID=?";
        try {
            Conn c=new Conn();
            PreparedStatement s=c.c.prepareStatement(query);
            s.setString(1,empid);
            ResultSet rs=s.executeQuery();

            if (rs.next()){
                return true;
            }


        }catch (Exception e4){
            e4.printStackTrace();
        }
        return false;
    }
}
