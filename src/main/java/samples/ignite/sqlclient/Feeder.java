package samples.ignite.sqlclient;

import javax.cache.Cache;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;

/**
 * Load data to the cache
 */
public class Feeder {
    /**
     * Entry point
     */
    public static void main(String[] args) {
        Ignition.setClientMode(true);

        try (Ignite ignite = Ignition.start("ignite-config.xml")) {
            CacheConfiguration<Integer, Person> cacheCfg = new CacheConfiguration<>("person");
            cacheCfg.setBackups(0)
                .setAtomicityMode(CacheAtomicityMode.ATOMIC)
                .setIndexedTypes(Integer.class, Person.class);

            Cache<Integer, Person> cache = ignite.getOrCreateCache(cacheCfg);

            for (int i = 0; i < 1000; i++) {
                cache.put(i, new Person(i, String.valueOf(i)));
                Thread.sleep(1000);
                System.out.println(i);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}