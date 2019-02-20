package com.thehartford.files;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thehartford.constant.Constant;

public class Application {

	public void applicationXml(String path) throws ParserConfigurationException, SAXException, IOException, TransformerException 
	{
		final Logger logger = Logger.getLogger(Application.class);
		
		File folder = new File(path);
		File[] listfiles = folder.listFiles();
		if(listfiles != null){
			for(File file : listfiles){
				if(file.isFile() && file.getName().equalsIgnoreCase("application.xml")){
					String absolutePath = file.getAbsolutePath();
					DocumentBuilderFactory documentbuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentbuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(absolutePath);
					Element rootTag = document.getDocumentElement();
					NodeList nodeList = document.getElementsByTagName("module");

					for (int i = 0; i < nodeList.getLength(); i++) {
						Node node = nodeList.item(i);
						Element element = (Element) nodeList.item(i);
						NodeList webList = element.getElementsByTagName("web-uri");
						String webUriText = webList.item(0).getTextContent();
						logger.info("childs" + webList.item(0).getTextContent());

						if (webUriText.equalsIgnoreCase("lvpservice.war")) {
							Comment comment = document.createComment(Constant.LVP_SERVICE);
							rootTag.insertBefore(comment, element);
							rootTag.removeChild(node);

							logger.info("child lvp  appeneded");

						} else if (webUriText.equalsIgnoreCase("appConfigWEB.war")) {
							Comment comment = document.createComment(Constant.APP_CONFIG_WEB);
							rootTag.insertBefore(comment, element);
							rootTag.removeChild(node);
							i--;
							logger.info("child app  appeneded");

						} else if (webUriText.equalsIgnoreCase("ocaweb.war")) {
							Comment comment = document.createComment(Constant.OCAWEB);
							rootTag.insertBefore(comment, element);
							rootTag.removeChild(node);
							i--;
							logger.info("child oca  appeneded");

						}

						else if (webUriText.equalsIgnoreCase("quoteemailservice.war")) {
							Comment comment = document.createComment(Constant.QUOTE_EMAIL_SERVICE);
							rootTag.insertBefore(comment, element);
							rootTag.removeChild(node);
							i--;
							logger.info("child quote  appeneded");
						} else if (webUriText.equalsIgnoreCase("opcweb.war")) {
							Comment comment = document.createComment(Constant.OPCWEB);
							rootTag.insertBefore(comment, element);
							rootTag.removeChild(node);
							i--;
							logger.info("child opc  appeneded");

						} else if (webUriText.equalsIgnoreCase("pi_oca_service.war")) {
							Comment comment = document.createComment(Constant.PI_SERVICE);
							rootTag.insertBefore(comment, element);
							rootTag.removeChild(node);
							i--;
							logger.info("child pi_oca  appeneded");
						} else {
							logger.info("Application file is commented");

						}
					}

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(document);
					StreamResult result = new StreamResult(new File(absolutePath));
					transformer.transform(source, result);
					logger.info("Application file updated successfully");

				}
				}
				}else{
					JOptionPane.showMessageDialog(null, "application.xml file is not present inside pi_csc_web");
				}
		}
		
}
