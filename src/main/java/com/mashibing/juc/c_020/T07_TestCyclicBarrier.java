package com.mashibing.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier {
	// 栅栏 凑个20个线程才开始执行，不满20个的时候，前面的线程一致等着
	// 限流时 使用 Guava RateLimiter 更合适
	public static int idx = 0;

	public static void main(String[] args) {
		// CyclicBarrier barrier = new CyclicBarrier(20);

		// CyclicBarrier barrier = new CyclicBarrier(20, () ->
		// System.out.println("满人，发车"));

		CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
			@Override
			public void run() {
				System.out.println("满人 , 发车");
			}
		});

		for (int i = 0; i < 100; i++) { // 会有 5个 满人 , 发车
			idx = i;
			new Thread(() -> {
				try {
					int idxl = idx;
					Thread.sleep(10000);
					barrier.await();
					System.out.println("idx:" + idxl);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();

		}
	}
}
