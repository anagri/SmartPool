package smartpool.common;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");

    public static final String HOST_NAME = "hostName";
    public static final String PORT_NUMBER = "port";
    public static final String CAS_SERVER_URL = "casServerUrl";
}
