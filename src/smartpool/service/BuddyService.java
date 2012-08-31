package smartpool.service;

import edu.yale.its.tp.cas.client.filter.CASFilter;
import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;
import smartpool.persistence.dao.BuddyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class BuddyService {

    public Buddy getBuddy(String username) {
        return new BuddyDao().selectBuddy(username);
    }

    public String getUserNameFromCAS(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(CASFilter.CAS_FILTER_USER);
    }

    public Buddy getCurrentBuddy(HttpServletRequest request) {
        return this.getBuddy(this.getUserNameFromCAS(request));
    }
}
