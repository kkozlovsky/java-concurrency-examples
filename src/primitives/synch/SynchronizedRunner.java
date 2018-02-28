package primitives.synch;

public class SynchronizedRunner {
	
	private int counter;

	private synchronized void increment() {
		counter++;
	}

//	private void increment() {
//		synchronized(this) {
//			counter++;
//		}
//	}

	
	private void doWork() throws InterruptedException {
		
		Thread first = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				increment();
			}
		});

		Thread second = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				increment();
			}
		});
		
		first.start();
		second.start();
		
		first.join();
		second.join();

		System.out.println(counter);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedRunner runner = new SynchronizedRunner();
		runner.doWork();
	}
}
