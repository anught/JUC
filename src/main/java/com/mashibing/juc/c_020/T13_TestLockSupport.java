package com.mashibing.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport {
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				if (i == 5) {
					LockSupport.park();// 当前线程阻塞
				}

				if (i == 8) {
					LockSupport.park();// 当前线程阻塞
				}

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t.start();
		LockSupport.unpark(t); // 可以先于park，预先取消park

//		try {
//			TimeUnit.SECONDS.sleep(8);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("after 8 senconds!");
//		LockSupport.unpark(t);

	}
}
