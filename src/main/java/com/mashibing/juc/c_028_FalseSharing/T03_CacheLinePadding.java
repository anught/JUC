package com.mashibing.juc.c_028_FalseSharing;

//  T03 T04 执行做执行时间对比；测试前提volatile 修饰arr。
//  T03 中 arr[0] arr[1] 两个数据因为在同一个缓存行（有可能不在，虽然不严谨，但是可用）， 当两个线程分别对 arr[0]
//  arr[1]进行写入时，会把缓存刷入内存（因为加了volatile），
//  当其中一个被修改，另一个线程所在的cpu访问总线发现本缓存中对应数据失效，会重新从内存中读取（要经历多层l3 l2 l1） 所以徒增功耗

//  T04 则不然，因为要修改的两个位置的值在不同的缓存行（假设cpu缓存行长度为64字节）。并不会触发重读，性能更优

public class T03_CacheLinePadding {
	public static volatile long[] arr = new long[2];

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(() -> {
			for (long i = 0; i < 10000_0000L; i++) {
				arr[0] = i;
			}
		});
		Thread t2 = new Thread(() -> {
			for (long i = 0; i < 10000_0000L; i++) {
				arr[1] = i;
			}
		});
		final long start = System.nanoTime();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println((System.nanoTime() - start) / 100_0000);
	}
}
