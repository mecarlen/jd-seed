package com.jd.seed.util.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>
 * 利用dom4j处理xml的工具类，包含xml操作的常用方法
 * </p>
 * 
 * @author metanoia.lang 2012-4-24
 */
public class Dom4jUtils {
	// ----------- u&d xml --------------
	/**
	 * <p>
	 * 在指定节点下添加节点，xpath必须是单节点
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param parentNodeXpath
	 *            String 父节点xpath
	 * @param name
	 *            String 添加节点name
	 * @param text
	 *            String 添加节点内容
	 * @param attributes
	 *            Map<String,String> 添加节点属性
	 * @throws Dom4jUtilsException
	 */
	public static void addElement(Document doc, String parentNodeXpath,
			String name, String text, Map<String, String> attributes)
			throws Dom4jUtilsException {
		if (doc == null || parentNodeXpath == null
				|| parentNodeXpath.equals("") || name == null
				|| name.equals("")) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.addElement(doc,parentNodeXpath,name,text,attributes) document is null or parentNodeXpath is null or name is null: doc="
							+ doc
							+ " parentNodeXpath="
							+ parentNodeXpath
							+ " name="
							+ name
							+ " text="
							+ text
							+ " attributes=" + attributes);
		}

		Element parentElement = findElement(doc, parentNodeXpath);
		if (parentElement == null) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.addElement(doc,parentNodeXpath,name,text,attributes) parentElement not found: "
							+ parentNodeXpath);
		} else {
			Element element = parentElement.addElement(name);

			if (text != null) {
				element.setText(text);
			}

			for (Entry<String, String> entry : attributes.entrySet()) {
				if (entry.getValue() != null) {
					element.addAttribute(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	// ----------- build xml ------------
	/**
	 * <p>
	 * 创建空Document
	 * </p>
	 * 
	 * @param encoding
	 *            String 编码
	 * @return Document org.dom4j.Document
	 */
	public static Document buildEmpty(String encoding) {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding(encoding == null ? "UTF-8" : encoding);
		return doc;
	}

	/**
	 * <p>
	 * 通过org.w3c.dom.Document构建xml Document对象
	 * </p>
	 * 
	 * @param document
	 *            org.w3c.dom.Document
	 * @return Document org.dom4j.Document 若无返回null
	 */
	public static Document buildFromDom(org.w3c.dom.Document document) {
		if (null == document) {
			return null;
		}

		DOMReader reader = new DOMReader();
		return reader.read(document);
	}

	/**
	 * <p>
	 * 通过xml文件构建xml Document对象
	 * </p>
	 * 
	 * @param filePath
	 *            String xml文件路径
	 * @param encoding
	 *            String 编码
	 * @return Document org.dom4j.Document 若无或异常返回null
	 * @throws Dom4jUtilsException
	 */
	public static Document buildFromFile(String filePath, String encoding)
			throws Dom4jUtilsException {
		if (null == filePath) {
			return null;
		}

		Document doc = null;
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding == null ? "UTF-8" : encoding);
		try {
			doc = reader.read(new File(filePath));
		} catch (DocumentException e) {
			throw new Dom4jUtilsException(e);
		} catch (NullPointerException e) {
			throw new Dom4jUtilsException(e);
		}
		return doc;
	}

	/**
	 * <p>
	 * 通过xml文件读取流构建xml Document对象
	 * </p>
	 * 
	 * @param stream
	 *            InputStream xml文件读取流
	 * @param encoding
	 *            String 编码
	 * @return Document org.dom4j.Document 若无或异常返回null
	 * @throws Dom4jUtilsException
	 */
	public static Document buildFromXMLStream(InputStream stream,
			String encoding) throws Dom4jUtilsException {
		if (null == stream) {
			return null;
		}

		Document doc = null;
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding == null ? "UTF-8" : encoding);
		try {
			doc = reader.read(stream);
		} catch (DocumentException e) {
			throw new Dom4jUtilsException(e);
		}
		return doc;
	}

	/**
	 * <p>
	 * 通过xml字符串构建xml Document对象
	 * </p>
	 * 
	 * @param xmlString
	 *            String Xml的字符串
	 * @param encoding
	 *            String 编码
	 * @return Document org.dom4j.Document 若无或异常返回null
	 * @throws Dom4jUtilsException
	 */
	public static Document buildFromXMLString(String xmlString, String encoding)
			throws Dom4jUtilsException {
		if (null == xmlString) {
			return null;
		}

		Document doc = null;
		SAXReader reader = new SAXReader();
		reader.setEncoding(encoding == null ? "UTF-8" : encoding);

		try {
			doc = reader.read(new StringReader(xmlString));
		} catch (DocumentException e) {
			throw new Dom4jUtilsException(e);
		} catch (NullPointerException e) {
			throw new Dom4jUtilsException(e);
		}
		return doc;
	}

	// ----------- read xml ------------
	/**
	 * <p>
	 * 通过Xpath 获取指定节点指定属性
	 * </p>
	 * 
	 * <p>
	 * NOTE：只支持获取单节点属性
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //foo/bar/author
	 * @param attributeName
	 *            String 节点属性Xpath e.g name
	 * @return String 属性值 若无或异常返回null
	 */
	public static String findAttribute(Document doc, String nodeXpath,
			String attributeName) {
		if (null == doc || null == nodeXpath || null == attributeName) {
			return null;
		}

		Node node = doc.selectSingleNode(nodeXpath);
		if (null == node) {
			return null;
		}
		return node.valueOf("@" + attributeName);
	}

	/**
	 * <p>
	 * 通过Xpath 获取指定节点所有属性
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //a
	 * @return Map<String,String> 若无返回空Map
	 */
	public static Map<String, String> findAttributes(Document doc,
			String nodeXpath) {
		Map<String, String> map = new HashMap<String, String>();
		if (null == doc || null == nodeXpath) {
			return map;
		}

		List<? extends Node> list = doc.selectNodes(nodeXpath + "/@*");
		for (Iterator<? extends Node> iter = list.iterator(); iter.hasNext();) {
			Attribute attribute = (Attribute) iter.next();
			map.put(attribute.getName(), attribute.getValue());
		}

		return map;
	}

	/**
	 * <p>
	 * 通过Xpath 获取指定节点对象
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //a[@id='b1']
	 * @return Element 若无返回null
	 */
	public static Element findElement(Document doc, String nodeXpath) {
		if (null == doc || null == nodeXpath) {
			return null;
		}

		Node node = doc.selectSingleNode(nodeXpath);
		if (null == node) {
			return null;
		}
		return (Element) node;
	}

	/**
	 * <p>
	 * 通过Xpath 获取指定节点集合
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param nodeXpath
	 *            String 定位节点Xpath e.g //a[@name='name1']
	 * @return List<Element> 若无返回空list
	 */
	public static List<Element> findElements(Document doc, String nodeXpath) {
		List<Element> list = new ArrayList<Element>();
		if (null == doc || null == nodeXpath) {
			return list;
		}

		List<? extends Node> nodelist = doc.selectNodes(nodeXpath);
		for (Iterator<? extends Node> iter = nodelist.iterator(); iter
				.hasNext();) {
			list.add((Element) iter.next());
		}

		return list;
	}

	/**
	 * <p>
	 * 将dom4j Document转换为 w3c Document对象
	 * </p>
	 * 
	 * @param document
	 *            org.dom4j.Document
	 * @return org.w3c.dom.Document 若无或异常返回null
	 * @throws Dom4jUtilsException
	 */
	public static org.w3c.dom.Document outputToDom(Document document)
			throws Dom4jUtilsException {
		if (null == document) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.outputToDom(document) Document is null");
		}

		DOMWriter domWriter = new DOMWriter();
		org.w3c.dom.Document doc = null;
		try {
			doc = domWriter.write(document);
		} catch (DocumentException e) {
			throw new Dom4jUtilsException(e);
		}

		return doc;
	}

	/**
	 * <p>
	 * 将Document转换为文件，按UTF-8编码输出
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param filePath
	 *            String 保存文件绝对路径
	 * @throws Dom4jUtilsException
	 */
	public static void outputToFile(Document doc, String filePath)
			throws Dom4jUtilsException {
		outputToFile(doc, filePath, "UTF-8");
	}

	/**
	 * <p>
	 * 将Document转换为文件，按指定编码输出
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param filePath
	 *            String 保存文件绝对路径
	 * @param encoding
	 *            String 编码
	 * @throws Dom4jUtilsException
	 */
	public static void outputToFile(Document doc, String filePath,
			String encoding) throws Dom4jUtilsException {
		if (null == doc || null == filePath) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.outputToFile(doc,filePath,encoding) Document is null or filePath is null");
		}

		OutputFormat format = new OutputFormat("\t", true,
				encoding == null ? "UTF-8" : encoding);
		try {
			FileWriter writer = new FileWriter(filePath);
			XMLWriter xmlwriter = new XMLWriter(writer, format);
			xmlwriter.write(doc);
			xmlwriter.close();
		} catch (IOException e) {
			throw new Dom4jUtilsException(e);
		}
	}

	// ----------- output xml ------------
	/**
	 * <p>
	 * 将Document输出到 System.out 按utf-8编码输出
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @throws Dom4jUtilsException
	 */
	public static void outputToStdout(Document doc) throws Dom4jUtilsException {
		outputToStdout(doc, "UTF-8");
	}

	/**
	 * <p>
	 * 将Document输出到 System.out 按指定编码输出
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param encoding
	 *            String 编码
	 * @throws Dom4jUtilsException
	 */
	public static void outputToStdout(Document doc, String encoding)
			throws Dom4jUtilsException {
		if (null == doc) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.outputToStdout(doc,encoding) Document is null");
		}

		OutputFormat format = new OutputFormat("\t", true,
				encoding == null ? "UTF-8" : encoding);
		try {
			XMLWriter xmlwriter = new XMLWriter(System.out, format);
			xmlwriter.write(doc);
			xmlwriter.close();
		} catch (IOException e) {
			throw new Dom4jUtilsException(e);
		}
	}

	/**
	 * <p>
	 * 将Document转换为xml字符串输出，按UTF-8编码输出
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @return String xml字符串 若无或异常返回空字符串
	 * @throws Dom4jUtilsException
	 */
	public static String outputToString(Document doc) throws Dom4jUtilsException {
		return outputToString(doc, "UTF-8");
	}

	/**
	 * <p>
	 * 将Document转换为xml字符串输出，按指定编码输出
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param encoding
	 *            String 编码
	 * @return String xml字符串 若无或异常返回空字符串
	 * @throws Dom4jUtilsException
	 */
	public static String outputToString(Document doc, String encoding)
			throws Dom4jUtilsException {
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();

		if (null == doc) {
			return byteRep.toString();
		}

		OutputFormat format = new OutputFormat("\t", true,
				encoding == null ? "UTF-8" : encoding);
		try {
			XMLWriter xmlwriter = new XMLWriter(byteRep, format);
			xmlwriter.write(doc);
			xmlwriter.close();
		} catch (IOException e) {
			throw new Dom4jUtilsException(e);
		}

		return byteRep.toString();
	}

	/**
	 * <p>
	 * 删除节点集合,符合nodeXpath的节点都会删除
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param nodeXpath
	 *            String 待删节点xpath
	 * @throws Dom4jUtilsException
	 */
	public static void removeElement(Document doc, String nodeXpath)
			throws Dom4jUtilsException {
		if (doc == null || nodeXpath == null || nodeXpath.equals("")) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.removeElement(doc,nodeXpath) document is null or nodeXpath is null: doc="
							+ doc + " nodeXpath=" + nodeXpath);
		}

		List<Element> elements = findElements(doc, nodeXpath);
		for (Element element : elements) {
			Element parentElement = element.getParent();
			parentElement.remove(element);
		}
	}

	/**
	 * <p>
	 * 更新节点内容和属性，xpath必须是单节点
	 * </p>
	 * 
	 * @param doc
	 *            org.dom4j.Document
	 * @param nodeXpath
	 *            String 目标节点xpath
	 * @param attributes
	 *            Map<String,String> 待更新属性map，没有的属性新增，已有的属性更新
	 * @param text
	 *            待更新节点文本
	 * @throws Dom4jUtilsException
	 */
	public static void updateElement(Document doc, String nodeXpath,
			Map<String, String> attributes, String text)
			throws Dom4jUtilsException {
		if (doc == null || nodeXpath == null || nodeXpath.equals("")) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.updateElement(doc,nodeXpath,attributes,text) document is null or nodeXpath is null: doc="
							+ doc
							+ " nodeXpath="
							+ nodeXpath
							+ " attributes="
							+ attributes + " text=" + text);
		}

		Element element = findElement(doc, nodeXpath);
		if (element == null) {
			throw new Dom4jUtilsException(
					"Dom4jUtil.updateElement(doc,nodeXpath,attributes,text) element not found XPath: "
							+ nodeXpath);
		} else {
			element.setText(text);
			for (Entry<String, String> entry : attributes.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Attribute att = element.attribute(key);
				if (att == null) {
					element.addAttribute(key, value);
				} else {
					att.setValue(value);
				}
			}
		}
	}

	private Dom4jUtils() {
	}
}
