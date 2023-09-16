package player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Flow {
    
    JFileChooser ArchivoSeleccionado=new JFileChooser();
    ArrayList ArregloCanciones=new ArrayList();
    
    void addSong(JFrame frame){
    
        ArchivoSeleccionado.setMultiSelectionEnabled(true);
        int fileValid=ArchivoSeleccionado.showOpenDialog(frame);
        
        if (fileValid==javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
            
        } 
        
        else if(fileValid==javax.swing.JFileChooser.APPROVE_OPTION) {
            
            File[] file=ArchivoSeleccionado.getSelectedFiles();
            ArregloCanciones.addAll(Arrays.asList(file));
            
        }
        
    }
    
    ArrayList getArregloCanciones(){
        return ArregloCanciones;
    }
    
    
    FileOutputStream fos;
    ObjectOutputStream oos;
    
    
    public void GuardarCanciones(JFrame frame){
    
        ArchivoSeleccionado.setMultiSelectionEnabled(false);
        
        int Valid=ArchivoSeleccionado.showSaveDialog(frame);
        
        if (Valid==javax.swing.JFileChooser.CANCEL_OPTION) {
            
            return;
            
        } 
        
        else if(Valid==javax.swing.JFileChooser.APPROVE_OPTION) {
            
            File pls=ArchivoSeleccionado.getSelectedFile();
            
            try {
                   fos=new FileOutputStream(pls + ".tgr");
                   oos=new ObjectOutputStream(fos);
                   
                   for(int i=0;i<ArregloCanciones.size();i++){
                          File tmp=(File) ArregloCanciones.get(i);
                          oos.writeObject(tmp);}
                   oos.close();
            }catch (Exception e) {  
            }
            
        }
    }
    
    FileInputStream hola;
    ObjectInputStream hola2;
    
    public void Abrir(JFrame frame){
        ArchivoSeleccionado.setMultiSelectionEnabled(false);
        int Valid=ArchivoSeleccionado.showOpenDialog(frame);
        
        if (Valid==javax.swing.JFileChooser.CANCEL_OPTION) {
            
            File pls=ArchivoSeleccionado.getSelectedFile();
            
            try {
                   hola=new FileInputStream(pls);
                   hola2=new ObjectInputStream(hola);
                   File tmp;
                   
                   while((tmp=(File) hola2.readObject())==null)
                   {
                       ArregloCanciones.add(tmp);
                       
                   }
                   
                   if((tmp=(File) hola2.readObject())==null)
                      {
                          ArregloCanciones.isEmpty();
                          
                      }
                   hola2.close();
                
            } catch (Exception e) {
            }
            
        } else {
        }
        
    }
    
}
