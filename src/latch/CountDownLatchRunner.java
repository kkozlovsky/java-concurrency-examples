package latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchRunner {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 3; i++) {
			executorService.submit(new Task(countDownLatch));
		}
		executorService.shutdown();
		
		countDownLatch.await(); // waiting countDownLatch = 0

		System.out.println("countDownLatch.await() was invoked");
	}
}

class Task implements Runnable {
	
	private CountDownLatch countDownLatch;

	public Task(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Task is done");
		countDownLatch.countDown();
	}
}
