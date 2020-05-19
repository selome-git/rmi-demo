package apiPackage;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.lang.SecurityManager;
import java.sql.ResultSet;
/**
 *
 * @author user
 */
public interface markInterface extends Remote {
  // String process(String studId) throws RemoteException,SQLException;
   Student passMark(String studId,String course) throws RemoteException,SQLException;
   
}

