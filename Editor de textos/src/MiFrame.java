import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MiFrame extends JFrame {

    private JTextArea areaTexto;
    private JScrollPane laminaAreaTexto;

    public MiFrame() {

        ImageIcon icono = new ImageIcon("D:\\Documents\\_ITLA\\PROGRAMACION\\PROGRAMACIÓN 1\\Segundo Parcial\\Editor de textos\\icono.png");
        setIconImage(icono.getImage());

        setTitle("A+ Notepad");
        setSize(800, 600);
        setLocationRelativeTo(null);
        cerrarFrame();
        setLayout(new BorderLayout());


        areaTexto = new JTextArea();
        areaTexto.setFont(new Font("Consolas", 0, 18));
        areaTexto.setTabSize(4);

        laminaAreaTexto = new JScrollPane(areaTexto);

        BarraMenu barraMenu = new BarraMenu(areaTexto, this);

        add(barraMenu, BorderLayout.NORTH);
        add(laminaAreaTexto, BorderLayout.CENTER);

    }

    /*
    Métodos para modificar la forma en la que se comporta
    el botón de cerrar
     */

    public void cerrarFrame() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    boolean cierre = CompruebaCerrado();
                    if (cierre) {
                        int x = crearNuevo();
                        if(x == 0 || x==1)System.exit(0);


                    } else {
                        System.exit(0);
                    }

                }
            });
        } catch (Exception e) {
        }
    }

    public boolean CompruebaCerrado() {
        String texto_prueba = compruebaTexto();
        String area_texto = areaTexto.getText();
        boolean respuesta = false;
        if (getTitle() == "A+ Notepad") {
            if (area_texto.length() > 0) {
                respuesta = true;
            } else {
                respuesta = false;
            }
        } else if (getTitle() != "A+ Notepad") {
            if (!texto_prueba.equals(area_texto)) {
                respuesta = true;
            } else {
                respuesta = false;
            }
        }
        return respuesta;
    }

    public String compruebaTexto() {
        JTextArea textoPrueba = new JTextArea();
        if (getTitle() != "A+ Notepad") {
            String ruta = getTitle();
            try {
                FileReader fr = new FileReader(ruta);
                int caracter = 0;
                Character contenido = ' ';
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

    public int crearNuevo() {
        int x;
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea guardar el archivo antes de salir?", "Confirmar Salida", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (seleccion) {
            case JOptionPane.YES_OPTION:     //Respuesta Sí
                System.out.println(seleccion);
                GuardarNuevo gn = new GuardarNuevo(this, areaTexto);
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

}