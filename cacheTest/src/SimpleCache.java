class SimpleCache {


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
