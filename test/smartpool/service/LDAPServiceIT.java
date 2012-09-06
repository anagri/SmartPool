package smartpool.service;


import org.junit.Before;
import org.junit.Test;
import smartpool.domain.LDAPResultSet;

import static org.junit.Assert.assertThat;
import static smartpool.util.matchers.ReflectionMatcher.reflectionEquals;

public class LDAPServiceIT {

    private LDAPService ldapService;

    @Before
    public void setUp() throws Exception {
        ldapService = new LDAPService();
    }

    @Test
    public void shouldReturnNameAndEmailFromLDAP() {
        LDAPResultSet result = ldapService.searchByUserName("mzhao");
        assertThat(result, reflectionEquals(new LDAPResultSet("Ming Zhao", "mzhao@thoughtworks.com")));
    }

    @Test
    public void shouldReturnResultForTestUser() throws Exception {
        LDAPResultSet result = ldapService.searchByUserName("test.twu");
        assertThat(result, reflectionEquals(new LDAPResultSet("Test User", "test@thoughtworks.com")));
    }

    @Test(expected = LDAPUserNotFoundException.class)
    public void shouldThrowExceptionIfUserNotFoundOnLDAP(){
        LDAPResultSet result =ldapService.searchByUserName("ghostuser");
    }
}
