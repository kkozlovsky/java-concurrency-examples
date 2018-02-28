package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRunner {
	
	public static void main(String[] args) throws InterruptedException {
		

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		Connection connection = Connection.getConnection();

		for (int i = 0; i < 100; i++) {
			executorService.submit(() -> {
				try {
					connection.semaphoreWork();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);

	}
}

class Connection {
	
	private static Connection connection = new Connection();
	private int connectionCount;
	private Semaphore semaphore = new Semaphore(10);
	
	private Connection () {}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public void semaphoreWork() throws InterruptedException {
		try {
			semaphore.acquire();
			doWork();
		} finally {
			semaphore.release();
		}
	}
	
	private void doWork() throws InterruptedException {
		synchronized (this) {
			connectionCount++;
			System.out.println(connectionCount);
		}
		Thread.sleep(3000);
		
		synchronized (this) {
			connectionCount--;
		}
	}
	
}
