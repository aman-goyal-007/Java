package ConnectionPool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPoolImpl implements ConnectionPool{

	private final int MAX;
	private BlockingQueue<MyConnection> queue;
	
	ConnectionPoolImpl(final int max, final String url, final String user, final String password) throws SQLException{
		this.MAX = max;
		queue = new ArrayBlockingQueue<>(this.MAX);
		for(int i=0;i<this.MAX;i++) {
			new MyConnection(DriverManager.getConnection(url, user, password));
		}
	}
	
	@Override
	public MyConnection getConnection() throws InterruptedException {
		return queue.take();
	}

	@Override
	public boolean releaseConnection(MyConnection conn) {
		return queue.offer(conn);
	}

}
