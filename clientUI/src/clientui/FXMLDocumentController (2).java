/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import apiPackage.Student;
import apiPackage.markInterface;

import java.io.IOException;
import java.net.URL;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
//import org.newdawn.slick.*;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


/**
 *
 * @author user
 */
public class FXMLDocumentController  implements Initializable {
        Registry registry;
 
    @FXML
    public Label labelMain;
     @FXML
   public com.gluonhq.charm.glisten.control.TextField txtId,txtCourse;
 
    
            
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
    
    }        
   

    


    @FXML
    private void onSubmit(ActionEvent event) throws RemoteException, SQLException {
        String id=txtId.getText(),course=txtCourse.getText();
         try {
             System.setSecurityManager(new RMISecurityManager());
            registry = LocateRegistry.getRegistry("localhost", 9090);//yenanten pc ip Wireless LAN adapter WiFi: blo mimetawn ip
        markInterface imark =(markInterface) registry.lookup("mark");
         Student studRec= imark.passMark(id, course);
           
if(studRec.getId().equalsIgnoreCase("null")||studRec.getMark()==0.0)
{
    labelMain.setText("Sorry:(\n No information found using that id and course");
}
else{
   labelMain.setText("ID - "+studRec.getId()+"\nNAME -"+studRec.getName()+"\nCourse - "+
           studRec.getCourse()+"\nMark - "+studRec.getMark()+"\nGrade - "+studRec.getGrade());

}   } catch (Exception ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        } 




        
      
    }

    @FXML
    private void onClear(ActionEvent event) {
       txtId.setText("");
      txtCourse.setText("");
      labelMain.setText(" ");
         
    }

    
    
}
