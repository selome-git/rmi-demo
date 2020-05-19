
package serverPackage;

import apiPackage.Student;
import apiPackage.markInterface;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class markImpl implements markInterface {
  
 
    
  @Override
    public Student passMark(String studId,String course) throws RemoteException,SQLException {
  String studIdPassed=studId,coursePassed=course;
 
       Student stud1=new Student();
       String response="Sorry , thats an invalid ID from impl...";

        String query="SELECT * FROM studentinfotable WHERE studId=? AND course=?";
           try {
               Class.forName("com.mysql.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(markImpl.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println(response);
                }
      Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/studInfodb","root","");

      PreparedStatement stmt=conn.prepareStatement(query);
     stmt.setString(1, studIdPassed);
     stmt.setString(2, coursePassed);
  ResultSet  rs=stmt.executeQuery();
 Student studPass=new Student();
 // String resultSetString="CANT FIND ID";
  while(rs.next())
            {
           studPass.setId(rs.getString(1));
           studPass.setName(rs.getString(2));
           studPass.setCourse(rs.getString(3));
           studPass.setMark(Double.parseDouble(rs.getString(4)));
           studPass.setGrade(Double.parseDouble(rs.getString(4)));//so that we pass the mark instead of grade
           
            }
         
    
   return studPass; }}

 
   
    

    
     


