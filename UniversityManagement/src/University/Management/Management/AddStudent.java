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

public class AddStudent extends JFrame implements ActionListener {
    JTextField namefiled,fatherfield,Address,Phone,Email,Class_X,Class_XII,Adhar;
    JComboBox SelectCourse,BranchType;
    JLabel rollnumber;
    JDateChooser dob;
    JPasswordField Password;
    JButton submit,cancel;
    static Set<String> rollnumberset=new HashSet<>();

    Random ran=new Random();

    AddStudent(){
        setLayout(null);
        JLabel studentdetails=new JLabel("New Student Details");
        studentdetails.setBounds(140,5,400,70);
        studentdetails.setFont(new Font("Thoma",Font.BOLD,35));
        add(studentdetails);
        JLabel name=new JLabel("Name");
        name.setBounds(20,70,60,30);
        name.setFont(new Font("Thoma",Font.BOLD,15));
        add(name);
        namefiled =new JTextField();
        namefiled.setBounds(90,78,110,20);
        add(namefiled);
        JLabel fathername=new JLabel("Father's Name");
        fathername.setBounds(290,70,160,30);
        fathername.setFont(new Font("Thoma",Font.BOLD,15));
        add(fathername);
        fatherfield =new JTextField();
        fatherfield.setBounds(410,78,110,20);
        add(fatherfield);
        JLabel Rollno=new JLabel("RollNo");
        Rollno.setBounds(20,100,60,30);
        Rollno.setFont(new Font("Thoma",Font.BOLD,15));
        add(Rollno);
        String generateRollnumber=generateUniqueRollnumber();
        rollnumber=new JLabel(generateRollnumber);
        rollnumber.setBounds(90,105,100,20);
        rollnumber.setFont(new Font("Thoma",Font.BOLD,15));
        add(rollnumber);

        JLabel date=new JLabel("Date Of Birth");
        date.setBounds(290,100,190,30);
        date.setFont(new Font("Thoma",Font.BOLD,15));
        add(date);

        dob=new JDateChooser();
        dob.setBounds(410,108,110,20);
        add(dob);
        JLabel addres=new JLabel("Address");
        addres.setBounds(20,130,80,30);
        addres.setFont(new Font("Thoma",Font.BOLD,15));
        add(addres);

        Address=new JTextField();
        Address.setBounds(90,138,110,20);
        add(Address);

        JLabel phone=new JLabel("Phone");
        phone.setBounds(290,130,80,30);
        phone.setFont(new Font("Thoma",Font.BOLD,15));
        add(phone);
        Phone=new JTextField();
        Phone.setBounds(410,138,110,20);
        add(Phone);

        JLabel email=new JLabel("Email ID");
        email.setBounds(20,160,80,30);
        email.setFont(new Font("Thoma",Font.BOLD,15));
        add(email);

        Email =new JTextField();
        Email.setBounds(90,168,110,20);
        add(Email);

        JLabel classx=new JLabel("Class X(%)");
        classx.setBounds(290,160,80,30);
        classx.setFont(new Font("Thoma",Font.BOLD,15));
        add(classx);

        Class_X =new JTextField();
        Class_X.setBounds(410,168,110,20);
        add(Class_X);

        JLabel classXII=new JLabel("Class XII(%)");
        classXII.setBounds(20,190,150,30);
        classXII.setFont(new Font("Thoma",Font.BOLD,15));
        add(classXII);

        Class_XII =new JTextField();
        Class_XII.setBounds(120,198,110,20);
        add(Class_XII);

        JLabel adar=new JLabel("Adhar Number");
        adar.setBounds(290,190,110,30);
        adar.setFont(new Font("Thoma",Font.BOLD,15));
        add(adar);

        Adhar =new JTextField();
        Adhar.setBounds(410,198,110,20);
        add(Adhar);

        JLabel Course=new JLabel("Course");
        Course.setBounds(20,220,80,30);
        Course.setFont(new Font("Thoma",Font.BOLD,15));
        add(Course);

        String[] cousrsetype={"Select Course","BTech","BE"};
        SelectCourse=new JComboBox<>(cousrsetype);
        SelectCourse.setBounds(90,228,110,20);
        add(SelectCourse);

        JLabel branch=new JLabel("Branch");
        branch.setBounds(290,220,80,30);
        branch.setFont(new Font("Thoma",Font.BOLD,15));
        add(branch);


        String[] Branch={"Select Branch","Comp Engg","Mech Engg","Civil Engg","EandTC Engg","Electrical Engg"};
        BranchType=new JComboBox<>(Branch);
        BranchType.setBounds(410,228,110,20);
        add(BranchType);


        JLabel password=new JLabel("Password");
        password.setBounds(20,250,80,30);
        password.setFont(new Font("Thoma",Font.BOLD,15));
        add(password);

        Password =new JPasswordField();
        Password.setBounds(110,258,110,20);
        add(Password);



        submit =new JButton("Submit");
        submit.setBounds(130,290,110,30);
        submit.setFont(new Font("Thoma",Font.BOLD,13));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel =new JButton("cancel");
        cancel.setBounds(290,290,110,30);
        cancel.setFont(new Font("Thoma",Font.BOLD,13));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);


        setSize(600,450);
        setLocation(500,100);
        setVisible(true);
    }
    private String generateUniqueRollnumber(){
        String rollno;
        do{
            long first4=Math.abs((ran.nextLong() %9000L)+1000L);
            rollno="1533"+first4;
        }while (rollnumberset.contains(rollno));
        rollnumberset.add(rollno);
        return rollno;
    }

    public static void main(String[] args) {
      new AddStudent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel){
            setVisible(false);
        }
        if (e.getSource()==submit){

            String studentname=namefiled.getText();
            String Fathername=fatherfield.getText();
            String RollNo=rollnumber.getText();
            Date dobdate=dob.getDate();
            java.sql.Date sqlDate = new java.sql.Date(dobdate.getTime());
            String Adress=Address.getText();
            String phone= Phone.getText();
            String email= Email.getText();
            String classx=Class_X.getText();
            String classxii=Class_XII.getText();
            String adhar=Adhar.getText();
            String course=SelectCourse.getSelectedItem().toString();
            String branch=BranchType.getSelectedItem().toString();
            String password=Password.getText();


            if (studentname.isEmpty() || Fathername.isEmpty() || Adress.isEmpty() || phone.isEmpty() || email.isEmpty() || classx.isEmpty() || classxii.isEmpty() || adhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All the fields should be filled");
                return;
            }
            if (phone.length()>10){
                JOptionPane.showMessageDialog(null,"invalid phone number");
                return;
            }

            if (!emailavailable(email)) {
                JOptionPane.showMessageDialog(this, "Email is already exists.");
                return;
            }
            if(!phoneavaliable(phone)){
                JOptionPane.showMessageDialog(this,"phone number already exists");
                return;
            }


            String Query="insert into addstudent(name, fathername, rollno,dob, address, phone,email,classX,classXII,adharno,course,branch,password) Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try{
                Conn c=new Conn();
                PreparedStatement s=c.c.prepareStatement(Query);
                s.setString(1,studentname);
                s.setString(2,Fathername);
                s.setString(3,RollNo);
                s.setDate(4,sqlDate);
                s.setString(5,Adress);
                s.setString(6,phone);
                s.setString(7,email);
                s.setString(8,classx);
                s.setString(9,classxii);
                s.setString(10,adhar);
                s.setString(11,course);
                s.setString(12,branch);
                s.setString(13,password);

                int row=s.executeUpdate();
                if (row==1){
                    JOptionPane.showMessageDialog(this,"Student added successfully");
                    setVisible(false);
                    new AddStudent();
                }
                else {
                    JOptionPane.showMessageDialog(this,"Registration fail");
                    new AddStudent();
                }

            }catch (Exception e3){
                e3.printStackTrace();
            }

        }
    }
    private Boolean emailavailable(String email){
        email= Email.getText();
        String query="select email from addstudent where email=?";
        try {
            Conn c=new Conn();
            PreparedStatement s=c.c.prepareStatement(query);
            s.setString(1,email);
            ResultSet rs=s.executeQuery();
            boolean avilable=!rs.next();
            rs.close();
            s.close();
            return avilable;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private Boolean phoneavaliable(String phone){
        phone=Phone.getText();
        String query="select phone from addstudent where phone=?";
        try{
            Conn c=new Conn();
            PreparedStatement s=c.c.prepareStatement(query);
            s.setString(1,phone);
            ResultSet rs=s.executeQuery();
            boolean available = !rs.next(); // If no result is found, email is available
            rs.close();
            s.close();
            return available;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
