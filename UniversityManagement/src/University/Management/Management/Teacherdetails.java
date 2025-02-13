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

public class Teacherdetails extends JFrame implements ActionListener {
    JButton search,print,cancel;
    JTextField empField;
    JTable table;
    DefaultTableModel model;
    HashSet<String> empidset;
    Teacherdetails(){
        empidset=new HashSet<>();
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel studentDetails = new JLabel("Teacher Details");
        studentDetails.setBounds(450, 5, 300, 30);
        studentDetails.setFont(new Font("Thoma", Font.BOLD, 19));
        add(studentDetails);

        JLabel rollNumLabel = new JLabel("Search by Employee Id");
        rollNumLabel.setBounds(10, 60, 300, 30);
        rollNumLabel.setFont(new Font("Thoma", Font.BOLD, 13));
        add(rollNumLabel);

        empField = new JTextField();
        empField.setBounds(190, 68, 200, 20);
        add(empField);

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

        model=new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("username");
        model.addColumn("email");
        model.addColumn("empolyeeID");
        model.addColumn("date of Birth");
        model.addColumn("Phone");
        model.addColumn("address");
        model.addColumn("Qualification");

        table=new JTable(model);
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10, 120, 960, 200);
        add(scrollPane);  // Ensure the scroll pane is added to the frame

        setLocation(400, 250);
        setSize(1000, 400);
        setVisible(true);


    }

    public static void main(String[] args) {
        new Teacherdetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String empid=empField.getText();

            if (!empidset.contains(empid)) {
                empidset.add(empid);
                String query = "SELECT * FROM register4 WHERE employeeID = ?";
                try {
                    Conn c = new Conn();
                    PreparedStatement s = c.c.prepareStatement(query);
                    s.setString(1, empid);
                    ResultSet rs = s.executeQuery();
                    // Check if there are any rows in the result set
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "The employee id  is invalid");
                    } else {
                        // Add the first row to the table
                        model.addRow(new Object[]{
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("employeeID"),
                                rs.getDate("dob"),
                                rs.getString("phone"),
                                rs.getString("address"),
                                rs.getString("Qualification")
                        });

                        // Add remaining rows if any
                        while (rs.next()) {
                            model.addRow(new Object[]{
                                    rs.getString("name"),
                                    rs.getString("username"),
                                    rs.getString("email"),
                                    rs.getString("employeeID"),
                                    rs.getDate("dob"),
                                    rs.getString("phone"),
                                    rs.getString("address"),
                                    rs.getString("Qualification")
                            });
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "The employeeid is repeated; the record is already displayed.");
            }
        }
        if (e.getSource()==print){
            try {
               table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }if (e.getSource()==cancel){
            setVisible(false);
        }
    }
}
