package University.Management.Management;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
   Conn(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           c=DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement","root","NEW_PASSWORD");
           s=c.createStatement();
       }catch (Exception e){
           e.printStackTrace();

       }
   }
}
