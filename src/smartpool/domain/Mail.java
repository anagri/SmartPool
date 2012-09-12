package smartpool.domain;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public class Mail {

    private Message message;

    public Mail(Message message) {
        this.message = message;
    }

    public Message compose(String to, String subject, String messageBody) throws MessagingException {
        message.setFrom(new InternetAddress("SmartPool"));
        InternetAddress recipientAddress = new InternetAddress(to);
        recipientAddress.validate();
        message.setRecipient(Message.RecipientType.TO, recipientAddress);
        message.setSubject(subject);
        message.setContent(messageBody,"text/html");
        return message;
    }
}