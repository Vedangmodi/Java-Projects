package University.Management.Management;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    JButton login,cancel,Register;
    JPasswordField pasword;
    JTextField userfield;

    Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //user
        JLabel login2 =new JLabel("Login");
        login2.setBounds(190,0,100,20);
        login2.setFont(new Font("Tahoma",Font.BOLD,19));
        add(login2);

        JLabel username=new JLabel("Employee ID");
        username.setBounds(50,40,100,20);
        add(username);
        //userfiled
         userfield=new JTextField();
        add(userfield);
        userfield.setBounds(150, 40, 150, 20);
        //password
        JLabel userpassword=new JLabel("Password");
        add(userpassword);
        userpassword.setBounds(50,90,100,20);
        //password field
         pasword=new JPasswordField();
        add(pasword);
        pasword.setBounds(150,90,150,20);
        //image
        ImageIcon iv=new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i3=iv.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon ic4=new ImageIcon(i3);
        JLabel image=new JLabel(ic4);
        image.setBounds(350, 10, 200, 200);
        add(image);
        login=new JButton("Login");
        login.setBounds(50,150,100,25);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.setFont(new Font("Tahoma",Font.BOLD,14));
        login.addActionListener(this);
        add(login);
        //Register Button
        Register=new JButton("Register");
        Register.setBounds(120,190,100,25);
        Register.setBackground(Color.BLACK);
        Register.setForeground(Color.white);
        Register.setFont(new Font("Tahoma",Font.BOLD,14));
        Register.addActionListener(this);
        add(Register);

      //  cancel.setBounds(170,150,100,20);
        cancel=new JButton("Cancel");
        cancel.setBounds(170,150,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Tahoma",Font.BOLD,14));
        cancel.addActionListener(this);
        add(cancel);

        setSize(550,300);
        setLocation(600,250);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==login){
            String username=userfield.getText();
            String password=pasword.getText();
            String query= "select username,password from register4 where EmployeeID='"+username+"'and password='"+password+"'";
            try{
                Conn c=new Conn();
              ResultSet rs=c.s.executeQuery(query);
              if (rs.next()){
                  setVisible(false);
                  new Project();
              }
              else {
                  JOptionPane.showMessageDialog(null,"Invalid Employee ID or password");
                  setVisible(false);
              }

            }catch (Exception e1){
                e1.printStackTrace();
            }


        }
        else if (e.getSource()==cancel){
            setVisible(false);
            new Logindecidingpage();
        }
        else if (e.getSource()==Register){
               new Register();
               setVisible(false);
        }

    }
    public static void main(String[] args) {
        new Login();
    }


}
