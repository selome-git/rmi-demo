/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientPackage;

import apiPackage.Student;
import apiPackage.markInterface;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException, SQLException {
        
        Registry registry= LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        markInterface imark =(markInterface) registry.lookup("mark");
        
        Scanner in= new Scanner(System.in);
        
        String loop="";
        
        while(!loop.equalsIgnoreCase("stop")){
            System.out.println("Enter your id number ");
            String idIn=in.nextLine();
            System.out.println("Course name ");
            String courseIn=in.nextLine();
            
           try
           {
                System.setSecurityManager(new RMISecurityManager());
           }catch(Exception  e)
           {
               e.printStackTrace();
           }
             Student studRec= imark.passMark(idIn, courseIn);
            
            System.out.println("ID\t\tStudents' name\t\t\t\tCourse\t\t\tMark\t\t\t\tGrade\n"+studRec.getId()+"\t\t"+studRec.getName()+"\t\t"+studRec.getCourse()+"\t\t"+studRec.getMark()+"\t\t"+studRec.getGrade()+"");
       
          
         
  
          
            System.out.println("\n\t\t To stop entering mark type 'STOP '");
            loop= in.nextLine();
            
      
        }
        
    }
    
}
