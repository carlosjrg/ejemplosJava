package es.cjrg.xml.stax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StaxWrite {

	public static void main(String[] args) {		

		// Se abre el fichero xml
		File mFXML = new File("./XMLs/xmlStaxOUT1.xml");
		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlw=null;
		try {
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(mFXML));
			
			// Write the default XML declaration
			xmlw.writeStartDocument();
			xmlw.writeCharacters("\n");
			xmlw.writeCharacters("\n");
			
			// Write a comment
			xmlw.writeComment("this is a comment");
			xmlw.writeCharacters("\n");
			
			// Write the root element "person" with a single attribute "gender"
			xmlw.writeStartElement("person");
			xmlw.writeNamespace("one", "http://namespaceOne");
			xmlw.writeAttribute("gender", "f");
			xmlw.writeCharacters("\n");
			
			// Write the "name" element with some content and two attributes
			xmlw.writeCharacters("    ");
			xmlw.writeStartElement("one", "name", "http://namespaceOne");
			xmlw.writeAttribute("hair", "pigtails");
			xmlw.writeAttribute("freckles", "yes");
			xmlw.writeCharacters("Juan Perez");
			
			// End the "name" element
			xmlw.writeEndElement();
			xmlw.writeCharacters("\n");
			
			// End the "person" element
			xmlw.writeEndElement();
			
			// End the XML document
			xmlw.writeEndDocument();
			
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (XMLStreamException e) {			
			e.printStackTrace();
		}
		finally {
			// Close the XMLStreamWriter to free up resources
			if(xmlw!=null){
				try {
					xmlw.close();
				} catch (XMLStreamException e) {				
					e.printStackTrace();
				}
			}
		}
	}

}
