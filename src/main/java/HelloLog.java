import com.unboundid.ldap.sdk.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HelloLog {

    public static LDAPConnection getConnection() throws LDAPException {
        // host, port, username and password
        return new LDAPConnection("192.168.254.180", 389);
    }
    public static List<SearchResultEntry> getResults(LDAPConnection connection, String baseDN, String filter) throws LDAPSearchException {
        SearchResult searchResult;

        if (connection.isConnected()) {
            searchResult = connection.search(baseDN, SearchScope.ONE, filter);

            return searchResult.getSearchEntries();
        }

        return null;
    }

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws LDAPException {
        String userInput = "${jndi:ldap://192.168.219.180:389/ou=Employee,dc=example,dc=com}";

        // passing user input into the logger
        logger.info("Test: "+userInput);

        Thing thing = new Thing("thing1", "thing1 description");
        System.out.println(thing);
        thing.serialize();
        Thing thing2 = thing.deSerialize();
        System.out.println(thing2);

        // %m{nolookups} has no effect for the following line
        // logger.printf(Level.INFO,"Test: %s", userInput);

        /*String baseDN = "DC=example,DC=com";
        String filter = "(&(|(objectClass=organizationalUnit)(ou=Employee)))";

        getResults(getConnection(), baseDN, filter)
                .stream()
                .map(SearchResultEntry::getDN)
                .forEach(logger::info);*/
    }
}
