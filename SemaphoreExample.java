//yossi hadad -312588288 , yarin aharonov - 318467206
//https://github.com/Yasonhadad/assig_4_02.git
package assig4_2;

import java.util.concurrent.*;

public class SemaphoreExample {
	static class SharedResource {
		private final Semaphore semaphore;

		public SharedResource(int permits) {
			semaphore = new Semaphore(permits);
		}

		public void accessResource() {
			try {
				semaphore.acquire();// Acquire a permit
				System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
				// Simulating some work
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();// Release the permit
			}
		}
	}

	static class Worker extends Thread {
		private final SharedResource sharedResource;

		public Worker(String name, SharedResource sharedResource) {
			super(name);
			this.sharedResource = sharedResource;
		}

		@Override
		public void run() {
			sharedResource.accessResource();
		}
	}

	public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(2); //  permits available

        // Create multiple threads trying to access the resource
        Thread thread1 = new Worker("Thread 1", sharedResource);
        Thread thread2 = new Worker("Thread 2", sharedResource);
        Thread thread3 = new Worker("Thread 3", sharedResource);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}


