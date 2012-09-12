package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.domain.Buddy;
import smartpool.domain.Carpool;
import smartpool.domain.CarpoolBuddy;
import smartpool.persistence.dao.BuddyDao;
import smartpool.persistence.dao.CarpoolBuddyDao;
import smartpool.persistence.dao.CarpoolDao;
import smartpool.persistence.dao.RouteDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class CarpoolService {
    private final CarpoolDao carpoolDao;
    private final BuddyDao buddyDao;
    private final RouteDao routeDao;
    private final CarpoolBuddyDao carpoolBuddyDao;

    @Autowired
    public CarpoolService(CarpoolDao carpoolDao, BuddyDao buddyDao, RouteDao routeDao, CarpoolBuddyDao carpoolBuddyDao) {
        this.carpoolDao = carpoolDao;
        this.buddyDao = buddyDao;
        this.routeDao = routeDao;
        this.carpoolBuddyDao = carpoolBuddyDao;
    }

    public Carpool getByName(String carpoolName) {
        Carpool carpool = carpoolDao.get(carpoolName);
        if (carpool == null) return carpool;

        ArrayList<CarpoolBuddy> carpoolBuddies = getCarpoolBuddyListByName(carpool.getName());
        carpool.setCarpoolBuddies(carpoolBuddies);

        carpool.setRoutePoints(routeDao.getLocationsFor(carpool.getName()));
        return carpool;
    }

    private ArrayList<CarpoolBuddy> getCarpoolBuddyListByName(String name) {
        ArrayList<CarpoolBuddy> carpoolBuddies = carpoolBuddyDao.getCarpoolBuddiesByCarpoolName(name);
        return carpoolBuddies;
    }

    public Carpool findCarpoolByName(String name) {
        Carpool carpool = carpoolDao.get(name);
        carpool.setCarpoolBuddies(getCarpoolBuddyListByName(name));
        return carpool;
    }

    public List<Carpool> findAllCarpoolsByLocation(String location) {
        List<Carpool> carpools = new ArrayList<Carpool>();
        if (isBlank(location)) {
            carpools = getAllCarpools();
        }
        else {
            List<String> carpoolNames = routeDao.getCarpoolNameListByLocation(location.trim());
            for (String name : carpoolNames) {
                carpools.add(findCarpoolByName(name));
            }
        }
        Collections.sort(carpools);
        return carpools;
    }

    public void insert(Carpool carpool) {
        carpoolDao.insert(carpool);
        for (CarpoolBuddy carpoolBuddy : carpool.getCarpoolBuddies()) {
            if (carpoolBuddy == null) continue;
            carpoolBuddyDao.insert(carpoolBuddy,carpool);
        }
        ArrayList<String> routePoints = carpool.getRoutePoints();
        int sequenceNumber = 0;
        for (String routePoint : routePoints) {
            sequenceNumber++;
            routeDao.insert(carpool.getName(), routePoint.trim(), sequenceNumber);
        }
    }

    private List<Carpool> getAllCarpools() {
        List<Carpool> carpools = carpoolDao.selectAllCarpools();

        for (Carpool carpool : carpools) {
            carpool.setCarpoolBuddies(getCarpoolBuddyListByName(carpool.getName()));
        }
        return carpools;
    }

    public boolean hasBuddy(String username, Carpool carpool) {
        for (CarpoolBuddy carpoolBuddy : carpool.getCarpoolBuddies()) {
            Buddy buddy = carpoolBuddy == null ? null : carpoolBuddy.getBuddy();
            String userName = buddy == null ? "" : buddy.getUserName();
            if (userName.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void drop(Carpool carpool,CarpoolBuddy buddy) {
            if(hasBuddy(buddy.getBuddy().getUserName(),carpool))
            carpoolBuddyDao.remove(buddy.getBuddy().getUserName(),carpool.getName());
    }

    public boolean canUserSendRequest(String username, Carpool carpool) {
        return !hasBuddy(username, carpool) && carpool.hasVacancy();
    }

    public boolean canUserSendRequest(String username, String carpool) {
        return canUserSendRequest(username, getByName(carpool));
    }

    public boolean isValidCarpool(String carpoolName) {
        return getByName(carpoolName) != null;
    }
}
