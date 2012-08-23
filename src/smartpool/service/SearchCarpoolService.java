package smartpool.service;

import org.springframework.stereotype.Service;
import smartpool.domain.Carpool;

@Service
public class SearchCarpoolService {
    public Carpool searchCarpoolsBy(String pickupPoint) {
        if ("domlur".equals(pickupPoint)){
            return CarpoolBuilder.CARPOOL_2;
        }
        return null;
    }
}