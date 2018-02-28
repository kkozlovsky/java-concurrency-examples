package consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerRunner {
		
	private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);


	public static void main(String[] args) throws InterruptedException {
		Thread producerThread = new Thread(() -> {
			try {
				produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread consumerThread = new Thread(() -> {
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		consumerThread.start();
		producerThread.start();
		
		consumerThread.join();
		producerThread.join();
				 
	}
	
	private static void produce() throws InterruptedException {
		while (true) {
			blockingQueue.put((int)(Math.random()*100));
		}
	}


	private static void consume() throws InterruptedException {
		
		while (true) {
			Thread.sleep(100);
			System.out.println(blockingQueue.take());
			System.out.println("Queue size: " + blockingQueue.size());
		}
	}
}
