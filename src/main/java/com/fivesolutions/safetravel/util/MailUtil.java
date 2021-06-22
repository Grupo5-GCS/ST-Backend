package com.fivesolutions.safetravel.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MailUtil {
	
	private static final Logger logger = LogManager.getLogger(MailUtil.class);

	
	public static void sendEmail(String emailTo, String subject, String text, String bodyHtml) {
		logger.info("Start MailUtil.sendEmail()");
		String fromEmail = "safeTravel@gmail.com"; // Para permitir enviar correo https://myaccount.google.com/lesssecureapps
		String password = "123";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		
		try {			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(subject);
			
			if(bodyHtml != null) {
				BodyPart messageBodyPart = new MimeBodyPart();
				Multipart multipart = new MimeMultipart();
				messageBodyPart.setContent(bodyHtml, "text/html; charset=utf-8");
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
			} else {
				message.setText(text);
			}
	        
			Transport.send(message);
			logger.info("End MailUtil.sendEmail()");
	        
		} catch(Exception me) {
			me.printStackTrace();
		}
		
	}

}
