package ConnectionPool;

import java.sql.Connection;

public interface ConnectionPool {
	MyConnection getConnection()  throws InterruptedException ;
	boolean releaseConnection(MyConnection conn);

}
