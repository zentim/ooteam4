package main.java.model.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 
 * For sending email
 *
 */
public class Mailer   {
	// Recipient email
	MailUtil m = new MailUtil();
	final String mailFrom = m.getMail();
	final String code = m.getCode();
	String mailSubject = "Email from Java";
	String user="";
	String content= "<br>By OOteam4 Shop";
	String subject="OOteam4 Shop Notification";
	
	Mailer(String u){
	
		user = u;
		
	}
	
    
    public void sendMail(String recipient,int pid,String productName) {
    	
    	try {
    		
    		Properties props = new Properties();
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.smtp.starttls.enable", "true");
    		props.put("mail.smtp.host", "smtp.gmail.com");
    		props.put("mail.smtp.port", "587");

    		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    		    @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                	return new PasswordAuthentication(mailFrom, code);
                }
    		});
   		
    		Message emailMessage = new MimeMessage(session);
    		Address recipientA = new InternetAddress(recipient);
    		
    		emailMessage.setRecipient(Message.RecipientType.CC,recipientA);
    		emailMessage.setFrom(new InternetAddress(mailFrom));
    		emailMessage.setSubject(subject);
    		content = "Hello " + user + "!!<br>"
    		        + "<a href='http://localhost:8080/ooteam4/foreproduct?pid="+ pid + "'>" 
    		        + productName 
    		        + "</a>" 
    		        + " is available now you can buy it. " + content;
    		emailMessage.setContent(content, "text/html; charset=utf-8");
    		Transport.send(emailMessage);
    		System.out.println("Mail successful!");
    	} catch (AddressException e) {
    		e.printStackTrace();
    	} catch (MessagingException e) {
    		e.printStackTrace();
    	}
        
    }
 

}
