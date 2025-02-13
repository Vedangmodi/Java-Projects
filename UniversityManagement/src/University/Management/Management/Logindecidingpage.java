package University.Management.Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentHashMap;

public class Logindecidingpage extends JFrame implements ActionListener {
    JButton loginasstudent,loginasfaculty,cancel;
    Logindecidingpage(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        loginasstudent=new JButton("login as Student");
        loginasstudent.setBounds(10,130,200,30);
        loginasstudent.setFont(new Font("Tahoma", Font.BOLD, 14));
        loginasstudent.setBackground(Color.BLACK);
        loginasstudent.setForeground(Color.WHITE);
        loginasstudent.addActionListener(this);
        add(loginasstudent);
        loginasfaculty=new JButton("login as Faculty");
        loginasfaculty.setBounds(240,130,200,30);
        loginasfaculty.setFont(new Font("Tahoma", Font.BOLD, 14));
        loginasfaculty.setBackground(Color.BLACK);
        loginasfaculty.setForeground(Color.WHITE);
        loginasfaculty.addActionListener(this);
        add(loginasfaculty);

        cancel = new JButton("Cancel");
        cancel.setBounds(170, 170, 100, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancel.addActionListener(this);
        add(cancel);
         ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("icons/university.jpg"));
         Image i3=ic.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
         ImageIcon i4=new ImageIcon(i3);
         JLabel img=new JLabel(i4);
         img.setBounds(125, -30, 200, 200);
         add(img);
        setSize(500,300);
        setLocation(600,300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Logindecidingpage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginasstudent){
             new StudentLogin();
             setVisible(false);
        }
        else if (e.getSource()==loginasfaculty){
            new Login();
            setVisible(false);

        } else if (e.getSource()==cancel) {
            setVisible(false);
        }

    }


}
