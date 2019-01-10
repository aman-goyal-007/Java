import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//Testing

public class Generics {

	public static void main(String...args){
/*		GenericsForObject object = new GenericsForObject();
		object.add(1, "Aman");
		object.add(1, "Pradeep");
		object.add(1, "Sumit");
		object.add(2, "Deepika");
		object.add(2, "Ajay");
		object.print();
*/
		
		
		
/*		One<String> one = new One<String>();
		List<String> listone = one.Method("o","oo","ooo");
		One<Integer> two = new One<Integer>();
		List<Integer> listtwo = two.Method(1,2,3);
		for(String s:listone)
			System.out.println(s);
		for(Integer s:listtwo)
			System.out.println(s);
		copy(new ArrayList<String>(),new ArrayList<Number>());
*/		
		
		Integer[] integer = new Integer[]{1,2,3};
		Number[] number=integer;
		number[0]=1.2;
		System.out.println(number[0]);
		
		
/*		List<Integer> intlist = new ArrayList<Integer>();
		intlist.add(1);
		intlist.add(2);
		intlist.add(3);
		System.out.println(max(intlist));
*/		
	}
	
	public static <T> void copy(List<T> src,List<? extends Number> dst){
		
	}
	public static <T extends Comparable<T>> T max(List<T> data){
		Iterator<T> iter = data.iterator();
		T obj=iter.next();
		T curr;
		while(iter.hasNext()){
			curr=iter.next();
			if(obj.compareTo(curr)<0) obj=curr;
		}
		return obj;
		
	}
	
	static  <T extends Object  & Comparable & Serializable> void mytest(T a){
		
	}
	
	
	
}

class One<E>{
	public List<E> Method(E...a){
		List<E> list = new ArrayList<>();
		for(E aa:a)
			list.add(aa);
		return list;
	}
}


class GenericsForObject{
	HashMap<Integer, ArrayList<String>> map = new HashMap<Integer,ArrayList<String>>();
	ArrayList<String> list =null;
	void add(Integer id,String name){
		if(map.containsKey(id)){
			list=map.get(id);
		}
		else {
			list = new ArrayList<String>();
		}
		list.add(name);
		map.put(id, list);
	}
	void print(){
		Set<Integer> set= map.keySet();
		for(Integer id:set){
			for(String name:map.get(id)){
				System.out.println("Batch id:"+id+" Name: "+name);
			}
		}
	}
	void Method1(ArrayList<String> a){
		
	}
}

