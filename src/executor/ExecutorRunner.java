package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorRunner {


	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			executorService.submit(new Task(i));
		}
		
		executorService.shutdown();

		System.out.println("All task are completed");

//		executorService.awaitTermination(3, TimeUnit.SECONDS);

	}
}

class Task implements Runnable {
	private int taskId;

	public Task(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.format("Task %d completed\n", taskId);
	}
}
