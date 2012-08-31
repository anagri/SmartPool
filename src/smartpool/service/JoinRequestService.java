package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.JoinRequest;
import smartpool.persistence.dao.JoinRequestDao;

@Service
public class JoinRequestService {
    private final JoinRequestDao joinrequestDao;

    @Autowired
    public JoinRequestService(JoinRequestDao joinrequestDao) {
        this.joinrequestDao = joinrequestDao;
    }

    public void sendJoinRequest(JoinRequest joinRequest) {
        joinrequestDao.sendJoinRequest(joinRequest);
    }
}
