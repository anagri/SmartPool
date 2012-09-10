package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.common.Mail;
import smartpool.persistence.dao.CarpoolBuddyDao;

import java.util.ArrayList;

@Service
public class MailService {

    CarpoolBuddyDao carpoolBuddyDao;

    @Autowired
    public MailService(CarpoolBuddyDao carpoolBuddyDao) {
        this.carpoolBuddyDao=carpoolBuddyDao;
    }

    public MailService() {
        this(new CarpoolBuddyDao());
    }

    public boolean sendMailToList(ArrayList<String> buddyEmailList, String subject, String message) {
        Mail mail = new Mail();
        for(String email : buddyEmailList){
            mail.sendMail(email, subject, message);
        }
        return true;
    }
}
