package com.airtel.prod.engg.email;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EmailUtils {

	@Value("${mail.smtp.host:smtp.gmail.com}")
	String smtpHost;

	@Value("${mail.smtp.port:587}")
	String smtpPort;
	
	@Value("${mail.smtp.connection.timeout:10000}")
	String connectionTimeOut;
	
	@Value("${mail.smtp.read.timeout:10000}")
	String readTimeOut;
	
	@Value("${emailPassword:pass}")
	private String Password;
	
	@Value("${Username:user}")
	private String Username;

	private Properties properties;
	
	private Session session;

	@PostConstruct
	public void init() {
		properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth",true);
		properties.put("mail.transport.protocol","smtp");
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.connectiontimeout", connectionTimeOut);
		properties.put("mail.smtp.timeout", readTimeOut);
		
		
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Username,Password);
			}
		});
	}
	
	public boolean sendEmail(EmailMessage emailMessage) throws Exception {
		try{
			// 0. Add Recipients.
			Message message = setRecipients(emailMessage);
			message.setSubject(emailMessage.getSubject());
			message.setSentDate(new java.util.Date());

			// 1. Prepare text for mail.
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(emailMessage.getBody(), emailMessage.getBodyContentType());

			// 2. Add Multipart.
			Multipart multipart = new MimeMultipart();
			message.setContent(multipart);
			
			// 2a. Add text into multipart.
			multipart.addBodyPart(messageBodyPart);
			
			// if attachment is required then attach file as well.
			// 3. Create attachment.
			
			if(null != emailMessage.getAttachInfos() && emailMessage.getAttachInfos().size() > 0) {
				List<AttachmentInfo> attachments = emailMessage.getAttachInfos();
				for(AttachmentInfo attach : attachments) {
					MimeBodyPart attachment = new MimeBodyPart();
					DataSource source = (null == attach.getAttachmentFileContent()) ?
											new FileDataSource(attach.getFilePath()) :
											new ByteArrayDataSource(attach.getAttachmentFileContent(), attach.getAttachmentMimeType())	;
					
					attachment.setDataHandler(new DataHandler(source));
					attachment.setFileName(attach.getFileNameForAttachment());
					multipart.addBodyPart(attachment);
				}
				
			}
			
			// 4. Send message.
			Transport.send(message);
			return true;

		} catch(MessagingException | UnsupportedEncodingException ex) {
			throw new Exception("Exception ocurred while preparing email message." + ex.getMessage());
		}
	}
	
	private Message setRecipients(EmailMessage emailMessage) throws MessagingException, UnsupportedEncodingException{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailMessage.getMailSender(),emailMessage.getMailTitle()));
			
		//set to list in message
		for(String to : emailMessage.getToEmail()) {
			message.addRecipients(RecipientType.TO, InternetAddress.parse(to));
		}

		//set cc list in message
		for(String cc : emailMessage.getCcEmail()){
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
		}
		
		//set bcc list in message
		for(String bcc : emailMessage.getBccEmail()){
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
		}
		
		return message;
	}
}
