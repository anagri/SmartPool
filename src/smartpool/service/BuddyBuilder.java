package smartpool.service;

import smartpool.domain.Buddy;

public class BuddyBuilder extends Buddy {
    public static Buddy buddy_1 = new Buddy("1", "Scott", "N/A", "N/A", "N/A");
    public static Buddy buddy_2 = new Buddy("2", "Amir", "N/A", "N/A", "N/A");

    public static Buddy getBuddy(String userName) {
        if(BuddyBuilder.buddy_1.getUsername().equals(userName))
            return BuddyBuilder.buddy_1;
        else if(BuddyBuilder.buddy_2.getUsername().equals(userName))
            return BuddyBuilder.buddy_2;
        return null;
    }
}
