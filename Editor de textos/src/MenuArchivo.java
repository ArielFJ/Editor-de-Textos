import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MenuArchivo extends JMenu {

    //Objetos del frame principal
    JTextArea areaTexto;
    MiFrame principal;

    //Variables necesarias para el manejo de archivos
    FileReader fr;
    String ruta;
    Character contenido;

    public MenuArchivo(JTextArea areaTexto, MiFrame frame) {
        super("Archivo");
        this.areaTexto = areaTexto;
        principal = frame;
        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem guardarComo = new JMenuItem("Guardar como");
        JMenuItem abrir = new JMenuItem("Abrir");

        add(nuevo);
        addSeparator();
        add(guardar);
        add(guardarComo);
        add(abrir);

        nuevo.addActionListener(new Nuevo());
        guardar.addActionListener(new Guardar());
        guardarComo.addActionListener(new GuardarComo());
        abrir.addActionListener(new Abrir());

        nuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        guardarComo.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
        abrir.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
    }

    //Variables necesarias para la comprobación de archivos existentes
    String texto_prueba;
    String area_texto;

    //Método para abrir un archivo
    private class Abrir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String texto_prueba = compruebaTexto();
            String area_texto = areaTexto.getText();

            if (principal.getTitle() == "A+ Notepad") {

                if (area_texto.length() > 0) {
                    crearNuevo();
                    abrirNuevo();
                } else {
                    abrirNuevo();
                }
            } else if (principal.getTitle() != "A+ Notepad") {
                if (!texto_prueba.equals(area_texto)) {
                    crearNuevo();
                    abrirNuevo();
                } else {
                    abrirNuevo();
                }
            }


        }
    }

    //Método para llamar un nuevo frame
    private class Nuevo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            texto_prueba = compruebaTexto();
            area_texto = areaTexto.getText();
            int x;

            if (principal.getTitle() == "A+ Notepad") {

                if (area_texto.length() > 0) {
                    x = crearNuevo();
                    if(x == 0 || x == 1) {
                        principal.dispose();
                        MiFrame frame = new MiFrame();
                        frame.setVisible(true);
                    }
                } else {
                    principal.dispose();
                    MiFrame frame = new MiFrame();
                    frame.setVisible(true);
                }
            } else if (principal.getTitle() != "A+ Notepad") {
                if (!texto_prueba.equals(area_texto)) {
                    crearNuevo();
                } else {
                    principal.dispose();
                    MiFrame frame = new MiFrame();
                    frame.setVisible(true);
                }
            }


        }
    }

    //Método para guardar
    private class Guardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GuardarNuevo gn = new GuardarNuevo(principal, areaTexto);
        }
    }

    //Método para guardar con diferente nombre
    private class GuardarComo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showSaveDialog(principal);
            String ruta;
            String texto = areaTexto.getText();

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                try {
                    ruta = fileChooser.getSelectedFile().getAbsolutePath();
                    FileWriter nuevo = new FileWriter(ruta);
                    for (int i = 0; i < texto.length(); i++) {
                        nuevo.write(texto.charAt(i));
                    }
                    principal.setTitle(ruta);
                    nuevo.close();
                } catch (IOException io) {
                }
            } else {
                JOptionPane.showMessageDialog(principal, "El archivo no se guardará.");
            }

        }
    }

    //Métodos que compactan aún más el comportamiento de los métodos anteriores
    //Los dividí así porque los uso en más de una parte
    public void abrirNuevo() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(principal);
        String ruta;
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            areaTexto.setText(null);
            try {
                ruta = fileChooser.getSelectedFile().getAbsolutePath();
                FileReader fw = new FileReader(ruta);
                int caracter = 0;
                Character palabra;
                while (caracter != -1) {
                    caracter = fw.read();
                    palabra = (char) caracter;
                    if (caracter != -1)
                        areaTexto.append(palabra.toString());
                }
                principal.setTitle(ruta);
                fw.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    private int crearNuevo() {
        int x;
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea guardar el archivo antes de salir?", "Confirmar Salida", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (seleccion) {
            case JOptionPane.YES_OPTION:     //Respuesta Sí
                System.out.println(seleccion);
                GuardarNuevo gn = new GuardarNuevo(principal, areaTexto);
                x = 0;
                break;

            case JOptionPane.NO_OPTION:       //Respuesta NO
                x = 1;
                break;

            default:
                x = 2;
                break;
        }
        return x;
    }

    public String compruebaTexto() {
        JTextArea textoPrueba = new JTextArea();
        if (principal.getTitle() != "A+ Notepad") {
            ruta = principal.getTitle();
            try {
                fr = new FileReader(ruta);
                int caracter = 0;
                while (caracter != -1) {
                    caracter = fr.read();
                    if (caracter != -1) {
                        contenido = (char) caracter;
                        textoPrueba.append(Character.toString(contenido));
                    }
                }
                fr.close();

            } catch (FileNotFoundException fnf) {
            } catch (IOException io) {
            }

        }

        return textoPrueba.getText();
    }



}
