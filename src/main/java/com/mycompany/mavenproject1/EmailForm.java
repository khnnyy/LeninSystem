package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailForm {

    
    private static JOVar mail = new JOVar();
    
    public static void readEmailConfig(){
        try {
      File myObj = new File("emailConfig.jit");
      Scanner myReader = new Scanner(myObj);
 
        String email = myReader.nextLine();
        mail.setEmail(email);
        System.out.println(email);
        
        String password = myReader.nextLine();
        mail.setPassword(password);
        System.out.println(password);

        
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
    public static JOVar getConfig(){
        return mail;
    }

    public static void sendEmail(String to, String from, String host, String subject, String body, String fileName) {
        try{
        readEmailConfig();
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587"); // Use the appropriate port for TLS/STARTTLS

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // Replace with your email and password
//                return new PasswordAuthentication("notmycandy56@gmail.com", "nzgx yqhz gigr amjh");
                return new PasswordAuthentication(mail.getEmail(), mail.getPassword());

            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the file if fileName is not null
            if (fileName != null) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(fileName);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
            }

            // Set the content of the message to the multipart message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    } catch(Exception e) {
            System.out.println(e);
    }
}
}
