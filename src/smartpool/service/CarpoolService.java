package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.BuddyDao;
import smartpool.persistence.dao.CarpoolDao;
import smartpool.persistence.dao.RouteDao;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class CarpoolService {
    private final CarpoolDao carpoolDao;
    private final BuddyDao buddyDao;
    private final RouteDao routeDao;

    @Autowired
    public CarpoolService(CarpoolDao carpoolDao, BuddyDao buddyDao, RouteDao routeDao) {
        this.carpoolDao = carpoolDao;
        this.buddyDao = buddyDao;
        this.routeDao = routeDao;
    }

    public Carpool getByName(String carpoolName) {
        Carpool carpool = carpoolDao.get(carpoolName);
        if (carpool == null) return  carpool;

        carpool.setBuddies(buddyDao.getBuddyListByCarpoolName(carpool.getName()));

        carpool.setRoutePoints(routeDao.getLocationsFor(carpool.getName()));
        return carpool;
    }

    public Carpool findCarpoolByName(String name) {
        Carpool carpool = carpoolDao.get(name);
        carpool.setBuddies(buddyDao.getBuddyListByCarpoolName(name));
        return carpool;
    }

    public List<Carpool> findAllCarpoolsByLocation(String location) {
        if (isBlank(location)) {
            return getAllCarpools();
        }

        List<String> carpoolNames = routeDao.getCarpoolNameListByLocation(location.trim());
        List<Carpool> carpools = new ArrayList<Carpool>();
        for (String name : carpoolNames) {
            carpools.add(findCarpoolByName(name));
        }
        return carpools;
    }

    public void insert(Carpool carpool) {
        carpoolDao.insert(carpool);
        for (Buddy buddy : carpool.getBuddies()) {
            if (buddy == null) continue;
            buddyDao.addToCarpool(buddy, carpool);
        }
        ArrayList<String> routePoints = carpool.getRoutePoints();
        int sequenceNumber=0;
        for (String routePoint : routePoints) {
            sequenceNumber++;
            routeDao.insert(carpool.getName(), routePoint.trim(),sequenceNumber);
        }
    }

    private List<Carpool> getAllCarpools() {
        List<Carpool> carpools = carpoolDao.selectAllCarpools();

        for (Carpool carpool : carpools) {
            carpool.setBuddies(buddyDao.getBuddyListByCarpoolName(carpool.getName()));
        }
        return carpools;
    }

    public boolean hasBuddy(String username, Carpool carpool) {
        ArrayList<Buddy> buddies = carpool.getBuddies();
        for (Buddy buddy : buddies) {
            if (buddy.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
