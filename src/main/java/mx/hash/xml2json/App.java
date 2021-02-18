package mx.hash.xml2json;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class App {

    static public void main(String[] args) {
        try {

            // Ruta del archivo XML
            String nombreArchivo = "A-2.xml";
            ConversorJSON conversor = new ConversorJSON();
            String json = conversor.convertir(nombreArchivo);
 
            System.out.println(json);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
