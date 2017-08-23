package com.airtel.prod.engg.email;

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
	
	@Test
	public void testEmail() throws Exception{
		System.out.println("starting the process...");
		String subject = "Mike testing 123...";
		String body = "Hi building the email utility please help in testing and use this utility with attachment";
		//simple sending the text message
//		EmailMessage message = new EmailMessage(sender, toList, ccList, bccList, subject, body, "text/html", mailTitle);
		
		String filePath = "/Users/b0096703/Desktop/output.txt";
		String fileNameForAttachment = "testDoc.txt";
		EmailMessage message = new EmailMessage(sender, toList, ccList, bccList, subject, body, "text/html",filePath,fileNameForAttachment, mailTitle);
		boolean emailSentStatus = utils.sendEmail(message, true);
		Assert.assertEquals(emailSentStatus, true);
		System.out.println("ending the process...");
	}
}
