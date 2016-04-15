package es.cjrg.xml.stax;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxRead {
	
	public static void main(String[] args) {
    
		System.out.println("INICIO");
		
		//Se toma el tiempo inicial
		long time_start, time_end;
		float time_total;
		time_start = System.currentTimeMillis();
		
		//Se abre el fichero xml
		File mFXML= new File("./XMLs/xml1.xml");		
	  
		//Se prepara el parseo
		XMLInputFactory factory = XMLInputFactory.newInstance();    
		XMLStreamReader reader = null;
		FileInputStream mFis = null;
		try {
			mFis = new FileInputStream(mFXML);
			reader = factory.createXMLStreamReader(mFis);	 

			//Se recorre el fichero
			while(reader.hasNext()){		
				tratarEvento(reader);			
			}
						
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (XMLStreamException e) {			
			e.printStackTrace();
		}	
		finally {
			if(reader!=null){
				try {
					reader.close();
				} catch (XMLStreamException e) {			
					e.printStackTrace();
				}
			}
		}		
 
		//Calcula tel tiempo que ha tardado el proceso
      	System.out.println("\nFIN");
      	time_end = System.currentTimeMillis();
      	time_total = time_end - time_start ;
		System.out.println("Ha tardado "+ time_total +" ms\n");			
	}

	
	private static void tratarEvento(XMLStreamReader reader) throws XMLStreamException {

		int event = reader.next();

		System.out.println("\nLinea=" + reader.getLocation().getLineNumber() + "\tColumna=" + reader.getLocation().getColumnNumber()
						+ "\tCharacterOffset=" + reader.getLocation().getCharacterOffset() + "-");

		switch (event) {

		case XMLStreamConstants.START_ELEMENT:
			System.out.print("[START_ELEMENT]\n");
			System.out.println("\tLocalName '" + reader.getLocalName() + "'");
			System.out.println("\tCharacterEncodingScheme='" + reader.getCharacterEncodingScheme() + "'");
			System.out.println("\tNamespaceURI='" + reader.getNamespaceURI() + "'");
			System.out.println("\tAttributeCount='" + reader.getAttributeCount() + "'");
			System.out.println("\tAttributes");
			for (int i = 0; i < reader.getAttributeCount(); i++) {
				System.out
						.println("\t\t'" + reader.getAttributeLocalName(i) + "'='" + reader.getAttributeValue(i) + "'");
			}
			System.out.println("\tNamespaceCount='" + reader.getNamespaceCount() + "'");
			for (int i = 0; i < reader.getNamespaceCount(); i++) {
				System.out.println("\t\t'" + reader.getNamespaceURI(i) + "'");
			}
			break;

		case XMLStreamConstants.END_ELEMENT:
			System.out.print("[END_ELEMENT]\n");
			System.out.println("\tLocalName='" + reader.getLocalName() + "'");
			break;

		case XMLStreamConstants.SPACE:
			System.out.print("[START_ELEMENT]\n");
			break;

		case XMLStreamConstants.CHARACTERS:
			System.out.print("[CHARACTERS]\n");
			System.out.println("\tText='" + reader.getText() + "'");
			break;

		case XMLStreamConstants.PROCESSING_INSTRUCTION:
			System.out.print("[PROCESSING_INSTRUCTION]\n");
			break;

		case XMLStreamConstants.COMMENT:
			System.out.print("[COMMENT]\n");
			System.out.print("\tComment '" + reader.getText() + "'\n");
			break;

		case XMLStreamConstants.DTD:
			System.out.print("[DTD]\n");
			if (reader.hasText())
				System.out.println("\t'" + reader.getText() + "'");
			break;

		case XMLStreamConstants.START_DOCUMENT:
			System.out.print("[START_DOCUMENT]\n");
			System.out.println("\tLocalName +'" + reader.getLocalName() + "'");
			break;

		case XMLStreamConstants.END_DOCUMENT:
			System.out.print("[END_DOCUMENT]\n");
			break;
		}
	}
}

/*
All states	getProperty(), hasNext(), require(), close(), getNamespaceURI(), isStartElement(), isEndElement(), isCharacters(), isWhiteSpace(), getNamespaceContext(), getEventType(),getLocation(), hasText(), hasName()
START_ELEMENT	next(), getName(), getLocalName(), hasName(), getPrefix(), getAttributeXXX(), isAttributeSpecified(), getNamespaceXXX(), getElementText(), nextTag()
ATTRIBUTE	next(), nextTag() getAttributeXXX(), isAttributeSpecified()
NAMESPACE	next(), nextTag() getNamespaceXXX()
END_ELEMENT	next(), getName(), getLocalName(), hasName(), getPrefix(), getNamespaceXXX(), nextTag()
CHARACTERS	next(), getTextXXX(), nextTag()
CDATA	next(), getTextXXX(), nextTag()
COMMENT	next(), getTextXXX(), nextTag()
SPACE	next(), getTextXXX(), nextTag()
START_DOCUMENT	next(), getEncoding(), getVersion(), isStandalone(), standaloneSet(), getCharacterEncodingScheme(), nextTag()
END_DOCUMENT	close()
PROCESSING_INSTRUCTION	next(), getPITarget(), getPIData(), nextTag()
ENTITY_REFERENCE	next(), getLocalName(), getText(), nextTag()
DTD	next(), getText(), nextTag()
*/
