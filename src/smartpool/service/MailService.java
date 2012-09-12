package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.Mail;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
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
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "mother.thoughtworks.com");
        return Session.getDefaultInstance(props);
    }
}
