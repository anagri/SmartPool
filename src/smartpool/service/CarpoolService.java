package smartpool.service;

import org.springframework.stereotype.Service;
import smartpool.domain.Carpool;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarpoolService {
    public Carpool findCarpoolBy(String name) {
        if (CarpoolBuilder.CARPOOL_1.getName().equals(name)) return CarpoolBuilder.CARPOOL_1;
        if(CarpoolBuilder.CARPOOL_2.getName().equals(name)) return CarpoolBuilder.CARPOOL_2;
        return null;
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

    public static class JoinCarPoolService {
    }
}
