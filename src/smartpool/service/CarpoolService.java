
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

    public Carpool findCarpoolByName(String name)
    {
        CarpoolDao carpoolDao = new CarpoolDao();
        Carpool carpool = carpoolDao.get(name);
        return carpool;
    }

    public List<String> findCarpoolByLocation(String location) {
        CarpoolDao carpoolDao=new CarpoolDao();
        List<String> carpools = carpoolDao.selectCarpoolByLocation(location);
        return carpools;
    }

    public List<Carpool> findAllCarpoolsByLocation(String location)
    {   List<Carpool> carpools = new ArrayList<Carpool>();
        if(location.equals(""))
            carpools = getAllCarpools();
        else
        {
        List<String> carpoolNames= findCarpoolByLocation(location.trim());
         if(!carpoolNames.isEmpty())
            for(String name:carpoolNames)
            {
                carpools.add(findCarpoolByName(name));
            }
        }
        return carpools;
    }

    public void insert(Carpool carpool) {
        carpoolDao.insert(carpool);
    }

    private List<Carpool> getAllCarpools() {

        CarpoolDao carpoolDao = new CarpoolDao();
        List<Carpool> carpools = carpoolDao.selectAllCarpools();
        return carpools;

    }

}
