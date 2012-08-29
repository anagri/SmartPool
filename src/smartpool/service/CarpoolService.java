package smartpool.service;

import org.springframework.stereotype.Service;
import smartpool.domain.Carpool;
import smartpool.persistence.dao.CarpoolDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarpoolService {
    private final CarpoolDao carpoolDao;

    public CarpoolService(){
        carpoolDao = new CarpoolDao();
    }

    public CarpoolService(CarpoolDao carpoolDao) {

        this.carpoolDao = carpoolDao;
    }

    public Carpool getByName(String name) {
        return carpoolDao.get(name);
    }

    public List<Carpool> findCarpoolByLocation(String location) {
        List<Carpool> result= new ArrayList<Carpool>();
        if(location.equals("Diamond District")){
            result.add(CarpoolBuilder.CARPOOL_1);
        }
        if(location.equals("")){
            result.add(CarpoolBuilder.CARPOOL_1);
            result.add(CarpoolBuilder.CARPOOL_2);
        }
        return result;
    }

    public void insert(Carpool carpool) {
        carpoolDao.insert(carpool);
    }

    public static class JoinCarPoolService {
    }
}
