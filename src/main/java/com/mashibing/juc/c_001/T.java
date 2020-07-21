/**
 * synchronized�ؼ���
 * ��ĳ���������
 * @author mashibing
 */

package com.mashibing.juc.c_001;

public class T {

	private int count = 10;
	private Object o = new Object();

	public void m() {
		// 任何线程想要访问下面这段代码，必须先拿到o的锁 ...此时锁定的是o，谁拿到这把锁，谁有权执行代码
		synchronized (o) {
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}

}
