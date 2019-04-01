import java.util.*;
import java.util.Map.Entry;

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

		Set<Entry<Integer, String>> set = treeMap.entrySet();
		for(Entry e:set)
			System.out.println(e.getValue());
	}
	
}
class MyComparator implements Comparator<Integer>{

	private Map<Integer, String> map;
	
	MyComparator(Map<Integer, String> map) {
		this.map = map;
	}
	@Override
	public int compare(Integer o1, Integer o2) {
		
		return map.get(o1).compareTo(map.get(o2));
	}
	
}
