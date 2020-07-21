package com.mashibing.juc.c_020;

import java.util.concurrent.Semaphore;

public class T11_TestSemaphore {
	public static void main(String[] args) {
		// Semaphore s = new Semaphore(2); //最多可以多少个线程获取信号量。也可以理解为有多少线程可以执行 限流
		Semaphore s = new Semaphore(2, true);//
		// 允许一个线程同时执行
		// Semaphore s = new Semaphore(1);

		new Thread(() -> {
			try {
				s.acquire();// 阻塞 取得信号 信号 减去1

				System.out.println("T1 running...");
				Thread.sleep(200);
				System.out.println("T1 running...");

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				s.release(); // 信号还回去
			}
		}).start();

		new Thread(() -> {
			try {
				s.acquire();

				System.out.println("T2 running...");
				Thread.sleep(200);
				System.out.println("T2 running...");

				s.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

	}
}
