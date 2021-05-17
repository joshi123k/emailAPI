package com.email.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String message,String subject,String to)
    {
        boolean f=false;

        String from="learnCodeWithKD@gmail.com";

        String host="smtp.gmail.com";

        Properties properties = System.getProperties();

        // set host

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1:get Session object
        Session session = Session.getInstance(properties,
                new Authenticator() {

                    @Override protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("learnCodeWithKD@gmail.com", "learn123@");
                    }



                });

        session.setDebug(true);

        // Step 2: compose Message[text,multi media]

        MimeMessage mimeMessage = new MimeMessage(session);

        try {

            mimeMessage.setFrom(from);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            mimeMessage.setSubject(subject);

            mimeMessage.setText(message);

         

            // Send the message

            Transport.send(mimeMessage);


            System.out.println("Mail Send Successly");

            f=true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return f;
    }

}
