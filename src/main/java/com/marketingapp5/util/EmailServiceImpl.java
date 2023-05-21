package com.marketingapp5.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage m = new SimpleMailMessage();
		m.setTo(to);
		m.setSubject(subject);
		m.setText(message);
		
		javaMailSender.send(m);

	}

}
