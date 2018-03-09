package com.giamdip.core;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.giamdip.model.MailSenderInput;

public class MailSender {

	@Autowired
	private String from;

	@Autowired
	private JavaMailSender mailSenderConfigurationManager;

	@Autowired
	private VelocityEngine velocityEngine;

	public void sendEmail(MailSenderInput input) {
		printInfo(input);
		try {
			mailSenderConfigurationManager.send(getPreparator(input));
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	private void printInfo(MailSenderInput input) {
		System.out.println("Sending eMail From: " + from);
		System.out.print("Sending eMail To: ");
		for (String to : input.getRecipients()) {
			System.out.print(to + "; ");
		}
		System.out.println();
	}

	private MimeMessagePreparator getPreparator(final MailSenderInput mailSenderInput) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mess = new MimeMessageHelper(mimeMessage);
				mess.setTo(mailSenderInput.getRecipients().toArray(new String[mailSenderInput.getRecipients().size()]));
				mess.setSubject(mailSenderInput.getSubject());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("attributes", mailSenderInput.getAttributes());
				mess.setText(buildTemplate(model, mailSenderInput.getTemplate()), true);
				mess.setFrom(from);
			}
		};
		return preparator;
	}

	private String buildTemplate(Map<String, Object> model, String template) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setMailSenderConfigurationManager(JavaMailSender mailSenderConfigurationManager) {
		this.mailSenderConfigurationManager = mailSenderConfigurationManager;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}
