package com.jiangqi.newtips.util.log4j;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 按线程号将log4日志保存到文件
 * @author jiangqi
 *
 */
public class MyFileAppender extends FileAppender {

	public synchronized void doAppend(LoggingEvent event) {
		if (closed) {
			LogLog.error("Attempted to append to closed appender named [" + name + "].");
			return;
		}

		if (!isAsSevereAsThreshold(event.getLevel())) {
			return;
		}

		Filter f = this.headFilter;

		long threadId = Thread.currentThread().getId();

		FILTER_LOOP: 
			while (f != null) {
			if (f instanceof StringCatchFilter) {
				StringCatchFilter ff = (StringCatchFilter) f;
				ff.setStringToMatch(String.valueOf(threadId)); // 动态设置参数拦截指定的clientNo下的登陆信息
			}
			switch (f.decide(event)) {
			case Filter.DENY:
				return;
			case Filter.ACCEPT:
				break FILTER_LOOP;
			case Filter.NEUTRAL:
				f = f.getNext();
			}
		}
		
		try {
			this.setFile("./logs/a-" + threadId + ".log", fileAppend, bufferedIO, bufferSize); // 动态设置拦截日志的输出路径
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.append(event); // 追加日志,包含登出error和登陆info
	}
}
