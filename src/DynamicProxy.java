import java.lang.reflect.*;

//Below class making use of dynamic proxy. AOP uses the same mechanism.
//Using invocation handler, you can call any other method on any object

public class DynamicProxy {

	public static void main(String...args){
		
		Original o = new Original();
		MyInterface m = (MyInterface)Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
				new Class[]{MyInterface.class},new MyInvocationHandler(o));
		
		
		m.method();
		
	}
	
	
}

interface MyInterface {

	void method();
	
	
}
class Original implements MyInterface{

	@Override
	public void method() {
			System.out.println("Original method method()");		
	}
	
}

class MyInvocationHandler implements InvocationHandler{

	MyInterface my;
	
	public MyInvocationHandler(MyInterface my) {
		this.my=my;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("Invocation Handler invoke method");
		method.invoke(my, args);
		return null;
	}

}


