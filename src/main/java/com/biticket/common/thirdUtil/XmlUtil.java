package com.biticket.common.thirdUtil;

import com.biticket.common.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

	/**
	 * 将参数转换为xml格式
	 * 
	 * @param data
	 * @return
	 */
	public static String map2Xml(Map<String, String> data, String rootName) {
		Document document = DocumentHelper.createDocument();

		Element root = document.addElement(rootName);

		for (Map.Entry<String, String> entry : data.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
            if (!StringUtils.hasValue(value)) {
                continue;
            }
			Element e = root.addElement(key);
			e.addCDATA(value);
		}
		return document.asXML();
	}
	
	/**
	 * 将参数转换为xml格式
	 * 
	 * @param data
	 * @return
	 */
	public static Document map2XmlObjet(Map<String, String> data, String rootName) {
		Document document = DocumentHelper.createDocument();

		Element root = document.addElement(rootName);
		for (Map.Entry<String, String> entry : data.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
            if (!StringUtils.hasValue(value)) {
                continue;
            }
			Element e = root.addElement(key);
			e.addCDATA(value);
		}
		return document;
	}

	public static Map<String, String> xml2Map(String xmlString,
			String charset) {
		try {
			SAXReader reader = new SAXReader();
			InputStream in = new ByteArrayInputStream(
					xmlString.getBytes(charset));
			Document document = reader.read(in);

			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			Map<String, String> result = new HashMap<String, String>();
			for (Element e : elements) {
				String key = e.getName();
				String value = e.getText();
				result.put(key, value);
			}
			return result;
		} catch (UnsupportedEncodingException | DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> xml2Map(String xmlString) {
		return xml2Map(xmlString, "UTF-8");
	}
}
