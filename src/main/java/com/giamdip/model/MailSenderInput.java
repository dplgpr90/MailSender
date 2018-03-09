package com.giamdip.model;

import java.io.Serializable;
import java.util.List;

public class MailSenderInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> recipients;

	private String subject;

	private CSMAttributeToPrint attributes;

	private String template;

	public CSMAttributeToPrint getAttributes() {
		return attributes;
	}

	public void setAttributes(CSMAttributeToPrint attributes) {
		this.attributes = attributes;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
