package com.thehartford.files;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thehartford.constant.Constant;
import com.thehartford.reader.DocumentReaderWriter;

public class WebConfiguration {
	
	public void webXml(String path) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		final Logger logger = Logger.getLogger(WebConfiguration.class);
		
		File folder = new File(path);
		File[] listfiles = folder.listFiles();
		if(listfiles != null){
			for (File file : listfiles){
				if (file.isDirectory())
	            {
					webXml(file.getAbsolutePath());
	            }
					if(file.isFile() && file.getName().equalsIgnoreCase("web.xml")){
						String absolutePath = file.getAbsolutePath();
						
						DocumentReaderWriter documentReaderWriter = new DocumentReaderWriter();
						Document document = documentReaderWriter.documentReader(file);
						
						Element rootTag = document.getDocumentElement();
						NodeList filterNodeList = document.getElementsByTagName("filter");
						NodeList filterMappingNodeList = document.getElementsByTagName("filter-mapping");
						
						for (int i = 0; i < filterNodeList.getLength(); i++) {
							Node node = filterNodeList.item(i);
							Element element = (Element) filterNodeList.item(i);
							NodeList nodes = element.getElementsByTagName("filter-name");
							logger.info("\n");
							logger.info("filterName " + nodes.item(0).getTextContent());
							String filter = nodes.item(0).getTextContent();
							if (filter.equalsIgnoreCase("WebSecurityFilter")) {
								Comment comment = document.createComment(Constant.WEB_SECURITY_FILTER);

								rootTag.insertBefore(comment, element);
								rootTag.removeChild(node);

							} else if (filter.equalsIgnoreCase("SMSessionCookieFilter")) {
								Comment comment = document.createComment(Constant.SM_FILTER);

								rootTag.insertBefore(comment, element);
								rootTag.removeChild(node);
								i--;
							}
							else {
								logger.info("Web file is updated");
							}
						}
						updateFilterMaping(filterMappingNodeList, document, rootTag);
						documentReaderWriter.documentWriter(document,absolutePath);
						logger.info("Web file updated successfully");
					}
				}
		}else{
			JOptionPane.showMessageDialog(null, "web.xml file is not present inside pi_csc_web");
		}
		
		}
		
	

	private static void updateFilterMaping(NodeList filterMappingNodeList, Document document, Element rootTag) {
		final Logger logger = Logger.getLogger(WebConfiguration.class);

		
		
		for (int i = 0; i < filterMappingNodeList.getLength(); i++) {
			Node node = filterMappingNodeList.item(i);
			Element element = (Element) filterMappingNodeList.item(i);
			NodeList filterNameList = element.getElementsByTagName("filter-name");
			logger.info("\n");
			logger.info("filter-mapping" + filterNameList.item(0).getTextContent());
			String filterMapping = filterNameList.item(0).getTextContent();
			if (filterMapping.equalsIgnoreCase("WebSecurityFilter")) {
				Comment comment = document.createComment(Constant.WEB_SECURITY_FILTER_MAPPING);
				rootTag.insertBefore(comment, element);
				rootTag.removeChild(node);

			} else if (filterMapping.equalsIgnoreCase("SMSessionCookieFilter")) {
				Comment comment = document.createComment(Constant.SM_FILTER_MAPPING);

				rootTag.insertBefore(comment, element);
				rootTag.removeChild(node);
				i--;

			}

			else {
				logger.info("Web file is updated");
				
			}

		}

	}

}
