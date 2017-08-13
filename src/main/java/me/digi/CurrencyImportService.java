package me.digi;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import business.Currency;


/**
	@author Mirko Eberlein
 *
 */
public class CurrencyImportService {
	private static final String ezbPath = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	private static Map<String,Currency> currencyMap;
	
	public static void importXML(){
		URL url;
		try {
			url = new URL(ezbPath);
			URLConnection connection = url.openConnection();
			Document doc = parseXML(connection.getInputStream());
			NodeList cubeNodes = doc.getElementsByTagName("Cube");
			currencyMap = new HashMap<>();
			Currency euro = new Currency();
			euro.setCourse(new BigDecimal(1));
			final String euroCode = "EUR";
			euro.setCode(euroCode);
			euro.setName(CurrencyNames.INSTANZCE.getName(euro.getCode()));
			currencyMap.put(euroCode, euro);
			for (int i = 0; i < cubeNodes.getLength(); i++) {
				handleData(cubeNodes.item(i));
			}
			CurrencyData.INSTANZCE.setCurrencyMap(currencyMap);
			
		} catch (Exception e) {
			System.out.println("Error Message " + e);
		}
	}
	private static void handleData(Node item){
		if (item.hasAttributes()) {
			Node currencyNode = item.getAttributes().getNamedItem("currency");
			if (currencyNode != null) {
				Node rateNote = item.getAttributes().getNamedItem("rate");
				String rateString = rateNote.getNodeValue();
				String currencyString = currencyNode.getNodeValue();
				Currency currency = new Currency();
				currency.setCourse(new BigDecimal(rateString));
				currency.setCode(currencyString);
				currency.setName(CurrencyNames.INSTANZCE.getName(currencyString));
			    currencyMap.put(currencyString, currency);
			}
		}
	}
	private static Document parseXML(InputStream inputStream) {
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(inputStream);
		} catch (Exception exception) {
			System.out.println("error parseXML " + exception);
		}
		return doc;
	}
}
