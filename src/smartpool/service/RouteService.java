package smartpool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartpool.persistence.dao.RouteDao;

import java.util.List;

@Service
public class RouteService {

    private RouteDao routeDao;

    @Autowired
    public RouteService(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public List<String> getCarpoolNameList(String location) {
        return routeDao.getCarpoolNameListByLocation(location);
    }

    public List<String> getAllLocation() {
        return routeDao.getAllLocations();
    }

}
