/**
 * synchronized�ؼ���
 * ��ĳ���������
 * @author mashibing
 */

package com.mashibing.juc.c_002;

public class T {

	private int count = 10;

	public void m() {
		synchronized (this) { // 锁定当前对象（反正都要锁定一个对象）
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}

}
