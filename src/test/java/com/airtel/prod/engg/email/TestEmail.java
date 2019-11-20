package com.airtel.prod.engg.email;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/email-test-context.xml"})
public class TestEmail {

	@Autowired
	private EmailUtils utils;
	
	@Value("${mail.sender}")
	private String sender;
	
	@Value("#{'${mail.recepients}'.split(',')}") 
	private List<String> toList;
	
	@Value("#{'${mail.cc.recipient}'.split(',')}") 
	private List<String> ccList;
	
	@Value("#{'${mail.bcc.recipient}'.split(',')}") 
	private List<String> bccList;
	
	@Value("${mail.title}")
	private String mailTitle;
	
	private static String delim=",";
	
	@Test
	public void testEmail() throws Exception{
		System.out.println("starting the process...");
		String subject = "Mike testing 123...";
//		String body = "Hi building the email utility please help in testing and use this utility with attachment";
		String body = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<body>\n" + 
				"\n" + 
				"<h1>My First Heading</h1>\n" + 
				"\n" + 
				"<p>My first paragraph.</p>\n" + 
				"\n" + 
				"</body>\n" + 
				"</html>";
		//simple sending the text message
//		EmailMessage message = new EmailMessage(sender, toList, ccList, bccList, subject, body, "text/html", mailTitle);
		List<AttachmentInfo> attachments = new ArrayList<>();
		AttachmentInfo attachment1 = new AttachmentInfo();
		StringBuffer buff = new StringBuffer();
		buff.append("Region"+delim+"Circle"+delim+"Area"+"\n");
		buff.append("ABC"+delim+"BCD"+delim+"DEF"+"\n");
		buff.append("GHI"+delim+"HIJ"+delim+"IJK"+"\n");
		String csv = buff.toString();
		attachment1.setAttachmentFileContent(csv.getBytes());
		attachment1.setAttachmentMimeType("text/csv");
		attachment1.setFileNameForAttachment("testDoc.csv");
		
		AttachmentInfo attachment2 = new AttachmentInfo();
		attachment2.setFilePath("/Users/b0096703/Desktop/permission_req.txt");
		attachment2.setFileNameForAttachment("testDoc.txt");
		
		attachments.add(attachment1);
		attachments.add(attachment2);
		
//		EmailMessage message = new EmailMessage(sender, toList, ccList, bccList, subject, body, "text/html",filePath,fileNameForAttachment, mailTitle);
		
		String fileNameForAttachment = "testDoc.csv";
		
		EmailMessage message = new EmailMessage(sender, 
				toList,
				ccList, 
				bccList, 
				subject, 
				body, 
				"text/html", 
				attachments, mailTitle);
		boolean emailSentStatus = utils.sendEmail(message);
		Assert.assertEquals(emailSentStatus, true);
		System.out.println("ending the process...");
	}
}
