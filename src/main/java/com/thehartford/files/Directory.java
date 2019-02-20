package com.thehartford.files;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thehartford.constant.Constant;
import com.thehartford.popup.UserInput;
import com.thehartford.reader.DocumentReaderWriter;

public class Directory {

	public void directory() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		final Logger logger = Logger.getLogger(Directory.class);
		
		File files = new File(Constant.APPCONFIG_PATH);
		if (!files.exists()) {
			if (files.mkdirs()) {
				logger.info("Multiple directories are created!");
			} else {
				logger.info("Failed to create multiple directories!");
			}
		}
		File file = new File(Constant.NTCS_PATH);
		if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "NTCS file need to be downloaded!!");
			UserInput pu = new UserInput();
			pu.generatepopup(Constant.DIR,Constant.TARGET_DIR_NTCS);
			logger.info("NTCS file copied");
		} else {
			JOptionPane.showMessageDialog(null, "NTCS file already exists!!");
			
		}
		File folder = new File(Constant.TARGET_DIR_NTCS);
		File[] listfiles = folder.listFiles();
		
		if(listfiles != null){
			
			for(File ntcsFile : listfiles){
				String ntcsPath = ntcsFile.getAbsolutePath();
				
				DocumentBuilderFactory documentbuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentbuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(ntcsPath);
				
				NodeList nodeList = document.getElementsByTagName("MAPS_SECURITY_FILTER");
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					NodeList webList = element.getElementsByTagName("value");
					logger.info("MAPS_SECURITY_FILTER Before Value" + webList.item(0).getTextContent());
					webList.item(i).setTextContent("FALSE");
					logger.info("MAPS_SECURITY_FILTER After Value" + webList.item(0).getTextContent());
				}
				DocumentReaderWriter documentReaderWriter = new DocumentReaderWriter();
				documentReaderWriter.documentWriter(document,ntcsPath);
				logger.info("NTCS file updated successfully");
				
			}
					
		}else{
			logger.info("NTCS file  not found");
			
		}
	
	}

}
