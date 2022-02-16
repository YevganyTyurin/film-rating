package com.epam.film.rating.service.util;

import com.epam.film.rating.service.exception.ServiceException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendingEmail {
    private String userEmail;

    public SendingEmail (String userEmail) {
        this.userEmail = userEmail;
    }

    public void sendMail() throws ServiceException {
        String email = "makeReviewsNotSpam@gmail.com";
        String password = "mR123456";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Verification on film-rating");
            message.setText("Your verification link :: " + "http://localhost:8080/film_rating/Controller?command=activateAccount&email=" + userEmail);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServiceException(e);
        }
    }
}
