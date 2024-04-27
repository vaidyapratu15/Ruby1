package com.play.testlibrary;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.testng.annotations.Test;

/*
 * @Author: Pratiksha Vaidya.
 * @Since : December 2022
 * @Discription : This class contain setup for attach Test-report with email. 
 */
public class MailTestClass {

	@Test
	public static void setAttachEmailReport() throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("Extent Report.html");
	//	attachment.setPath("");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Test Report ");
		attachment.setName("Test.html"); 

		MultiPartEmail email = new MultiPartEmail();
		System.out.println("mail sending...");
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587); // 465 //25
		email.setAuthenticator(new DefaultAuthenticator("qapwohlig2022@gmail.com", "pzivzradvfylqaxp"));
		email.setSSLOnConnect(true); 
		email.setFrom("qapwohlig2022@gmail.com");
		email.setSubject("PratikshaQA - PlayExch Report");
		email.setMsg("Please Find The Attached Report File ");
		email.addTo("pratiksha.vaidya@wohlig.com");	 
//      email.addTo("wilfred.william@wohlig.com ");    
//		email.addTo("dhananjay.sonar@wohlig.com");
		//email.addTo("tejal.gavade@wohlig.com"); //email.addTo("darshan.satardekar@wohlig.com");
	//	email.addCc("idris.galiyara@wohlig.com");  //	email.addCc("danish@wohlig.com");
		email.attach(attachment);
		email.send();
		System.out.println("Mail sent");

	}

}













//wilfred.william@wohlig.com   pratiksha.vaidya@wohlig.com   danish@wohlig.com
/*
 * Email email = new SimpleEmail(); email.setHostName("smtp.gmail.com");
 * email.setSmtpPort(465); email.setAuthenticator(new
 * DefaultAuthenticator("qapwohlig2022@gmail.com", "pzivzradvfylqaxp"));
 * email.setSSLOnConnect(true); email.setFrom("qapwohlig2022@gmail.com");
 * email.setSubject("TestMail"); email.setMsg("This is a test mail ... :-)");
 * email.addTo("vaidya.pratu15@gmail.com"); email.send();
 */