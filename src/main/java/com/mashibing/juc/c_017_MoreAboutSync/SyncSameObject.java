/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 * @author mashibing
 */
package com.mashibing.juc.c_017_MoreAboutSync;

import java.util.concurrent.TimeUnit;

public class SyncSameObject {

	/* final */ Object o = new Object();

	void m() {
		synchronized (o) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());

			}
		}
	}

	public static void main(String[] args) {
		SyncSameObject t = new SyncSameObject();
		// ������һ���߳�
		new Thread(t::m, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// �����ڶ����߳�
		Thread t2 = new Thread(t::m, "t2");

		t.o = new Object(); // ���������ı䣬����t2�̵߳���ִ�У����ע�͵���仰���߳�2����Զ�ò���ִ�л���

		t2.start();

	}

}
