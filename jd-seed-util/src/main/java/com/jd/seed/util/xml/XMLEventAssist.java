package com.jd.seed.util.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

/**
 * <p>
 * XMLEvent 辅助类 方便获取标签、属性、文本等
 * </p>
 * 
 * @author metanoia.lang 2012-4-25
 */
public class XMLEventAssist {
	private Map<String, QName>	curQNames	= new HashMap<String, QName>();

	public String getStartTag(XMLEvent event) {
		return event.asStartElement().getName().getLocalPart();
	}

	public String getEndTag(XMLEvent event) {
		return event.asEndElement().getName().getLocalPart();
	}

	public Attribute getAttribute(XMLEvent event, String attributeName) {
		return event.asStartElement().getAttributeByName(
				hasQName(attributeName));
	}

	public Iterator<?> getAttributes(XMLEvent event) {
		return event.asStartElement().getAttributes();
	}

	private QName hasQName(String name) {
		if (curQNames.containsKey(name)) {
			return curQNames.get(name);
		} else {
			QName qn = new QName(name);
			curQNames.put(name, qn);
			return qn;
		}
	}

}
