import java.util.concurrent.Callable;

interface Bird {
    Egg lay();
}

class Chicken implements Bird{
    public Chicken() {
    }

    public static void main(String[] args) throws Exception {
        Chicken chicken = new Chicken();
        System.out.println(chicken instanceof Bird);
    }

	@Override
	public Egg lay() {
		return new Egg();
	}
}

class Egg {
	int i=0;
	Egg(){
		
	}
    public Egg(Callable<Bird> createBird)  {
    	super();
    }

    public Bird hatch() {
    	if(i!=0) {
    		throw new IllegalStateException();
    	}
    	i++;
        return new Chicken();
    }
    
    public Bird hatch(Bird b) {
    	if(b instanceof Chicken) {
    		return b;
    	}else {
    		return b;
    	}
    }

    
}