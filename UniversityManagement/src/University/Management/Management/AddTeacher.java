package University.Management.Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import com.toedter.calendar.JDateChooser;

public class AddTeacher extends JFrame implements ActionListener {
    JTextField nameField, usernameField, emailField, phoneField, addressField;
    JDateChooser dobField;
    JComboBox qualificationField;
    JLabel empid;
    JPasswordField passwordField;
    JButton submit, cancel;
    Random ran=new Random();
    static Set<String> employeeIDSet = new HashSet<>();

    AddTeacher() {
        setLayout(null);

        JLabel teacherDetails = new JLabel("New Teacher Details");
        teacherDetails.setBounds(140, 5, 400, 70);
        teacherDetails.setFont(new Font("Thoma", Font.BOLD, 35));
        add(teacherDetails);

        // Name
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 70, 60, 30);
        nameLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(90, 78, 110, 20);
        add(nameField);

        // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(290, 70, 80, 30);
        usernameLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(410, 78, 110, 20);
        add(usernameField);

        // Email
        JLabel emailLabel = new JLabel("Email ID");
        emailLabel.setBounds(20, 130, 80, 30);
        emailLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(90, 138, 110, 20);
        add(emailField);

        // Employee ID
        JLabel employeeIDLabel = new JLabel("Employee ID");
        employeeIDLabel.setBounds(290, 130, 100, 30);
        employeeIDLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(employeeIDLabel);

        String employeeid=uniqueemployeeId();
        empid=new JLabel(employeeid);
        empid.setBounds(410, 138, 110, 20);
        empid.setFont(new Font("Thoma", Font.BOLD, 13));
        add(empid);

        // Date of Birth
        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(20, 170, 100, 30);
        dobLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(dobLabel);

        dobField = new JDateChooser();
        dobField.setBounds(130, 178, 110, 20);
        add(dobField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(290, 170, 80, 30);
        phoneLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(410, 178, 110, 20);
        add(phoneField);

        // Address
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(20, 220, 80, 30);
        addressLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(90, 228, 110, 20);
        add(addressField);

        // Qualification

       // qualificationLabel.setBounds(290, 250, 100, 30);
        JLabel qualification=new JLabel("qualification");
        qualification.setBounds(290, 220, 120, 30);
        qualification.setFont(new Font("Thoma",Font.BOLD,15));
        add(qualification);


        String[] Branch1={"Select Branch","Comp Engg","Mech Engg","Civil Engg","EandTC Engg","Electrical Engg"};
        qualificationField=new JComboBox<>(Branch1);
        qualificationField.setBounds(410,228,110,20);
        add(qualificationField);


        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 260, 80, 30);
        passwordLabel.setFont(new Font("Thoma", Font.BOLD, 15));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 268, 110, 20);
        add(passwordField);

        // Submit and Cancel buttons
        submit = new JButton("Submit");
        submit.setBounds(130, 300, 110, 30);
        submit.setFont(new Font("Thoma", Font.BOLD, 13));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(290, 300, 110, 30);
        cancel.setFont(new Font("Thoma", Font.BOLD, 13));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(600, 450);
        setLocation(500, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            setVisible(false);
        }
        if (e.getSource() == submit) {
            String name = nameField.getText();
            String username = usernameField.getText();
            String email = emailField.getText();
            String employeeID = empid.getText();
            Date dobDate = dobField.getDate();
            java.sql.Date sqlDate = new java.sql.Date(dobDate.getTime());
            String phone = phoneField.getText();
            String address = addressField.getText();
            String qualification = qualificationField.getSelectedItem().toString();
            String password = new String(passwordField.getPassword());

            // Check for empty fields
            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || employeeID.isEmpty() || phone.isEmpty() || address.isEmpty() || qualification.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled");
                return;
            }
            if (phone.length()>10){
                JOptionPane.showMessageDialog(null,"invalid phone number");
                return;
            }
            //check phone availabolity
              boolean phoneavailable=isphoneavailabel(phone);
            if (!phoneavailable){
                JOptionPane.showMessageDialog(null,"phone number already exists");
                return;
            }
            boolean emailavailable=iseamailavailable(email);
            if (!emailavailable){
                JOptionPane.showMessageDialog(this,"email already exists");
                return;
            }

            String query = "INSERT INTO register4(name, username, email, employeeID, dob, phone, address, Qualification, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                Conn c = new Conn();
                PreparedStatement s = c.c.prepareStatement(query);
                s.setString(1, name);
                s.setString(2, username);
                s.setString(3, email);
                s.setString(4, employeeID);
                s.setDate(5, sqlDate);
                s.setString(6, phone);
                s.setString(7, address);
                s.setString(8, qualification);
                s.setString(9, password);

                int row = s.executeUpdate();
                if (row == 1) {
                    JOptionPane.showMessageDialog(this, "Teacher added successfully");
                    setVisible(false);
                    new AddTeacher();
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean isphoneavailabel(String phone){
        String query="select phone from register4 where phone=?";
        try {
            Conn c=new Conn();
            PreparedStatement s=c.c.prepareStatement(query);
            s.setString(1,phone);
            ResultSet rs=s.executeQuery();
            if (rs.next()){
                return false;
            }
            else {
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private boolean iseamailavailable(String email){
        String query="select email from register4 where email=?";
        try {
            Conn c=new Conn();
            PreparedStatement s=c.c.prepareStatement(query);
            s.setString(1,email);
            ResultSet rs=s.executeQuery();
            if (rs.next()){
                return false;
            }
            else {
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private String uniqueemployeeId(){
        String empid;
        do {
            long firs4=Math.abs((ran.nextLong()%9000L)+1000L);
            empid="1333"+firs4;

        }while (employeeIDSet.contains(empid));
        return empid;
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}
