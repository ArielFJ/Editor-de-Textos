import javax.swing.*;
import java.io.*;
/*
* Clase que se encarga de guardar los archivos
* */
public class GuardarNuevo {

    public GuardarNuevo(MiFrame principal, JTextArea areaTexto) {

        JFileChooser fileChooser = new JFileChooser();

        if (principal.getTitle() == "A+ Notepad") {
            int seleccion = fileChooser.showSaveDialog(principal);
            File nuevoArchivo = fileChooser.getSelectedFile();
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter rutaNuevo = new FileWriter(nuevoArchivo.toString());
                    String texto = areaTexto.getText();
                    for (int i = 0; i < texto.length(); i++) {
                        rutaNuevo.write(texto.charAt(i));
                    }
                    principal.setTitle(nuevoArchivo.toString());
                    rutaNuevo.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            String ruta = principal.getTitle();
            try {
                FileWriter archivo = new FileWriter(ruta);
                String texto = areaTexto.getText();
                for (int i = 0; i < texto.length(); i++) {
                    archivo.write(texto.charAt(i));
                }
                archivo.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }


    }

}