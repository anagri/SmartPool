package smartpool.functional;


import org.junit.Test;
import smartpool.functional.page.LoginPage;
import smartpool.functional.page.ViewProfilePage;

import static junit.framework.Assert.assertEquals;

public class ViewProfileTest extends BaseTest {

    public final String OWN_USER_NAME="Test User";
    public final String OWN_USER_EMAIL="test@thoughtworks.com";
    public final String OWN_USER_PHONE_NO="1234567890";

    public final String OTHER_USER_NAME="Ayush Tulsyan";
    public final String OTHER_USER_EMAIL="ayusht@thoughtworks.com";
    public final String OTHER_USER_PHONE_NO="1234567890";

    public final String EDIT_BUTTON_TEXT="Edit";
    public final String CONTACT_BUTTON_TEXT="Contact";

    public ViewProfilePage viewProfilePage;

    @Test
    public void viewMyProfileAndItsDetails() {
        viewProfilePage = new LoginPage(webDriver).login().goToMyProfilePage();
        assertEquals(OWN_USER_NAME, viewProfilePage.getProfileName());
        assertEquals(OWN_USER_PHONE_NO, viewProfilePage.getProfilePhoneNumber());
        assertEquals(OWN_USER_EMAIL, viewProfilePage.getProfileEmail());
    }

    @Test
    public void viewBuddyProfileAndItsDetails() {
        viewProfilePage = new LoginPage(webDriver).login().goToListCarpoolsPage().gotoViewCarpoolDetails().goToBuddyProfilePage();
        assertEquals(OTHER_USER_NAME, viewProfilePage.getProfileName());
        assertEquals(OTHER_USER_PHONE_NO, viewProfilePage.getProfilePhoneNumber());
        assertEquals(OTHER_USER_EMAIL, viewProfilePage.getProfileEmail());
    }

    @Test
    public void shouldDisplayEditButtonWhenViewingMyProfile() {
        viewProfilePage = new LoginPage(webDriver).login().goToMyProfilePage();
        assertEquals(EDIT_BUTTON_TEXT, viewProfilePage.getProfileButton());
    }

    @Test
    public void shouldDisplayContactButtonWhenViewingBuddyProfile() {
        viewProfilePage = new LoginPage(webDriver).login().goToListCarpoolsPage().gotoViewCarpoolDetails().goToBuddyProfilePage();
        assertEquals(CONTACT_BUTTON_TEXT, viewProfilePage.getProfileButton());
    }
}

