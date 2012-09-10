package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;
import smartpool.domain.CarpoolBuddy;
import smartpool.domain.JoinRequest;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.JoinRequestDao;

import java.util.ArrayList;

@Service
public class JoinRequestService {
    private final JoinRequestDao joinrequestDao;
    private final CarpoolBuddyDao carpoolBuddyDao;
    private final MailService mailService;

    @Autowired
    public JoinRequestService(JoinRequestDao joinrequestDao, CarpoolBuddyDao carpoolBuddyDao, MailService mailService) {
        this.joinrequestDao = joinrequestDao;
        this.carpoolBuddyDao = carpoolBuddyDao;
        this.mailService = mailService;
    }

    public JoinRequestService() {
        this(new JoinRequestDao(),new CarpoolBuddyDao(),new MailService());
    }

    public void sendJoinRequest(JoinRequest joinRequest) {
        joinrequestDao.sendJoinRequest(joinRequest);
        sendEmailToList(joinRequest.getCarpoolName());
    }

    public boolean isRequestSent(Buddy buddy, String carpoolName) {
        return joinrequestDao.isRequestSent(buddy, carpoolName);
    }

    public ArrayList<String> getCarpoolBuddies(String carpoolName) {
        ArrayList<CarpoolBuddy> carpoolBuddyList  = carpoolBuddyDao.getCarpoolBuddiesByCarpoolName(carpoolName);
        ArrayList<String> buddyEmailList = new ArrayList<String>();
        for(CarpoolBuddy carpoolBuddy : carpoolBuddyList){
            buddyEmailList.add(carpoolBuddy.getBuddy().getEmailId());
        }
        return buddyEmailList;
    }

    public boolean sendEmailToList(String carpoolName) {
        ArrayList<String> buddyEmailList = getCarpoolBuddies(carpoolName);
        return mailService.sendMailToList(buddyEmailList, "", "");
    }
}
