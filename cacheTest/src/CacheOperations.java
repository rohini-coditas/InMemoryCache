/**
 * Created by rohini on 10/9/19.
 */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;



public class CacheOperations implements CacheOperationService {

    /**
     * Holds size of the cache
     */
    public static final int CACHE_SIZE = 5;

    /**
     * Holds default expire dates
     */
    long defaultExpireTime = 100;

    /**
     * Holds custom expiration dates
     */
    private final Map <String, Long> expire = new HashMap <>();

    /**
     * Holds data to be added in the cache
     */
    private ConcurrentMap <String, Object> cache = new ConcurrentHashMap();

    /**
     * Holds Deque to implement LRU in cache
     */
    static Deque <String> dq = new LinkedList <>();

    private static final Logger LOGGER = Logger.getLogger(CacheOperations.class.getName());



    @Override
    public void add(String key, Object data) {
        removeExpiredObjectsFromCache();
        if (validateTheCacheObjectInput(key, data)) {
            addDataIntoTheCache(key, data, defaultExpireTime);
        }
    }


    @Override
    public Object get(String key) {

        final Long expireTime = this.expire.get(key);
        if (expireTime == null) return null;
        if (System.currentTimeMillis() > expireTime) {
            remove(key);
            return null;
        }
        return cache.containsKey(key) ? cache.get(key) : null;
    }

    @Override
    public void remove(String key) {
        cache.entrySet().removeIf(entry -> entry.getKey().equals(key));
    }

    @Override
    public void displayCacheData() {
        cache.forEach((k, v) -> LOGGER.info("Name : " + k + " Info : " + v));
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public long size() {
        return cache.size();
    }

    /**
     * Before adding the data into the cache this methods checks whether any
     * existing objects are expired or not if expired then it removes it from the cache
     * then we proceed to add an new object
     */
    public void removeExpiredObjectsFromCache() {
        cache.entrySet().removeIf(entry ->
                this.expire.get(entry.getKey()) < System.currentTimeMillis());
    }

    /**
     * This method validates the key and data to be stored into the cache.
     *
     * @param key  - Key for the object in the cache. If null then the object is not been added into the cache.
     * @param data - If data is null and then does not allow to add data into the cache and if the key is present into
     *             the cache then deletes the entry of that key because the object is null.
     * @return true if key and data are validated successfully else false.
     */
    private Boolean validateTheCacheObjectInput(String key, Object data) {

        if (key == null || key.isEmpty()) {
            return false;
        }
        else if (data == null || data.toString().isEmpty()) {
            remove(key);
            return false;
        }
        return true;
    }

    /**
     * This is the core logic to add the data into the cache if their is place into the cache to add then
     * the object is been added or updated else by the use of replaceDataBasedOnLRU algorithm the object is added into the cache.
     *
     * @param key  - To be added into the cache
     * @param data - To be added into the cache.
     */
    private void addDataIntoTheCache(String key, Object data, final long expireTime) {
        if (cache.size() < CACHE_SIZE) {
            cache.put(key, data);
            dq.push(key);
            this.expire.put(key, System.currentTimeMillis() + expireTime * 1000);
        }
        else {
            insertOrUpdateDataBasedOnLRU(key, data, expireTime);
        }
    }


    /**
     * This algorithm checks if key to be added is already present in the cache if yes then remove the existing key add new at first position
     * if there is no place in cache then least recently used key get removed from the cache the new key gets added at first position.
     *
     * @param key  - To be added into the cache
     * @param data - To be added into the cache.
     */
    public void insertOrUpdateDataBasedOnLRU(String key, Object data, final long expireTime) {
        if (!cache.containsKey(key)) {
            if (dq.size() == CACHE_SIZE) {
                String last = dq.removeLast();
                cache.remove(last);
            }
        }
        else {
            Iterator <String> itr = dq.iterator();
            while (itr.hasNext()) {
                if (itr.next() == key) {
                    dq.remove(key);
                }
            }
        }
        dq.push(key);
        cache.put(key, data);
        this.expire.put(key, System.currentTimeMillis() + expireTime * 1000);
    }
}
