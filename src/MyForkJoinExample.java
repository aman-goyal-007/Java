import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.*;

public class MyForkJoinExample {

	public static void main(String...args){
		ForkJoinPool forkJoinPool = new ForkJoinPool();
/*		ExampleRecursiveAction action = new ExampleRecursiveAction(24);
		forkJoinPool.invoke(action);
*/		ExampleRecursiveTask task = new ExampleRecursiveTask(24);
		int result = forkJoinPool.invoke(task);
		System.out.println("Result of RecursiveTask :"+result);
	}
	
}


class ExampleRecursiveAction extends RecursiveAction{
	int workload;
	public ExampleRecursiveAction(int workload) {
		this.workload=workload;
	}
	@Override
	protected void compute() {
		if(workload>4){
			System.out.println("Workload is more :"+this.workload);
			List<ExampleRecursiveAction> subAction = new ArrayList<>();
			subAction.addAll(createSubAction());
			for(ExampleRecursiveAction action:subAction){
				action.fork();
			}
			System.out.println("Fork Done");
			
		}else{
			System.out.println("Doing my work : "+Thread.currentThread());
		}
	}
	
	private List<ExampleRecursiveAction> createSubAction(){
		List<ExampleRecursiveAction> localList = new ArrayList<>();
		localList.add(new ExampleRecursiveAction(this.workload/2));
		localList.add(new ExampleRecursiveAction(this.workload/2));
		return localList;
	}
	
	
	
}



class ExampleRecursiveTask extends RecursiveTask<Integer>{
	int workload;
	public ExampleRecursiveTask(int workload) {
		this.workload=workload;
	}
	@Override
	protected Integer compute() {
		if(workload>4){
			System.out.println("Workload is more :"+this.workload);
			List<ExampleRecursiveTask> subAction = new ArrayList<>();
			subAction.addAll(createSubTask());
			for(ExampleRecursiveTask action:subAction){
				action.fork();
			}
			int result =0;
			
			for(ExampleRecursiveTask task:subAction){
				result+=task.join();
			}
			
			System.out.println("Fork and Join Done :"+result);
			return result;
		}else{
			System.out.println("Doing my work : "+Thread.currentThread());
			return this.workload;
		}

	}
	
	private List<ExampleRecursiveTask> createSubTask(){
		List<ExampleRecursiveTask> localList = new ArrayList<>();
		localList.add(new ExampleRecursiveTask(this.workload/2));
		localList.add(new ExampleRecursiveTask(this.workload/2));
		return localList;
	}
	
	
	
}


