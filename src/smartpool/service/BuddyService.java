package smartpool.service;

import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;

@Service
public class BuddyService {

    public Buddy getBuddy(String username) {
        Buddy buddy=new BuddyDao().selectBuddy(username);
        return buddy;
    }

//    public String getUserNameFromCAS(){
//       // String ident = (String) request.getSession().getAttribute(CASFilter.CAS_FILTER_USER);
//        return request.getRemoteUser();
//    }

    public String getUserName(){
        return "1";
    }

    public Object findBuddyProfile(int id) {

        return null;
    }
}
