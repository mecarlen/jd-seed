package com.jd.seed.util.mail;

import java.util.Date;

import org.junit.Test;

import com.jd.seed.util.date.DateConvertor;
import com.jd.seed.util.date.EnumDateFormat;

/**
 * <pre>
 * mail模板测试
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月15日 下午7:57:02
 */
public class JunitMail {

	@Test
	public void sendMail(){
		String templateName="product_add_";
		SendMailInstance mailInstance = MailSenderConfig.newMailInstance(templateName);
		//更新实际发件人
//		String fromAddress = "whrd_tms@jd.com";
//		mailInstance.setFromAddress(fromAddress);
		//邮件人
		String toAddress = "wangfenghua@jd.com";
		String toUser = "王丰华";
		mailInstance.addToAddress(toAddress);
		//抄送
		String ccAddress = "zhaowen6@jd.com";
		mailInstance.addCcAddress(ccAddress);
		String ccUser = "赵文";
		//设置邮件标题
		mailInstance.paddingSubject("${toUser}", toUser);
		mailInstance.paddingSubject("${ccUser}", ccUser);
		//设置正文
		mailInstance.paddingContent("${toUser}", toUser);
		String productName = "京易通";
		mailInstance.paddingContent("${productName}", productName);
		String currTime = DateConvertor.date2Str(new Date(), EnumDateFormat.TIME_YMDHMS);
		mailInstance.paddingContent("${currTime}", currTime);
		mailInstance.paddingContent("${userinfo}", toUser);
		//发送
		MailSender.sendHtmlMail(mailInstance);
	}
}
