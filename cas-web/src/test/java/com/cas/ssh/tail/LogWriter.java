package com.cas.ssh.tail;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter implements Runnable {
	private File logFile = null;
	private long lastTimeFileSize = 0; // 上次文件大小
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public LogWriter(File logFile) {
		this.logFile = logFile;
		lastTimeFileSize = logFile.length();
	}

	/**
	 * 实时输出日志信息
	 */
	public void run() {
		while (true) {
			try {
				long len = logFile.length();
				if (len < lastTimeFileSize) {
					System.out.println("Log file was reset. Restarting logging from start of file.");
					lastTimeFileSize = len;
				} else if(len > lastTimeFileSize) {
					RandomAccessFile randomFile = new RandomAccessFile(logFile, "r");
					randomFile.seek(lastTimeFileSize);
					String tmp = null;
					while ((tmp = randomFile.readLine()) != null) {
						System.out.println(dateFormat.format(new Date()) + "\t"
								+ tmp);
					}
					lastTimeFileSize = randomFile.length();
					randomFile.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
