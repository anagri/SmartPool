package smartpool.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimeService {
    private String time;

    public String getTime() {
        return (new Date()).toString();
    }

}
