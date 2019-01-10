import java.io.Externalizable;
import java.io.Serializable;
import java.util.ArrayList;


public class TestClass{
	public static void main(String...args){
		System.out.println(A.AN.getName());
	}
}


enum A{
	AN("ANKIT");
	String ac;
	A(String a){
		ac=a;
	}
	String getName(){
		return this.ac;
	}
}
