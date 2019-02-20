package com.thehartford.files;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.thehartford.reader.DocumentReaderWriter;

public class Configuration {

	public void listFiles(String path) throws ParserConfigurationException, SAXException, IOException {
		
		final Logger logger = Logger.getLogger(Configuration.class);
		
		File folder = new File(path);
		File[] listfiles = folder.listFiles();
		if(listfiles != null){
			for (File file : listfiles){
				if (file.isDirectory())
					{
            	listFiles(file.getAbsolutePath());
						}
				if(file.isFile() && file.getName().equalsIgnoreCase("configXMLData.xml")){
            		logger.info(file.getName());
            		DocumentReaderWriter documentReaderWriter = new DocumentReaderWriter();
					Document document = documentReaderWriter.documentReader(file);
            		try {
            			
            			Element root = document.getDocumentElement(); 
            			logger.info("Before");  
            			logger.info("Using getElementByTagName appconfig-localoverridepath: " + root.getElementsByTagName("appconfig-localoverridepath").item(0).getTextContent());    
                        root.getElementsByTagName("appconfig-localoverridepath").item(0).setTextContent("C:/CSC2/appconfig");
            			logger.info("After");  
            			logger.info("Using getElementByTagName appconfig-localoverridepath: " + root.getElementsByTagName("appconfig-localoverridepath").item(0).getTextContent()); 
            			
            			String absolutePath = file.getAbsolutePath();
            			documentReaderWriter.documentWriter(document,absolutePath);
          
            		} catch (TransformerConfigurationException e) {
                  			e.printStackTrace();
            		} catch (TransformerException e) {
                  			e.printStackTrace();
            		} 
            	               
            }

		}
		}else{
			JOptionPane.showMessageDialog(null, "configXmlData.xml file is not present inside pi_csc_common");
		}
			
	}

}
