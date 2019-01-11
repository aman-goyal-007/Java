import java.lang.reflect.Field;

// Need to create deep cloning using this
/*
Deep clocning is not a good idea. Instead class should provide copy constructor if required.
 */

public class DeepClone {

	public static void main(String...args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Child c = new Child();
		c.childName="child";
		c.p.name="parent";
		
		getClone(c, c.getClass().newInstance());
		
	}
	
	private static void getClone(Object o,Object clone) throws IllegalAccessException, ClassNotFoundException, InstantiationException{
		Class myClass = o.getClass();

		Field[] c = myClass.getDeclaredFields();
		for(Field f:c){
			Class type=f.getType();
			if(type==int.class || type==char.class || type==double.class || type==float.class){
				System.out.println("Nothing :|"+type.getName());
			}else{
				
				System.out.println(f.getName());
				System.out.println(Class.forName(type.getName()).newInstance());
				f.get(type.newInstance());
			}
		}
	}
	
}

class Parent{
	String name;
}
class Child{
	int i;
	String childName;
	Parent p=new Parent();
}

