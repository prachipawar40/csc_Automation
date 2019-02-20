package com.thehartford.files;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thehartford.main.Automation;
import com.thehartford.reader.DocumentReaderWriter;

public class DBSetup {

	public void listJdbcFiles(String path) throws ParserConfigurationException, SAXException, IOException, TransformerException{

		final Logger logger = Logger.getLogger(Automation.class);

		File folder = new File(path);
		File[] listfiles = folder.listFiles();
		if(listfiles != null){
			for (File file : listfiles){
				if (file.isDirectory())
				{
					listJdbcFiles(file.getAbsolutePath());
				}
				if(file.isFile() && file.getName().equalsIgnoreCase("csc2_ds-jdbc.xml")){

					String absolutePath = file.getAbsolutePath();
					DocumentReaderWriter documentReaderWriter = new DocumentReaderWriter();
					Document document = documentReaderWriter.documentReader(file);
					NodeList nodeList = document.getElementsByTagName("jdbc-driver-params");

					for (int i = 0; i < nodeList.getLength(); i++) {

						Element element = (Element) nodeList.item(i);
						String webList = element.getElementsByTagName("url").item(0).getTextContent();
						logger.info("before"+webList);
						element.getElementsByTagName("url").item(0).setTextContent("jdbc:oracle:thin:@//xdhfd2-eop-scan:1521/PIEXADEV_OLTP_SRVC");

					}
					documentReaderWriter.documentWriter(document,absolutePath);
					logger.info("JDBC file updated successfully");


				}

			}
		}else{
			logger.info("please create domain csc_file not found");
		}
	}

}
