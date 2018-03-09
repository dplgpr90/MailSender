package tester;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.giamdip.core.MailSender;
import com.giamdip.model.CSMAttributeToPrint;
import com.giamdip.model.MailSenderInput;

public class MailSenderTest {

	private MailSender mailSender;

	@Before
	public void setUp() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("email.xml");
		mailSender = applicationContext.getBean("mailSender", MailSender.class);
	}

	@Test
	public void test() {
		MailSenderInput input = new MailSenderInput();
		input.setRecipients(Arrays.asList("yourmail@mail.com"));// TODO define here recipients
		input.setSubject("Notification");
		input.setTemplate("velocity/template1.vm");
		CSMAttributeToPrint attributes = new CSMAttributeToPrint();
		attributes.setName("Test");
		attributes.setSurname("Test");
		input.setAttributes(attributes);
		mailSender.sendEmail(input);
	}

}
