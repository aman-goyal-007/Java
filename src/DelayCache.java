import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
	final Calendar startDateTime;
	final Map<K,CacheEntry<T>> cacheMap;
	final TimeUnit timeUnit;
	final long delay;
	final int size;
	Cache(int size,TimeUnit timeUnit,long delay){
		this.startDateTime = Calendar.getInstance();
		this.cacheMap = new ConcurrentHashMap<>(size);
		this.timeUnit = timeUnit;
		this.delay = delay;
		this.size=size;
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					invalidateOldElements();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		
	}
	
	void putBlock(K key,T element) throws InterruptedException{
		while(cacheMap.size()>=size){
			invalidateOldElements();
			Thread.sleep(1000);
		}
//		System.out.println(key);
		cacheMap.put(key,new CacheEntry(element));
	}
	
	boolean tryPut(K key,T element) throws InterruptedException{
		if(cacheMap.size()>=size)
			invalidateOldElements();
		if(cacheMap.size()>=size) return false;
		else
			cacheMap.put(key,new CacheEntry(element));
		return true;
	}

	int getCurrentSize(){
		return cacheMap.size();
	}
	void invalidateOldElements(){
		Set<K> keys = cacheMap.keySet();
		Iterator<K> iterator = keys.iterator();
		while(iterator.hasNext()){
			CacheEntry<T> data = cacheMap.get(iterator.next());
			if(data.isDelayed()) iterator.remove();
		}
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
		Calendar elementCreationTime;
		public CacheEntry(T data) {
			this.data = data;
			elementCreationTime=Calendar.getInstance();
		}
		boolean isDelayed(){
			long difference=timeUnit.convert(Calendar.getInstance().getTimeInMillis()-elementCreationTime.getTimeInMillis(),TimeUnit.MILLISECONDS);
			return (difference-delay)>0;
			
		}
		T getDataPart(){
			return data;
		}
		
		
	}



	
	public void run() {
		while(true){
			invalidateOldElements();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


