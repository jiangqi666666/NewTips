package com.jiangqi.newtips.exception.redis;

/**
 * redis设置超时异常类
 * @author jiangqi
 *
 */
@SuppressWarnings("serial")
public class RedisExpireException extends Exception {
	public RedisExpireException(String key){
		super("设置超时时间失败："+key);
	}
}
