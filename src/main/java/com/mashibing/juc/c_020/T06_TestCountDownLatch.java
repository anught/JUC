package com.mashibing.juc.c_020;

import java.util.concurrent.CountDownLatch;

public class T06_TestCountDownLatch {
	public static void main(String[] args) {
		// usingJoin();
		usingCountDownLatch();
	}

	private static void usingCountDownLatch() {
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int result = 0;
				for (int j = 0; j < 10000; j++)
					result += j;
				latch.countDown();
			});
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		try {
			latch.await();// 在这里 栓门；总共threads.length个门栓 每一个线程结束打开一个门栓 ，打开全部门栓才向下执行 用 join 也可以
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("end latch");
	}

	private static void usingJoin() {
		Thread[] threads = new Thread[100];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int result = 0;
				for (int j = 0; j < 10000; j++)
					result += j;
			});
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("end join");
	}
}
