/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * volatile只是 保证线程可见性，但是count++ 不是原子操作 
 * 解决问题的方式是 加synchronized
 * @author mashibing
 */
package com.mashibing.juc.c_012_Volatile;

import java.util.ArrayList;
import java.util.List;

public class T04_VolatileNotSync {
	volatile int count = 0;

	void m() {
		for (int i = 0; i < 10000; i++)
			count++;
	}

	public static void main(String[] args) {
		T04_VolatileNotSync t = new T04_VolatileNotSync();

		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}

		threads.forEach((o) -> o.start());

		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.count);

	}

}
