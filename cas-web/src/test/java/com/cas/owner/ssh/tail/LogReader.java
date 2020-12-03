package com.cas.owner.ssh.tail;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogReader implements Runnable {
	private File logFile = null;
	private long lastTimeFileSize = 0; // 上次文件大小
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public LogReader(File logFile) {
		this.logFile = logFile;
		lastTimeFileSize = logFile.length();
	}

	/**
	 * 实时输出日志信息
	 */
	public void run() {
		while (true) {
			try {
				RandomAccessFile randomFile = new RandomAccessFile(logFile, "r");
				randomFile.seek(lastTimeFileSize);
				String tmp = null;
				while ((tmp = randomFile.readLine()) != null) {
					System.out.println(dateFormat.format(new Date()) + "\t"
							+ tmp);
				}
				lastTimeFileSize = randomFile.length();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		File logFile = new File("/Users/xianglong/Desktop/test.log");
		Thread rthread = new Thread(new LogReader(logFile));
		rthread.start();
	}

}
