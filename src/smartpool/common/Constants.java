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

    public static final String NEW_BUDDY_NOTIFICATION_SUBJECT = "SmartPool New Buddy Join Request";
    public static final String NEW_BUDDY_NOTIFICATION_MESSAGE = "Smartpool Notification Date  :  %s<br /><br />" +
                                                                "<b>%s</b> has requested to join <b>%s</b><br /><br />"+
                                                                "<u>Details</u><br /><br />"+
                                                                "%s<br /><br />"+
                                                                "Approve : Click on this link %s<br /><br />"+
                                                                "Disapprove : Click on this link %s<br /><br />"+
                                                                "----------------------------------------<br /><br />"+
                                                                "*This is a system generated email. Please DO NOT reply.";
    public static final String MSG_403 = "Only Admin can access this page.";

    public static final String Approve_Link = "carpool/approve/%s";
    public static final String Disapprove_Link = "carpool/disapprove/%s";
}
