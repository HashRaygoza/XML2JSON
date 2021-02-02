package com.enercon.xml2json;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.json.JSONObject;
import org.json.XML;

public class App {

    static public void main(String[] args) {
        try {
            // Generador de constructor de objetos XML
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            // Esto es para agilizar la lectura de archivos grandes
            documentBuilderFactory.setNamespaceAware(false);
            documentBuilderFactory.setValidating(false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/namespaces", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/validation", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            // constructor de objetos XML
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();            

            // Ruta del archivo XML
            String nombreArchivo = "A-2.xml";
            File archivo = new File(nombreArchivo);

            // Objeto Documento XML
            Document documento = documentBuilder.parse(archivo);
            
            // Esto ayuda al procesamiento
            documento.getDocumentElement().normalize();

            StringWriter writer = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(writer));

            String xmlString = writer.getBuffer().toString();
            
            JSONObject jsonObj = XML.toJSONObject(xmlString);
            String json = jsonObj.toString(4);
 
            System.out.println(json);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
