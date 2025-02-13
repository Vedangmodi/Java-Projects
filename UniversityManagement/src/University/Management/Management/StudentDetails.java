package University.Management.Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class StudentDetails extends JFrame implements ActionListener {
    JTextField rollnumField;
    JButton search,print,cancel;
    JTable table;
    DefaultTableModel model;
    HashSet<String> rollSet;  // Stores roll numbers to avoid duplicates

    StudentDetails() {
        rollSet = new HashSet<>();  // Initialize the HashSet

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel studentDetails = new JLabel("Student Details");
        studentDetails.setBounds(450, 5, 300, 30);
        studentDetails.setFont(new Font("Thoma", Font.BOLD, 19));
        add(studentDetails);

        JLabel rollNumLabel = new JLabel("Search by Roll number");
        rollNumLabel.setBounds(10, 60, 300, 30);
        rollNumLabel.setFont(new Font("Thoma", Font.BOLD, 13));
        add(rollNumLabel);

        rollnumField = new JTextField();
        rollnumField.setBounds(190, 68, 200, 20);
        add(rollnumField);

        search = new JButton("Search");
        search.setBounds(410, 68, 100, 20);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Thoma", Font.BOLD, 13));
        search.addActionListener(this);
        add(search);
        print = new JButton("print");
        print.setBounds(530, 68, 100, 20);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.setFont(new Font("Thoma", Font.BOLD, 13));
        print.addActionListener(this);
        add(print);

        cancel = new JButton("cancel");
        cancel.setBounds(660, 68, 100, 20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Thoma", Font.BOLD, 13));
        cancel.addActionListener(this);
        add(cancel);

        // Set up the table model and add column headers
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Father's Name");
        model.addColumn("Roll No");
        model.addColumn("Date of Birth");
        model.addColumn("Address");
        model.addColumn("Phone");
        model.addColumn("Email");
        model.addColumn("Class X");
        model.addColumn("Class XII");
        model.addColumn("Aadhar Number");
        model.addColumn("Course");
        model.addColumn("Branch");

        // Initialize the JTable with the model and add it to a JScrollPane
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 120, 960, 200);
        add(scrollPane);  // Ensure the scroll pane is added to the frame

        setLocation(400, 250);
        setSize(1000, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String rollnum = rollnumField.getText();

            if (!rollSet.contains(rollnum)) {
                rollSet.add(rollnum);
                String query = "SELECT * FROM addstudent WHERE rollno = ?";
                try {
                    Conn c = new Conn();
                    PreparedStatement s = c.c.prepareStatement(query);
                    s.setString(1, rollnum);
                    ResultSet rs = s.executeQuery();

                    // Check if there are any rows in the result set
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "The roll number is invalid");
                    } else {
                        // Add the first row to the table
                        model.addRow(new Object[]{
                                rs.getString("name"),
                                rs.getString("fathername"),
                                rs.getString("rollno"),
                                rs.getDate("dob"),
                                rs.getString("address"),
                                rs.getString("phone"),
                                rs.getString("email"),
                                rs.getString("classX"),
                                rs.getString("classXII"),
                                rs.getString("adharno"),
                                rs.getString("course"),
                                rs.getString("branch")
                        });

                        // Add remaining rows if any
                        while (rs.next()) {
                            model.addRow(new Object[]{
                                    rs.getString("name"),
                                    rs.getString("fathername"),
                                    rs.getString("rollno"),
                                    rs.getDate("dob"),
                                    rs.getString("address"),
                                    rs.getString("phone"),
                                    rs.getString("email"),
                                    rs.getString("classX"),
                                    rs.getString("classXII"),
                                    rs.getString("adharno"),
                                    rs.getString("course"),
                                    rs.getString("branch")
                            });
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "The roll number is repeated; the record is already displayed.");
            }
        }
        else if (e.getSource()==print){
            try {
                table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource()==cancel){
            setVisible(false);
        }



    }
}
