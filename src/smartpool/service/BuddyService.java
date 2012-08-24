package smartpool.service;

import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;

public class BuddyService {

    public Buddy getBuddy(String username) {
        Buddy buddy=new BuddyDao().selectBuddy(username);
        return buddy;
    }
}
