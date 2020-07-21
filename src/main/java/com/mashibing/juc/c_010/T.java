/**
 *可重入锁 ---------- 
 * 同一线程中 已经获取锁了，该方法接着调用的方法里又要获取锁，时允许的，，，如果不可重入则会死锁   自己写一个不可重入锁 ------
 * 
 * 例子中锁的是同一个对象 tt
 * @author mashibing
 */
package com.mashibing.juc.c_010;

import java.util.concurrent.TimeUnit;

public class T {
	synchronized void m() {
		System.out.println("m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}

	public static void main(String[] args) {
		new TT().m();

		Integer a = 1;
	}

}

class TT extends T {
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}
