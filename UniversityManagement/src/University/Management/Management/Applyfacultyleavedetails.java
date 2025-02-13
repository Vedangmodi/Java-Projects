package University.Management.Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class Applyfacultyleavedetails extends JFrame implements ActionListener {
    JTextField empField;
    JButton search,cancel,print;
    JTable table;
    DefaultTableModel model;
    HashSet<String> empidset;


    Applyfacultyleavedetails(){
        empidset=new HashSet<>();
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel studentDetails = new JLabel("Teacher Details");
        studentDetails.setBounds(450, 5, 300, 30);
        studentDetails.setFont(new Font("Thoma", Font.BOLD, 19));
        add(studentDetails);

        JLabel rollNumLabel = new JLabel("Search by Employee ID");
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

        model =new DefaultTableModel();
        model.addColumn("empolyeeID");
        model.addColumn("Name");
        model.addColumn("email");
        model.addColumn("date of leave");
        model.addColumn("date of return");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 120, 960, 200);
        add(scrollPane);

        setLocation(400, 250);
        setSize(1000, 400);

        setVisible(true);

    }
    public static void main(String[] args) {
        new Applyfacultyleavedetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            setVisible(false);
        }
        else if (e.getSource()==print){
            try {
                table.print();
            }catch (Exception e4){
                e4.printStackTrace();
            }
        }
        else if (e.getSource()==search){
            String empid=empField.getText();
            if (empid==null){
                JOptionPane.showMessageDialog(this,"employee ID Cant be null");
            }
            if (!empidset.contains(empid)) {
                empidset.add(empid);
                String query = "SELECT l.EmployeeID, r.name, r.email, l.dateofleave, l.dateofreturn " +
                        "FROM register4 AS r " +
                        "inner JOIN leaveapplication AS l ON r.EmployeeID = l.EmployeeID " +
                        "WHERE l.EmployeeID = ?";

                try {
                Conn c=new Conn();
                PreparedStatement s=c.c.prepareStatement(query);
                s.setString(1,empid);
                ResultSet rs=s.executeQuery();
                if (!rs.next()){
                    JOptionPane.showMessageDialog(this,"leave details failed to fetch");
                }
                else {
                    model.addRow(new Object[]{
                            rs.getString("EmployeeID"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDate("dateofleave"),
                            rs.getDate("dateofreturn")
                    });
                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("EmployeeID"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getDate("dateofleave"),
                                rs.getDate("dateofreturn")
                        });
                    }

                }

            }catch (Exception e5){
                e5.printStackTrace();
            }

        } else {
                JOptionPane.showMessageDialog(null, "The employeeid is repeated; the record is already displayed.");
            }
        }



    }
}
