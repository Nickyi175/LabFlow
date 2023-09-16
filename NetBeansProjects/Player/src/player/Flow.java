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

    FileOutputStream FileOutput;
    ObjectOutputStream ObjOutput;
    FileInputStream FileInput;
    ObjectInputStream ObjInput;
    JFileChooser ArchivoSeleccionado = new JFileChooser();
    ArrayList ArregloCanciones = new ArrayList();

    public void addSong(JFrame frame) {
        ArchivoSeleccionado.setMultiSelectionEnabled(true);
        int fileValid = ArchivoSeleccionado.showOpenDialog(frame);

        if (fileValid == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;

        } else if (fileValid == javax.swing.JFileChooser.APPROVE_OPTION) {

            File[] file = ArchivoSeleccionado.getSelectedFiles();
            ArregloCanciones.addAll(Arrays.asList(file));
        }
    }

    

    public void SaveSong(JFrame frame) {
        ArchivoSeleccionado.setMultiSelectionEnabled(false);
        int Valid = ArchivoSeleccionado.showSaveDialog(frame);

        if (Valid == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
        } else if (Valid == javax.swing.JFileChooser.APPROVE_OPTION) {
            File pls = ArchivoSeleccionado.getSelectedFile();
            try {
                FileOutput = new FileOutputStream(pls + ".tgr");
                ObjOutput = new ObjectOutputStream(FileOutput);

                for (int i = 0; i < ArregloCanciones.size(); i++) {
                    File tmp = (File) ArregloCanciones.get(i);
                    ObjOutput.writeObject(tmp);
                }
                ObjOutput.close();
            } catch (Exception e) {
            }

        }
    }

    public void OpenSong(JFrame frame) {
        ArchivoSeleccionado.setMultiSelectionEnabled(false);
        int Valid = ArchivoSeleccionado.showOpenDialog(frame);

        if (Valid == javax.swing.JFileChooser.CANCEL_OPTION) {
            File pls = ArchivoSeleccionado.getSelectedFile();
            try {
                FileInput = new FileInputStream(pls);
                ObjInput = new ObjectInputStream(FileInput);
                File file;
                while ((file = (File) ObjInput.readObject()) == null) {
                    ArregloCanciones.add(file);
                }

                if ((file = (File) ObjInput.readObject()) == null) {
                    ArregloCanciones.isEmpty();
                }
                ObjInput.close();

            } catch (Exception e) {
            }
        } else {
        }
    }
    
    ArrayList getArregloCanciones() {
        return ArregloCanciones;
    }
}
