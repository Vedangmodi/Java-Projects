package University.Management.Management;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class StudentLogin extends JFrame implements ActionListener {
    JTextField usernamefield;
    JPasswordField passwordfield;
    JButton Loginasstd,cancel;
    StudentLogin(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel login=new JLabel("Login for Students");
        login.setBounds(60,0,400,70);
        login.setFont(new Font("Tahoma",Font.BOLD,30));
        add(login);
        JLabel username=new JLabel("Rollnumber");
        username.setBounds(10,80,100,70);
        username.setFont(new Font("Tahoma",Font.BOLD,13));
        add(username);
        usernamefield=new JTextField();
        usernamefield.setBounds(100,105,150,20);
        usernamefield.addActionListener(this);
        add(usernamefield);
        JLabel password=new JLabel("password");
        password.setBounds(10,120,100,70);
        add(password);
        passwordfield=new JPasswordField();
        passwordfield.setBounds(100,145,150,20);
        add(passwordfield);
        //login
        Loginasstd=new JButton("login");
        Loginasstd.setBounds(10,190,100,25);
        Loginasstd.setFont(new Font("Tahoma",Font.BOLD,13));
        Loginasstd.setBackground(Color.BLACK);
        Loginasstd.setForeground(Color.WHITE);
        Loginasstd.addActionListener(this);
        add(Loginasstd);
        //Cancel
        cancel=new JButton("cancel");
        cancel.setBounds(120,190,100,25);
        cancel.setFont(new Font("Tahoma",Font.BOLD,13));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("icons/Studentlogin.png"));
        Image i3=ic.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i4=new ImageIcon(i3);
        JLabel img=new JLabel(i4);
        img.setBounds(270,60,200,200);
        add(img);

        setSize(500,350);
        setLocation(600,300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new StudentLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=e.getActionCommand();
        if (msg.equals("login")){
            String username=usernamefield.getText();
            String password=passwordfield.getText();
            if (username.isEmpty() ||password.isEmpty()){
                JOptionPane.showMessageDialog(null,"the fields can not be empty");
                return;
            }
            String query = "select rollno, password from addstudent where rollno = ? and password = ?";
            try {
                Conn c = new Conn();
                PreparedStatement s = c.c.prepareStatement(query);
                s.setString(1, username);
                s.setString(2, password);
                ResultSet rs = s.executeQuery();  // No query parameter needed here
                if (rs.next()) {
                    setVisible(false);
                    new Project();
                } else {
                    JOptionPane.showMessageDialog(null, "The credentials entered are wrong");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (msg.equals("Register")) {

        } else if (msg.equals("cancel")) {
            setVisible(false);
            new Logindecidingpage();
        }
    }
}
