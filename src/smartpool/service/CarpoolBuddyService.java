package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.persistence.dao.CarpoolBuddyDao;

@Service
public class CarpoolBuddyService {
    private CarpoolBuddyDao carpoolBuddyDao;

    @Autowired
    public CarpoolBuddyService(CarpoolBuddyDao carpoolBuddyDao) {

        this.carpoolBuddyDao = carpoolBuddyDao;
    }

    public void delete(String carpoolName, String buddyUserName) {
        carpoolBuddyDao.remove(buddyUserName,carpoolName);
    }
}
