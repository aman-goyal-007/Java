import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

//This implementation will support both time based and limit based caching
public class DelayCache {

	public static void main(String...args){
		Cache<Integer,Integer> cache = new Cache(2, TimeUnit.SECONDS, 1);
		try {
			cache.putBlock(2,2);
			cache.putBlock(3,3);
			cache.putBlock(4,4);
			System.out.println(cache.get(2));
			System.out.println(cache.get(3));
			System.out.println(cache.get(4));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}


class Cache<K,T> {
	private final Map<K,CacheEntry<T>> cacheMap;
	private final TimeUnit timeUnit;
	private final long delay;
	private final int size;
	Cache(int size,TimeUnit timeUnit,long delay){
		this.cacheMap = new ConcurrentHashMap<>(size);
		this.timeUnit = timeUnit;
		this.delay = delay;
		this.size=size;
		Thread t = new Thread(()-> {
				while(true){
					invalidateOldElements();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		});
		t.setDaemon(true);
		t.start();
		
	}
	
	void putBlock(K key,T element){
		while(cacheMap.size()>=size){
			invalidateOldElements();
		}
		cacheMap.put(key,new CacheEntry<T>(element));
		System.out.println(key+" added");
	}
	
	boolean tryPut(K key,T element) {
		if(cacheMap.size()>=size)
			invalidateOldElements();
		if(cacheMap.size()>=size) return false;
		else
			cacheMap.put(key,new CacheEntry<T>(element));
		return true;
	}

	private int getCurrentSize(){
		return cacheMap.size();
	}
	private void invalidateOldElements(){
		cacheMap.entrySet().removeIf(a->a.getValue().isDelayed());
	}
	
	
	T get(K key){
		CacheEntry<T> dataCache;
		T data=null;
		if((dataCache=cacheMap.get(key)) !=null){
			data = dataCache.getDataPart();
		}
		return data;
	}
	class CacheEntry<T>{

		T data;
		LocalDateTime dateTime;
		private CacheEntry(T data) {
			this.data = data;
			dateTime = LocalDateTime.now();
		}
		boolean isDelayed(){

			long checkHowOld= Duration.between(dateTime,LocalDateTime.now()).getSeconds() - timeUnit.toSeconds(delay);

			return (checkHowOld)>0;
			
		}
		T getDataPart(){
			return data;
		}
		
		
	}

	
}


