import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LRU {
	
	public static void main(String...args){
		MYLRU<Integer,String> myLRU = new MYLRU(2);
		myLRU.put(1, "One");
		myLRU.printLRU();
		myLRU.put(2, "Two");
		myLRU.printLRU();
		myLRU.put(3, "Three");
		myLRU.printLRU();
		myLRU.getObject(3);
		myLRU.put(4, "Four");
		myLRU.printLRU();
		
	}

}

class MYLRU<K,V>{
	Map<K, V> myLRU;
	final int MAXSIZE;
	MYLRU(int size){
		myLRU = new LinkedHashMap<>(size);
		MAXSIZE=size;
	}
	
	void put(K key,V value){
		
		if(myLRU.size()>=MAXSIZE){
			removeLeastRecentUsed();
		}
		myLRU.put(key, value);
	}
	void removeLeastRecentUsed(){
		if(!myLRU.isEmpty())
			myLRU.remove(myLRU.keySet().iterator().next());
	}
	
	V getObject(K key){
		V v=null;
		if((v=myLRU.get(key)) != null){
			if(myLRU.size()>=MAXSIZE)
				removeLeastRecentUsed();
			myLRU.remove(key);
			myLRU.put(key, v);
		}
		return v;
	}
	void printLRU(){
		System.out.println("---------------------------");
		Set<K> keys=myLRU.keySet();
		for(K key:keys){
			System.out.println(myLRU.get(key));
		}
	}


	class LRUCache<K,V>{
		int capacity;


	}

	class LRUNode<K,V>{
		K key;
		V value;
		LRUNode pre;
		LRUNode next;

		public LRUNode(K key, V value) {
			this.key = key;
			this.value = value;
		}


	}


}
