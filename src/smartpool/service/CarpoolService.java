package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.BuddyDao;
import smartpool.persistence.dao.CarpoolDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarpoolService {
    private final CarpoolDao carpoolDao;
    private final BuddyDao buddyDao;
    private final RouteService routeService;

    @Autowired
    public CarpoolService(CarpoolDao carpoolDao, BuddyDao buddyDao, RouteService routeService) {
        this.carpoolDao = carpoolDao;
        this.buddyDao = buddyDao;
        this.routeService = routeService;
    }

    public Carpool getByName(String name) {
        return carpoolDao.get(name);
    }

    public Carpool findCarpoolByName(String name) {
        Carpool carpool = carpoolDao.get(name);
        carpool.setBuddies(buddyDao.getBuddyListByCarpoolName(name));
        return carpool;
    }

    public List<Carpool> findAllCarpoolsByLocation(String location) {
        location = location.trim();
        List<Carpool> carpools = new ArrayList<Carpool>();
        if (location.isEmpty()) {
            carpools = getAllCarpools();
        } else {
            List<String> carpoolNames = routeService.getCarpoolNameList(location);

            for (String name : carpoolNames) {
                carpools.add(findCarpoolByName(name));
            }
        }
        return carpools;
    }

    public void insert(Carpool carpool) {
        carpoolDao.insert(carpool);
    }

    private List<Carpool> getAllCarpools() {
        List<Carpool> carpools = carpoolDao.selectAllCarpools();
        return carpools;
    }

}
