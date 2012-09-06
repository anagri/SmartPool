package smartpool.service;

public class LDAPUserNotFoundException extends RuntimeException {
    public LDAPUserNotFoundException(String username) {
        super(String.format("%s user not found on LDAP server", username));
    }
}
