package com.mashibing.juc.c_020;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class T12_TestExchanger {

	static Exchanger<String> exchanger = new Exchanger<>();

	// 可以理解为Exchanger 处有两个格子，当一个线程到了exchange将值放到格子里，阻塞，，
	// 另一个线程指向到exchange放到格子里完成交换，接着两个线程向下执行
	// 场景： 一个线程停止，通知另一个线程给它点东西；；；双人游戏两个人交换装备
	public static void main(String[] args) {
		new Thread(() -> {
			String s = "T1";
			try {
				// s = exchanger.exchange(s); // 也可以设置timeout exchange(V x, long timeout,
				// TimeUnit unit)
				try {
					s = exchanger.exchange(s, 10, TimeUnit.MILLISECONDS);
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					s = exchanger.exchange(s);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " " + s);

		}, "t1").start();

		new Thread(() -> {
			String s = "T2";
			try {
				Thread.sleep(1000);
				s = exchanger.exchange(s); //
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " " + s);

		}, "t2").start();

	}
}
