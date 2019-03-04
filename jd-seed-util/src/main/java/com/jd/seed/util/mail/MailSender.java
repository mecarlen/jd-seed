package com.jd.seed.util.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 邮件发送类
 * </p>
 * 
 * @author metanoia.lang 2012-4-25
 * @author mecarlen 2018年3月15日 下午7:53:50
 */
public class MailSender {
	private static Logger LOGGER = LoggerFactory.getLogger(MailSender.class);

	private static List<MimeBodyPart> getFiles(Map<String, File> files) {
		if (files == null || files.isEmpty()) {
			return null;
		}

		List<MimeBodyPart> list = new ArrayList<MimeBodyPart>();
		for (Entry<String, File> file : files.entrySet()) {
			try {
				String fn = MimeUtility.encodeWord(file.getKey());
				FileDataSource fds = new FileDataSource(file.getValue());
				DataHandler dh = new DataHandler(fds);

				MimeBodyPart fp = new MimeBodyPart();
				fp.setContentID("<" + fp.getFileName() + ">");
				fp.setFileName(fn);
				fp.setDataHandler(dh);
				list.add(fp);
			} catch (IOException e) {
				LOGGER.error("",e);
			} catch (MessagingException e) {
				LOGGER.error("",e);
			}
		}

		return list;
	}

	/**
	 * <p>
	 * 获得构造好的发送mail对象（有邮件标题，无邮件内容）
	 * </p>
	 * 
	 * @param sendMailInstance
	 *            SendMailInstance 发送邮件实例
	 * @return javax.mail.Message
	 * @throws MessagingException
	 */
	private static Message newMailMessage(SendMailInstance sendMailInstance) throws MessagingException {
		// 判断是否需要身份认证
		MailAuthenticator authenticator = null;
		Properties pro = sendMailInstance.getProperties();
		if (sendMailInstance.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MailAuthenticator(sendMailInstance.getUserName(), sendMailInstance.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(sendMailInstance.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);

		// 创建邮件的接收者地址，并设置到邮件消息中
		List<String> toAddress = sendMailInstance.getToAddress();
		if (toAddress != null) {
			List<Address> toList = new ArrayList<Address>();
			for (String to : toAddress) {
				Address toadd = new InternetAddress(to);
				toList.add(toadd);
			}
			Address[] toArray = new Address[toList.size()];
			toArray = toList.toArray(toArray);
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipients(Message.RecipientType.TO, toArray);
		}

		// 创建邮件的抄送人地址，并设置到邮件消息中
		List<String> ccAddress = sendMailInstance.getCcAddress();
		if (ccAddress != null) {
			List<Address> ccList = new ArrayList<Address>();
			for (String cc : ccAddress) {
				Address ccadd = new InternetAddress(cc);
				ccList.add(ccadd);
			}
			Address[] ccArray = new Address[ccList.size()];
			ccArray = ccList.toArray(ccArray);
			// Message.RecipientType.CC属性标示抄送者的类型为CC
			mailMessage.setRecipients(Message.RecipientType.CC, ccArray);
		}

		// 创建邮件的暗送人地址，并设置到邮件消息中
		List<String> bccAddress = sendMailInstance.getBccAddress();
		if (bccAddress != null) {
			List<Address> bccList = new ArrayList<Address>();
			for (String bcc : bccAddress) {
				Address bccadd = new InternetAddress(bcc);
				bccList.add(bccadd);
			}
			Address[] bccArray = new Address[bccList.size()];
			bccArray = bccList.toArray(bccArray);
			// Message.RecipientType.BCC属性标示抄送者的类型为BCC
			mailMessage.setRecipients(Message.RecipientType.BCC, bccArray);
		}

		// 设置邮件消息的主题
		mailMessage.setSubject(sendMailInstance.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());

		// 处理附件
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		mailMessage.setContent(mainPart);

		Map<String, File> files = sendMailInstance.getFiles();
		if (files != null) {
			List<MimeBodyPart> listfile = getFiles(files);
			if (listfile != null) {
				for (MimeBodyPart fp : listfile) {
					mainPart.addBodyPart(fp);
				}
			}
		}

		return mailMessage;
	}

	/**
	 * <p>
	 * 发送HTML邮件
	 * </p>
	 * 
	 * <p>
	 * NOTE:如果收件人、抄送人、暗抄人邮件地址错误仍然会发送成功，错误地址不会收到邮件
	 * </p>
	 * 
	 * @param sendMailInstance
	 *            SendMailInstance 待发邮件实例
	 * @throws MailException
	 */
	public static void sendHtmlMail(SendMailInstance sendMailInstance) throws MailException {
		try {
			Message mailMessage = newMailMessage(sendMailInstance);

			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(sendMailInstance.getContent(), "text/html; charset=utf-8");

			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = (Multipart) mailMessage.getContent();
			mainPart.addBodyPart(html);

			// 发送邮件
			Transport.send(mailMessage);
		} catch (Exception e) {
			throw new MailException(e);
		}
	}

	private MailSender() {
	}
}
