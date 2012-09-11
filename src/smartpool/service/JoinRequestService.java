package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.common.Constants;
import smartpool.domain.Buddy;
import smartpool.domain.CarpoolBuddy;
import smartpool.domain.JoinRequest;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.JoinRequestDao;

import java.util.ArrayList;
import java.util.List;

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

    public void sendJoinRequest(JoinRequest joinRequest, Buddy buddy) {
        joinrequestDao.sendJoinRequest(joinRequest);
        sendEmailToList(joinRequest,buddy);
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

    public void sendEmailToList(JoinRequest joinRequest, Buddy buddy) {
        List<String> buddyEmailList = getCarpoolBuddies(joinRequest.getCarpoolName());
        String message = String.format(Constants.NEW_BUDDY_NOTIFICATION_MESSAGE, buddy.getName(), joinRequest);
        mailService.sendMailToList(buddyEmailList, Constants.NEW_BUDDY_NOTIFICATION_SUBJECT, message);
    }
}
