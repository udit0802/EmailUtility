package com.airtel.prod.engg.email;

import java.util.ArrayList;
import java.util.List;

public class EmailMessage {

	private String mailSender;
	
	private List<String> toEmail;
	
	private List<String> ccEmail;
	
	private List<String> bccEmail;
	
	private String subject;
	
	private String body;
	
	private String bodyContentType;
	
	private String filePath;
	
	private byte[] attachmentFileContent;
	
	private String attachmentMimeType;
	
	private String fileNameForAttachment;
	
	private String mailTitle;
	
	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}

	public void setToEmail(List<String> toEmail) {
		this.toEmail = toEmail;
	}

	public void setCcEmail(List<String> ccEmail) {
		this.ccEmail = ccEmail;
	}

	public void setBccEmail(List<String> bccEmail) {
		this.bccEmail = bccEmail;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setBodyContentType(String bodyContentType) {
		this.bodyContentType = bodyContentType;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileNameForAttachment(String fileNameForAttachment) {
		this.fileNameForAttachment = fileNameForAttachment;
	}

	public EmailMessage(String mailSender, 
						List<String> toEmail,
						List<String> ccEmail, 
						List<String> bccEmail, 
						String subject, 
						String body, 
						String bodyContentType) {
		this.mailSender = mailSender;

		this.toEmail = toEmail;
		EmailAddressValidator.validateEmailAddress(toEmail);
		
		this.ccEmail = setAddressList(ccEmail);
		this.bccEmail = setAddressList(bccEmail);
		this.subject = subject;
		this.body = body;
		this.bodyContentType = bodyContentType;
	}
	
	public EmailMessage(String mailSender, List<String> toEmail, List<String> ccEmail, List<String> bccEmail,
			String subject, String body, String bodyContentType, String mailTitle) {
		this.mailSender = mailSender;

		this.toEmail = toEmail;
		EmailAddressValidator.validateEmailAddress(toEmail);

		this.ccEmail = setAddressList(ccEmail);
		this.bccEmail = setAddressList(bccEmail);
		this.subject = subject;
		this.body = body;
		this.bodyContentType = bodyContentType;
		this.mailTitle = mailTitle;
	}
	
	public EmailMessage(String mailSender, 
			List<String> toEmail,
			List<String> ccEmail, 
			List<String> bccEmail, 
			String subject, 
			String body, 
			String bodyContentType, 
			String filePath,
			String fileNameForAttachment) {
		this.mailSender = mailSender;
		
		this.toEmail = toEmail;
		EmailAddressValidator.validateEmailAddress(toEmail);
		
		this.ccEmail = setAddressList(ccEmail);
		this.bccEmail = setAddressList(bccEmail);
		this.subject = subject;
		this.body = body;
		this.bodyContentType = bodyContentType;
		this.filePath = filePath;
		this.fileNameForAttachment = fileNameForAttachment;
	}
	
	public EmailMessage(String mailSender, 
			List<String> toEmail,
			List<String> ccEmail, 
			List<String> bccEmail, 
			String subject, 
			String body, 
			String bodyContentType, 
			String filePath,
			String fileNameForAttachment, String mailTitle) {
		this.mailSender = mailSender;
		
		this.toEmail = toEmail;
		EmailAddressValidator.validateEmailAddress(toEmail);
		
		this.ccEmail = setAddressList(ccEmail);
		this.bccEmail = setAddressList(bccEmail);
		this.subject = subject;
		this.body = body;
		this.bodyContentType = bodyContentType;
		this.filePath = filePath;
		this.fileNameForAttachment = fileNameForAttachment;
		this.mailTitle = mailTitle;
	}

	public EmailMessage(String mailSender, 
			List<String> toEmail,
			List<String> ccEmail, 
			List<String> bccEmail, 
			String subject, 
			String body, 
			String bodyContentType, 
			byte[] attachmentFileContent,
			String attachmentMimeType,
			String fileNameForAttachment, String mailTitle) {
		this.mailSender = mailSender;
		
		this.toEmail = toEmail;
		EmailAddressValidator.validateEmailAddress(toEmail);
		
		this.ccEmail = setAddressList(ccEmail);
		this.bccEmail = setAddressList(bccEmail);
		this.subject = subject;
		this.body = body;
		this.bodyContentType = bodyContentType;
		this.attachmentFileContent = attachmentFileContent;
		this.attachmentMimeType= attachmentMimeType;
		this.fileNameForAttachment = fileNameForAttachment;
		this.mailTitle = mailTitle;
	}

	private List<String> setAddressList(List<String> emailAddresses) {
		if(emailAddresses == null || emailAddresses.size() == 0) {
			emailAddresses = new ArrayList<String>();
		}else {
			EmailAddressValidator.validateEmailAddress(emailAddresses);
		}
		return emailAddresses;
	}

	public String getMailSender() {
		return mailSender;
	}

	public List<String> getToEmail() {
		return toEmail;
	}

	public List<String> getCcEmail() {
		return ccEmail;
	}

	public List<String> getBccEmail() {
		return bccEmail;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getBodyContentType() {
		return bodyContentType;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileNameForAttachment() {
		return fileNameForAttachment;
	}

	@Override
	public String toString() {
		return "EmailMessage [mailSender=" + mailSender + ", toEmail=" + toEmail + ", ccEmail=" + ccEmail
				+ ", bccEmail=" + bccEmail + ", subject=" + subject + ", body=" + body + ", bodyContentType="
				+ bodyContentType + ", filePath=" + filePath + ", fileNameForAttachment=" + fileNameForAttachment
				+ ", mailTitle=" + mailTitle + "]";
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public byte[] getAttachmentFileContent() {
		return attachmentFileContent;
	}

	public void setAttachmentFileContent(byte[] attachmentFileContent) {
		this.attachmentFileContent = attachmentFileContent;
	}

	public String getAttachmentMimeType() {
		return attachmentMimeType;
	}

	public void setAttachmentMimeType(String attachmentMimeType) {
		this.attachmentMimeType = attachmentMimeType;
	}
}
