package samples.ignite.sqlclient;

import java.util.List;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.Query;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;

/**
 * Command line tool ot run SQL against a cache.
 */
public class SqlClient {
    /**
     * Command line app entry point.
     * @param args command line args "config cache sql"
     */
    public static void main(String args[]) {
        Ignition.setClientMode(true);

        if (args.length != 3) {
            System.out.format(
                "Specify config file, cache and SQL query, for example:\n\t" +
                    "java -cp samples-ignite-sqlclient.jar %s ignite-config.xml person " +
                    "\"SELECT id, name from Person\"\n",
                SqlClient.class.getName());
        }
        else {
            try (Ignite ignite = Ignition.start(args[0])) {
                IgniteCache<BinaryObject, BinaryObject> cache = ignite.cache(args[1]).withKeepBinary();

                Query<List<?>> qry = new SqlFieldsQuery(args[2]);

                QueryCursor<List<?>> cur = cache.query(qry);

                for (List<?> row : cur.getAll()) {
                    for (Object cell : row)
                        System.out.format("%s\t", cell);

                    System.out.println();
                }
            }
        }
    }
}
