import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuDiseno extends JMenu {

    JTextArea areaTexto;
    MiFrame principal;
    Font defecto;

    JCheckBox negrita;
    JCheckBox cursiva;

    public MenuDiseno(JTextArea areaTexto, MiFrame frame) {
        super("Diseño");
        this.areaTexto = areaTexto;
        principal = frame;
        JMenu fuente = new JMenu("Fuente");
        JMenuItem fondo = new JMenuItem("Fondo");
        defecto = areaTexto.getFont();

        JMenuItem colorFuente = new JMenuItem("Color");
        JMenuItem tamanoFuente = new JMenuItem("Tamaño");
        JMenuItem tipoFuente = new JMenuItem("Tipo");
        JMenu estiloFuente = new JMenu("Estilo");

        negrita = new JCheckBox("Negrita");
        cursiva = new JCheckBox("Cursiva");

        fuente.add(colorFuente);
        fuente.add(tamanoFuente);
        fuente.add(tipoFuente);
        fuente.add(estiloFuente);

        negrita.addActionListener(new EstiloFuente());
        cursiva.addActionListener(new EstiloFuente());

        estiloFuente.add(negrita);
        estiloFuente.add(cursiva);

        colorFuente.addActionListener(new ColorFuente());
        tamanoFuente.addActionListener(new TamanoFuente());
        tipoFuente.addActionListener(new TipoFuente());
        estiloFuente.addActionListener(new EstiloFuente());

        fondo.addActionListener(new Fondo());

        add(fuente);
        add(fondo);
    }

    JColorChooser colorChooser = new JColorChooser();
    Color color;


    private class Fondo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color = colorChooser.showDialog(principal, "Elige el color de fondo", Color.BLUE);
            areaTexto.setBackground(color);
        }
    }

    private class ColorFuente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color = colorChooser.showDialog(principal, "Elige el color de fuente", Color.BLACK);
            areaTexto.setForeground(color);
            areaTexto.setCaretColor(color);
        }
    }

    private class TamanoFuente implements ActionListener {
        Integer tamano;

        @Override
        public void actionPerformed(ActionEvent e) {
            String respuesta = JOptionPane.showInputDialog(principal, "Introduzca el tamaño de la fuente", "Ajustes de Fuente", JOptionPane.QUESTION_MESSAGE);
            tamano = Integer.parseInt(respuesta.trim());
            String fuente = areaTexto.getFont().getName();
            int estilo = areaTexto.getFont().getStyle();
            areaTexto.setFont(new Font(fuente, estilo, tamano));
        }
    }

    private class TipoFuente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MarcoFuente marcoFuente = new MarcoFuente(principal, areaTexto, estiloFuente);
            marcoFuente.setVisible(true);
        }
    }

    int estiloFuente;

    private class EstiloFuente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            estiloFuente = 0;

            String fuente = areaTexto.getFont().getName();
            int tamano = areaTexto.getFont().getSize();

            System.out.println(fuente);

            if (negrita.isSelected()) estiloFuente += Font.BOLD;

            if (cursiva.isSelected()) estiloFuente += Font.ITALIC;

            areaTexto.setFont(new Font(fuente, estiloFuente, tamano));
        }
    }

}
