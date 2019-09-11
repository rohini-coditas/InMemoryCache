/**
 * Created by rohini on 10/9/19.
 */

public interface CacheOperationService {

    /**
     * This method is used to add Cache Objects.
     * @param key - We are using Map as a cache so we will need a key to retrieve or save the object.
     * @param data - Data is the object to be stored in the cache.
     * @return - nothing
     */
    public void add(String key, Object data);


    /**
     * This method is used to display Cache Objects.
     * @return - nothing
     */
    public void displayCacheData();



    /**
     * This method is used to get the cache object.
     * @param key - If the key is present then the respective Object will be retrieved from the cache.
     * @return -  Object from the cache for with key as a input will be retrieved else NULL will be returned.
     * 			If the Object in the cache is expired then NULL will be returned
     */
    public Object get(String key);

    /**
     * This method is used to remove the object from the cache by using the key.
     * @param key - If the key is present then the respective Object will be removed else nothing will be removed from the cache.
     * @return - True if object removed else false
     */
    public void remove(String key);

    /**
     * This method is used to clear the whole cache.
     * @param - no.
     * @return - nothing
     */
    public void clear();


    /**
     * This method is used to the current size of the cache.
     * @param no
     * @return - number of objects in the cache.
     */
    public long size();

}
