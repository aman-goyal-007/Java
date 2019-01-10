import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SortHashmapByValues {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(5, "b");
		map.put(2, "p");
		map.put(1, "a");
		map.put(4, "c");
		MyComparator comparator  = new MyComparator(map);
		TreeMap<Integer, String> treeMap = new TreeMap<>(comparator);
		treeMap.putAll(map);

		Set<Entry<Integer, String>> set=treeMap.entrySet();
		for(Entry e:set)
			System.out.println(e.getValue());
	}
	
}
class MyComparator implements Comparator<Integer>{

	HashMap<Integer, String> map;
	
	public MyComparator(HashMap<Integer, String> map) {
		this.map = map;
	}
	@Override
	public int compare(Integer o1, Integer o2) {
		
		return map.get(o1).compareTo(map.get(o2));
	}
	
}
