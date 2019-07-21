import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarcoFuente extends JFrame {

    MiFrame principal;
    JLabel etiqueta;
    JComboBox listaFuentes;
    JTextArea areaTexto;
    String fuenteSeleccionada;
    int estiloFuente;

    /*
    Clase creada para modificar el tipo de la fuente
     */
    public MarcoFuente(MiFrame frame, JTextArea areaTexto, int estiloFuente) {

        this.areaTexto = areaTexto;
        principal = frame;
        listaFuentes = new JComboBox();
        this.estiloFuente = estiloFuente;

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fontNames.length; i++) {
            listaFuentes.addItem(fontNames[i]);
        }

        setTitle("Elija el tipo de fuente");
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(400, 300);
        setLocationRelativeTo(principal);

        etiqueta = new JLabel("Texto de Prueba.");
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        JPanel laminaBotones = new JPanel();

        listaFuentes.setSelectedItem(areaTexto.getFont().getName());
        etiqueta.setFont(new Font(areaTexto.getFont().getName(), Font.PLAIN, 36));

        listaFuentes.addActionListener(new ListaFuentes());
        aceptar.addActionListener(new Aceptar());
        cancelar.addActionListener(new Cancelar());

        laminaBotones.add(aceptar);
        laminaBotones.add(cancelar);

        add(listaFuentes, BorderLayout.NORTH);
        add(etiqueta, BorderLayout.CENTER);
        add(laminaBotones, BorderLayout.SOUTH);
    }

    private class ListaFuentes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fuenteSeleccionada = (String) listaFuentes.getSelectedItem();
            etiqueta.setFont(new Font(fuenteSeleccionada, Font.PLAIN, 44));
        }
    }

    private class Aceptar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int tamano = areaTexto.getFont().getSize();
            areaTexto.setFont(new Font(fuenteSeleccionada, estiloFuente, tamano));
            setVisible(false);
            dispose();
        }
    }

    private class Cancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    }
}
