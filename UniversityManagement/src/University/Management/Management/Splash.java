package University.Management.Management;


import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame implements Runnable {
    Thread t;
    Splash(){
        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i2=ic.getImage().getScaledInstance(850,850,Image.SCALE_DEFAULT);
        ImageIcon ic2=new ImageIcon(i2);
        JLabel image=new JLabel(ic2);
        add(image);
        t=new Thread(this);
        t.start();
        setVisible(true);
        int x=1;
        for (int i=2;i<=600;i++,x+=1){
            setLocation(900-((i+x)/2),350-(i/2));
            setSize(i/2+x,i+x/6);
            try {
                Thread.sleep(7);
            }catch (Exception e){}
        }


    }
    public void run(){
       try{
           Thread.sleep(9000);
           setVisible(false);
           //next frame
           new Logindecidingpage();

       }
       catch (Exception e){}
    }
    public static void main(String[] args) {
        new Splash();
    }
}
