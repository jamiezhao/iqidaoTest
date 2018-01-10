package iqidaoTest.Utils;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlData {
	
	public static String getParamFromXml(String paramName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		String paramValue = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse("TestData.xml");
			NodeList paramList = doc.getElementsByTagName(paramName);
			paramValue = paramList.item(0).getFirstChild().getNodeValue();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paramValue;
		
	}

}
