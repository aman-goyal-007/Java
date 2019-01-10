import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Need to create deep cloning using this
public class DeepClone {

	public static void main(String...args) throws CloneNotSupportedException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException{
		Child c = new Child();
		c.childName="child";
		c.p.name="parent";
		
		getClone(c, c.getClass().newInstance());
		
	}
	
	public static Object getClone(Object o,Object clone) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException{
		Class myClass = o.getClass();
		Class clonedClass=clone.getClass();
		Field[] clonedFields = clonedClass.getDeclaredFields();
		Field[] c = myClass.getDeclaredFields();
		int i=0;
		for(Field f:c){
			Class type=f.getType();
			if(type==int.class || type==char.class || type==double.class || type==float.class){
				System.out.println("Nothing :|"+type.getName());
			}else{
				
				System.out.println(f.getName());
				System.out.println(Class.forName(type.getName()).newInstance());
				f.get(type.newInstance());
//				getClone(f.get(f.getName()),clonedFields[i]);
	//			System.out.println(clonedFields[i]);
			}
			i++;
		}
		return clone;
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

