package smartpool.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import smartpool.common.Constants;
import smartpool.domain.LDAPResultSet;

import java.util.Properties;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static smartpool.util.matchers.ReflectionMatcher.reflectionEquals;

public class LDAPServiceIT {

    private LDAPService ldapService;
    @Mock
    private Properties appProperties;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        ldapService = new LDAPService(appProperties);
        when(appProperties.getProperty(Constants.LDAP_URL)).thenReturn("ldap://ldap.thoughtworks.com:389/dc=Corporate,dc=ThoughtWorks,dc=COM");
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
        ldapService.searchByUserName("ghostuser");
    }
}
