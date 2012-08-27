package smartpool.service;

import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;

@Service
public class BuddyService {

    public Buddy getBuddy(String userName) {
        return new Buddy(userName);
    }

    public Buddy buildBuddy(String userName) {
          return BuddyBuilder.getBuddy(userName);
    }
}
