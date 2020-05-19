/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverPackage;

import apiPackage.Student;
import apiPackage.markInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.SecurityManager;
import java.rmi.RMISecurityManager;
import java.sql.PreparedStatement;

/**
 *
 * @author user
 */
public class main {
    public void dbConnect(){
     
        try{
           Class.forName("com.mysql.jdbc.Driver");
  }  catch (ClassNotFoundException ex) {
               Logger.getLogger(markImpl.class.getName()).log(Level.SEVERE, null, ex);
           }   
    }
    
    
   
        public static void main(String[] args) throws RemoteException {
   
        
      
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
           System.setProperty("java.rmi.server.hostname","192.168.1.106");//yeserveru Wireless LAN adapter WiFi: blo mimetaw
        Registry registry=LocateRegistry.createRegistry(9090);
      // System.setSecurityManager(new RMISecurityManager());
        
        markImpl markImpl= new markImpl();
        Student stud=new Student();
       markInterface iMark=(markInterface)UnicastRemoteObject.exportObject(markImpl, 0);
       registry.rebind("mark", iMark);
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       System.out.println("server is runnnnnning");
      Scanner in= new Scanner(System.in); 
     boolean mainLoop=true;
    
     while(mainLoop)
     {
         int input=0;
         try{
       System.out.println("press 1 - To add students  ");
       System.out.println("press 2 - To edit students grade ");
       System.out.println("Press 3 - To make student drop a course");
      input= in.nextInt();
      in.nextLine();
         }catch(Exception e)
         {
             System.out.println("INCORRECT INPUT\n\t\t*******"+e.toString()+"***********");
         }
      
      boolean loop=true;
      
          while(input==1&&loop)
      { 
          try{
               System.out.println("Student adding form\n-------------------");
          System.out.println("Enter students name");
          stud.setName(in.nextLine());
          
          System.out.println(" ID");
          stud.setId(in.nextLine());
          
          System.out.println(" Course");
          stud.setCourse(in.nextLine());
          
          System.out.println("Mark");
          Double markIn=in.nextDouble();
          stud.setMark(markIn);
in.nextLine();
          String grade=stud.setGrade(markIn);
         
       try {        
             Connection  conn= DriverManager.getConnection("jdbc:mysql://localhost/studinfodb","root","");
  
           PreparedStatement stmt=conn.prepareStatement("insert into studentinfotable(studId,studName,course,studMark,studGrade) values(?,?,?,?,?)");
       
       stmt.setString(1, stud.getId());
       stmt.setString(2, stud.getName());
       stmt.setString(3, stud.getCourse());
       stmt.setString(4, markIn.toString());
       stmt.setString(5, grade);
       
       stmt.executeUpdate();
       System.out.println("Inserted to DB succesfully !!!\nTo stop adding students press C");
     String end=in.nextLine();
       if(end.equalsIgnoreCase("c"));
       {
           loop=false;
       }
       
       
        } catch (Exception ex) {
        ex.printStackTrace();
           Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
       }}
          catch(Exception e){
           System.out.println("INCORRECT DATA ENTRY, Details displayed below\n\t\t\t*********"+e.toString()+"*********");
           in.nextLine();
         
      }
      }
      while(input==2&&loop)
      {
          try
      {
           System.out.println("Editing form\n-------------------");
          System.out.println("Students id :");
          String editId=in.nextLine();
          System.out.println("Course name :");
          String editCourse=in.nextLine();
          System.out.println("Mark to be edited ");
          double editMark=in.nextDouble();
          in.nextLine();
       try {        
             Connection  conn= DriverManager.getConnection("jdbc:mysql://localhost/studinfodb","root","");
  
           PreparedStatement stmt=conn.prepareStatement("UPDATE studentinfotable set studMark =?,studGrade=? where studId = ? AND course =? ");
      
       stmt.setString(1, ""+editMark);
       stmt.setString(2,stud.setGrade(editMark));
       stmt.setString(3, editId);
       stmt.setString(4, editCourse);
       
     int result=  stmt.executeUpdate();
     if(result<=0)
     
     {
     System.out.println("Sorry there no student with that information ");
     }
     else
     {
       System.out.println("Edited student with id "+editId +" succesfully !!!");
     }
     System.out.println("To stop editing mark press C");
     String end=in.nextLine();
       if(end.equalsIgnoreCase("c"))
       {
           loop=false;
           System.out.println("Editing finished  ");
       }
       
       } catch (Exception ex) {
        ex.printStackTrace();
           Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
       }}catch(Exception e){
            System.out.println("INCORRECT DATA ENTRY, Details displayed below\n\t\t\t*********"+e.toString()+"*********");
            in.nextLine();
               }
          
      }
      while(input==3&&loop)
      {
          try{
              System.out.println("Dropping form\n-------------------");
          System.out.println("Students id :");
          String dropId=in.nextLine();
          System.out.println("Course to be dropped :");
          String dropCourse=in.nextLine();
          System.out.println("Are you sure you want to drop "+dropCourse+" of student with id  "+dropId+" \n If so type 'yes' ");
          if(in.nextLine().equalsIgnoreCase("yes"))
          {
       try {        
             Connection  conn= DriverManager.getConnection("jdbc:mysql://localhost/studinfodb","root","");
  
           PreparedStatement stmt=conn.prepareStatement("DELETE FROM studentinfotable WHERE studId =? AND course=? ");
      
      
       stmt.setString(1, dropId);
       stmt.setString(2, dropCourse);
       
     int result= stmt.executeUpdate();
     if(result<=0)
          {
     System.out.println("Sorry there no student with that information ");
     }
     else
     {
       System.out.println("student with id "+dropId +" dropped the course "+dropCourse+" succesfully !!!");
     }
     System.out.println("To exit  press C, to  continue press enter ");
     String end=in.nextLine();
       if(end.equalsIgnoreCase("c"))
       {
           loop=false;
           System.out.println("Dropping course finished  ");
       }
       } catch (Exception ex) {
        ex.printStackTrace();
           Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
       }}else
          {
              System.out.println("Dropping canceled ");
             
          }}catch(Exception e)
       {
         System.out.println("INCORRECT DATA ENTRY, Details displayed below\n\t\t\t*********"+e.toString()+"*********");
         in.nextLine();
       }
          
      }
      System.out.println("If there no further transaction  type 'END'");
      String endMain=in.nextLine();
      if(endMain.equalsIgnoreCase("END"))
      {
         mainLoop=false;
      System.out.println("+++++++++++++ Transactions ended +++++++++++");
      }   
     }
             
      }
        } 
       
          
      
       
        
    

