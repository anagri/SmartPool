package smartpool.service;

import org.springframework.stereotype.Component;
import smartpool.domain.LDAPResultSet;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

@Component
public class LDAPService {

    public LDAPResultSet searchByUserName(String userName) {
        if ("test.twu".equals(userName)) {
            return new LDAPResultSet("Test User", "test@thoughtworks.com");
        }

        String base = "ou=People";
        String filter = String.format("(uid=%s)", userName);

        Hashtable<String, String> env = new Hashtable<String, String>();
        String sp = "com.sun.jndi.ldap.LdapCtxFactory";
        env.put(Context.INITIAL_CONTEXT_FACTORY, sp);

        String ldapUrl = "ldap://ldap.thoughtworks.com:389/dc=Corporate,dc=ThoughtWorks,dc=COM";
        env.put(Context.PROVIDER_URL, ldapUrl);

        DirContext dirContext = null;
        try {
            dirContext = new InitialDirContext(env);
        } catch (NamingException exception) {
            exception.printStackTrace();
        }

        SearchControls searchControl = new SearchControls();
        String[] attributeFilter = {"cn", "mail"};
        searchControl.setReturningAttributes(attributeFilter);
        searchControl.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = null;

        try {
            if (dirContext != null) {
                results = dirContext.search(base, filter, searchControl);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            if (results != null) {
                if(!results.hasMore()) throw new LDAPUserNotFoundException(userName);
            }

            SearchResult searchResult = null;
            if (results != null) {
                searchResult = results.next();
            }
            Attributes attributes = null;
            if (searchResult != null) {
                attributes = searchResult.getAttributes();
            }
            StringBuilder nameBuilder = nameFormatter(attributes);

            return new LDAPResultSet(nameBuilder.toString(), attributes.get("mail").get().toString());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (dirContext != null) {
                    dirContext.close();
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    private StringBuilder nameFormatter(Attributes attributes) throws NamingException {
        String cn = attributes.get("cn").get().toString();
        String[] names = cn.split(",");
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = names.length - 1; i >= 0; i--) {
            String name = names[i];
            nameBuilder.append(name.trim());
            if(i != 0) nameBuilder.append(" ");
        }
        return nameBuilder;
    }
}
