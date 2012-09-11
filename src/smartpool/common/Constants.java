package smartpool.common;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm");

    public static final String HOST_NAME = "hostName";
    public static final String PORT_NUMBER = "port";
    public static final String CAS_SERVER_URL = "casServerUrl";
    public static final String MAIL_USER = "mailUser";
    public static final String MAIL_PASSWORD = "mailPassword";
    public static final String LDAP_URL = "ldapURL";

    public static final String FIELD_REQUIRED = "field.required";
    public static final String FIELD_INVALID = "field.invalid";

    public static final String NEW_BUDDY_NOTIFICATION_SUBJECT = "New Buddy Request To Join Your Carpool";
    public static final String NEW_BUDDY_NOTIFICATION_MESSAGE = "Hi\nA new buddy wants to join your carpool. Approve/Reject his request\n" +
                                                                "Below is the buddy details:\n" +
                                                                "Buddy Name  :%s%s";
}
