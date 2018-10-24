package com.airtel.prod.engg.email;

import java.util.Arrays;

public class AttachmentInfo {

	private String filePath;
	
	private byte[] attachmentFileContent;
	
	private String attachmentMimeType;
	
	private String fileNameForAttachment;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getFileNameForAttachment() {
		return fileNameForAttachment;
	}

	public void setFileNameForAttachment(String fileNameForAttachment) {
		this.fileNameForAttachment = fileNameForAttachment;
	}

	@Override
	public String toString() {
		return "AttachmentInfo [filePath=" + filePath + ", attachmentFileContent="
				+ Arrays.toString(attachmentFileContent) + ", attachmentMimeType=" + attachmentMimeType
				+ ", fileNameForAttachment=" + fileNameForAttachment + "]";
	}
	
	
}
