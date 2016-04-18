package es.cjrg.xml.staxWoodstox;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.validation.XMLValidationException;
import org.codehaus.stax2.validation.XMLValidationProblem;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;

public class TestXmlSchemaValidation {
	
	public static void main(String[] args) throws Exception {
		(new TestXmlSchemaValidation()).execute("./XMLs/validEmployee.xml","./XMLs/employeeSchema.xsd");
		(new TestXmlSchemaValidation()).execute("./XMLs/invalidEmployee.xml","./XMLs/employeeSchema.xsd");
	}

	private void execute(String xmlFileName, String relaxNgFileName) throws Exception {
		
		//load DTD
		XMLValidationSchemaFactory xmlValidationSchemaFactory = XMLValidationSchemaFactory.newInstance(XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA);
		  		
		InputStream schemaInputStream = new FileInputStream(new File(relaxNgFileName));
		XMLValidationSchema xmlValidationSchema = xmlValidationSchemaFactory.createSchema(schemaInputStream);
		 
		 //load Valid XML file
		InputStream xmlInputStream = new FileInputStream(new File(xmlFileName));
		XMLInputFactory2 xmlInputFactory2 = (XMLInputFactory2)XMLInputFactory.newInstance();
		XMLStreamReader2 xmlStreamReader =(XMLStreamReader2) xmlInputFactory2.createXMLStreamReader(xmlInputStream);
		try{ 
			 //validate the XML file
			 xmlStreamReader.validateAgainst(xmlValidationSchema);
			 //traverse the streaming document
			 while(xmlStreamReader.hasNext()){
				 xmlStreamReader.next();
			 }
		} catch(XMLValidationException e){
			String sTipoError=null;
			
			switch (e.getValidationProblem().getSeverity()) {
			case XMLValidationProblem.SEVERITY_ERROR:
				sTipoError="ERROR";
				break;
			case XMLValidationProblem.SEVERITY_FATAL:
				sTipoError="FATAL";
				break;
			case XMLValidationProblem.SEVERITY_WARNING:
				sTipoError="WARNING";
				break;				
			default:
				break;
			}
			
			System.err.println("XML file: " + xmlFileName + " failed to validatate against: " + relaxNgFileName);
			System.err.println("TIPO:"+sTipoError);
			System.err.println("\t"+e.getMessage());
			System.err.println("\t"+ e.getValidationProblem().getMessage());
			System.err.println("\t"+ e.getValidationProblem().getType() );
			System.err.println("\t"+ e.getValidationProblem().getSeverity() );

			return ;
		}
		 System.out.println("XML file: " + xmlFileName + " successfully validated against: " + relaxNgFileName);		
	}
}
