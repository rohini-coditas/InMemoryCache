# Internal Memory Cache (InMemoryCache)
```
InMemoryCache is a cache which is been used to avoid frequent datastore operations by storing the most frequently accessed object in the cache.

InMemoryCache application is Developed based on LRU Cache which have HashMap and Doubly LinkedList, In Which HashMap will hold the keys and address of the Nodes of Doubly LinkedList And Doubly LinkedList will hold the values of keys.
We will remove element from Tail and add element on Head of LinkedList and whenever any entry is accessed , it will be moved to Head. so that recently used entries will be on Top(Head) and Least used will be on Bottom(Tail).

If Cache No Of Elements reaches to Max Capacity then Elements will remove from Tail of Linked List and added to the head.```
```

## Syntax

```bash
ConcurrentMap<K,V> cache= new ConcurrentMap<>(size);
K = Key for the cached object (String)
V = The object to be stored into the cache (CacheObject)
size = No of Objects to be stored into the cache
```

## Functionalities
```
This Memory Cache Provides Followng Features:


1. Any get calls to the underlying data store should be cached in memory.
2. There is a limited size to the cache.
3. Most recently used objects should be available in cache and the ones which are not accessed very frequently should be discarded.
4. There should be provisions to flush the cache.
5. Any update to any record should update the cache and then the underlying store.(In case the updated object is present in the cache)
```

## Methods
```
1 Add
2 Get
3 Remove
4 Flush
5.Display
```

## 1. Add

 This method is used to add element in the cache.which takes two args<key,Obj> 
 key: key for the cached object.
 Obj: object to be stored in the cache.
 
```
cacheOperationService.add("Infosys", new Company("Infosys", 1000, "Service", "Sudha Murthi"));
cacheOperationService.add("TCS", new Company("TCS", 1000, "Service", "Tata"));
cacheOperationService.add("Coditas", new Company("Cdoitas", 1000, "Service", "mitul bid"));
```

## 2.Get
To retrieve the object from the cache using the key passed to the get() function.

```
Company TCS = (Company)cacheOperationService.get("TCS");
```
## 4.Remove
Remove the existing object from the cache by the key passed to the method.

```
cacheFunctionalityService.remove("TCS");
```
## 5. Flush
Clear the whole cache.

```
cacheFunctionalityService.clear();
```

