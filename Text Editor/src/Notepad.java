import java.io.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Notepad extends JFrame implements ActionListener, WindowListener {

    JTextArea jta = new JTextArea();
    JComboBox fontbox;
    File fnameContainer;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;

    public Notepad()
    {
        Font fnt = new Font("Arial", Font.PLAIN,15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmtheme = new JMenu("Theme");
        JMenu jmhelp = new JMenu("Help");

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontbox = new JComboBox(fonts);
        fontbox.addActionListener(this);
        fontbox.setSelectedItem("Arial");

        fontLabel = new JLabel(" Font Size: ");


        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50,25));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                jta.setFont(new Font(jta.getFont().getFamily(),Font.PLAIN,(int)fontSizeSpinner.getValue()));
            }

        });

        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true);

        jta.setFont(fnt);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        con.add(sbrText);

        createMenuItem(jmfile,"New");
        createMenuItem(jmfile,"Open");
        createMenuItem(jmfile,"Save");
        jmfile.addSeparator();
        createMenuItem(jmfile,"Exit");

        createMenuItem(jmedit,"Cut");
        createMenuItem(jmedit,"Copy");
        createMenuItem(jmedit,"Paste");
//		createMenuItem(jmedit,"Undo");
//		createMenuItem(jmedit,"Redo");

        createMenuItem(jmtheme,"Light");
        createMenuItem(jmtheme,"Dim");
        createMenuItem(jmtheme,"Lights out");

        createMenuItem(jmhelp,"About Notepad");

        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmtheme);
        jmb.add(jmhelp);
        jmb.add(fontLabel);
        jmb.add(fontSizeSpinner);
        jmb.add(fontbox);

        setJMenuBar(jmb);


        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);

    }

    private void createMenuItem(JMenu jm, String txt) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        jm.add(jmi);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser jfc = new JFileChooser();
        if(e.getActionCommand().equals("New"))
        {
            this.setTitle("Untitled.txt - Notepad");
            jta.setText("");
            fnameContainer = null;
        }
        else if(e.getActionCommand().equals("Open"))
        {
            int ret = jfc.showDialog(null,"Open");
            if(ret == JFileChooser.APPROVE_OPTION)
            {
                File fy1 = jfc.getSelectedFile();
                try {
                    OpenFile(fy1.getAbsolutePath());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                this.setTitle(fy1.getName() + " - Notepad");
                fnameContainer = fy1;
            }
        }
        else if(e.getActionCommand().equals("Save"))
        {
            if(fnameContainer != null)
            {
                jfc.setCurrentDirectory(fnameContainer);
                jfc.setSelectedFile(fnameContainer);
            }
            else
            {
                jfc.setSelectedFile(new File("Untitled.txt"));
            }

            int ret = jfc.showSaveDialog(null);
            if(ret == JFileChooser.APPROVE_OPTION)
            {
                File fy1 = jfc.getSelectedFile();
                try {
                    SaveFile(fy1.getAbsolutePath());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                this.setTitle(fy1.getName() + " - Notepad");
                fnameContainer = fy1;
            }

        }

        else if(e.getActionCommand().equals("Exit"))
        {
            Exiting();
        }
        else if(e.getActionCommand().equals("Copy"))
        {
            jta.copy();
        }
        else if(e.getActionCommand().equals("Paste"))
        {
            jta.paste();
        }
        else if(e.getActionCommand().equals("Light"))
        {
            jta.setBackground(Color.WHITE);
            jta.setForeground(Color.BLACK);
        }
        else if(e.getActionCommand().equals("Dim"))
        {
            Color darkBlue = new Color(0,0,31);
            jta.setBackground(darkBlue);
            jta.setForeground(Color.WHITE);
        }
        else if(e.getActionCommand().equals("Lights out"))
        {
            jta.setBackground(Color.BLACK);
            jta.setForeground(Color.WHITE);
        }
        else if(e.getActionCommand().equals("About Notepad"))
        {
            JOptionPane.showMessageDialog(this, "Created by Tanisha", "Notepad", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getActionCommand().equals("Cut"))
        {
            jta.cut();
        }

        if(e.getSource() == fontbox)
        {
            jta.setFont(new Font((String)fontbox.getSelectedItem(),Font.PLAIN,jta.getFont().getSize()));
        }
    }


    private void Exiting() {
        System.exit(0);
    }

    private void SaveFile(String fname) throws IOException{
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
        o.writeBytes(jta.getText());
        o.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void OpenFile(String fname) throws IOException{
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String l;
        jta.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while((l = d.readLine()) != null)
        {
            jta.setText(jta.getText() + l + "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {


    }


}