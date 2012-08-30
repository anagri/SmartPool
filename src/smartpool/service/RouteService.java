package smartpool.service;

import smartpool.persistence.dao.RouteDao;

import java.util.List;

public class RouteService {

    private RouteDao routeDao;

    public RouteService(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public List<String> getCarpoolNameList(String location) {
        return routeDao.getCarpoolNameListByLocation(location);
    }
}
