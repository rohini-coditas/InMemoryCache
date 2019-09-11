/**
 * Created by rohini on 9/9/19.
 */

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SimpleCache<T>  {

    /**
     * Objects are stored here
     */
    private final Map <String, Object> objects;
    /**
     * Holds custom expiration dates
     */
    private final Map <String, Long> expire;
    /**
     * The default expiration date
     */
    private final long defaultExpire;
    private final ExecutorService threads;

    // store keys of cache
    static Deque<Integer> dq;
    // store references of key in cache
    static HashSet<Integer> map;
    // maximum capacity of cache
    static int csize;



    /*Constructs the cache with a default expiration time for the objects of
      100 seconds.*/
    public SimpleCache() {
        this(100);
    }



    public void init(int n){
        dq = new LinkedList<>();
        map = new HashSet<>();
        csize = n;
    }

    public void refer(int x)
    {
        if (!objects.containsKey(x)) {
            if (dq.size() == csize) {
                int last = dq.removeLast();
                objects.remove(last);
            }
        }
        else {
            /* The found page may not be always the last element, even if it's an
               intermediate element that needs to be removed and added to the start
               of the Queue */
            int index = 0, i = 0;
            Iterator<Integer> itr = dq.iterator();
            while (itr.hasNext()) {
                if (itr.next() == x) {
                    index = i;
                    break;
                }
                i++;
            }
            dq.remove(index);
        }
        dq.push(x);
        map.add(x);
    }

    /**
     * Construct a cache with a custom expiration date for the objects.
     *
     * @param defaultExpire default expiration time in seconds
     */
    public SimpleCache(final long defaultExpire) {
        this.objects = Collections.synchronizedMap(new HashMap <String, Object>());
        this.expire = Collections.synchronizedMap(new HashMap <String, Long>());
        this.defaultExpire = defaultExpire;
        this.threads = Executors.newFixedThreadPool(256);
        Executors.newScheduledThreadPool(2).scheduleWithFixedDelay(this.removeExpired(), this.defaultExpire / 2, this.defaultExpire, TimeUnit.SECONDS);
    }


    /**
     * Put an object into the cache.
     *
     * @param name the object will be referenced with this name in the cache
     * @param obj  the object
     */
    public void put(final String name, final Object obj) {
        this.put(name, obj, this.defaultExpire);
    }

    /**
     * This Runnable removes expired objects.
     */
    private final Runnable removeExpired() {
        return new Runnable() {
            public void run() {
                expire.forEach((name, expireTime) -> {
                    if (System.currentTimeMillis() > expire.get(name)) {
                        threads.execute(createRemoveRunnable(name));
                    }
                });
            }
        };
    }

    /**
     * This Runnable flush the entire cache.
     */
    private final Runnable flushCache() {
        return new Runnable() {
            public void run() {
                objects.forEach((name, expireTime) -> {
                    threads.execute(createRemoveRunnable(name));

                });
            }
        };
    }


    /**
     * Returns a runnable that removes a specific object from the cache.
     *
     * @param name the name of the object
     */
    private final Runnable createRemoveRunnable(final String name) {
        return new Runnable() {
            public void run() {
                objects.remove(name);
                expire.remove(name);
            }
        };
    }

    /**
     * Put an object into the cache with a custom expiration date.
     *
     * @param name       the object will be referenced with this name in the cache
     * @param obj        the object
     * @param expireTime custom expiration time in seconds
     */

    public void put(final String name, Object obj, final long expireTime) {

        this.objects.put(name, obj);
        this.expire.put(name, System.currentTimeMillis() + expireTime * 1000);
    }

    /**
     * Returns an object from the cache.
     *
     * @param name the name of the object you'd like to get
     * @return the object for the given name and type
     */
    public Object get(final String name) {
        final Long expireTime = this.expire.get(name);
        if (expireTime == null) return null;
        if (System.currentTimeMillis() > expireTime) {
            this.threads.execute(this.createRemoveRunnable(name));
            return null;
        }
        return this.objects.get(name);
    }
    public void display()
    {
        Iterator<Integer> itr = dq.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }

    public void test() throws Exception {
        put("first",new Employee(1,"Rohini"));
        put("Second",new Employee(2,"Roshini"));

        System.out.println(get("first").toString());



//        put("Name", "Rohini");
//        put("RollNo", "3");
//        put("subject", "english");
//        System.out.println("Cache Result: " + get("Name"));
//        flushCache();
//        System.out.println("Cache Result: " + get("Name"));

//        init(4);
//        refer(1);
//        refer(2);
//        refer(3);
//        refer(1);
//        refer(4);
//        refer(5);
//        System.out.println("***8");
//        display();
    }


    public static void main(String args[]) throws Exception {
        CacheOperations cacheOperations=new CacheOperations();
        cacheOperations.add("Infosys",new Company("Infosys",1000,"Service","Sudha Murthi"));
        cacheOperations.add("TCS",new Company("TCS",1000,"Service","Tata"));
        cacheOperations.add("Cdoitas",new Company("Cdoitas",1000,"Service","Tata"));
        cacheOperations.add("IBM",new Company("IBM",1000,"Service","Tata"));


        cacheOperations.displayCacheData();

        System.out.println("Testing get: ");
        System.out.println(cacheOperations.get("Cdoitas").toString());

//        cacheOperations.remove("TCS");
//
//        System.out.println("Testing get TCS: ");
//        System.out.println(cacheOperations.get("TCS").toString());

        cacheOperations.removeExpiredObjectsFromCache();

//        cacheOperations.clear();
        System.out.println("Testing get Clear: ");
        System.out.println(cacheOperations.get("IBM").toString());



        System.exit(0);
    }
}
