package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.common.Constants;
import smartpool.domain.Buddy;
import smartpool.domain.CarpoolBuddy;
import smartpool.domain.JoinRequest;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.JoinRequestDao;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JoinRequestService {
    private final JoinRequestDao joinRequestDao;
    private final CarpoolBuddyDao carpoolBuddyDao;
    private final MailService mailService;
    private final Properties appProperties;

    @Autowired
    public JoinRequestService(JoinRequestDao joinRequestDao, CarpoolBuddyDao carpoolBuddyDao, MailService mailService,Properties appProperties) {
        this.joinRequestDao = joinRequestDao;
        this.carpoolBuddyDao = carpoolBuddyDao;
        this.mailService = mailService;
        this.appProperties = appProperties;
    }

    public void sendJoinRequest(JoinRequest joinRequest, Buddy buddy,UUID uuid) {
        joinRequestDao.sendJoinRequest(joinRequest);
        joinRequestDao.addUniqueIdToPendingRequest(buddy.getUserName(), joinRequest.getCarpoolName(), uuid);
        sendEmailToList(joinRequest, buddy);
    }

    public boolean isRequestSent(Buddy buddy, String carpoolName) {
        return joinRequestDao.isRequestSent(buddy, carpoolName);
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
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        String smartPoolUrl = "http://"+appProperties.getProperty("hostName")+":"+appProperties.getProperty("port")+"/"+appProperties.getProperty("applicationName")+"/";
        String approveLink= smartPoolUrl+String.format(Constants.Approve_Link,joinRequestDao.getUniqueIdFromPendingRequest(buddy.getUserName(),joinRequest.getCarpoolName()));
        String disApproveLink= smartPoolUrl+String.format(Constants.Disapprove_Link,joinRequestDao.getUniqueIdFromPendingRequest(buddy.getUserName(),joinRequest.getCarpoolName()));

        String message = String.format(Constants.NEW_BUDDY_NOTIFICATION_MESSAGE, date, buddy.getName(), joinRequest.getCarpoolName(),joinRequest,approveLink,disApproveLink);
        mailService.sendMailToList(buddyEmailList, Constants.NEW_BUDDY_NOTIFICATION_SUBJECT, message);
    }

    public void deletePendingRequest(String uid) {
        joinRequestDao.deletePendingRequest(uid);
    }

    public String getBuddyUserNameFromUid(String uid) {
        return joinRequestDao.getBuddyUserNameFromUid(uid);
    }

    public String getCarpoolNameFromUid(String uid) {
        return joinRequestDao.getCarpoolNameFromUid(uid);
    }

    public JoinRequest getJoinRequestByUserNameAndCarpoolName(String userName, String carpoolName) {
        return joinRequestDao.selectUsersRequest(userName,carpoolName);
    }

    public boolean isUidPresent(String uid) {
        return joinRequestDao.isUidPresent(uid);
    }
}
