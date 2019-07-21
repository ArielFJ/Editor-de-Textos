import javax.swing.*;

public class BarraMenu extends JMenuBar {

    public BarraMenu(JTextArea areaTexto, MiFrame frame) {

        MenuArchivo archivo = new MenuArchivo(areaTexto, frame);
        MenuOpciones opciones = new MenuOpciones(areaTexto, frame);
        MenuAyuda ayuda = new MenuAyuda(frame);

        add(archivo);
        add(opciones);
        add(ayuda);

    }

}
