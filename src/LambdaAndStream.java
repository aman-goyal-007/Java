import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Stream;

//Lambda 8 and streaming learning
public class LambdaAndStream {

	public static void main(String...args) throws IOException{
		TreeSet<Test> set = new TreeSet<>();
		set.add(new Test(2));
		set.add(new Test(2));
		try(Stream<String> lines=Files.lines(Paths.get("C:\\Users\\agoy22\\Desktop\\a.txt"))){
			lines.findFirst();
//			lines.map(s->s.trim()).flatMap(arg0)().forEach(System.out::println);;
//			lines.flatMap(line->Arrays.stream(line.split(" "))).forEach(System.out::println);;
		}
	}
}


class Test implements Comparable<Test>{
	int i;
	Test(int pi){
		i=pi;
	}
	
	public String toString(){
		return i+"";
	}

	@Override
	public int compareTo(Test arg0) {
		
		return this.i-arg0.i;
	}
}
