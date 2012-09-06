package smartpool.service;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class BuddyService {
    @Autowired
    private BuddyDao buddyDao;

    public BuddyService() {
    }

    public BuddyService(BuddyDao buddyDao) {
        this.buddyDao = buddyDao;
    }

    public Buddy getBuddy(String username) {
        return buddyDao.selectBuddy(username);
    }

    public String getUserNameFromCAS(HttpServletRequest request) {
        return extractUsernameFromRequest(request);
    }

    public static String extractUsernameFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(CASFilter.CAS_FILTER_USER);
    }

    public Buddy getCurrentBuddy(HttpServletRequest request) {
        return this.getBuddy(this.getUserNameFromCAS(request));
    }

    public boolean exists(String username) {
        return buddyDao.exists(username);
    }
}