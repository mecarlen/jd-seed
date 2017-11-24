package com.jd.seed.util.mail;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.seed.util.xml.XMLEventAssist;

/**
 * <p>
 * 邮件配置类 处理邮件参数及邮件模板
 * </p>
 * 
 * @author metanoia.lang 2012-4-24
 */
public class MailSenderConfig {
	private static Logger LOGGER = LoggerFactory.getLogger(MailSenderConfig.class);
	private String mailServerHost;
	private String mailServerPort = "25";
	private String fromAddress;
	private String userName;
	private String password;
	private boolean validate = true;
	private Map<String, SendMailTemplate> tmap = new HashMap<String, SendMailTemplate>();

	private static MailSenderConfig config = new MailSenderConfig();

	private MailSenderConfig() {
		init();
	}

	public static MailSenderConfig getConfig() {
		return config;
	}

	public static SendMailInstance newMailInstance(String templateName) throws MailException {
		SendMailTemplate mt = config.getMailTemplate(templateName);
		SendMailInstance mi = new SendMailInstance(mt);
		mi.setMailServerHost(config.getMailServerHost()).setMailServerPort(config.getMailServerPort())
				.setFromAddress(config.getFromAddress()).setUserName(config.getUserName())
				.setPassword(config.getPassword()).setValidate(config.isValidate());
		return mi;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public boolean isValidate() {
		return validate;
	}

	public SendMailTemplate getMailTemplate(String name) {
		return tmap.get(name);
	}

	public Map<String, SendMailTemplate> getMailTemplate() {
		return tmap;
	}

	private void init() {
		InputStream confStream = MailSenderConfig.class.getResourceAsStream("/sendMail-conf.xml");
		XMLInputFactory factory = XMLInputFactory.newFactory();
		XMLEventReader reader = null;
		try {
			reader = factory.createXMLEventReader(confStream);
			initConfig(reader);
			initTemplate(reader);
			reader.close();
		} catch (XMLStreamException e) {
			LOGGER.error("init",e);
		}
	}

	private void initConfig(XMLEventReader reader) {
		try {
			XMLEventAssist xeAssist = new XMLEventAssist();
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement() && "config".equalsIgnoreCase(xeAssist.getStartTag(event))) {
					while (reader.hasNext()) {
						event = reader.nextEvent();
						if (event.isStartElement() && "mailServerHost".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							mailServerHost = reader.getElementText();
						} else if (event.isStartElement()
								&& "mailServerPort".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							mailServerPort = reader.getElementText();
						} else if (event.isStartElement()
								&& "fromAddress".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							fromAddress = reader.getElementText();
						} else if (event.isStartElement() && "userName".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							userName = reader.getElementText();
						} else if (event.isStartElement() && "password".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							password = reader.getElementText();
						} else if (event.isStartElement() && "validate".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							validate = Boolean.parseBoolean(reader.getElementText());
						} else if (event.isEndElement() && "config".equalsIgnoreCase(xeAssist.getEndTag(event))) {
							break;
						}
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			LOGGER.error("initConfig",e);
		}
	}

	private void initTemplate(XMLEventReader reader) {
		try {
			XMLEventAssist xeAssist = new XMLEventAssist();
			String name = null;
			String subject = null;
			String text = null;
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement() && "templates".equalsIgnoreCase(xeAssist.getStartTag(event))) {
					while (reader.hasNext()) {
						event = reader.nextEvent();
						if (event.isStartElement() && "template".equalsIgnoreCase(xeAssist.getStartTag(event))) {
							name = xeAssist.getAttribute(event, "name").getValue();
							while (reader.hasNext()) {
								event = reader.nextEvent();
								if (event.isStartElement() && "subject".equalsIgnoreCase(xeAssist.getStartTag(event))) {
									subject = reader.getElementText();
								} else if (event.isStartElement()
										&& "content".equalsIgnoreCase(xeAssist.getStartTag(event))) {
									text = reader.getElementText();
								} else if (event.isEndElement()
										&& "template".equalsIgnoreCase(xeAssist.getEndTag(event))) {
									SendMailTemplate mt = new SendMailTemplate(name, subject, text);
									tmap.put(name, mt);
									break;
								}
							}
						}
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			LOGGER.error("initTemplate",e);
		}
	}
}
