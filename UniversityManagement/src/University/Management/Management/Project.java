package University.Management.Management;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project extends JFrame implements ActionListener {
    Project(){
        setSize(1650,850);
        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2=ic.getImage().getScaledInstance(1650,750,Image.SCALE_DEFAULT);
        ImageIcon ic4=new ImageIcon(i2);
        JLabel image=new JLabel(ic4);
        add(image);
        //addiing menu bar
        JMenuBar sb=new JMenuBar();
        //info
        JMenu newInformation=new JMenu("new Information");
        newInformation.setFont(new Font("Serif", Font.ITALIC, 14));
        newInformation.setForeground(Color.BLUE);
        sb.add(newInformation);
        JMenuItem facultyinfo=new JMenuItem("new Faculty Information");
        facultyinfo.addActionListener(this);
        newInformation.add(facultyinfo);
        JMenuItem Studentinfo=new JMenuItem("new Student Information");
        Studentinfo.addActionListener(this);
        newInformation.add(Studentinfo);
        //details
        JMenu Details=new JMenu("view Details");
        Details.setFont(new Font("Serif", Font.ITALIC, 14));
        Details.setForeground(Color.RED);
        sb.add(Details);
        JMenuItem facultydetails=new JMenuItem("view Faculty details");
        facultydetails.addActionListener(this);
        Details.add(facultydetails);

        JMenuItem Studentdetails=new JMenuItem("view Student details");
        Studentdetails.addActionListener(this);
        Details.add(Studentdetails);

        //leave
        JMenu leaves=new JMenu("apply for leaves");
        leaves.setFont(new Font("Serif", Font.ITALIC, 14));
        leaves.setForeground(Color.BLUE);
        sb.add(leaves);
        JMenuItem facultyleaves=new JMenuItem("apply Faculty Leaves");
        leaves.add(facultyleaves);
        facultyleaves.addActionListener(this);

        //leave details
        JMenu leavesdetails=new JMenu("leaves details");
        leavesdetails.setFont(new Font("Serif", Font.ITALIC, 14));
        leavesdetails.setForeground(Color.RED);
        sb.add(leavesdetails);
        JMenuItem facultyleavesdetails=new JMenuItem("Faculty Leaves details");
        leavesdetails.add(facultyleavesdetails);


        //exam
        JMenu exam=new JMenu("Examination");
        exam.setFont(new Font("Serif", Font.ITALIC, 14));
        exam.setForeground(Color.BLUE);
        sb.add(exam);
        JMenuItem viewResults=new JMenuItem("view Results");
        exam.add(viewResults);
        JMenuItem entermarks=new JMenuItem("enter marks");
        exam.add(entermarks);

        //updateinfo
        JMenu updatedetaials=new JMenu("update Information");
        updatedetaials.setFont(new Font("Serif", Font.ITALIC, 14));
        updatedetaials.setForeground(Color.RED);
        sb.add(updatedetaials);
        JMenuItem updatefacultyinfo=new JMenuItem("update faculty information");
        updatedetaials.add(updatefacultyinfo);
        JMenuItem updatestudentinfo=new JMenuItem("update student information");
        updatedetaials.add(updatestudentinfo);

        //fee
        JMenu fee=new JMenu("fee Information");
        fee.setFont(new Font("Serif", Font.ITALIC, 14));
        fee.setForeground(Color.BLUE);
        sb.add(fee);
        JMenuItem feeStructure=new JMenuItem("fee Structure");
        fee.add(feeStructure);
        JMenuItem feeform=new JMenuItem("student fee form");
        fee.add(feeform);

        //utility
        JMenu utility=new JMenu("Utility");
        utility.setFont(new Font("Serif", Font.ITALIC, 14));
        utility.setForeground(Color.RED);
        sb.add(utility);
        JMenuItem notepad=new JMenuItem("notepad");
        utility.add(notepad);
        JMenuItem calculator=new JMenuItem("calculator");
        utility.add(calculator);
        notepad.addActionListener(this);
        calculator.addActionListener(this);

        // Exit menu
        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("Serif", Font.ITALIC, 14));
        exit.setForeground(Color.BLUE);
        sb.add(exit);
        JMenuItem ex = new JMenuItem("Exit");
        ex.addActionListener(this);  // Attach ActionListener here
        exit.add(ex);
        setJMenuBar(sb);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg=e.getActionCommand();
        if (msg.equals("Exit")){
            setVisible(false);
        } else if (msg.equals("calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");

            }catch (Exception ae){

            }

        } else if (msg.equals("notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");

            }catch (Exception ae){

            }

        }else if(msg.equals("new Student Information")){
            new AddStudent();

        }
        else if (msg.equals("new Faculty Information")){
            new AddTeacher();
        }
        else if (msg.equals("view Faculty details")) {
            new Teacherdetails();

        }

        else if (msg.equals("view Student details")) {
            new StudentDetails();

        }
        else if (msg.equals("apply Faculty Leaves")) {
            new applyLeavesFaculty();

        }

    }
    public static void main(String[] args) {


        new Project();
    }

}
