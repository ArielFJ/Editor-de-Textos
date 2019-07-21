import javax.swing.*;
import java.awt.event.*;

public class MenuAyuda extends JMenu {

    MiFrame principal;

    public MenuAyuda(MiFrame frame) {
        super("Ayuda");
        principal = frame;
        JMenuItem version = new JMenuItem("Versión");
        JMenuItem autor = new JMenuItem("Autor");

        version.addActionListener(new Version());
        autor.addActionListener(new Autor());

        add(version);
        addSeparator();
        add(autor);
    }

    private class Version implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(principal, "Pre-Alpha \n 2019.7", "Versión", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class Autor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mensaje = "Jesús A. Fermín Jimenez\n" +
                    "2018-6362\n" +
                    "afermin266@gmail.com\n" +
                    "829-898-8989";
            JOptionPane.showMessageDialog(principal, mensaje, "Sobre el autor", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
