package com.thehartford.reader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DocumentReaderWriter {
	
	public Document documentReader(File file) throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory documentbuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentbuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		return document;
		
		
	}
	public void documentWriter(Document document,String path) throws TransformerException{
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(document);
	StreamResult result = new StreamResult(new File(path));
	transformer.transform(source, result);
	
	}
}
