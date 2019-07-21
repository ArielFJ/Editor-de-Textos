import javax.swing.*;
import java.awt.event.*;

public class MenuOpciones extends JMenu {

    JTextArea areaTexto;
    MiFrame principal;

    public MenuOpciones(JTextArea areaTexto, MiFrame frame) {
        super("Opciones");
        principal = frame;
        this.areaTexto = areaTexto;
        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem pegar = new JMenuItem("Pegar");
        MenuDiseno diseno = new MenuDiseno(areaTexto, principal);

        copiar.addActionListener(new Copiar());
        cortar.addActionListener(new Cortar());
        pegar.addActionListener(new Pegar());

        copiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        pegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        cortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        add(copiar);
        add(cortar);
        add(pegar);
        addSeparator();
        add(diseno);
    }

    private class Copiar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            areaTexto.copy();
        }
    }

    private class Cortar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            areaTexto.cut();
        }
    }

    private class Pegar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            areaTexto.paste();
        }
    }

}
