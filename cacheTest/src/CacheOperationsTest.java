/**
 * Created by rohini on 10/9/19.
 */


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CacheOperationsTest {
    CacheOperationService cacheOperationService;

    @Before
    public void instantiateMapWithData()  {
        cacheOperationService = new CacheOperations();
        cacheOperationService.add("Infosys", new Company("Infosys", 1000, "Service", "Sudha Murthi"));
        cacheOperationService.add("TCS", new Company("TCS", 1000, "Service", "Tata"));
        cacheOperationService.add("Coditas", new Company("Cdoitas", 1000, "Service", "mitul bid"));
        cacheOperationService.add("IBM", new Company("IBM", 1000, "Service", "Tata"));
        cacheOperationService.add("Tibco", new Company("IBM", 1000, "Service", "Tata"));

    }

    @Test
    public void add() {
        cacheOperationService.add("Tieto", new Company("Tieto", 1000, "Service", "Tata"));
        assertNotEquals(null,cacheOperationService.get("Tieto"));
    }

    @Test
    public void addToCacheOnSizeExeceed() {
        long cacheSize=cacheOperationService.size();
        cacheOperationService.add("Tieto", new Company("Tieto", 1000, "Service", "Tata"));
        assertEquals(cacheSize,cacheOperationService.size());
        assertNotEquals(null,cacheOperationService.get("Tieto"));
    }


    @Test
    public void remove() {
        cacheOperationService.remove("TCS");
        cacheOperationService.remove("Infosys");
        assertEquals(null,cacheOperationService.get("TCS"));
        assertEquals(null,cacheOperationService.get("Infosys"));

    }

    @Test
    public void clear() {
        cacheOperationService.clear();
        assertEquals(cacheOperationService.size(), 0);
    }

    @Test
    public  void get(){

        Object oldObject = cacheOperationService.get("TCS");
        Object updatedObjectWithTime =  cacheOperationService.get("TCS");

        assertEquals(((Company)oldObject).getName(),((Company)updatedObjectWithTime).getName());
        assertEquals(((Company)oldObject).getFounder(),((Company)updatedObjectWithTime).getFounder());
        assertEquals(((Company)oldObject).getNoOfEmployees(),((Company)updatedObjectWithTime).getNoOfEmployees());
        assertEquals(((Company)oldObject).getType(),((Company)updatedObjectWithTime).getType());


        cacheOperationService.add("6sense", new Company("6sense", 1000, "Service", "Tata"));
        oldObject = cacheOperationService.get("6sense");

        Object updatedObjectWithValues = cacheOperationService.get("6sense");

        assertEquals(((Company)oldObject).getName(),((Company)updatedObjectWithValues).getName());
        assertEquals(((Company)oldObject).getFounder(),((Company)updatedObjectWithValues).getFounder());
        assertEquals(((Company)oldObject).getNoOfEmployees(),((Company)updatedObjectWithValues).getNoOfEmployees());
        assertEquals(((Company)oldObject).getType(),((Company)updatedObjectWithValues).getType());
    }

    @Test
    public void storeUpdatedDataInCache(){

        Object oldObject = cacheOperationService.get("TCS");
        Object updatedObjectWithTime =  cacheOperationService.get("TCS");

        assertEquals(((Company)oldObject).getName(),((Company)updatedObjectWithTime).getName());
        assertEquals(((Company)oldObject).getFounder(),((Company)updatedObjectWithTime).getFounder());
        assertEquals(((Company)oldObject).getNoOfEmployees(),((Company)updatedObjectWithTime).getNoOfEmployees());
        assertEquals(((Company)oldObject).getType(),((Company)updatedObjectWithTime).getType());


        cacheOperationService.add("6sense", new Company("6sense", 1000, "Service", "Tata"));
        Object updatedObjectWithValues = cacheOperationService.get("6sense");

        assertEquals(((Company)oldObject).getName(),((Company)updatedObjectWithTime).getName());
        assertEquals(((Company)oldObject).getFounder(),((Company)updatedObjectWithTime).getFounder());
        assertEquals(((Company)oldObject).getNoOfEmployees(),((Company)updatedObjectWithTime).getNoOfEmployees());
        assertEquals(((Company)oldObject).getType(),((Company)updatedObjectWithTime).getType());
    }

}