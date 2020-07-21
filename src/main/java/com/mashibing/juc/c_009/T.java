/**
 * 可重入锁 ---------- 
 * 同一线程中 已经获取锁了，该方法接着调用的方法里又要获取锁，时允许的，，，如果不可重入则会死锁   自己写一个不可重入锁 ------
 * @author mashibing
 */
package com.mashibing.juc.c_009;

import java.util.concurrent.TimeUnit;

public class T {
	synchronized void m1() {
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
		System.out.println("m1 end");
	}

	synchronized void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2");
	}

	public static void main(String[] args) {
		new T().m1();
	}
}
