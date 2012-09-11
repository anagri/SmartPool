package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.common.Constants;
import smartpool.domain.Mail;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service
public class MailService {

    private Properties appProperties;
    private Mail mail;

    @Autowired
    public MailService(Properties appProperties) {
        this.appProperties = appProperties;
        this.mail = new Mail(new MimeMessage(getSession()));
    }

    public MailService(Properties appProperties, Mail mail) {
        this.appProperties = appProperties;
        this.mail = mail;
    }

    public void sendMailToList(List<String> emailList, String subject, String messageBody) {
        for(String emailTo : emailList) {
            try {
                Transport.send(mail.compose(emailTo, subject, messageBody));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(appProperties.getProperty(Constants.MAIL_USER), appProperties.getProperty(Constants.MAIL_PASSWORD));
                    }
                });
    }
}
